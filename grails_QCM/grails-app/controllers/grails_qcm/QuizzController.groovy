package grails_qcm

import javax.persistence.OneToMany;

class QuizzController {

	def scaffold = true

	def fillDDB = {
		QuizzService qServ = new QuizzService();
		qServ.fillDDB();
		render("success")
	}
}
