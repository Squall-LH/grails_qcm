<%@ page import="grails_qcm.Question"%>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'question.label', default: 'Question')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<div class="marged">
<p>QCM Terminé</p>

<g:form>
		<g:submitButton name="send" value="Envoyer le QCM" />
		<g:submitButton name="abandon" value="Abandonner" />
</g:form>
</div>
</body>
</html>
