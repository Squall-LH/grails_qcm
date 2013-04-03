package grails_qcm

import org.apache.shiro.SecurityUtils;

class RunQuizzController {
	
	def index(Integer id) {
		log.info("Quizz Id to run: " + id)

		def quizz = Quizz.get(id);

		def setsOfquestions = quizz.setsOfQuestions;

		def SetOfQuestions setOfquestions = setsOfquestions.iterator()[0];
		setOfquestions = SetOfQuestions.findById(setOfquestions.id);
		Set questionSet = new HashSet(setOfquestions.questions);
		def questionList = questionSet.toList();

		questionList.each{ log.info("!!!! " + it.statement) }

		session["questionList"] = questionList;
		session["setOfQuestions"] = setOfquestions;
		session["title"] = quizz.title;
		session["desc"] = quizz.description;
		session["time_limit"] = quizz.time_limit;
		session["quizz"] = quizz;

		log.debug("quizz's title :" + quizz.title);

		redirect(action: 'start', param: questionList)
	}
	
	def startFlow={
		def List questionList = session["questionList"];
		def setOfQuestions = session["setOfQuestions"];
		def Integer time_limit = session.time_limit.toInteger();
		def title = session.title;
		def desc = session.desc;
		def Integer time_left = -1;
		def begin_timer_server = null;
		def Quizz quizz = session.quizz;
		
		//setOfQuestions.questions.toList().each{ log.info("************* " + it.statement) }

		questionList.each{ log.info("************* " + it.statement) }

		initQuizz {
			log.info("initQuizz")
			onEntry {
				flow.setOfQuestions = setOfQuestions;
				flow.indiceQuestion = 0;
				flow.answerList = new ArrayList(); // Le <Integer> n'a aucun effet, flow est dynamique
				time_left = -1;
				[title: title, desc: desc]
			}
			onExit {
				begin_timer_server = new Date();
			}
			on("next").to "nextQuestion"
			on("end").to "end"
		}

		// action state qui n'affiche pas de vue et redirige vers un autre state
		submitAnswer {
			action {
				def answer = params.answer;
				log.info("ANSWER: " + answer);
				
				if(answer == null) {
					//afficher message erreur
					flash.warn = "Vous devez cocher une case."
					error()
				} else if(isCollectionOrArray(answer)) {
					flash.warn = "Une seule case doit être cochée."
					error()
				} else {
					if(params.questionId != null) {
						def questionId = params.questionId.toInteger();
						log.debug("questionId: " + questionId);
						flow.indiceQuestion = questionId+1;
					} else {
						throw new Exception("Invalid Param Sent");
					}
					log.info("adding to flow.answerList: " + answer);
					flow.answerList.add(answer);
					success()
				}
				
				if(flow.indiceQuestion >= flow.setOfQuestions.questions.size()) {
					success()
				} else {
					success()
				}

				time_left = params.time_left.toInteger();
				log.debug("time_left: " + time_left);
			}
			on("success").to("nextQuestion");
			on("error").to("nextQuestion");
		}


		nextQuestion {
			log.info("nextQuestion")

			onEntry {
				/*
				 *  On a un timer côté client dont on récupère le temps restant dans time_left.
				 *  On a aussi un timer côté serveur pour remarquer si une valeur étrange est donnée depuis le client.
				 *  Si la différence entre le temps restant côté client et le temps restant côté serveur est supéreur à alpha, on 
				 *  remet en temps restant le temps restant côté serveur.
				 */
				def Integer alpha = 3;
				def Integer time;
				def Integer oldTime = -1;

				if(time_left == -1) { // si premiere question donc jamais passé de temps restant en param depuis le client
					time = time_limit;
				} else {
					oldTime = time_left;
					time = time_left;
				}
				
				def current_date_serv = new Date();
				def serverTimeLeft = time_limit - ((current_date_serv.time - begin_timer_server.time) / 1000);
				
				def clientTimeLeft = time;
				def Integer diffClientServer = clientTimeLeft  - serverTimeLeft;
				
				log.debug("serverTimeLeft: " + serverTimeLeft + " | clientTimeLeft " + clientTimeLeft);
				log.debug("Diff entre timer côté server et timer client: " + diffClientServer);
				
				if(Math.abs(diffClientServer) > alpha) {
					log.warn("diff du timer entre client et serveur supérieur à " + alpha + ", on reprend le temps serveur.");
					time = serverTimeLeft;
				}
				
				if(flow.indiceQuestion >= questionList.size()) {
					[time_left: time, lastQuestion: true, questionId: flow.indiceQuestion]
				} else {
					def Question question = questionList.get(flow.indiceQuestion);
					[time_left: time, total: questionList.size(), questionId: flow.indiceQuestion, statement: question.statement, description: question.description, lastQuestion: false, propositionList: question.propositions.toList(), answerList: flow.answerList]
				}
			}
			onExit { log.info("onExit nextQuestion") }

			on("prev").to "prev"
			on("submit").to "submitAnswer"
			on("send") {
				if(true) { // on vérifie que tout les params sont ok avant de procéder à l'évaluation
					log.info("answer list: " + flow.answerList)
				}
			}.to "success"
			on("abandon").to "faillure"
		}
		
		prev {
			action {
				log.debug("Bouton précédent pressé.");
				if(params.questionId != null) {
					def questionId = params.questionId.toInteger();
					log.debug("questionId: " + questionId);
					flow.indiceQuestion = questionId-1;
				} else {
					throw new Exception("Invalid Param Sent");
				}
				time_left = params.time_left.toInteger();
				log.debug("time_left: " + time_left);
				success()
			}
			on("success").to "nextQuestion";
		}

		success {
			onEntry {
				// On calcule le score obtenu.
				log.info("FINAL_answer list: " + flow.answerList)
				def score = 0;
				questionList.each {
					it.propositions.each {
						if(it.correct) {
							log.info("FINAL_it " + it.id)
							def String ids = it.id.toString();
							log.info("FINAL_id_answer: " + ids);
							if(flow.answerList.contains(ids)) {
								score++;
							}
						}
					}
				}
				log.info("score: " + score);
				
				// On sauvegarde la session
				def ShiroUser shiroUser = ShiroUser.findByUsername(SecurityUtils.subject.principal);
				log.debug(SecurityUtils.subject.principal);
				
				def Set<Proposition> ensProp = new HashSet<Proposition>();
				
				flow.answerList.each {
					ensProp.add(Proposition.get(it.toInteger()));
				}
				
				def Session sess = new Session(quizz: quizz, finished_on: new Date(), started_on: begin_timer_server, user: shiroUser.user, proposition: ensProp);
				
				if(null == sess.save()) {
					log.error("Could not save session.");
					sess.errors?.allErrors?.each{
						log.error(it);
					};
					throw new Exception("Could not save session.");
				}
				
				[score: score, total: questionList.size()]
			}
		}

		end {
			redirect(action: "index", controller: "index")
		}

		faillure()
	}

	
	def time_out() {
		
	}

	boolean isCollectionOrArray(object) {
		[Collection, Object[]].any { it.isAssignableFrom(object.getClass()) }
	}
}
