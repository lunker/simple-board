<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>

<ftt:page>
	<div>CodeList</div>
	<table border="1">
		<tr>
			<td>Code No</td>
			<td>Code Name</td>
			<td>Short Code</td>
			<td>Category No</td>
		</tr>
		<c:if test="${codeList != null && codeList.size() > 0}">
			<c:forEach var="code" items="${codeList}" varStatus="status">
				<tr>
					<td>${code.codeNo}</td>
					<td>${code.codeName}</td>
					<td>${code.shortCode}</td>
					<td>${code.categoryNo}</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${codeList == null || codeList.size() == 0}">
			<tr>
				<td colspan="4">코드를 찾을 수 없습니다.</td>
			</tr>
		</c:if>
	</table>
</ftt:page>