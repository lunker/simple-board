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

	댓글 ${response.data.count}
	<ul class="comment-list">
		<c:if test="${response.data.comments != null}">
			<c:forEach items="${response.data.comments}" var="row" varStatus="status">
			<li>
				<div class="comment-item"> 
					<div class="comment-header">
						<span class="comment-header-user">${row.commentUserNickname}</span>  

						<span class="comment-header-date"><fmt:formatDate value="${row.commentRegDt}" pattern="yyyy-MM-dd H:m"/></span>
						
						<c:if test="${row.commentUserNumId == userNumId}">
							<c:set var="isModify" value="false"/>
							
							<c:choose>
								<c:when test="${isModify == true}">
										수정!
								</c:when>
								
								<c:otherwise>
									<span class="comment-header-tool"> 
										<a id='comment-modify-name<c:out value="comment-content${status.index}"/>'onClick="toggleComment(${status.index})"> 수정</a> | <a onclick="deleteComment(${row.articleId}, ${row.commentId})">삭제</a> 
									</span>
								</c:otherwise>
							</c:choose>
							
						</c:if>
					</div>
					
					<div class='comment-content <c:out value="comment-content${status.index}"/>'> 
						${row.commentContent}
					</div>
					
					<div class='comment-content <c:out value="comment-content${status.index}"/>' style="display: none;">
						<input type="text" id='<c:out value="comment-modify-content${status.index}"/>' value='${row.commentContent}'/>
						<a onClick="modifyComment(${row.commentId}, ${status.index})">수정</a>
					</div>
								
				</div>
				<hr>
			</li>
			</c:forEach>
		</c:if>
	</ul>
</div>