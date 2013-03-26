package grails_qcm

class ShiroRole {
    String name

    static hasMany = [ users: ShiroUser, permissions: String ]
    static belongsTo = ShiroUser

    static constraints = {
        name(nullable: false, blank: false, unique: true)
    }
	
	static mapping = {
		users lazy: false
		permissions lazy: false
	}
}
