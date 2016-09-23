<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript">

	$(function(){
		$("#btnLogin").click(function(){
			console.log("로그인");
			
			var userId = $("#userId").val();
			var userPwd = $("#userPwd").val();
			
			if(userId == ""){
				alert("아이디를 입력하세요.");
				return ;
			}
			
			if(userPwd == ""){
				alert("비밀번호를 입력하세요.");
				return ; 
			}
			
			if(userPwd.includes(" ") || userPwd.includes("\t")){
				alert("공백을 제외하고 입력하세요.");
				return ;
			}
			console.log("${pageContext.request.contextPath}");
			$.ajax({
				type: "GET",
				url: "${pageContext.request.contextPath}/user",
				beforeSend: function(xhr){
					xhr.setRequestHeader("userId", userId);
					xhr.setRequestHeader("userPwd",userPwd);
				},
				success: function(res){
					
					alert(res.message);
					if(res.status=="200"){
						location.replace("${pageContext.request.contextPath}/");	
					}
					//location.href="${pageContext.request.contextPath}/";
				},
				error: function(data){
					alert("시스템 에러");
				}
			});
		});
		
		$("#btnSignup").click(function(){
			console.log("회원가입");
			location.href="${pageContext.request.contextPath}/page/signup";
		});
	});
</script>
<ftt:page>
	<div class="container">
		<h1>로그인</h1>
		<hr>
		<form class="form-horizontal">
		  <div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">아이디</label>
			<div class="col-sm-5">
				<input id="userId" type="text" class="form-control" placeholder="아이디" />
			</div>
		  </div>
		  
		  <div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">비밀번호</label>
			<div class="col-sm-5">
			  <input id="userPwd" class="form-control" placeholder="비밀번호" type="password"/>
			</div>
		  </div>
		  
		  <div class="form-group">
			<div class="col-sm-offset-2 col-sm-10 horizontal" >
				<button type="button" class="btn btn-default" id="btnLogin">로그인</button>
				<button type="button" class="btn btn-default" id="btnSignup">회원가입</button>
			</div>
		  </div>
		</form>
	</div>
</ftt:page>