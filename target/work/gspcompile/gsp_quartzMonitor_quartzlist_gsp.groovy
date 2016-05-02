import org.quartz.Trigger
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

@GrailsPlugin(name='quartz-monitor', version='0.3-RC3')
class gsp_quartzMonitor_quartzlist_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/plugins/quartz-monitor-0.3-RC3/grails-app/views/quartz/list.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'http-equiv':("Content-Type"),'content':("text/html; charset=UTF-8")],-1)
printHtmlPart(1)
invokeTag('set','g',5,['var':("layoutName"),'value':(grailsApplication.config.quartz?.monitor?.layout)],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':(layoutName ?: 'main')],-1)
printHtmlPart(1)
createTagBody(2, {->
createClosureForHtmlPart(2, 3)
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(3)
expressionOut.print(resource(dir: 'css', file: 'quartz-monitor.css', plugin: 'quartz-monitor'))
printHtmlPart(4)
expressionOut.print(resource(dir: 'css', file: 'jquery.countdown.css', plugin: 'quartz-monitor'))
printHtmlPart(4)
expressionOut.print(resource(dir: 'css', file: 'jquery.clock.css', plugin: 'quartz-monitor'))
printHtmlPart(5)
})
invokeTag('captureHead','sitemesh',11,[:],1)
printHtmlPart(6)
createTagBody(1, {->
printHtmlPart(7)
if(true && (scheduler.isInStandbyMode())) {
printHtmlPart(8)
invokeTag('createLink','g',17,['action':("startScheduler")],-1)
printHtmlPart(9)
invokeTag('resource','g',17,['dir':("images"),'file':("play-all.png"),'plugin':("quartz-monitor")],-1)
printHtmlPart(10)
}
else {
printHtmlPart(8)
invokeTag('createLink','g',20,['action':("stopScheduler")],-1)
printHtmlPart(11)
invokeTag('resource','g',20,['dir':("images"),'file':("pause-all.png"),'plugin':("quartz-monitor")],-1)
printHtmlPart(10)
}
printHtmlPart(12)
if(true && (flash.message)) {
printHtmlPart(13)
expressionOut.print(flash.message)
printHtmlPart(14)
}
printHtmlPart(15)
expressionOut.print(now.time)
printHtmlPart(16)
expressionOut.print(now)
printHtmlPart(17)
if(true && (grailsApplication.config.quartz.monitor.showTriggerNames)) {
printHtmlPart(18)
}
printHtmlPart(19)
loop:{
int i = 0
for( job in (jobs) ) {
printHtmlPart(20)
expressionOut.print((i % 2) == 0 ? 'odd' : 'even')
printHtmlPart(21)
expressionOut.print(job.name)
printHtmlPart(22)
if(true && (grailsApplication.config.quartz.monitor.showTriggerNames)) {
printHtmlPart(23)
expressionOut.print(job.trigger?.name)
printHtmlPart(22)
}
printHtmlPart(24)
createTagBody(3, {->
expressionOut.print(job.duration >= 0 ? "Job ran in: " + job.duration + "ms" : (job.error ? "Job threw exception: " + job.error : ""))
})
invokeTag('set','g',50,['var':("tooltip")],3)
printHtmlPart(25)
expressionOut.print(job.status?:"not-run")
printHtmlPart(26)
expressionOut.print(tooltip)
printHtmlPart(27)
expressionOut.print(job.lastRun)
printHtmlPart(28)
expressionOut.print(tooltip)
printHtmlPart(22)
if(true && (scheduler.isInStandbyMode() || job.triggerStatus == Trigger.TriggerState.PAUSED)) {
printHtmlPart(29)
}
else {
printHtmlPart(30)
expressionOut.print(job.trigger?.nextFireTime?.time ?: "")
printHtmlPart(27)
expressionOut.print(job.trigger?.nextFireTime)
printHtmlPart(22)
}
printHtmlPart(31)
if(true && (job.status != 'running')) {
printHtmlPart(32)
if(true && (job.trigger)) {
printHtmlPart(33)
invokeTag('createLink','g',62,['action':("stop"),'params':([jobName:job.name, triggerName:job.trigger.name, triggerGroup:job.trigger.group])],-1)
printHtmlPart(34)
invokeTag('resource','g',62,['dir':("images"),'file':("stop.png"),'plugin':("quartz-monitor")],-1)
printHtmlPart(35)
if(true && (job.triggerStatus == Trigger.TriggerState.PAUSED)) {
printHtmlPart(36)
invokeTag('createLink','g',64,['action':("resume"),'params':([jobName:job.name, jobGroup:job.group])],-1)
printHtmlPart(37)
invokeTag('resource','g',64,['dir':("images"),'file':("resume.png"),'plugin':("quartz-monitor")],-1)
printHtmlPart(35)
}
else if(true && (job.trigger.mayFireAgain())) {
printHtmlPart(36)
invokeTag('createLink','g',67,['action':("pause"),'params':([jobName:job.name, jobGroup:job.group])],-1)
printHtmlPart(38)
invokeTag('resource','g',67,['dir':("images"),'file':("pause.png"),'plugin':("quartz-monitor")],-1)
printHtmlPart(35)
}
printHtmlPart(32)
}
else {
printHtmlPart(33)
invokeTag('createLink','g',71,['action':("start"),'params':([jobName:job.name, jobGroup:job.group])],-1)
printHtmlPart(39)
invokeTag('resource','g',71,['dir':("images"),'file':("start.png"),'plugin':("quartz-monitor")],-1)
printHtmlPart(40)
}
printHtmlPart(41)
invokeTag('createLink','g',73,['action':("runNow"),'params':([jobName:job.name, jobGroup:job.group])],-1)
printHtmlPart(42)
invokeTag('resource','g',73,['dir':("images"),'file':("run.png"),'plugin':("quartz-monitor")],-1)
printHtmlPart(40)
if(true && (job.trigger instanceof org.quartz.CronTrigger)) {
printHtmlPart(33)
invokeTag('createLink','g',75,['action':("editCronTrigger"),'params':([triggerName:job.trigger.name, triggerGroup:job.trigger.group])],-1)
printHtmlPart(43)
invokeTag('resource','g',75,['dir':("images"),'file':("reschedule.png"),'plugin':("quartz-monitor")],-1)
printHtmlPart(40)
}
printHtmlPart(44)
}
printHtmlPart(45)
i++
}
}
printHtmlPart(46)
if(!(true && (grailsApplication.config.quartz.monitor.showCountdown == false))) {
printHtmlPart(47)
invokeTag('javascript','g',86,['src':("jquery.countdown.js"),'plugin':("quartz-monitor")],-1)
printHtmlPart(47)
invokeTag('javascript','g',87,['src':("jquery.color.js"),'plugin':("quartz-monitor")],-1)
printHtmlPart(1)
}
printHtmlPart(1)
if(!(true && (grailsApplication.config.quartz.monitor.showTickingClock == false))) {
printHtmlPart(47)
invokeTag('javascript','g',90,['src':("jquery.clock.js"),'plugin':("quartz-monitor")],-1)
printHtmlPart(1)
}
printHtmlPart(1)
invokeTag('javascript','g',92,['src':("quartz-monitor.js"),'plugin':("quartz-monitor")],-1)
printHtmlPart(6)
})
invokeTag('captureBody','sitemesh',93,[:],1)
printHtmlPart(48)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1395516953656L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
