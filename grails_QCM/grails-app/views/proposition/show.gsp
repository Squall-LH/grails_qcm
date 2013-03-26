
<%@ page import="grails_qcm.Proposition" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'proposition.label', default: 'Proposition')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-proposition" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-proposition" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list proposition">
			
				<g:if test="${propositionInstance?.answer}">
				<li class="fieldcontain">
					<span id="answer-label" class="property-label"><g:message code="proposition.answer.label" default="Answer" /></span>
					
						<span class="property-value" aria-labelledby="answer-label"><g:fieldValue bean="${propositionInstance}" field="answer"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${propositionInstance?.correct}">
				<li class="fieldcontain">
					<span id="correct-label" class="property-label"><g:message code="proposition.correct.label" default="Correct" /></span>
					
						<span class="property-value" aria-labelledby="correct-label"><g:formatBoolean boolean="${propositionInstance?.correct}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${propositionInstance?.question}">
				<li class="fieldcontain">
					<span id="question-label" class="property-label"><g:message code="proposition.question.label" default="Question" /></span>
					
						<span class="property-value" aria-labelledby="question-label"><g:link controller="question" action="show" id="${propositionInstance?.question?.id}">${propositionInstance?.question?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${propositionInstance?.id}" />
					<g:link class="edit" action="edit" id="${propositionInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
