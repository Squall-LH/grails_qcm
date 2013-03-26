package grails_qcm

class Proposition implements Serializable {
	
	static Boolean linkMe = false
	
	String answer;
	Boolean correct;
	Question question;
	
	static belongsTo = [question: Question]
	
    static constraints = {
		answer blank: false
		answer shared: "text"
    }
}
