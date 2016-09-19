<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:eval expression="@environment.getProperty('noticeExt.domain.static')" var="staticDomain" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />

<title>Sample Web Application</title>

<ftt:defaultCss />
<ftt:defaultJs />

</head>
<body class="skin-black">
	<hr>
	<center>
		<jsp:doBody /><!-- 몸체로 전달받은 내용을 그대로 출력 -->
	</center>
	<hr>
</body>
</html>