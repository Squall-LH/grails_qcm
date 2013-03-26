package grails_qcm

class ShiroUser {
    String username
    String passwordHash
    
	static belongsTo = [user:User]
	
    static hasMany = [ roles: ShiroRole, permissions: String ]

    static constraints = {
        username(nullable: false, blank: false, unique: true)
    }
	
	static mapping = {
		roles lazy: false
		permissions lazy: false
	}
}
