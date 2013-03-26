package grails_qcm

import java.io.Serializable;

class Category implements Serializable {

	// On affiche le menu CRUD de cet entitée sur la page d'index ou pas
	static Boolean linkMe = true
	
	String title;
	String description;
	
    static constraints = {
		title blank: false, unique: true
		description shared: "text"
    }
}
