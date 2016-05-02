import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='commentable', version='0.8.1')
class gsp_commentable_commentable_comment_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/commentable-0.8.1/grails-app/views/commentable/_comment.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(comment.id)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('gravatar','avatar',7,['cssClass':("commentAvatar"),'size':("50"),'email':(comment?.poster.email),'gravatarRating':("R"),'defaultGravatarUrl':(createLinkTo(absolute: true, dir:'/images',file:'grails-icon.png'))],-1)
printHtmlPart(3)
})
invokeTag('isAvailable','plugin',9,['name':("avatar")],1)
printHtmlPart(4)
if(true && (noEscape)) {
printHtmlPart(5)
expressionOut.print(comment?.body)
printHtmlPart(6)
}
else {
printHtmlPart(7)
expressionOut.print(comment?.body?.encodeAsHTML())
printHtmlPart(8)
}
printHtmlPart(9)
expressionOut.print(comment?.poster.fullName)
printHtmlPart(10)
invokeTag('formatDate','g',20,['format':("MMM dd, yyyy HH:mm a"),'date':(comment.dateCreated)],-1)
printHtmlPart(11)
createClosureForHtmlPart(12, 1)
invokeTag('link','g',21,['controller':("commentable"),'action':("delete"),'params':([id:comment.id, commentPageURI:request.forwardURI.replaceAll(request.contextPath, '')])],1)
printHtmlPart(13)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1394316608172L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
