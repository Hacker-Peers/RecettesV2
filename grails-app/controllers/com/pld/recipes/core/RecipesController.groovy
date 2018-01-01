package com.pld.recipes.core

import org.compass.core.engine.SearchEngineQueryParseException
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.grails.comments.CommentLink
import grails.converters.JSON
import com.pld.recipes.security.User
import grails.plugin.springsecurity.annotation.Secured
import org.springframework.web.multipart.*

@Transactional(readOnly = true)
class RecipesController {

    static allowedMethods = [save: "POST", update: "POST", delete: "DELETE"]

    def filterPaneService
    def searchableService
    def springSecurityService
	def grailsApplication

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def tag = params.tag;
        if (tag) {
            def result = Recipes.findAllByTag(tag, params)
            respond result, model:[recipesInstanceCount: Recipes.countByTag(tag)]
        } else if (params.sort == 'averageRating') {
            def sortValue = params.remove("sort")
            def maxValue = params.remove("max")
            def offsetValue = params.remove("offset")
            def result = Recipes.list(params).sort{it.averageRating}
            if (params.order == 'desc') {
                result = result.reverse()
            }
            params.sort = sortValue
            params.max = maxValue
            params.offset = offsetValue
//
//            def result = Recipes.listOrderByAverageRating()
//            if (params.order == 'desc') {
//                result = result.reverse()
//            }
            def offset = params.offset
            if (offset) {
                offset = offset.toInteger()
            } else {
                offset = 0
            }
            
            respond result.subList(offset, Math.min(offset + max, result.size())), model:[recipesInstanceCount: result.size()]
        } else {
            respond Recipes.list(params), model:[recipesInstanceCount: Recipes.count()]
        }
    }
	
	def embed(Recipes recipesInstance) {
		getDocument(recipesInstance, true)
	}
	
	def download(Recipes recipesInstance) {
		getDocument(recipesInstance, false)
	}
    
    private def getDocument(Recipes recipesInstance, boolean embedded) {
        if (recipesInstance?.reference?.matches("\\w{1}:.*")) {
            def file = new File(recipesInstance?.reference)
            if (file.exists()) {
			    if (file.name.toLowerCase().endsWith("pdf")) {
					response.setContentType("application/pdf")
			    } else if (file.name.toLowerCase().endsWith("jpg")) {
					response.setContentType("image/jpeg")
				} else {
					response.setContentType("application/octet-stream")
				}
				if (!embedded) {
					response.setHeader("Content-disposition", "attachment; filename=${file.name}")
				}
                response.outputStream << file.newInputStream()
                return
            }
        }
        
        // The file doesn't exist.
        flash.error = message(code: 'recipies.reference.filenotfound.message', args: [recipesInstance?.reference])
        render( view:'show', model:[recipesInstance: recipesInstance ] )
    }

    /**
     * Index page with search form and results
     */
    def search() {
        if (!params.q?.trim()) {
            render( view:'index', model:[
                recipesInstanceList: Recipes.list(params),
                recipesInstanceCount: Recipes.count() ] )
        } else {
            def criteria = params.q.split(" ")
            def results = [:]
            def resultCounts = [:]
            criteria.each { criterion ->
                params.max = 100
                def searchResults = searchableService.search(criterion, params)
                searchResults.results.each { searchResult ->
                    if (searchResult instanceof com.pld.recipes.core.Recipes) {
                        def count = resultCounts.get(searchResult.id)
                        if (!count) {
                            results.put(searchResult.id, searchResult)
                            resultCounts.put(searchResult.id, 1)
                        } else {
                            count++
                            resultCounts.put(searchResult.id, count)
                        }
                    } else if (searchResult instanceof org.grails.taggable.Tag) {
                        Recipes.findAllByTag(searchResult.name).each { tagResult ->
                            def count = resultCounts.get(tagResult.id)
                            if (!count) {
                                results.put(tagResult.id, tagResult)
                                resultCounts.put(tagResult.id, 1)
                            } else {
                                count++
                                resultCounts.put(tagResult.id, count)
                            }
                        }
                    }
                }
            }
            def recipesList = []
            resultCounts.sort{it-> -it.value}.each { it
                recipesList << results.get(it.key)
            }

            render( view:'index', model:[
                    recipesInstanceList: recipesList,
                    recipesInstanceCount: recipesList.size() ] )
        }
        
    }    

    def filter(String filterTag) {
        if(!params.max) {
            params.max = 10
        }
        
        def result
        if (params.sort == 'averageRating') {
            def sortValue = params.remove("sort")
            result = filterPaneService.filter( params, Recipes ).sort{it.averageRating}
            if (params.order == 'desc') {
                result = result.reverse()
            }
            params.sort = sortValue
        } else {
            result = filterPaneService.filter( params, Recipes )
        }
        
        render( view:'index',
            model:[ recipesInstanceList: result,
                recipesInstanceCount: filterPaneService.count( params, Recipes ),
                filterParams: org.grails.plugin.filterpane.FilterPaneUtils.extractFilterParams(params),
                params:params ] )
    }

    def show(Recipes recipesInstance) {
        respond recipesInstance
    }

    @Secured(['ROLE_ADMIN', 'ROLE_MASTER_USER', 'ROLE_TRUSTED_USER'])
    def create() {
        respond new Recipes(params)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_MASTER_USER', 'ROLE_TRUSTED_USER'])
    @Transactional
    def save() {
        def recipesInstance = new Recipes(params)
        if (recipesInstance == null) {
            notFound()
            return
        }
        
        def user = User.get(springSecurityService.principal.id)
        recipesInstance.createdBy = user.username
        recipesInstance.createdOn = new Date()
        recipesInstance.lastUpdatedBy = recipesInstance.createdBy
        recipesInstance.lastUpdatedOn = recipesInstance.createdOn

		uploadRecipeFileIfProvided(recipesInstance, request);
        
        if (recipesInstance.hasErrors()) {
            respond recipesInstance.errors, view:'create'
            return
        }
		
        recipesInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'recipesInstance.label', default: 'Recipes'), recipesInstance.id])
                redirect recipesInstance
            }
            '*' { respond recipesInstance, [status: CREATED] }
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_MASTER_USER', 'ROLE_TRUSTED_USER'])
    def edit(Recipes recipesInstance) {
        respond recipesInstance
    }

    @Secured(['ROLE_ADMIN', 'ROLE_MASTER_USER', 'ROLE_TRUSTED_USER'])
    @Transactional
    def update(Recipes recipesInstance) {
        if (recipesInstance == null) {
            notFound()
            return
        }

        def user = User.get(springSecurityService.principal.id)
        recipesInstance.lastUpdatedBy = user.username
        recipesInstance.lastUpdatedOn = new Date()

		if (!uploadRecipeFileIfProvided(recipesInstance, request)) {
		
		}
        
        if (recipesInstance.hasErrors()) {
            respond recipesInstance.errors, view:'edit'
            return
        }

        recipesInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Recipes.label', default: 'Recipes'), recipesInstance.id])
                redirect recipesInstance
            }
            '*'{ respond recipesInstance, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_MASTER_USER'])
    @Transactional
    def delete(Recipes recipesInstance) {

        if (recipesInstance == null) {
            notFound()
            return
        }

        recipesInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Recipes.label', default: 'Recipes'), recipesInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'recipesInstance.label', default: 'Recipes'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
	
	void uploadRecipeFileIfProvided(Recipes recipesInstance, def baseRequest) {
	    MultipartRequest request =  baseRequest as MultipartRequest
		String content = request.getContentType()
		if (content.contains("multipart/form-data") || (request instanceof MultipartHttpServletRequest)) {
			MultipartFile uploadedFile = request.getFile('recipeFile')
			if (uploadedFile && !uploadedFile.empty) {
				def basePath = grailsApplication.config.recipes.cron.detectNewRecipes.basePath + "\\Uploaded"
				File dest = new File(basePath, uploadedFile.originalFilename)
				if (dest.exists()) {
					dest = new File(basePath, System.currentTimeMillis() + "_" + uploadedFile.originalFilename)
				}
				uploadedFile.transferTo(dest)
				recipesInstance.reference = dest.absolutePath
			}
		}

	}
}
