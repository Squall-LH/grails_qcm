package grails_qcm



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(TestController)
@Mock([Category])
class TestControllerTests {

    void testSomething() {
       def c = new TestController();
	   c.register();
    }
}
