package grails_qcm

class Quizz {
	
	static Boolean linkMe = true
	
	String title;
	String description;
	Boolean published;
	Integer time_limit;
	Date publish_up;
	Date publish_down;
	Boolean show_results;
	Set setsOfQuestions;
	
	static hasMany = [setsOfQuestions: SetOfQuestions]
	
    static constraints = {
		title blank: false, unique: true
		description shared: "text"
		publish_up shared: "dateVal"
    }
}
