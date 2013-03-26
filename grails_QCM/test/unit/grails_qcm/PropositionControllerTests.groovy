package grails_qcm



import org.junit.*
import grails.test.mixin.*

@TestFor(PropositionController)
@Mock(Proposition)
class PropositionControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/proposition/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.propositionInstanceList.size() == 0
        assert model.propositionInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.propositionInstance != null
    }

    void testSave() {
        controller.save()

        assert model.propositionInstance != null
        assert view == '/proposition/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/proposition/show/1'
        assert controller.flash.message != null
        assert Proposition.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/proposition/list'

        populateValidParams(params)
        def proposition = new Proposition(params)

        assert proposition.save() != null

        params.id = proposition.id

        def model = controller.show()

        assert model.propositionInstance == proposition
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/proposition/list'

        populateValidParams(params)
        def proposition = new Proposition(params)

        assert proposition.save() != null

        params.id = proposition.id

        def model = controller.edit()

        assert model.propositionInstance == proposition
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/proposition/list'

        response.reset()

        populateValidParams(params)
        def proposition = new Proposition(params)

        assert proposition.save() != null

        // test invalid parameters in update
        params.id = proposition.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/proposition/edit"
        assert model.propositionInstance != null

        proposition.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/proposition/show/$proposition.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        proposition.clearErrors()

        populateValidParams(params)
        params.id = proposition.id
        params.version = -1
        controller.update()

        assert view == "/proposition/edit"
        assert model.propositionInstance != null
        assert model.propositionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/proposition/list'

        response.reset()

        populateValidParams(params)
        def proposition = new Proposition(params)

        assert proposition.save() != null
        assert Proposition.count() == 1

        params.id = proposition.id

        controller.delete()

        assert Proposition.count() == 0
        assert Proposition.get(proposition.id) == null
        assert response.redirectedUrl == '/proposition/list'
    }
}
