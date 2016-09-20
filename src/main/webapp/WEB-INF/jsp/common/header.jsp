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
		
		$("#btnLogout").click(function(){
			$.ajax({
				type:"GET",
				url: "${pageContext.request.contextPath}/user/logout",
				success: function(data){
					console.log("로그아웃 성공");
					location.reload(true);
				},
				error: function(err){
					console.log("로그아웃 실패");
				}
			});
		});
	});
</script>

<div>
	<c:choose>
		<c:when test="${logined == true }">
			<p id=userField class="clickable"> ${userNickname}(${userId}) </p> <button id="btnLogout" type="submit">  로그아웃</button>
		</c:when>
		<c:otherwise> 
			<button id="btnLoginPage" type="submit">로그인</button>
		</c:otherwise>
	</c:choose>

</div>
