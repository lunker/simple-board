<%@ tag language="java" pageEncoding="UTF-8" body-content="tagdependent"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:eval expression="@environment.getProperty('noticeExt.domain.static')" var="staticDomain" />
<!DOCTYPE html>
<%@ attribute name="head_area" fragment="true" %>
<%@attribute name="body_area" fragment="true" %>

<html lang="ko">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />

<title>Sample Web Application</title>

<ftt:defaultCss />
<ftt:defaultJs />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

</head>
<body class="skin-black">
	<hr>
	<center>
	
		<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
				
		<jsp:doBody />
		
	</center>
	<hr>
</body>
</html>