package grails_qcm

class Question implements Serializable {

	static Boolean linkMe = true
	
	String statement;
	String description;
	Category category;
	Set propositions;
	
	static hasMany = [propositions: Proposition]
	
    static constraints = {
		statement blank: false
		description shared: "text"
		propositions nullable:true
    }
	
	static mapping = {
		propositions lazy: false
	}
}
