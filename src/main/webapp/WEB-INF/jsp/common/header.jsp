<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>


<script type="text/javascript">
	$(function(){
		$("#btnLoginPage").click(function(){
			alert("로그인 페이지 call");
			alert("${pageContext.request.contextPath}");
			location.href="${pageContext.request.contextPath}/page/login";
		});
	});
</script>

<div>
	<c:choose>
		<c:when test="${logined == true }">
			${userNickname}(${userId}) <button id="btnLoginPage" type="submit">  로그아웃</button>
		</c:when>
		<c:otherwise> 
			<button id="btnLoginPage" type="submit">로그인</button>
		</c:otherwise>
	</c:choose>

</div>
