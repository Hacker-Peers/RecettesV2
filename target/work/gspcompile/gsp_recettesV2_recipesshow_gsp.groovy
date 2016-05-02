import com.pld.recipes.core.Recipes
import com.pld.recipes.security.User
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_recettesV2_recipesshow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/recipes/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(2)
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'recipes.label', default: 'Recipes'))],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',8,['code':("default.show.label"),'args':([entityName])],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(2)
createTagBody(2, {->
printHtmlPart(3)
createTagBody(3, {->
printHtmlPart(4)
invokeTag('createLink','g',35,['controller':("ajax"),'action':("recipesExistingTags")],-1)
printHtmlPart(5)
})
invokeTag('javascript','g',64,[:],3)
printHtmlPart(2)
})
invokeTag('ifAnyGranted','sec',65,['roles':("ROLE_ADMIN,ROLE_MASTER_USER")],2)
printHtmlPart(0)
})
invokeTag('captureHead','sitemesh',66,[:],1)
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(6)
invokeTag('message','g',68,['code':("default.link.skip.label"),'default':("Skip to content&hellip;")],-1)
printHtmlPart(7)
invokeTag('message','g',70,['code':("default.show.label"),'args':([entityName])],-1)
printHtmlPart(8)
if(true && (flash.message)) {
printHtmlPart(9)
expressionOut.print(flash.message)
printHtmlPart(10)
}
printHtmlPart(2)
if(true && (flash.error)) {
printHtmlPart(11)
expressionOut.print(flash.error)
printHtmlPart(12)
}
printHtmlPart(13)
invokeTag('message','g',81,['code':("recipes.basicinfo.label"),'default':("Basic info.")],-1)
printHtmlPart(14)
if(true && (recipesInstance?.name)) {
printHtmlPart(15)
invokeTag('message','g',86,['code':("recipes.name.label"),'default':("Name")],-1)
printHtmlPart(16)
invokeTag('fieldValue','g',88,['bean':(recipesInstance),'field':("name")],-1)
printHtmlPart(17)
}
printHtmlPart(18)
if(true && (recipesInstance?.category)) {
printHtmlPart(19)
invokeTag('message','g',95,['code':("recipes.category.label"),'default':("Category")],-1)
printHtmlPart(20)
invokeTag('fieldValue','g',97,['bean':(recipesInstance),'field':("category")],-1)
printHtmlPart(17)
}
printHtmlPart(18)
if(true && (recipesInstance?.source)) {
printHtmlPart(21)
invokeTag('message','g',104,['code':("recipes.source.label"),'default':("Source")],-1)
printHtmlPart(22)
invokeTag('fieldValue','g',106,['bean':(recipesInstance),'field':("source.label")],-1)
printHtmlPart(17)
}
printHtmlPart(18)
if(true && (recipesInstance?.reference)) {
printHtmlPart(23)
invokeTag('message','g',113,['code':("recipes.reference.label"),'default':("Reference")],-1)
printHtmlPart(24)
if(true && (recipesInstance.reference.matches("\\w{1}:.*"))) {
printHtmlPart(25)
createTagBody(4, {->
expressionOut.print(new File(recipesInstance.reference).name)
})
invokeTag('link','g',117,['action':("download"),'id':(recipesInstance.id)],4)
printHtmlPart(26)
}
else if(true && (recipesInstance.reference.matches("http.*"))) {
printHtmlPart(27)
expressionOut.print(recipesInstance.reference)
printHtmlPart(28)
invokeTag('fieldValue','g',120,['bean':(recipesInstance),'field':("reference")],-1)
printHtmlPart(29)
}
else {
printHtmlPart(30)
expressionOut.print(recipesInstance.reference)
printHtmlPart(28)
invokeTag('fieldValue','g',123,['bean':(recipesInstance),'field':("reference")],-1)
printHtmlPart(29)
}
printHtmlPart(31)
}
printHtmlPart(18)
if(true && (recipesInstance?.notes)) {
printHtmlPart(32)
invokeTag('message','g',132,['code':("recipes.notes.label"),'default':("Notes")],-1)
printHtmlPart(33)
invokeTag('fieldValue','g',134,['bean':(recipesInstance),'field':("notes")],-1)
printHtmlPart(17)
}
printHtmlPart(34)
invokeTag('message','g',140,['code':("recipes.tags.label"),'default':("Tags")],-1)
printHtmlPart(35)
createTagBody(2, {->
printHtmlPart(26)
createTagBody(3, {->
printHtmlPart(25)
invokeTag('hiddenField','g',148,['name':("recipeId"),'value':(recipesInstance?.id)],-1)
printHtmlPart(25)
invokeTag('textArea','g',149,['name':("tags"),'id':("tags"),'value':(recipesInstance?.tags?.sort()?.join(", "))],-1)
printHtmlPart(25)
invokeTag('actionSubmit','g',150,['class':("save"),'action':("update"),'value':(message(code: 'default.button.apply.label', default: 'Apply'))],-1)
printHtmlPart(36)
})
invokeTag('formRemote','g',152,['name':("setRecipeTags"),'on404':("alert('Failed to set tags!')"),'update':("updateMe"),'url':([controller: 'ajax', action:'updateRecipeTags'])],3)
printHtmlPart(37)
})
invokeTag('ifAnyGranted','sec',154,['roles':("ROLE_ADMIN,ROLE_MASTER_USER")],2)
printHtmlPart(38)
createTagBody(2, {->
printHtmlPart(26)
expressionOut.print(recipesInstance?.tags?.sort()?.join(", "))
printHtmlPart(38)
})
invokeTag('ifAnyGranted','sec',157,['roles':("ROLE_TRUSTED_USER,ROLE_GUEST_USER")],2)
printHtmlPart(39)
invokeTag('message','g',162,['code':("recipes.rating.label"),'default':("Rating")],-1)
printHtmlPart(40)
createTagBody(2, {->
printHtmlPart(26)
invokeTag('ratings','rateable',166,['bean':(recipesInstance),'id':(recipesInstance.id),'active':("true")],-1)
printHtmlPart(38)
})
invokeTag('ifAnyGranted','sec',167,['roles':("ROLE_MASTER_USER")],2)
printHtmlPart(38)
createTagBody(2, {->
printHtmlPart(26)
invokeTag('ratings','rateable',169,['bean':(recipesInstance),'active':("false")],-1)
printHtmlPart(38)
})
invokeTag('ifNotGranted','sec',170,['roles':("ROLE_MASTER_USER")],2)
printHtmlPart(41)
invokeTag('message','g',175,['code':("recipes.createdby.label"),'default':("Created by")],-1)
printHtmlPart(42)

def createdBy = recipesInstance.createdBy
                        if (createdBy) {
                            def createdByUser = User.findByUsername(createdBy)
                            if (createdByUser?.fullName) {
                                createdBy = createdByUser?.fullName
                            }
                        }

printHtmlPart(43)
expressionOut.print(createdBy)
printHtmlPart(44)
invokeTag('formatDate','g',186,['format':("yyyy-MM-dd HH:mm"),'date':(recipesInstance.createdOn)],-1)
printHtmlPart(45)
invokeTag('message','g',190,['code':("recipes.updatedby.label"),'default':("Last Updated by")],-1)
printHtmlPart(42)

def updatedBy = recipesInstance.lastUpdatedBy
                        if (updatedBy) {
                            def updatedByUser = User.findByUsername(updatedBy)
                            if (updatedByUser?.fullName) {
                                updatedBy = updatedByUser?.fullName
                            }
                        }

printHtmlPart(46)
expressionOut.print(updatedBy)
printHtmlPart(44)
invokeTag('formatDate','g',201,['format':("yyyy-MM-dd HH:mm"),'date':(recipesInstance.lastUpdatedOn)],-1)
printHtmlPart(47)
if(true && (recipesInstance?.ingredients)) {
printHtmlPart(48)
invokeTag('message','g',211,['code':("recipes.ingredients.label"),'default':("Ingredients list")],-1)
printHtmlPart(49)
loop:{
int i = 0
for( ingredient in (recipesInstance.ingredients.split("[\n,]")) ) {
printHtmlPart(50)
expressionOut.print(ingredient)
printHtmlPart(51)
i++
}
}
printHtmlPart(52)
}
printHtmlPart(53)
if(true && (recipesInstance?.preparationTime || recipesInstance?.marinationTime || recipesInstance?.cookingTime || recipesInstance?.instructions)) {
printHtmlPart(48)
invokeTag('message','g',225,['code':("recipes.instructiondetails.label"),'default':("Instruction details")],-1)
printHtmlPart(49)
if(true && (recipesInstance?.preparationTime)) {
printHtmlPart(54)
invokeTag('message','g',229,['code':("recipes.preparationTime.label"),'default':("Preparation Time (min)")],-1)
printHtmlPart(55)
invokeTag('fieldValue','g',231,['bean':(recipesInstance),'field':("preparationTime")],-1)
printHtmlPart(56)
}
printHtmlPart(57)
if(true && (recipesInstance?.marinationTime)) {
printHtmlPart(58)
invokeTag('message','g',238,['code':("recipes.marinationTime.label"),'default':("Marination Time (min)")],-1)
printHtmlPart(59)
invokeTag('fieldValue','g',240,['bean':(recipesInstance),'field':("marinationTime")],-1)
printHtmlPart(56)
}
printHtmlPart(57)
if(true && (recipesInstance?.cookingTime)) {
printHtmlPart(60)
invokeTag('message','g',247,['code':("recipes.cookingTime.label"),'default':("Cooking Time (min)")],-1)
printHtmlPart(61)
invokeTag('fieldValue','g',249,['bean':(recipesInstance),'field':("cookingTime")],-1)
printHtmlPart(56)
}
printHtmlPart(57)
if(true && (recipesInstance?.instructions)) {
printHtmlPart(62)
invokeTag('message','g',256,['code':("recipes.instructions.label"),'default':("Instructions")],-1)
printHtmlPart(63)
invokeTag('fieldValue','g',258,['bean':(recipesInstance),'field':("instructions")],-1)
printHtmlPart(64)
}
printHtmlPart(65)
}
printHtmlPart(2)
createTagBody(2, {->
printHtmlPart(2)
createTagBody(3, {->
printHtmlPart(66)
createTagBody(4, {->
invokeTag('message','g',269,['code':("default.button.edit.label"),'default':("Edit")],-1)
})
invokeTag('link','g',269,['class':("edit"),'action':("edit"),'resource':(recipesInstance)],4)
printHtmlPart(67)
createTagBody(4, {->
printHtmlPart(68)
invokeTag('actionSubmit','g',271,['class':("delete"),'action':("delete"),'value':(message(code: 'default.button.delete.label', default: 'Delete')),'onclick':("return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');")],-1)
printHtmlPart(67)
})
invokeTag('ifAnyGranted','sec',272,['roles':("ROLE_ADMIN,ROLE_MASTER_USER")],4)
printHtmlPart(69)
})
invokeTag('form','g',274,['url':([resource:recipesInstance, action:'delete']),'method':("DELETE")],3)
printHtmlPart(2)
})
invokeTag('ifAnyGranted','sec',275,['roles':("ROLE_ADMIN,ROLE_MASTER_USER,ROLE_TRUSTED_USER")],2)
printHtmlPart(2)
invokeTag('render','comments',276,['bean':(recipesInstance)],-1)
printHtmlPart(70)
})
invokeTag('captureBody','sitemesh',278,[:],1)
printHtmlPart(71)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1422574881186L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
