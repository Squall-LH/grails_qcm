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
<p>QCM Abandonnée, limite de temps dépassée</p>
<g:link controller="index" action="index">Retourner à l'index</g:link>
</div>
</body>
</html>
