<%@ page import="grails_qcm.Proposition" %>



<div class="fieldcontain ${hasErrors(bean: propositionInstance, field: 'answer', 'error')} required">
	<label for="answer">
		<g:message code="proposition.answer.label" default="Answer" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="answer" cols="40" rows="5" maxlength="2000" required="" value="${propositionInstance?.answer}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: propositionInstance, field: 'correct', 'error')} ">
	<label for="correct">
		<g:message code="proposition.correct.label" default="Correct" />
		
	</label>
	<g:checkBox name="correct" value="${propositionInstance?.correct}" />
</div>

<div class="fieldcontain ${hasErrors(bean: propositionInstance, field: 'question', 'error')} required">
	<label for="question">
		<g:message code="proposition.question.label" default="Question" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="question" name="question.id" from="${grails_qcm.Question.list()}" optionKey="id" required="" value="${propositionInstance?.question?.id}" class="many-to-one"/>
</div>

