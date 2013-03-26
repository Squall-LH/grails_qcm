package grails_qcm

class ErrorController {

    def index() { }
	
	def notFound() {
		render("L'URL n'existe pas.")
	}
}
