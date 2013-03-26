package grails_qcm

import java.io.Serializable;

class SetOfQuestions implements Serializable {

	static Boolean linkMe = true
	
	String title;
	String type;
	Set questions;
	
	SetOfQuestions(SetOfQuestions s) {
		title = s.title;
		type = s.type;
		questions = s.questions;
	}
	
	static hasMany = [questions: Question]
	
    static constraints = {
		title blank: false, unique: true
    }
	
	static mapping = {
		questions lazy: false
	}
	
	def Integer size() {
		return questions.size();
	}
}
