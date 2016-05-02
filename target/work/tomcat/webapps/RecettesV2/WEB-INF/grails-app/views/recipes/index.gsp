<%@ page import="com.pld.recipes.core.Recipes" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'recipes.label', default: 'Recipes')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    <rateable:resources/>
    <style type="text/css">
        .fixedWidthColumn {
        width: 20%;
        }
    </style>
    <r:require module="filterpane" />
</head>
<body>
<sec:ifNotGranted roles="ROLE_ADMIN,ROLE_MASTER_USER,ROLE_TRUSTED_USER,ROLE_GUEST_USER">
    <div class='errors'><g:message code="springSecurity.denied.message" /></div>
</sec:ifNotGranted>
<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MASTER_USER,ROLE_TRUSTED_USER,ROLE_GUEST_USER">
    <a href="#list-recipes" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <div id="list-recipes" class="content scaffold-list" role="main">
        <h1><g:message code="default.list.label" args="[entityName]" /></h1>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <table>
            <thead>
                <tr>
                    <td colspan="6" style="text-align: right">
                        <g:form url='[controller: "recipes", action: "search"]' id="searchableForm" name="searchableForm" method="get">
                            <g:textField name="q" value="${params.q}" size="50"/> <input type="submit" value="Search" />
                        </g:form>
            <filterpane:filterButton text="Filter" /> | <a class="clearFilter" href="${createLink(uri: '/')}"><g:message code="default.clearFilter.label" default="Clear filter"/></a>
            <filterpane:filterPane domain="com.pld.recipes.core.Recipes" excludeProperties="createdBy,createdOn,lastUpdatedBy,lastUpdatedOn"/>

            </td>
            </tr>
            <tr>
                <g:if test="${params.action == 'search'}">
                    <th>${message(code: 'recipes.name.label', default: 'Name')}</th>
                    <th>${message(code: 'recipes.category.label', default: 'Category')}</th>
                    <th>${message(code: 'recipes.source.label', default: 'Source')}</th>
                    <th>${message(code: 'recipes.notes.label', default: 'Notes')}</th>
                    <th class="fixedWidthColumn">${message(code: 'recipes.tags.label', default: 'Tags')}</th>
                    <th>${message(code: 'recipes.averageRating.label', default: 'Avg. Rating')}</th>
                    </g:if>
                    <g:else>
                        <g:sortableColumn property="name" title="${message(code: 'recipes.name.label', default: 'Name')}"  params="${filterParams}"/>
                        <g:sortableColumn property="category" title="${message(code: 'recipes.category.label', default: 'Category')}"  params="${filterParams}"/>
                        <g:sortableColumn property="source" title="${message(code: 'recipes.source.label', default: 'Source')}"  params="${filterParams}"/>
                        <g:sortableColumn property="notes" title="${message(code: 'recipes.notes.label', default: 'Notes')}"  params="${filterParams}"/>
                        <th class="fixedWidthColumn">${message(code: 'recipes.tags.label', default: 'Tags')}</th>
                        <g:sortableColumn class="fixedWidthColumn" property="averageRating" title="${message(code: 'recipes.averageRating.label', default: 'Avg. Rating')}"  params="${filterParams ? filterParams : params}"/>
                    </g:else>
            </tr>
            </thead>
            <tbody>
                <g:each in="${recipesInstanceList}" status="i" var="recipesInstance">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                        <td><g:link action="show" id="${recipesInstance.id}">${fieldValue(bean: recipesInstance, field: "name")}</g:link></td>

                        <td>${fieldValue(bean: recipesInstance, field: "category")}</td>
                        <td>${recipesInstance.source?.label}</td>
                        <td>${fieldValue(bean: recipesInstance, field: "notes")}</td>
                        <td>${recipesInstance?.tags?.sort()?.join(",")}</td>

                        <td>
                <sec:ifAnyGranted roles="ROLE_MASTER_USER">
                    <rateable:ratings bean='${recipesInstance}' id="${recipesInstance.id}" active='true'/>
                </sec:ifAnyGranted>
                <sec:ifNotGranted roles="ROLE_MASTER_USER">
                    <rateable:ratings bean='${recipesInstance}' active='false'/>
                </sec:ifNotGranted>
                </td>
                </tr>
            </g:each>
            </tbody>
        </table>
        <div class="pagination">
            <g:paginate total="${recipesInstanceCount ?: 0}" params="${filterParams ? filterParams : params}"/>
        </div>
    </div>
</sec:ifAnyGranted>
</body>
</html>
