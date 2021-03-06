<%@ page import="com.pld.recipes.security.Role" %>
<%@ page import="com.pld.recipes.security.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'fullName', 'error')} required">
    <label for="fullName">
        <g:message code="user.fullName.label" default="Full Name" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="fullName" required="" value="${userInstance?.fullName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
    <label for="username">
        <g:message code="user.username.label" default="Username" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="username" required="" value="${userInstance?.username}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
    <label for="password">
        <g:message code="user.password.label" default="Password" />
        <span class="required-indicator">*</span>
    </label>
    <g:passwordField name="password" required="" value="${userInstance?.password}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'accessLevel', 'error')} required">
    <label for="password">
        <g:message code="user.accessLevel.label" default="Access Level" />
        <span class="required-indicator">*</span>
    </label>
    <g:if test="${defaultAccessLevel.authority == 'ROLE_ADMIN'}">
        ${defaultAccessLevel.authority}
        <g:hiddenField name="accessLevel" value="${defaultAccessLevel.authority}"/>
    </g:if>
    <g:else>
        <g:select name="accessLevel"
        from="${availableRoles}"
        value="${defaultAccessLevel.authority}" optionKey="authority" optionValue="authority" required="" />
    </g:else>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'accountExpired', 'error')} ">
    <label for="accountExpired">
        <g:message code="user.accountExpired.label" default="Account Expired" />

    </label>
    <g:checkBox name="accountExpired" value="${userInstance?.accountExpired}" />

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'accountLocked', 'error')} ">
    <label for="accountLocked">
        <g:message code="user.accountLocked.label" default="Account Locked" />

    </label>
    <g:checkBox name="accountLocked" value="${userInstance?.accountLocked}" />

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'enabled', 'error')} ">
    <label for="enabled">
        <g:message code="user.enabled.label" default="Enabled" />

    </label>
    <g:checkBox name="enabled" value="${userInstance?.enabled}" />

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'passwordExpired', 'error')} ">
    <label for="passwordExpired">
        <g:message code="user.passwordExpired.label" default="Password Expired" />

    </label>
    <g:checkBox name="passwordExpired" value="${userInstance?.passwordExpired}" />

</div>

