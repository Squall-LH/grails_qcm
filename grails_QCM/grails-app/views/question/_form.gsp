<%@ page import="grails_qcm.Question" %>



<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'statement', 'error')} required">
	<label for="statement">
		<g:message code="question.statement.label" default="Statement" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="statement" required="" value="${questionInstance?.statement}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="question.description.label" default="Description" />
		
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="2000" value="${questionInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'category', 'error')} required">
	<label for="category">
		<g:message code="question.category.label" default="Category" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="category" name="category.id" from="${grails_qcm.Category.list()}" optionKey="id" required="" value="${questionInstance?.category?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: questionInstance, field: 'propositions', 'error')} ">
	<label for="propositions">
		<g:message code="question.propositions.label" default="Propositions" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${questionInstance?.propositions?}" var="p">
    <li><g:link controller="proposition" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="proposition" action="create" params="['question.id': questionInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'proposition.label', default: 'Proposition')])}</g:link>
</li>
</ul>

</div>

