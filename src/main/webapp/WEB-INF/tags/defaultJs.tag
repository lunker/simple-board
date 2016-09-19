<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:eval expression="@environment.getProperty('ftt.domain.static')" var="staticDomain"/>

<script src="//${staticDomain}/external/jquery/jquery.min.js" type="text/javascript"></script> 
<script src="//${staticDomain}/external/bootstrap/js/bootstrap.min.js" type="text/javascript"></script> 

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

<!-- pageContext.request.contextPath는 application.properites의 server.context-path 설정 값  -->
<script src="${pageContext.request.contextPath}/static/sample.js" type="text/javascript"></script> 