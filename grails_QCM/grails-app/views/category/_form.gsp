<%@ page import="grails_qcm.Category" %>



<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="category.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${categoryInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: categoryInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="category.description.label" default="Description" />
		
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="2000" value="${categoryInstance?.description}"/>
</div>

