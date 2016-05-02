<%@ page import="com.pld.recipes.core.Recipes" %>
<%@ page import="com.pld.recipes.core.Category" %>
<%@ page import="com.pld.recipes.core.Source" %>

<fieldset class="form">
    <legend class="formSection"><g:message code="recipes.basicinfo.label" default="Basic info." /></legend>
    <div class="fieldcontain ${hasErrors(bean: recipesInstance, field: 'name', 'error')} ">
        <label for="name">
            <g:message code="recipes.name.label" default="Name" required="" />
            <span class="required-indicator">*</span>
        </label>
        <g:textField name="name" value="${recipesInstance?.name}"/>

    </div>

    <div class="fieldcontain ${hasErrors(bean: recipesInstance, field: 'category', 'error')} ">
        <label for="category">
            <g:message code="recipes.category.label" default="Category" required="" />
            <span class="required-indicator">*</span>
        </label>
        <g:select name="category" 
        from="${Category?.values()}"
        keys="${Category?.values()*.name()}" required="" 
        value="${recipesInstance?.category?.name()}"/>

    </div>

    <div class="fieldcontain ${hasErrors(bean: recipesInstance, field: 'source', 'error')} ">
        <label for="source">
            <g:message code="recipes.source.label" default="Source" required="" />
            <span class="required-indicator">*</span>
        </label>
        <g:select name="source" 
        from="${Source?.values()*.label}"
        keys="${Source?.values()*.name()}" required="" 
        value="${recipesInstance?.source?.name()}"/>

    </div>

    <div class="fieldcontain ${hasErrors(bean: recipesInstance, field: 'reference', 'error')} ">
        <label for="reference">
            <g:message code="recipes.reference.label" default="Reference" />
        </label>
        <g:textField name="reference" value="${recipesInstance?.reference}"/>

    </div>

    <div class="fieldcontain ${hasErrors(bean: recipesInstance, field: 'notes', 'error')} ">
        <label for="notes">
            <g:message code="recipes.notes.label" default="Notes" />

        </label>
        <g:textArea name="notes" value="${recipesInstance?.notes}"/>

    </div>
</fieldset>

<fieldset class="form">
    <legend class="formSection"><g:message code="recipes.ingredients.label" default="Ingredients list" /></legend>
    <div class="fieldcontain ${hasErrors(bean: recipesInstance, field: 'ingredients', 'error')}">
        <label for="ingredients">
            <g:message code="recipes.ingredients.label" default="Ingredients" />
        </label>
        <g:textArea name="ingredients" value="${recipesInstance?.ingredients}"/>
    </div>
</fieldset>

<fieldset>
    <legend class="formSection"><g:message code="recipes.instructiondetails.label" default="Instruction details" /></legend>

    <div class="fieldcontain ${hasErrors(bean: recipesInstance, field: 'preparationTime', 'error')}">
        <label for="preparationTime">
            <g:message code="recipes.preparationTime.label" default="Preparation Time (min)" />
        </label>
        <g:field name="preparationTime" type="number" value="${recipesInstance.preparationTime}" />
    </div>

    <div class="fieldcontain ${hasErrors(bean: recipesInstance, field: 'marinationTime', 'error')}">
        <label for="marinationTime">
            <g:message code="recipes.marinationTime.label" default="Marination Time (min)" />
        </label>
        <g:field name="marinationTime" type="number" value="${recipesInstance.marinationTime}" />
    </div>

    <div class="fieldcontain ${hasErrors(bean: recipesInstance, field: 'cookingTime', 'error')}">
        <label for="cookingTime">
            <g:message code="recipes.cookingTime.label" default="Cooking Time (min)" />
        </label>
        <g:field name="cookingTime" type="number" value="${recipesInstance.cookingTime}" />

    </div>
    <div class="fieldcontain ${hasErrors(bean: recipesInstance, field: 'instructions', 'error')}">
        <label for="instructions">
            <g:message code="recipes.instructions.label" default="Instructions" />
        </label>
        <g:textArea name="instructions" value="${recipesInstance?.instructions}"/>
    </div>
</fieldset>
