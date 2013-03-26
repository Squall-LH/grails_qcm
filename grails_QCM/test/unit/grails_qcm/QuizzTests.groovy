package grails_qcm



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Quizz)
@Mock(Quizz)
class QuizzTests {

	// Test le custom validator dateVal définit dans Config.
    void testDateVall() {
		def date1 = new Date();
		def date2 = new Date();
		date2 = date2.next();
		
        def quizz = new Quizz(description: "", title: "quizz_1", published: false, show_results: false,  time_limit: 10, publish_up: date1, publish_down: date2);
		assert null != quizz.save(flush:true);
		
		quizz = new Quizz(description: "", title: "quizz_2", published: false, show_results: false,  time_limit: 10, publish_up: date2, publish_down: date1);
		assert null == quizz.save(flush:true);
    }
}
