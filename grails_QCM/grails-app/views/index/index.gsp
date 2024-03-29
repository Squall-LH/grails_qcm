<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>Welcome to Grails</title>
<style type="text/css" media="screen">
#status {
	background-color: #eee;
	border: .2em solid #fff;
	margin: 2em 2em 1em;
	padding: 1em;
	width: 12em;
	float: left;
	-moz-box-shadow: 0px 0px 1.25em #ccc;
	-webkit-box-shadow: 0px 0px 1.25em #ccc;
	box-shadow: 0px 0px 1.25em #ccc;
	-moz-border-radius: 0.6em;
	-webkit-border-radius: 0.6em;
	border-radius: 0.6em;
}

.ie6 #status {
	display: inline;
	/* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
}

#status ul {
	font-size: 0.9em;
	list-style-type: none;
	margin-bottom: 0.6em;
	padding: 0;
}

#status li {
	line-height: 1.3;
}

#status h1 {
	text-transform: uppercase;
	font-size: 1.1em;
	margin: 0 0 0.3em;
}

#page-body {
	margin: 2em 1em 1.25em 18em;
}

h2 {
	margin-top: 1em;
	margin-bottom: 0.3em;
	font-size: 1em;
}

p {
	line-height: 1.5;
	margin: 0.25em 0;
}

#controller-list ul {
	list-style-position: inside;
}

#controller-list li {
	line-height: 1.3;
	list-style-position: inside;
	margin: 0.25em 0;
}

.inside_list ul {
	list-style-position: inside;
}

.inside_list li {
	line-height: 1.3;
	list-style-position: inside;
	margin: 0.25em 0;
}

@media screen and (max-width: 480px) {
	#status {
		display: none;
	}
	#page-body {
		margin: 0 1em 1em;
	}
	#page-body h1 {
		margin-top: 0;
	}
}
</style>
</head>
<body>
	<a href="#page-body" class="skip"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div id="status" role="complementary">
		<h1>Application Status</h1>
		<ul>
			<li>App version: <g:meta name="app.version" /></li>
			<li>Grails version: <g:meta name="app.grails.version" /></li>
			<li>Groovy version: ${GroovySystem.getVersion()}</li>
			<li>JVM version: ${System.getProperty('java.version')}</li>
			<li>Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</li>
			<li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
			<li>Domains: ${grailsApplication.domainClasses.size()}</li>
			<li>Services: ${grailsApplication.serviceClasses.size()}</li>
			<li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
		</ul>
		<h1>Installed Plugins</h1>
		<ul>
			<g:each var="plugin"
				in="${applicationContext.getBean('pluginManager').allPlugins}">
				<li>
					${plugin.name} - ${plugin.version}
				</li>
			</g:each>
		</ul>
	</div>
	<div id="page-body" role="main">
		<h1>Interface d'admin de Grails_QCM</h1>

		<div id="controller-list" role="navigation">
			<g:link action="signOut" controller="Auth">Log out</g:link>
		
			<h2>Gestions des quizz:</h2>
			
			<ul>
				<g:each var="c"
					in="${grailsApplication.controllerClasses.sort { it.fullName }}">
					<g:if
						test="${(!c.name.equals('Dbdoc') && (!c.name.equals('RunQuizz')) && (!c.name.equals('Error')) && (!c.name.equals('Auth')) && (!c.name.equals('Index')))}">
						<li class="controller"><g:link
								controller="${c.logicalPropertyName}">
								${c.name}
							</g:link></li>
					</g:if>
				</g:each>
			</ul>
			
		</div>


		<h2>Tester un Quizz</h2>
		<div id="list_quizz" class="inside_list">
		<ul>
			<g:each var="quizzInstance" in="${quizzInstanceList}">
				<li><g:link controller="RunQuizz" action="index"
						id="${quizzInstance.id}">
						${fieldValue(bean: quizzInstance, field: "title")}
					</g:link></li>
			</g:each>
		</ul>
		</div>
		
		<h2>Tester un cas pratique</h2>
		<g:link controller="PracticalCase" action="index">Cas Pratique</g:link>
	</div>
</body>
</html>
