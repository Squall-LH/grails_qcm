package grails_qcm

import org.apache.shiro.crypto.hash.Sha256Hash

class QuizzService {

	def fillDDB() {
		// Ajoute un admin ayant accès à toutes les actions de tous les controleurs pour Shiro
		def shiro_admin = new ShiroUser(username: "admin", passwordHash: new Sha256Hash("admin").toHex())
		shiro_admin.addToPermissions("*:*")

		
		
		// Ajoute un user de base pouvant uniquement lancer un Quizz
		def shiro_user = new ShiroUser(username: "user", passwordHash: new Sha256Hash("user").toHex())
		shiro_user.addToPermissions("RunQuizz:*")
		shiro_user.addToPermissions("Index:*")
		shiro_user.save()

		def admin = new User(email: "me@adme.me", firstName: "admin", name: "admin", shiroUser: shiro_admin);
		admin.save();

		def user = new User(email: "me@me.me", firstName: "user", name: "user",  shiroUser: shiro_user);
		user.save();

		def user2 = User.findByName("user")
		
		// Ajoute un Quizz
		def cat = new Category(description: "", title: "PHP")
		cat.save();

		def question = new Question(category: cat, statement: "PHP Niveau 1", description: "Quel est le type de retour de la fonction strcmp() ?");
		if(!question.save(flush:true)) {
			log.info("question saving failed");
			question.errors?.allErrors?.each{ println it };
		}

		def question2 = new Question(category: cat, statement: "PHP niveau 1", description: "Quel valeur prend l'attribut type de la balise HTML input pour obtenir un bouton ?");
		question2.save(flush:true);


		def prop1 = new Proposition(correct: true, question: question, answer: "int");
		def prop2 = new Proposition(correct: false, question: question, answer: "boolean");

		prop1.save();
		prop2.save();

		def prop3 = new Proposition(correct: true, question: question2, answer: "submit");
		def prop4 = new Proposition(correct: false, question: question2, answer: "send");

		prop3.save();
		prop4.save();

		Set<Question> questions = new HashSet<Question>();
		questions.add(question);
		questions.add(question2);

		def setOfQuestions = new SetOfQuestions(title: "set1", type: "myType", questions: questions);
		setOfQuestions.save();

		def Quizz quizz = new Quizz(setsOfQuestions: setOfQuestions, description: "Quizz PHP débutant", publish_up: new Date(), publish_down: new Date().plus(1), published: true, show_results: false, time_limit: 60, title: "Quizz PHP Niv 1");
		if(!quizz.save(flush:true)) {
			log.info("quizz saving failed");
			quizz.errors?.allErrors?.each{ println it };
		}
	}
}
