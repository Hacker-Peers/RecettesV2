<div id="comment${comment.id}" class="comment">
    <hr/>	
	<plugin:isAvailable name="avatar">
		<div class="avatar">	
			<avatar:gravatar cssClass="commentAvatar" size="50"
			        email="${comment?.poster.email}" gravatarRating="R"
			        defaultGravatarUrl="${createLinkTo(absolute: true, dir:'/images',file:'grails-icon.png')}"/>
		</div>			
	</plugin:isAvailable>
	
	<div class='commentBody'>
		<g:if test="${noEscape}">
			${comment?.body}
		</g:if>
		<g:else>
        	${comment?.body?.encodeAsHTML()}				
		</g:else>
	</div>
	<div class="commentDetails">
		<i>By ${comment?.poster.fullName}, on <g:formatDate format="MMM dd, yyyy HH:mm a" date="${comment.dateCreated}"/></i>
		<g:link controller='commentable' action='delete' params='[id:comment.id, commentPageURI:request.forwardURI.replaceAll(request.contextPath, '')]'>remove</g:link>
	</div>
</div>
