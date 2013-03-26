package grails_qcm

import org.springframework.dao.DataIntegrityViolationException

class PropositionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [propositionInstanceList: Proposition.list(params), propositionInstanceTotal: Proposition.count()]
    }

    def create() {
        [propositionInstance: new Proposition(params)]
    }

    def save() {
        def propositionInstance = new Proposition(params)
        if (!propositionInstance.save(flush: true)) {
            render(view: "create", model: [propositionInstance: propositionInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'proposition.label', default: 'Proposition'), propositionInstance.id])
        redirect(action: "show", id: propositionInstance.id)
    }

    def show(Long id) {
        def propositionInstance = Proposition.get(id)
        if (!propositionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proposition.label', default: 'Proposition'), id])
            redirect(action: "list")
            return
        }

        [propositionInstance: propositionInstance]
    }

    def edit(Long id) {
        def propositionInstance = Proposition.get(id)
        if (!propositionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proposition.label', default: 'Proposition'), id])
            redirect(action: "list")
            return
        }

        [propositionInstance: propositionInstance]
    }

    def update(Long id, Long version) {
        def propositionInstance = Proposition.get(id)
        if (!propositionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proposition.label', default: 'Proposition'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (propositionInstance.version > version) {
                propositionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'proposition.label', default: 'Proposition')] as Object[],
                          "Another user has updated this Proposition while you were editing")
                render(view: "edit", model: [propositionInstance: propositionInstance])
                return
            }
        }

        propositionInstance.properties = params

        if (!propositionInstance.save(flush: true)) {
            render(view: "edit", model: [propositionInstance: propositionInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'proposition.label', default: 'Proposition'), propositionInstance.id])
        redirect(action: "show", id: propositionInstance.id)
    }

    def delete(Long id) {
        def propositionInstance = Proposition.get(id)
        if (!propositionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proposition.label', default: 'Proposition'), id])
            redirect(action: "list")
            return
        }

        try {
            propositionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'proposition.label', default: 'Proposition'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'proposition.label', default: 'Proposition'), id])
            redirect(action: "show", id: id)
        }
    }
}
