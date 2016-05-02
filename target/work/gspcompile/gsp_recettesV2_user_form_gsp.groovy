import com.pld.recipes.security.Role
import com.pld.recipes.security.User
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_recettesV2_user_form_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/user/_form.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
expressionOut.print(hasErrors(bean: userInstance, field: 'fullName', 'error'))
printHtmlPart(2)
invokeTag('message','g',8,['code':("user.fullName.label"),'default':("Full Name")],-1)
printHtmlPart(3)
invokeTag('textField','g',11,['name':("fullName"),'required':(""),'value':(userInstance?.fullName)],-1)
printHtmlPart(4)
expressionOut.print(hasErrors(bean: userInstance, field: 'username', 'error'))
printHtmlPart(5)
invokeTag('message','g',17,['code':("user.username.label"),'default':("Username")],-1)
printHtmlPart(3)
invokeTag('textField','g',20,['name':("username"),'required':(""),'value':(userInstance?.username)],-1)
printHtmlPart(4)
expressionOut.print(hasErrors(bean: userInstance, field: 'password', 'error'))
printHtmlPart(6)
invokeTag('message','g',26,['code':("user.password.label"),'default':("Password")],-1)
printHtmlPart(3)
invokeTag('passwordField','g',29,['name':("password"),'required':(""),'value':(userInstance?.password)],-1)
printHtmlPart(4)
expressionOut.print(hasErrors(bean: userInstance, field: 'accessLevel', 'error'))
printHtmlPart(6)
invokeTag('message','g',35,['code':("user.accessLevel.label"),'default':("Access Level")],-1)
printHtmlPart(3)
if(true && (defaultAccessLevel.authority == 'ROLE_ADMIN')) {
printHtmlPart(7)
expressionOut.print(defaultAccessLevel.authority)
printHtmlPart(7)
invokeTag('hiddenField','g',40,['name':("accessLevel"),'value':(defaultAccessLevel.authority)],-1)
printHtmlPart(8)
}
else {
printHtmlPart(7)
invokeTag('select','g',45,['name':("accessLevel"),'from':(availableRoles),'value':(defaultAccessLevel.authority),'optionKey':("authority"),'optionValue':("authority"),'required':("")],-1)
printHtmlPart(8)
}
printHtmlPart(9)
expressionOut.print(hasErrors(bean: userInstance, field: 'accountExpired', 'error'))
printHtmlPart(10)
invokeTag('message','g',51,['code':("user.accountExpired.label"),'default':("Account Expired")],-1)
printHtmlPart(11)
invokeTag('checkBox','g',54,['name':("accountExpired"),'value':(userInstance?.accountExpired)],-1)
printHtmlPart(4)
expressionOut.print(hasErrors(bean: userInstance, field: 'accountLocked', 'error'))
printHtmlPart(12)
invokeTag('message','g',60,['code':("user.accountLocked.label"),'default':("Account Locked")],-1)
printHtmlPart(11)
invokeTag('checkBox','g',63,['name':("accountLocked"),'value':(userInstance?.accountLocked)],-1)
printHtmlPart(4)
expressionOut.print(hasErrors(bean: userInstance, field: 'enabled', 'error'))
printHtmlPart(13)
invokeTag('message','g',69,['code':("user.enabled.label"),'default':("Enabled")],-1)
printHtmlPart(11)
invokeTag('checkBox','g',72,['name':("enabled"),'value':(userInstance?.enabled)],-1)
printHtmlPart(4)
expressionOut.print(hasErrors(bean: userInstance, field: 'passwordExpired', 'error'))
printHtmlPart(14)
invokeTag('message','g',78,['code':("user.passwordExpired.label"),'default':("Password Expired")],-1)
printHtmlPart(11)
invokeTag('checkBox','g',81,['name':("passwordExpired"),'value':(userInstance?.passwordExpired)],-1)
printHtmlPart(15)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1395515409664L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
