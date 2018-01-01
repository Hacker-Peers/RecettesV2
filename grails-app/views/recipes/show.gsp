<%@ page import="com.pld.recipes.core.Recipes" %>
<%@ page import="com.pld.recipes.security.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'recipes.label', default: 'Recipes')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
        <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MASTER_USER">
	<style>
	.ui-autocomplete-loading {
		background: white url('http://jqueryui.com/resources/demos/autocomplete/images/ui-anim_basic_16x16.gif') right center no-repeat;
	}
	
	.embedImg {
		#transform: rotate(90deg);
		max-width: 100%;
	}
	</style>
        <g:javascript>
            function split( val ) {
              return val.split( /,\s*/ );
            }
            function extractLast( term ) {
              return split( term ).pop();
            }

            $(document).ready(function() {
               $( "#tags" )
                // don't navigate away from the field on tab when selecting an item
                .bind( "keydown", function( event ) {
                  if ( event.keyCode === $.ui.keyCode.TAB &&
                      $( this ).data( "ui-autocomplete" ).menu.active ) {
                    event.preventDefault();
                  }
                })
                .autocomplete({
                  minLength: 0,
                  source: function( request, response ) {
                    $.getJSON( '<g:createLink controller='ajax' action='recipesExistingTags'/>', {
                      term: extractLast( request.term )
                    }, response );
                  },
                  search: function() {
                        // custom minLength
                        var term = extractLast( this.value );
                        if ( term.length < 1 ) {
                                return false;
                        }
                  },
                  focus: function() {
                    // prevent value inserted on focus
                    return false;
                  },
                  select: function( event, ui ) {
                    var terms = split( this.value );
                    // remove the current input
                    terms.pop();
                    // add the selected item
                    terms.push( ui.item.value );
                    // add placeholder to get the comma-and-space at the end
                    terms.push( "" );
                    this.value = terms.join( ", " );
                    return false;
                  }
                });
                
            });        
        </g:javascript>
        </sec:ifAnyGranted>
</head>
<body>
    <a href="#show-recipes" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <div id="show-recipes" class="content scaffold-show" role="main">
        <h1><g:message code="default.show.label" args="[entityName]" /></h1>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <g:if test="${flash.error}">
            <div class="errors" role="status">
                ${flash.error}
            </div>
        </g:if>

        <fieldset class="form">
            <legend class="formSection"><g:message code="recipes.basicinfo.label" default="Basic info." /></legend>
            <ol class="property-list recipes">

                <g:if test="${recipesInstance?.name}">
                    <li class="fieldcontain">
                        <span id="name-label" class="property-label"><g:message code="recipes.name.label" default="Name" /></span>

                        <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${recipesInstance}" field="name"/></span>

                    </li>
                </g:if>

                <g:if test="${recipesInstance?.category}">
                    <li class="fieldcontain">
                        <span id="category-label" class="property-label"><g:message code="recipes.category.label" default="Category" /></span>

                        <span class="property-value" aria-labelledby="category-label"><g:fieldValue bean="${recipesInstance}" field="category"/></span>

                    </li>
                </g:if>

                <g:if test="${recipesInstance?.source}">
                    <li class="fieldcontain">
                        <span id="source-label" class="property-label"><g:message code="recipes.source.label" default="Source" /></span>

                        <span class="property-value" aria-labelledby="source-label"><g:fieldValue bean="${recipesInstance}" field="source.label"/></span>

                    </li>
                </g:if>

                <li class="fieldcontain">
                    <span id="tags-label" class="property-label"><g:message code="recipes.tags.label" default="Tags" /></span>

                    <span class="property-value" aria-labelledby="tags-label">
                        <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MASTER_USER">
                            <g:formRemote name="setRecipeTags"
                                          on404="alert('Failed to set tags!')"
                                          update="updateMe"
                                          url="[controller: 'ajax', action:'updateRecipeTags']">
                                <g:hiddenField name="recipeId" value="${recipesInstance?.id}" />
                                <g:textArea name="tags" id="tags" value="${recipesInstance?.tags?.sort()?.join(", ")}"/>
                                <g:actionSubmit class="save" action="update" value="${message(code: 'default.button.apply.label', default: 'Apply')}" />
                                <div id="updateMe"><!-- this div is updated with the result of the submit --></div>
                            </g:formRemote>
                            
                        </sec:ifAnyGranted>
                        <sec:ifAnyGranted roles="ROLE_TRUSTED_USER,ROLE_GUEST_USER">
                            ${recipesInstance?.tags?.sort()?.join(", ")}
                        </sec:ifAnyGranted>
                    </span>
                </li>

                <li class="fieldcontain">
                    <span id="rating-label" class="property-label"><g:message code="recipes.rating.label" default="Rating" /></span>

                    <span class="property-value" aria-labelledby="rating-label">
                        <sec:ifAnyGranted roles="ROLE_MASTER_USER">
                            <rateable:ratings bean='${recipesInstance}' id="${recipesInstance.id}" active='true'/>
                        </sec:ifAnyGranted>
                        <sec:ifNotGranted roles="ROLE_MASTER_USER">
                            <rateable:ratings bean='${recipesInstance}' active='false'/>
                        </sec:ifNotGranted>
                    </span>
                </li>

                <g:if test="${recipesInstance?.reference}">
                    <li class="fieldcontain">
                        <span id="reference-label" class="property-label"><g:message code="recipes.reference.label" default="Reference" /></span>

                        <span class="property-value" aria-labelledby="reference-label">
                            <g:if test="${recipesInstance.reference.matches("\\w{1}:.*")}">
                                <g:link action="download" id="${recipesInstance.id}">${new File(recipesInstance.reference).name}</g:link><br/>
								<g:if test="${new File(recipesInstance.reference).name.toLowerCase().endsWith(".pdf")}">
									<object data="/RecettesV2/recipes/embed/${recipesInstance.id}" type="application/pdf" width="100%" height="750px">
										<embed src="/RecettesV2/recipes/embed/${recipesInstance.id}" type="application/pdf">
											<p>This browser does not support PDFs.</p>
										</embed>
									</object>
								</g:if>
								<g:elseif test="${new File(recipesInstance.reference).name.toLowerCase().endsWith(".jpg")}">
									<img class="embedImg" src="/RecettesV2/recipes/embed/${recipesInstance.id}"/>
								</g:elseif>
                            </g:if>
                            <g:elseif test="${recipesInstance.reference.matches("http.*")}">
                                <a href="${recipesInstance.reference}" target="_displaySource"><g:fieldValue bean="${recipesInstance}" field="reference"/></a><br/>
								<iframe src="${recipesInstance.reference}" width="100%" height="750px"></iframe>
                            </g:elseif>
                            <g:else>
                                <a href="http://${recipesInstance.reference}" target="_displaySource"><g:fieldValue bean="${recipesInstance}" field="reference"/></a><br/>
								<iframe src="http://${recipesInstance.reference}" width="100%" height="750px"></iframe>
                            </g:else>
                        </span>

                    </li>
                </g:if>

                <g:if test="${recipesInstance?.notes}">
                    <li class="fieldcontain">
                        <span id="notes-label" class="property-label"><g:message code="recipes.notes.label" default="Notes" /></span>

                        <span class="property-value" aria-labelledby="notes-label"><g:fieldValue bean="${recipesInstance}" field="notes"/></span>

                    </li>
                </g:if>

                <li class="fieldcontain">
                    <span id="created-label" class="property-label"><g:message code="recipes.createdby.label" default="Created by" /></span>

                    <%
                        def createdBy = recipesInstance.createdBy
                        if (createdBy) {
                            def createdByUser = User.findByUsername(createdBy)
                            if (createdByUser?.fullName) {
                                createdBy = createdByUser?.fullName
                            }
                        }
                    %>
                    <i><span class="property-value" aria-labelledby="created-label">${createdBy} (<g:formatDate format="yyyy-MM-dd HH:mm" date="${recipesInstance.createdOn}"/>)</span></i>
                </li>

                <li class="fieldcontain">
                    <span id="updated-label" class="property-label"><g:message code="recipes.updatedby.label" default="Last Updated by" /></span>

                    <%
                        def updatedBy = recipesInstance.lastUpdatedBy
                        if (updatedBy) {
                            def updatedByUser = User.findByUsername(updatedBy)
                            if (updatedByUser?.fullName) {
                                updatedBy = updatedByUser?.fullName
                            }
                        }
                    %>
                    <i><span class="property-value" aria-labelledby="updated-label">${updatedBy} (<g:formatDate format="yyyy-MM-dd HH:mm" date="${recipesInstance.lastUpdatedOn}"/>)</span></i>
                </li>

                
            </ol>

        </fieldset>

        <g:if test="${recipesInstance?.ingredients}">
            <fieldset class="form">
                <legend class="formSection"><g:message code="recipes.ingredients.label" default="Ingredients list" /></legend>
                <ol class="property-list recipes">
                    <g:each in="${recipesInstance.ingredients.split("[\n,]")}" status="i" var="ingredient">
                        <li class="fieldcontain">
                            <span class="property-value">${ingredient}</span>
                        </li>
                    </g:each>
                </ol>

            </fieldset>
        </g:if>

        <g:if test="${recipesInstance?.preparationTime || recipesInstance?.marinationTime || recipesInstance?.cookingTime || recipesInstance?.instructions}">
            <fieldset class="form">
                <legend class="formSection"><g:message code="recipes.instructiondetails.label" default="Instruction details" /></legend>
                <ol class="property-list recipes">
                    <g:if test="${recipesInstance?.preparationTime}">
                        <li class="fieldcontain">
                            <span id="preparationTime-label" class="property-label"><g:message code="recipes.preparationTime.label" default="Preparation Time (min)" /></span>

                            <span class="property-value" aria-labelledby="preparationTime-label"><g:fieldValue bean="${recipesInstance}" field="preparationTime"/></span>

                        </li>
                    </g:if>

                    <g:if test="${recipesInstance?.marinationTime}">
                        <li class="fieldcontain">
                            <span id="marinationTime-label" class="property-label"><g:message code="recipes.marinationTime.label" default="Marination Time (min)" /></span>

                            <span class="property-value" aria-labelledby="marinationTime-label"><g:fieldValue bean="${recipesInstance}" field="marinationTime"/></span>

                        </li>
                    </g:if>

                    <g:if test="${recipesInstance?.cookingTime}">
                        <li class="fieldcontain">
                            <span id="cookingTime-label" class="property-label"><g:message code="recipes.cookingTime.label" default="Cooking Time (min)" /></span>

                            <span class="property-value" aria-labelledby="cookingTime-label"><g:fieldValue bean="${recipesInstance}" field="cookingTime"/></span>

                        </li>
                    </g:if>

                    <g:if test="${recipesInstance?.instructions}">
                        <li class="fieldcontain">
                            <span id="instructions-label" class="property-label"><g:message code="recipes.instructions.label" default="Instructions" /></span>

                            <pre class="property-value" aria-labelledby="instructions-label"><g:fieldValue bean="${recipesInstance}" field="instructions"/></pre>

                        </li>
                    </g:if>

                </ol>
            </fieldset>
        </g:if>
        <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MASTER_USER,ROLE_TRUSTED_USER">
        <g:form url="[resource:recipesInstance, action:'delete']" method="DELETE">
            <fieldset class="buttons">
                <g:link class="edit" action="edit" resource="${recipesInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_MASTER_USER">
                    <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </sec:ifAnyGranted>
            </fieldset>
        </g:form>
        </sec:ifAnyGranted>
        <comments:render bean="${recipesInstance}" />
    </div>
</body>
</html>
