import com.pld.recipes.core.Recipes
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_recettesV2_recipesindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/recipes/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(1)
invokeTag('set','g',6,['var':("entityName"),'value':(message(code: 'recipes.label', default: 'Recipes'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',7,['code':("default.list.label"),'args':([entityName])],-1)
})
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(2)
invokeTag('resources','rateable',8,[:],-1)
printHtmlPart(3)
invokeTag('require','r',14,['module':("filterpane")],-1)
printHtmlPart(4)
})
invokeTag('captureHead','sitemesh',15,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
invokeTag('message','g',18,['code':("springSecurity.denied.message")],-1)
printHtmlPart(6)
})
invokeTag('ifNotGranted','sec',19,['roles':("ROLE_ADMIN,ROLE_MASTER_USER,ROLE_TRUSTED_USER,ROLE_GUEST_USER")],2)
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(7)
invokeTag('message','g',21,['code':("default.link.skip.label"),'default':("Skip to content&hellip;")],-1)
printHtmlPart(8)
invokeTag('message','g',23,['code':("default.list.label"),'args':([entityName])],-1)
printHtmlPart(9)
if(true && (flash.message)) {
printHtmlPart(10)
expressionOut.print(flash.message)
printHtmlPart(11)
}
printHtmlPart(12)
createTagBody(3, {->
printHtmlPart(13)
invokeTag('textField','g',32,['name':("q"),'value':(params.q),'size':("50")],-1)
printHtmlPart(14)
})
invokeTag('form','g',33,['url':([controller: "recipes", action: "search"]),'id':("searchableForm"),'name':("searchableForm"),'method':("get")],3)
printHtmlPart(15)
invokeTag('filterButton','filterpane',34,['text':("Filter")],-1)
printHtmlPart(16)
expressionOut.print(createLink(uri: '/'))
printHtmlPart(17)
invokeTag('message','g',34,['code':("default.clearFilter.label"),'default':("Clear filter")],-1)
printHtmlPart(18)
invokeTag('filterPane','filterpane',35,['domain':("com.pld.recipes.core.Recipes"),'excludeProperties':("createdBy,createdOn,lastUpdatedBy,lastUpdatedOn")],-1)
printHtmlPart(19)
if(true && (params.action == 'search')) {
printHtmlPart(20)
expressionOut.print(message(code: 'recipes.name.label', default: 'Name'))
printHtmlPart(21)
expressionOut.print(message(code: 'recipes.category.label', default: 'Category'))
printHtmlPart(21)
expressionOut.print(message(code: 'recipes.source.label', default: 'Source'))
printHtmlPart(21)
expressionOut.print(message(code: 'recipes.notes.label', default: 'Notes'))
printHtmlPart(22)
expressionOut.print(message(code: 'recipes.tags.label', default: 'Tags'))
printHtmlPart(21)
expressionOut.print(message(code: 'recipes.averageRating.label', default: 'Avg. Rating'))
printHtmlPart(23)
}
else {
printHtmlPart(24)
invokeTag('sortableColumn','g',49,['property':("name"),'title':(message(code: 'recipes.name.label', default: 'Name')),'params':(filterParams)],-1)
printHtmlPart(24)
invokeTag('sortableColumn','g',50,['property':("category"),'title':(message(code: 'recipes.category.label', default: 'Category')),'params':(filterParams)],-1)
printHtmlPart(24)
invokeTag('sortableColumn','g',51,['property':("source"),'title':(message(code: 'recipes.source.label', default: 'Source')),'params':(filterParams)],-1)
printHtmlPart(24)
invokeTag('sortableColumn','g',52,['property':("notes"),'title':(message(code: 'recipes.notes.label', default: 'Notes')),'params':(filterParams)],-1)
printHtmlPart(25)
expressionOut.print(message(code: 'recipes.tags.label', default: 'Tags'))
printHtmlPart(26)
invokeTag('sortableColumn','g',54,['class':("fixedWidthColumn"),'property':("averageRating"),'title':(message(code: 'recipes.averageRating.label', default: 'Avg. Rating')),'params':(filterParams ? filterParams : params)],-1)
printHtmlPart(27)
}
printHtmlPart(28)
loop:{
int i = 0
for( recipesInstance in (recipesInstanceList) ) {
printHtmlPart(29)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(30)
createTagBody(4, {->
expressionOut.print(fieldValue(bean: recipesInstance, field: "name"))
})
invokeTag('link','g',62,['action':("show"),'id':(recipesInstance.id)],4)
printHtmlPart(31)
expressionOut.print(fieldValue(bean: recipesInstance, field: "category"))
printHtmlPart(32)
expressionOut.print(recipesInstance.source?.label)
printHtmlPart(32)
expressionOut.print(fieldValue(bean: recipesInstance, field: "notes"))
printHtmlPart(32)
expressionOut.print(recipesInstance?.tags?.sort()?.join(","))
printHtmlPart(33)
createTagBody(4, {->
printHtmlPart(27)
invokeTag('ratings','rateable',71,['bean':(recipesInstance),'id':(recipesInstance.id),'active':("true")],-1)
printHtmlPart(34)
})
invokeTag('ifAnyGranted','sec',72,['roles':("ROLE_MASTER_USER")],4)
printHtmlPart(34)
createTagBody(4, {->
printHtmlPart(27)
invokeTag('ratings','rateable',74,['bean':(recipesInstance),'active':("false")],-1)
printHtmlPart(34)
})
invokeTag('ifNotGranted','sec',75,['roles':("ROLE_MASTER_USER")],4)
printHtmlPart(35)
i++
}
}
printHtmlPart(36)
invokeTag('paginate','g',82,['total':(recipesInstanceCount ?: 0),'params':(filterParams ? filterParams : params)],-1)
printHtmlPart(37)
})
invokeTag('ifAnyGranted','sec',85,['roles':("ROLE_ADMIN,ROLE_MASTER_USER,ROLE_TRUSTED_USER,ROLE_GUEST_USER")],2)
printHtmlPart(4)
})
invokeTag('captureBody','sitemesh',86,[:],1)
printHtmlPart(38)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1402488838141L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
