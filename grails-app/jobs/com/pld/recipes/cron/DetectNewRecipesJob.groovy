package com.pld.recipes.cron

import com.pld.recipes.core.Recipes
import com.pld.recipes.core.Category
import com.pld.recipes.core.Source


class DetectNewRecipesJob {
    def grailsApplication
    
    def folderCategoryMappings = [
        'Asiatiques': Category.Asiatique,
        'Breuvages': Category.Breuvage,
        'Grains, Noix, Fromages et Charcuteries': Category.Noix_Fromage,
        'Hamburgers et sandwichs': Category.Sandwich,
        'Mexicains': Category.Mexicain,
        'Oeufs et omelettes': Category.Oeuf,
        'Outils et documentation': Category.Documentation,
        'Pains, pâtisseries et desserts': Category.Patisserie,
        'Pâtes, riz et risotto': Category.Pates_Riz,
        'Plats préparés': Category.Casserole,
        'Poissons et Fruits de mer': Category.Poisson,
        'Salades, légumes et accompagnements': Category.Legume,
        'Soupes, crèmes et sauces': Category.Soupe,
        'Viandes': Category.Viande,
        'Vinaigrettes et Marinades': Category.Vinaigrette_Marinade,
        'Volailles': Category.Volaile
    ]
    static triggers = {
        cron name: 'myTrigger', cronExpression: "0 0 * * * ?"
    }

    def execute() {
        def basePath = grailsApplication.config.recipes.cron.detectNewRecipes.basePath
        new File(basePath).eachFile {folder ->
            if (folder.isDirectory() && folder.name != ".svn" && folderCategoryMappings.get(folder.name)) {
                folder.eachFile { recipesFile ->
                    if (recipesFile.name.endsWith(".pdf")) {
                        Recipes r = Recipes.findByReference(recipesFile.absolutePath)
                        if (!r) {
                            println recipesFile.absolutePath
                            r = new Recipes(name: recipesFile.name.replace(".pdf", ""),
                                category: folderCategoryMappings.get(folder.name),
                                source: Source.Autre,
                                reference: recipesFile.absolutePath,
                                createdBy: 'system',
                                createdOn: new Date(),
                                lastUpdatedBy: 'system',
                                lastUpdatedOn:  new Date())
                            r.save()
                            
                            r.addTags(['Nouvelle'])
                        }
                    }
                }
            }
        }
    }
}
