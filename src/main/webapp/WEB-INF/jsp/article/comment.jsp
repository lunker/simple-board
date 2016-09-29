<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript">

	function expandList(articleId, commentPageNum, commentPrintNum){
		loadCommentComponent(articleId, commentPageNum, commentPrintNum);
	}
	
	function modifyComment(commentId, index){
		
		var commentContent = $('#comment-modify-content'+index).val();
		
		var resource = "?commentId="+commentId+"&commentContent="+commentContent;
		
		$.ajax({
			type: "PUT",
			url: "${pageContext.request.contextPath}/comment"+resource,
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
			success: function(data){
				console.log(data.message);
				loadCommentComponent();
			},
			error: function(err){
				alert("에러");
			}
		});		
	}

	$(function() {
					
	});
</script>

<div>

	댓글 ${response.data.count}
	테스트 ${response.data.count}
	
	<ul class="comment-list">
		<c:if test="${response.data.comments != null}">

			<c:choose>
				<c:when test="${(pagingInfo.commentPageNum+1) * pagingInfo.commentPrintNum > response.data.count}">
					<c:set value="${response.data.count}" var="printLimit"/>
				</c:when>
				<c:otherwise>
					<c:set value="${(pagingInfo.commentPageNum+1) * pagingInfo.commentPrintNum}" var="printLimit"/>
				</c:otherwise>
			</c:choose>
			
			<c:forEach items="${response.data.comments}" var="row" varStatus="status" begin="0" end="${printLimit}">
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
	
				<c:if test="${response.data.count > printLimit }">
					<li>
						<div class="comment-item" style="text-align: center;"> 
							<a onclick="expandList(${articleId}, ${pagingInfo.commentPageNum+1}, ${pagingInfo.commentPrintNum})">더보기</a>										
						</div>
						<hr>
					</li>
				</c:if>
		</c:if>
	</ul>
</div>