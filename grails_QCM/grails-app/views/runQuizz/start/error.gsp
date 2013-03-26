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
<p>Une erreur est survenue, la session est donc abandonnée</p>
<g:form>
	<g:submitButton name="end" value="Fin" />
</g:form>
</div>
</body>
</html>