package grails_qcm

import java.util.Set;

class Session {

	static Boolean linkMe = true
	
	User user;
	Quizz quizz;
	Date started_on;
	Date finished_on;
	Set proposition;
	
	static hasMany = [proposition: Proposition]
	
    static constraints = {
		started_on validator: {Date val1, obj -> val1.before(obj.finished_on)}
		proposition nullable: false, blank: false
    }
}
