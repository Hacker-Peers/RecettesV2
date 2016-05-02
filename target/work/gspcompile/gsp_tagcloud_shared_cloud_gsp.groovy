import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='tagcloud', version='0.3')
class gsp_tagcloud_shared_cloud_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/tagcloud-0.3/grails-app/views/shared/_cloud.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(size.start)
printHtmlPart(1)
expressionOut.print(size.end)
printHtmlPart(2)
expressionOut.print(size.unit)
printHtmlPart(3)
expressionOut.print(color.start)
printHtmlPart(4)
expressionOut.print(color.end)
printHtmlPart(5)
for( entry in (tags) ) {
printHtmlPart(6)
if(true && (!paramName)) {
printHtmlPart(7)
createTagBody(3, {->
expressionOut.print(entry.key)
})
invokeTag('link','g',21,['controller':(controller),'action':(action),'id':(entry.key),'rel':(entry.value),'params':(params)],3)
printHtmlPart(6)
}
else {
printHtmlPart(7)

params."${paramName}" = entry.key
        if (id) params.id = id
		if (resetOffset) {
			params.offset = 0
		}

printHtmlPart(7)
createTagBody(3, {->
expressionOut.print(entry.key)
})
invokeTag('link','g',34,['controller':(controller),'action':(action),'rel':(entry.value),'params':(params)],3)
printHtmlPart(6)
}
printHtmlPart(8)
}
printHtmlPart(9)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1394660837184L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
