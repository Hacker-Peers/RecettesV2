import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='commentable', version='0.8.1')
class gsp_commentable_commentable_comments_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/commentable-0.8.1/grails-app/views/commentable/_comments.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
invokeTag('set','g',1,['var':("comments"),'value':(commentable.comments)],-1)
printHtmlPart(0)
invokeTag('render','g',7,['template':("/commentable/comment"),'collection':(comments),'var':("comment"),'plugin':("commentable"),'model':([noEscape:noEscape])],-1)
printHtmlPart(1)
invokeTag('message','g',12,['code':("comment.add.title"),'default':("Post a Comment")],-1)
printHtmlPart(2)
invokeTag('message','g',17,['code':("comment.add.description"),'default':("")],-1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
invokeTag('richEditor','gui',22,['id':("commentBody"),'name':("comment.body"),'value':(""),'width':("100%")],-1)
printHtmlPart(4)
})
invokeTag('isAvailable','plugin',23,['name':("grails-ui")],2)
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
invokeTag('textArea','g',25,['id':("commentBody"),'name':("comment.body")],-1)
printHtmlPart(6)
})
invokeTag('isNotAvailable','plugin',26,['name':("grails-ui")],2)
printHtmlPart(4)
invokeTag('hiddenField','g',27,['name':("update"),'value':("comments")],-1)
printHtmlPart(7)
invokeTag('hiddenField','g',28,['name':("commentLink.commentRef"),'value':(commentable.id)],-1)
printHtmlPart(4)
invokeTag('hiddenField','g',29,['name':("commentLink.type"),'value':(commentable.class.name)],-1)
printHtmlPart(8)
expressionOut.print(request.URI)
printHtmlPart(4)
invokeTag('hiddenField','g',31,['name':("commentPageURI"),'value':("/${request.forwardURI.replaceAll(request.contextPath + '/', '')}")],-1)
printHtmlPart(4)
invokeTag('submitButton','g',33,['name':(g.message(code:'comment.post.button.name', 
											 'default':'Post'))],-1)
printHtmlPart(9)
})
invokeTag('formRemote','g',34,['name':("addCommentForm"),'url':([controller:'commentable',action:'add']),'update':("comments")],1)
printHtmlPart(10)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1394316090304L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
