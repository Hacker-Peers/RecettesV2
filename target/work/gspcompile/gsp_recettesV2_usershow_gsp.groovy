import com.pld.recipes.security.User
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_recettesV2_usershow_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/user/show.gsp" }
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
invokeTag('set','g',7,['var':("entityName"),'value':(message(code: 'user.label', default: 'User'))],-1)
printHtmlPart(2)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',8,['code':("default.show.label"),'args':([entityName])],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',9,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',11,['code':("default.link.skip.label"),'default':("Skip to content&hellip;")],-1)
printHtmlPart(5)
invokeTag('message','g',13,['code':("default.show.label"),'args':([entityName])],-1)
printHtmlPart(6)
if(true && (flash.message)) {
printHtmlPart(7)
expressionOut.print(flash.message)
printHtmlPart(8)
}
printHtmlPart(9)
if(true && (userInstance?.fullName)) {
printHtmlPart(10)
invokeTag('message','g',21,['code':("user.fullName.label"),'default':("Full Name")],-1)
printHtmlPart(11)
invokeTag('fieldValue','g',23,['bean':(userInstance),'field':("fullName")],-1)
printHtmlPart(12)
}
printHtmlPart(13)
if(true && (userInstance?.username)) {
printHtmlPart(14)
invokeTag('message','g',30,['code':("user.username.label"),'default':("Username")],-1)
printHtmlPart(15)
invokeTag('fieldValue','g',32,['bean':(userInstance),'field':("username")],-1)
printHtmlPart(12)
}
printHtmlPart(16)
invokeTag('message','g',38,['code':("user.accountExpired.label"),'default':("Account Expired")],-1)
printHtmlPart(17)
invokeTag('formatBoolean','g',40,['boolean':(userInstance?.accountExpired)],-1)
printHtmlPart(18)
invokeTag('message','g',44,['code':("user.accountLocked.label"),'default':("Account Locked")],-1)
printHtmlPart(19)
invokeTag('formatBoolean','g',46,['boolean':(userInstance?.accountLocked)],-1)
printHtmlPart(20)
invokeTag('message','g',50,['code':("user.enabled.label"),'default':("Enabled")],-1)
printHtmlPart(21)
invokeTag('formatBoolean','g',52,['boolean':(userInstance?.enabled)],-1)
printHtmlPart(22)
invokeTag('message','g',56,['code':("user.passwordExpired.label"),'default':("Password Expired")],-1)
printHtmlPart(23)
invokeTag('formatBoolean','g',58,['boolean':(userInstance?.passwordExpired)],-1)
printHtmlPart(24)
createTagBody(2, {->
printHtmlPart(25)
createTagBody(3, {->
invokeTag('message','g',65,['code':("default.button.edit.label"),'default':("Edit")],-1)
})
invokeTag('link','g',65,['class':("edit"),'action':("edit"),'resource':(userInstance)],3)
printHtmlPart(26)
createTagBody(3, {->
printHtmlPart(27)
invokeTag('actionSubmit','g',67,['class':("delete"),'action':("delete"),'value':(message(code: 'default.button.delete.label', default: 'Delete')),'onclick':("return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');")],-1)
printHtmlPart(26)
})
invokeTag('ifAnyGranted','sec',68,['roles':("ROLE_ADMIN")],3)
printHtmlPart(28)
})
invokeTag('form','g',70,['url':([resource:userInstance, action:'delete']),'method':("DELETE")],2)
printHtmlPart(29)
})
invokeTag('captureBody','sitemesh',72,[:],1)
printHtmlPart(30)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1395515277585L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
