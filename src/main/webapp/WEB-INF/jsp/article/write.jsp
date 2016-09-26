<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>

<script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
<script>tinymce.init({ selector:'textarea' });</script>
  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript">

	$(function(){
		
		$("#btnWriteArticle").click(function(){
			
			var articleContent = tinyMCE.activeEditor.getContent({format: 'text'})
			var articleTitle = $("#articleTitle").val();
			
			var boardId = 1; // tmp
			
			var articleData = {
				"boardId" :		boardId,
				"articleUserNumId": ${userNumId},
				"articleContent" : articleContent,
				"articleTitle": articleTitle
			};
			
			$.ajax({
				type: "POST",
				url: "${pageContext.request.contextPath}/article",
				headers: { 
			        'Accept': 'application/json',
			        'Content-Type': 'application/json' 
			    },
			    data: JSON.stringify(articleData),
				success: function(res){
					alert(res.message);
					if(res.status=="200"){
						// 게시판 메인 페이지로 이동 
						location.replace("${pageContext.request.contextPath}/");	
					}
				},
				error: function(data){
					alert("시스템 에러");
				}
			});
		});
		
		$("#btnCancelArticle").click(function(){
			if(confirm("정말 나가시겠습니까?") == true){
				location.href="${pageContext.request.contextPath}/";
			}
		});
		
	});
</script>
<ftt:page>
	<div class="container">
		<h1>글쓰기</h1>
		<hr>
		<form class="form-horizontal">
		  <div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">게시판</label>
			
		<!-- 	<div class="col-sm-5">
				<input id="userId" type="text" class="form-control" placeholder="아이디" />
			</div>
		  -->
		  <div class="col-sm-5">
			자유게시판
		  </div>
		  
		   
		  </div>
		  
		  <div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">제목</label>
			<div class="col-sm-8">
			  <input id="articleTitle" class="form-control" placeholder="제목을 입력해주세요." type="text"/>
			</div>
		  </div>
		  <div class="form-group">
			  <div style="margin: 0 auto; width: 900px;">
				<textarea id="articleContent">내용을 입력하세요.</textarea>  
			  </div>
		  </div>
		  
		  <div class="form-group">
			<div class="col-sm-offset-2 col-sm-10 horizontal" >
				<button type="button" class="btn btn-default" id="btnWriteArticle">글쓰기</button>
				<button type="button" class="btn btn-default" id="btnCancelArticle">취소</button>
			</div>
		  </div>
		</form>
	</div>
</ftt:page>