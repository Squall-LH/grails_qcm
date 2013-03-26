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
<p>
	Score final:
	${score}/${total}
</p>
</div>
<g:form class="marged">
	<g:submitButton name="end" value="Fin" />
</g:form>

</body>
</html>