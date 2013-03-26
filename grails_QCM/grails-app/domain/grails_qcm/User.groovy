package grails_qcm

class User {

	static Boolean linkMe = true
	
	String name;
	String firstName;
	String email;
	ShiroUser shiroUser;
	
	static hasOne = [shiroUser:ShiroUser]
	
    static constraints = {
		name blank: false
		firstName blank: false
		name(unique: ['firstName'])
		email email: true
		email unique: true
		shiroUser unique: true
    }
	
	@Override
	public String toString() {
		return "Name: " + name + " | FirstName :" + firstName + " | email: " + email;
	}
}
