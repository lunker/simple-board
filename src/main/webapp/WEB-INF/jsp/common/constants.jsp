<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>


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
