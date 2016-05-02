<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<%@ page import="com.pld.recipes.core.Recipes" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title><g:layoutTitle default="Grails"/></title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
        <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
        <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
        <style type='text/css'>
            .ratingDisplay {
            width: initial;
            }
        </style>
        <g:javascript library="jquery"/>
        <g:javascript library="jquery-ui"/>
        <g:javascript library="application"/>		
        <rateable:resources/>
        <g:layoutHead/>
    <r:layoutResources />
</head>
<body>
    <div id="grailsLogo" role="banner" align="right">
        <sec:ifLoggedIn>
            Welcome <sec:username/> (<g:link controller='logout'>Logout</g:link>)
            </sec:ifLoggedIn>
            <sec:ifNotLoggedIn>
            <g:link controller='login' action='auth'>Login</g:link>
            </sec:ifNotLoggedIn>
        </div>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MASTER_USER,ROLE_TRUSTED_USER">
                    <li><g:link class="create" controller="recipes" action="create"><g:message code="default.new.label" args="['Recipes']" /></g:link></li>
                </sec:ifAnyGranted>
                <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MASTER_USER">
                    <li>|</li>
                    <li><g:link class="list" controller="user" action="index"><g:message code="default.list.label" args="['Users']" /></g:link></li>
                    <li><g:link class="create" controller="user" action="create"><g:message code="default.new.label" args="['User']" /></g:link></li>
                </sec:ifAnyGranted>
                <sec:ifAnyGranted roles="ROLE_ADMIN">
                    <li>|</li>
                    <li><g:link class="list" controller="quartz" action="list">Quartz</g:link></li>
                    <li><g:link class="list" controller="dbconsole">DB</g:link></li>
                </sec:ifAnyGranted>
            </ul>
        </div>
    <g:layoutBody/>
    <div class="footer" role="contentinfo">
        <tc:tagCloud bean="${Recipes}" paramName='tag' controller="recipes" action="index" params="params" resetOffset="true" sort="true"/>
    </div>
    <div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
<r:layoutResources />
</body>
</html>
