<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>

<ftt:page>


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
			console.log("${pageContext.request.contextPath}");
			$.ajax({
				type: "GET",
				url: "${pageContext.request.contextPath}/user",
				beforeSend: function(xhr){
					xhr.setRequestHeader("userId", userId);
					xhr.setRequestHeader("userPwd",userPwd);
				},
				success: function(data){
					console.log("로그인성공");
					
					location.href="${pageContext.request.contextPath}/";
				},
				error: function(data){
					console.log("로그인실패");
				}
			});
		});
		
		$("#btnSignup").click(function(){
			console.log("회원가입");
			location.href="${pageContext.request.contextPath}/page/signup";
		});
	});
</script>

<div>
	<br>
	login page
	<br>
	<br>
	<br>
	 
	id : <input id="userId" type="text" name="id" /><br>
	password : <input id="userPwd" type="password" name="password"/> <br>
	
	<button id="btnLogin">로그인</button>
	<button id="btnSignup">회원가입</button>
</div>

</ftt:page>