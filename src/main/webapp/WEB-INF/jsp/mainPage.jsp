<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>

<ftt:page>
	
	<c:choose>
		<c:when test="${logined == true }">
			게시판 메인 페이지
		</c:when>
		<c:otherwise> 
			로그인 하세요 
		</c:otherwise>
	</c:choose>
</ftt:page>
