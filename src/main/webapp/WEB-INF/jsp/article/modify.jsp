<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>

<script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
<script>tinymce.init({ selector:'textarea' });</script>
  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript">

	$(function(){
		
		$("#btnModify").click(function(){
			
		
			
			var articleTitle = $("#articleTitle").val();
			var articleContent = tinyMCE.activeEditor.getContent({format: 'text'})
			
			var articleInfo = {
					"articleId": ${articleId},
					"articleUserNumId":${userNumId},
					"articleTitle": articleTitle,
					"articleContent":articleContent
			};
			
			$.ajax({
				type: "PUT",
				url: "${pageContext.request.contextPath}/article",
				headers: { 
			        'Accept': 'application/json',
			        'Content-Type': 'application/json' 
			    },
				data: JSON.stringify(articleInfo),
				success: function(res){
					
					if(res.status == "200" ){
						alert(res.message);
						location.href="${pageContext.request.contextPath}/page/article/"+${article.articleId};
					}
					else{
						alert(res.message);
					}
				},
				error: function(err){
					alert("시스템에러");	
				}
			});// end ajax
			
		});
		
		$("#btnModifyCancel").click(function(){
			location.href="${pageContext.request.contextPath}/page/board";
		});
		
	});
</script>
<ftt:page>
	<div class="container">
		<h1>수정하기</h1>
		<hr>
		
		<form class="form-horizontal">
		  <div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">게시판</label>
			  <div class="col-sm-5">
				자유게시판
			  </div>
		  </div>
		  
		  <div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">제목</label>
			<div class="col-sm-8">
			  <input id="articleTitle" class="form-control" placeholder="제목을 입력해주세요." type="text" value='<c:out value="${article.articleTitle}"/>'/>
			</div>
		  </div>
		  <div class="form-group">
			  <div style="margin: 0 auto; width: 900px;">
				<textarea id="articleContent">${article.articleContent}</textarea>  
			  </div>
		  </div>
		  
		  <div class="form-group">
			<div class="col-sm-offset-2 col-sm-10 horizontal" >
				<button type="button" class="btn btn-default" id="btnModify">수정하기</button>
				<button type="button" class="btn btn-default" id="btnModifyCancel">취소</button>
			</div>
		  </div>
		</form>
		
	</div>
</ftt:page>