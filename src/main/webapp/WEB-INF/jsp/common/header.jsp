<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>


<script type="text/javascript">
	$(function(){
		$("#userField").click(function(){
			location.href="${pageContext.request.contextPath}/page/mypage";
		});
		
		$("#btnLoginPage").click(function(){
			//alert("로그인 페이지 call");
			//alert("${pageContext.request.contextPath}");
			location.href="${pageContext.request.contextPath}/page/login";
		});
		
		$("#title").click(function(){
			location.href="${pageContext.request.contextPath}/";
		});
		
		$("#btnLogout").click(function(){
			$.ajax({
				type:"GET",
				url: "${pageContext.request.contextPath}/user/logout",
				success: function(data){
					
					//location.reload(true);
					location.replace("${pageContext.request.contextPath}/");
				},
				error: function(err){
					alert("시스템 에러. 관리자에게 문의하세요.");
				}
			});
		});
	});
</script>

<div class="navbar">
	<div class="container">
		<div class="navbar-left">
			<div class="clickable" id="title">
				<h4>Home</h4>
			</div>
		</div>
		<div class="navbar-right">
			<c:choose>
				<c:when test="${logined == true }">
					<p id=userField class="clickable"> ${userNickname}(${userId}) </p> <button id="btnLogout" class="btn btn-default"type="submit">  로그아웃</button>
				</c:when>
				<c:otherwise> 
					<button id="btnLoginPage" class="btn btn-default" type="submit">로그인</button>
				</c:otherwise>
			</c:choose>
		</div>
		
	</div>
</div>
