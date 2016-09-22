<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>

<script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
<script>tinymce.init({ selector:'textarea' });</script>
  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript">

	$(function(){
		
		$("#btnWriteArticle").click(function(){
			
			$.ajax({
				type: "POST",
				url: "${pageContext.request.contextPath}/article",
				beforeSend: function(xhr){
					xhr.setRequestHeader("userId", userId);
					xhr.setRequestHeader("userPwd",userPwd);
				},
				success: function(res){
					
					alert(res.message);
					if(res.status=="100"){
						location.replace("${pageContext.request.contextPath}/");	
					}
					//location.href="${pageContext.request.contextPath}/";
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
			<div class="col-sm-5">
				<input id="userId" type="text" class="form-control" placeholder="아이디" />
			</div>
		  </div>
		  
		  <div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">제목</label>
			<div class="col-sm-8">
			  <input id="userPwd" class="form-control" placeholder="제목을 입력해주세요." type="password"/>
			</div>
		  </div>
		  <textarea>Easy! You should check out MoxieManager!</textarea>
		  <div class="form-group">
			<div class="col-sm-offset-2 col-sm-10 horizontal" >
				<button type="button" class="btn btn-default" id="btnWriteArticle">글쓰기</button>
				<button type="button" class="btn btn-default" id="btnCancleArticle">취소</button>
			</div>
		  </div>
		</form>
	</div>
</ftt:page>