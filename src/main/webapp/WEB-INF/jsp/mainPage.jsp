<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>

<ftt:page>



	<c:choose>
		<c:when test="${logined == true }">
			게시판 메인 페이지
			<jsp:include page="/WEB-INF/jsp/board.jsp"></jsp:include>
			
		</c:when>
		<c:otherwise>
			게시판 메인 페이지 <br>
			로그인 하세요 
		</c:otherwise>
	</c:choose>
</ftt:page>

