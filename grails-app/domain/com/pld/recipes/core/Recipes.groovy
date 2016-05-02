package com.pld.recipes.core

import org.grails.rateable.*
import org.grails.comments.*
import org.grails.taggable.*

class Recipes implements Rateable, Commentable, Taggable {

    static searchable = true
    String name
    Category category
    Source source
    String notes
    String reference
    String ingredients
    String instructions
    Integer cookingTime
    Integer preparationTime
    Integer marinationTime
    String createdBy
    Date createdOn
    String lastUpdatedBy
    Date lastUpdatedOn

    static constraints = {
        name blank:false
        category blank:false
        reference nullable:true
        source nullable:false
        notes nullable:true
        instructions nullable:true
        cookingTime nullable:true
        preparationTime nullable:true
        marinationTime nullable:true
        ingredients nullable:true
        createdBy nullable:false
        createdOn nullable:false
        lastUpdatedBy nullable:false
        lastUpdatedOn nullable:false
        
    }
}
