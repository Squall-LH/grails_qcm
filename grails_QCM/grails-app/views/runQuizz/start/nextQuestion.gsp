
<%@ page import="grails_qcm.Question"%>
<!DOCTYPE html>
<html>
<head>
<g:javascript library="jquery" />
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'question.label', default: 'Question')}" />
<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
	<g:if test="${warn}">
		<div class="errors">
			${warn}
		</div>
	</g:if>
	
	<p id="timer">
	</p>
	
	<div class="marged">
		<g:if test="${lastQuestion}">
			<g:form>
				<g:hiddenField name="questionId" value="${questionId}" />
				<g:hiddenField id="timer_param" name="time_left" value="${time_left}" />
				<g:submitButton name="send" value="Envoyer le QCM" />
				<g:submitButton name="prev" value="Question précédente" />
				<g:submitButton name="abandon" value="Abandonner" />
			</g:form>
		</g:if>
		<g:else>

			<p>
				Question:
				${statement}
			</p>

			<p>
				Contenu:
				${description}
			</p>
			<g:form class="marged">
				<table border="1">
					<g:each var="p" in="${propositionList}">
						<tr>
							<td><g:checkBox name="answer" value="${p.id}"
									checked="${answerList.contains(p.id.toString())}" /> ${p.answer}</td>
						</tr>
					</g:each>
				</table>
				<g:hiddenField name="questionId" value="${questionId}" />
				<g:hiddenField id="timer_param" name="time_left" value="${time_left}" />
				<g:submitButton name="submit" value="Question suivante" />
				<g:if test="${questionId > 0}">
				<g:submitButton name="prev" value="Question précédente" />
				</g:if>
				<g:submitButton name="abandon" value="Abandonner" />
			</g:form>

			<div id="currentQuestion">
				<p>
					Question
					${questionId+1}
					/
					${total}
				</p>
			</div>
		</g:else>
	</div>


<script>
	var time_left = ${time_left};
	var timer = document.getElementById('timer');
	var timerId = null;
	
	function dec() {
		$('#timer').html('Temps restant: ' + time_left);
		$('#timer_param').attr('value', time_left);
		if(time_left > 0) {
			time_left--;
		}
		else {
			clearInterval(timerId);
			window.location.replace("<g:createLink url="[action:'time_out',controller:'runQuizz']" />");
		}
	}

	$(document).ready(function() {
			dec();
			timerId = setInterval("dec()",1000);
	      }
	);
</script>
</body>
</html>
