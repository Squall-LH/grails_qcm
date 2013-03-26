package grails_qcm

import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
@Mock(User)
class UserTests {

	void testUniqueNameConstraint() {
		def user = new User(name:"AA", firstName:"AA", email: "me@google.fr");
		def user2 = new User(name:"AA", firstName:"AA", email: "be@@free.fr");

		assert null != user.save(flush:true);
		log.info("User ajouté: " + user.toString());

		assert null == user2.save(flush:true);
		log.info("impossible d'ajouter un user avec même couple nom/prénom");
	}
}
