<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript">

	// 게시판 오픈
	function openBoard(boardId){
		location.href="${pageContext.request.contextPath}/page/board?boardId="+boardId;
		return;
	}

	$(function() {
					
	});
</script>

<div>
	<ul class="comment-list">
		<c:if test="${response.data != null}">
			<c:forEach items="${response.data}" var="row" varStatus="status">
			<li style="float: left; margin-right: 5px;">
				<a onclick='openBoard(${row.boardId})'>${row.boardName}</a>
			</li>
			</c:forEach>
		</c:if>
	</ul>
</div>