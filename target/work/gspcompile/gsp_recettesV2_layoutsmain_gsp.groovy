import com.pld.recipes.core.Recipes
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_recettesV2_layoutsmain_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/main.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',9,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("Content-Type"),'content':("text/html; charset=UTF-8")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',10,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("X-UA-Compatible"),'content':("IE=edge,chrome=1")],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('layoutTitle','g',11,['default':("Grails")],-1)
})
invokeTag('captureTitle','sitemesh',11,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',11,[:],2)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',12,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("viewport"),'content':("width=device-width, initial-scale=1.0")],-1)
printHtmlPart(3)
expressionOut.print(resource(dir: 'images', file: 'favicon.ico'))
printHtmlPart(4)
expressionOut.print(resource(dir: 'images', file: 'apple-touch-icon.png'))
printHtmlPart(5)
expressionOut.print(resource(dir: 'images', file: 'apple-touch-icon-retina.png'))
printHtmlPart(6)
expressionOut.print(resource(dir: 'css', file: 'main.css'))
printHtmlPart(7)
expressionOut.print(resource(dir: 'css', file: 'mobile.css'))
printHtmlPart(8)
invokeTag('javascript','g',23,['library':("jquery")],-1)
printHtmlPart(2)
invokeTag('javascript','g',24,['library':("jquery-ui")],-1)
printHtmlPart(2)
invokeTag('javascript','g',25,['library':("application")],-1)
printHtmlPart(9)
invokeTag('resources','rateable',26,[:],-1)
printHtmlPart(2)
invokeTag('layoutHead','g',27,[:],-1)
printHtmlPart(1)
invokeTag('layoutResources','r',28,[:],-1)
printHtmlPart(10)
})
invokeTag('captureHead','sitemesh',29,[:],1)
printHtmlPart(10)
createTagBody(1, {->
printHtmlPart(11)
createTagBody(2, {->
printHtmlPart(12)
invokeTag('username','sec',33,[:],-1)
printHtmlPart(13)
createClosureForHtmlPart(14, 3)
invokeTag('link','g',33,['controller':("logout")],3)
printHtmlPart(15)
})
invokeTag('ifLoggedIn','sec',34,[:],2)
printHtmlPart(16)
createTagBody(2, {->
printHtmlPart(16)
createClosureForHtmlPart(17, 3)
invokeTag('link','g',36,['controller':("login"),'action':("auth")],3)
printHtmlPart(16)
})
invokeTag('ifNotLoggedIn','sec',37,[:],2)
printHtmlPart(18)
expressionOut.print(createLink(uri: '/'))
printHtmlPart(19)
invokeTag('message','g',41,['code':("default.home.label")],-1)
printHtmlPart(20)
createTagBody(2, {->
printHtmlPart(21)
createTagBody(3, {->
invokeTag('message','g',43,['code':("default.new.label"),'args':(['Recipes'])],-1)
})
invokeTag('link','g',43,['class':("create"),'controller':("recipes"),'action':("create")],3)
printHtmlPart(22)
})
invokeTag('ifAnyGranted','sec',44,['roles':("ROLE_ADMIN,ROLE_MASTER_USER,ROLE_TRUSTED_USER")],2)
printHtmlPart(23)
createTagBody(2, {->
printHtmlPart(24)
createTagBody(3, {->
invokeTag('message','g',47,['code':("default.list.label"),'args':(['Users'])],-1)
})
invokeTag('link','g',47,['class':("list"),'controller':("user"),'action':("index")],3)
printHtmlPart(25)
createTagBody(3, {->
invokeTag('message','g',48,['code':("default.new.label"),'args':(['User'])],-1)
})
invokeTag('link','g',48,['class':("create"),'controller':("user"),'action':("create")],3)
printHtmlPart(22)
})
invokeTag('ifAnyGranted','sec',49,['roles':("ROLE_ADMIN,ROLE_MASTER_USER")],2)
printHtmlPart(23)
createTagBody(2, {->
printHtmlPart(24)
createClosureForHtmlPart(26, 3)
invokeTag('link','g',52,['class':("list"),'controller':("quartz"),'action':("list")],3)
printHtmlPart(25)
createClosureForHtmlPart(27, 3)
invokeTag('link','g',53,['class':("list"),'controller':("dbconsole")],3)
printHtmlPart(22)
})
invokeTag('ifAnyGranted','sec',54,['roles':("ROLE_ADMIN")],2)
printHtmlPart(28)
invokeTag('layoutBody','g',57,[:],-1)
printHtmlPart(29)
invokeTag('tagCloud','tc',59,['bean':(Recipes),'paramName':("tag"),'controller':("recipes"),'action':("index"),'params':("params"),'resetOffset':("true"),'sort':("true")],-1)
printHtmlPart(30)
invokeTag('message','g',61,['code':("spinner.alt"),'default':("Loading&hellip;")],-1)
printHtmlPart(31)
invokeTag('layoutResources','r',62,[:],-1)
printHtmlPart(10)
})
invokeTag('captureBody','sitemesh',63,[:],1)
printHtmlPart(32)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1422824006674L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
