<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		
		
			
	});
</script>

<div>
	<ul class="comment-list">
		<c:if test="${response.data != null}">
			<c:forEach items="${response.data}" var="row">
			<li>
				<div class="comment-item"> 
					<div class="comment-header">
						<span class="comment-header-user">${row.commentUserNickname}</span>  

						<span class="comment-header-date"><fmt:formatDate value="${row.commentRegDt}" pattern="yyyy-MM-dd H:m"/></span>
						
						<c:if test="${row.commentUserNumId == userNumId}">
							<span class="comment-header-tool"> <a >수정</a> | <a onclick="deleteComment(${row.articleId}, ${row.commentId})">삭제</a> </span>
						</c:if>
					</div>
					
					<div class="comment-content"> 
						${row.commentContent}
					</div>
								
				</div>
				<hr>
			</li>
			</c:forEach>
		</c:if>
	</ul>
</div>