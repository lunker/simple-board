<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>

<ftt:page>

<div class="container">
	<c:choose>
		<c:when test="${logined == true }">
			<%-- <jsp:include page="/WEB-INF/jsp/board.jsp"></jsp:include> --%>
			<%-- <%@include file="/WEB-INF/jsp/board.jsp"%> --%>
						
			<!-- <script type="text/javascript">
				location.href="${pageContext.request.contextPath}/page/board";
			</script>
			 -->
		</c:when>
		<c:otherwise>
			<h1>Simple Board</h1>
			<hr>
			로그인 하세요 
		</c:otherwise>
	</c:choose>
</div>
</ftt:page>

