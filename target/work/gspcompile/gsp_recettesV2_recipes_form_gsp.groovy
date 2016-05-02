import com.pld.recipes.core.Recipes
import com.pld.recipes.core.Category
import com.pld.recipes.core.Source
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_recettesV2_recipes_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/recipes/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(0)
printHtmlPart(1)
invokeTag('message','g',6,['code':("recipes.basicinfo.label"),'default':("Basic info.")],-1)
printHtmlPart(2)
expressionOut.print(hasErrors(bean: recipesInstance, field: 'name', 'error'))
printHtmlPart(3)
invokeTag('message','g',9,['code':("recipes.name.label"),'default':("Name"),'required':("")],-1)
printHtmlPart(4)
invokeTag('textField','g',12,['name':("name"),'value':(recipesInstance?.name)],-1)
printHtmlPart(5)
expressionOut.print(hasErrors(bean: recipesInstance, field: 'category', 'error'))
printHtmlPart(6)
invokeTag('message','g',18,['code':("recipes.category.label"),'default':("Category"),'required':("")],-1)
printHtmlPart(4)
invokeTag('select','g',24,['name':("category"),'from':(Category?.values()),'keys':(Category?.values()*.name()),'required':(""),'value':(recipesInstance?.category?.name())],-1)
printHtmlPart(5)
expressionOut.print(hasErrors(bean: recipesInstance, field: 'source', 'error'))
printHtmlPart(7)
invokeTag('message','g',30,['code':("recipes.source.label"),'default':("Source"),'required':("")],-1)
printHtmlPart(4)
invokeTag('select','g',36,['name':("source"),'from':(Source?.values()*.label),'keys':(Source?.values()*.name()),'required':(""),'value':(recipesInstance?.source?.name())],-1)
printHtmlPart(5)
expressionOut.print(hasErrors(bean: recipesInstance, field: 'reference', 'error'))
printHtmlPart(8)
invokeTag('message','g',42,['code':("recipes.reference.label"),'default':("Reference")],-1)
printHtmlPart(9)
invokeTag('textField','g',44,['name':("reference"),'value':(recipesInstance?.reference)],-1)
printHtmlPart(5)
expressionOut.print(hasErrors(bean: recipesInstance, field: 'notes', 'error'))
printHtmlPart(10)
invokeTag('message','g',50,['code':("recipes.notes.label"),'default':("Notes")],-1)
printHtmlPart(11)
invokeTag('textArea','g',53,['name':("notes"),'value':(recipesInstance?.notes)],-1)
printHtmlPart(12)
invokeTag('message','g',59,['code':("recipes.ingredients.label"),'default':("Ingredients list")],-1)
printHtmlPart(2)
expressionOut.print(hasErrors(bean: recipesInstance, field: 'ingredients', 'error'))
printHtmlPart(13)
invokeTag('message','g',62,['code':("recipes.ingredients.label"),'default':("Ingredients")],-1)
printHtmlPart(9)
invokeTag('textArea','g',64,['name':("ingredients"),'value':(recipesInstance?.ingredients)],-1)
printHtmlPart(14)
invokeTag('message','g',69,['code':("recipes.instructiondetails.label"),'default':("Instruction details")],-1)
printHtmlPart(15)
expressionOut.print(hasErrors(bean: recipesInstance, field: 'preparationTime', 'error'))
printHtmlPart(16)
invokeTag('message','g',73,['code':("recipes.preparationTime.label"),'default':("Preparation Time (min)")],-1)
printHtmlPart(9)
invokeTag('field','g',75,['name':("preparationTime"),'type':("number"),'value':(recipesInstance.preparationTime)],-1)
printHtmlPart(17)
expressionOut.print(hasErrors(bean: recipesInstance, field: 'marinationTime', 'error'))
printHtmlPart(18)
invokeTag('message','g',80,['code':("recipes.marinationTime.label"),'default':("Marination Time (min)")],-1)
printHtmlPart(9)
invokeTag('field','g',82,['name':("marinationTime"),'type':("number"),'value':(recipesInstance.marinationTime)],-1)
printHtmlPart(17)
expressionOut.print(hasErrors(bean: recipesInstance, field: 'cookingTime', 'error'))
printHtmlPart(19)
invokeTag('message','g',87,['code':("recipes.cookingTime.label"),'default':("Cooking Time (min)")],-1)
printHtmlPart(9)
invokeTag('field','g',89,['name':("cookingTime"),'type':("number"),'value':(recipesInstance.cookingTime)],-1)
printHtmlPart(20)
expressionOut.print(hasErrors(bean: recipesInstance, field: 'instructions', 'error'))
printHtmlPart(21)
invokeTag('message','g',94,['code':("recipes.instructions.label"),'default':("Instructions")],-1)
printHtmlPart(9)
invokeTag('textArea','g',96,['name':("instructions"),'value':(recipesInstance?.instructions)],-1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1422572669706L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
