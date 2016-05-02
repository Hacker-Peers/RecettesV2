package com.pld.recipes.util

import com.pld.recipes.core.Recipes
import grails.converters.JSON

class AjaxController {

    def recipesExistingTags() {
        def term = params.term?.toLowerCase()
        if (term) {
            render (Recipes.allTags.findAll{it.startsWith(term)}.sort() as JSON)
        } else {
            render (Recipes.allTags.sort() as JSON)
        }
    }
    
    def updateRecipeTags() {
        try {
            if (params?.recipeId) {
                Recipes r = Recipes.get(params?.recipeId)
                def newTags = []
                (params?.tags == null ? "" : params?.tags).split(",").each { tag ->
                    newTags << tag.trim()
                }
                r.setTags(newTags)
                render "<div class='message'>Updated at ${new Date()}</div>"
            } else {
                render "<div class='errors'>Recipe with ID ${params?.recipeId} not found, at ${new Date()}.</div>"
            }
        } catch (Exception e) {
                render "<div class='errors'>Recipe with ID ${params?.recipeId} not found, at ${new Date()}. (${e.message})</div>"
        }
        //println recipesInstance.tags
    }
}
