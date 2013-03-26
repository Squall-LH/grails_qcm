package grails_qcm

class IndexController {

    def index() {
		log.info("!! Nb de Quizz Dispo: " + Quizz.count())
		[quizzInstanceList: Quizz.list(max:20), quizzInstanceTotal: Quizz.count()]
	}
}
