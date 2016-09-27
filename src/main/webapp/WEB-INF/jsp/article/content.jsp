<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
<script>tinymce.init({ selector:'textarea' });</script> -->

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		
		$("#btnArticleList").click(function(){
			location.href="${pageContext.request.contextPath}/page/board";
		});

	});
</script>

<ftt:page>
	<div class="container">
		<div class="col-lg-12">
			<div class="content-title col-lg-3 ">${response.data.articleTitle} | 자유게시판</div>
			<div class="col-lg-3 col-lg-offset-5">
				<fmt:formatDate pattern="yyyy-MM-dd H:m"
					value="${response.data.articleRegDt}" />
			</div>
			<div class="col-lg-5">${response.data.articleUserNickname}</div>
		</div>

		<div class="content-body col-lg-12">
			<textarea rows="20" cols="100">
				${response.data.articleContent}
			</textarea>
		</div>

		<div class="content-footer col-lg-12">
			댓글 ${response.data.articleComments}  조회수 ${response.data.articleHits}   좋아요 ${response.data.articleLikes}
		</div>
		<div class="content-footer col-lg-12">
			<input id="userContactNum" class="form-control" type="text" placeholder="내용을 입력해주세요"/> <button type="button" class="btn btn-default" id="btnCommentWrite">등록</button>
		</div>
		<hr>
		
		<div class="content-footer col-lg-12">
			<c:if test="${response.data.articleUserNumId == userNumId}">
				<button type="button" class="btn btn-default" id="btnCommentWrite">수정</button>
			</c:if>
			<button type="button" class="btn btn-default" id="btnArticleList">목록</button>
		</div>
		
	</div>
</ftt:page>