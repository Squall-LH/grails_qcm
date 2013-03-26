package grails_qcm



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Category)
class CategoryTests {

    void testSomething() {
		def c = new Category(title: "Test cat",description: "Test cat desc",  );
		assert null != c.save(flush: true)

		def c2 = new Category(title: "Test cat",description: "Test cat2 desc",  );
		
		Category.withTransaction { status ->
			if(c2.title == "${c.title}") {
				status.setRollbackOnly()
			} else {
				c2.save()
			}
		}
		assert Category.count() == 1 	
    }
}
