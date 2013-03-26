<%@ page import="grails_qcm.SetOfQuestions" %>



<div class="fieldcontain ${hasErrors(bean: setOfQuestionsInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="setOfQuestions.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${setOfQuestionsInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: setOfQuestionsInstance, field: 'questions', 'error')} ">
	<label for="questions">
		<g:message code="setOfQuestions.questions.label" default="Questions" />
		
	</label>
	<g:select name="questions" from="${grails_qcm.Question.list()}" multiple="multiple" optionKey="id" size="5" value="${setOfQuestionsInstance?.questions*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: setOfQuestionsInstance, field: 'type', 'error')} ">
	<label for="type">
		<g:message code="setOfQuestions.type.label" default="Type" />
		
	</label>
	<g:textField name="type" value="${setOfQuestionsInstance?.type}"/>
</div>

