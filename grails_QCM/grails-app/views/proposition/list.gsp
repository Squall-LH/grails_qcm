
<%@ page import="grails_qcm.Proposition" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'proposition.label', default: 'Proposition')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-proposition" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-proposition" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="answer" title="${message(code: 'proposition.answer.label', default: 'Answer')}" />
					
						<g:sortableColumn property="correct" title="${message(code: 'proposition.correct.label', default: 'Correct')}" />
					
						<th><g:message code="proposition.question.label" default="Question" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${propositionInstanceList}" status="i" var="propositionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${propositionInstance.id}">${fieldValue(bean: propositionInstance, field: "answer")}</g:link></td>
					
						<td><g:formatBoolean boolean="${propositionInstance.correct}" /></td>
					
						<td>${fieldValue(bean: propositionInstance, field: "question")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${propositionInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
