<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:eval expression="@environment.getProperty('ftt.domain.static')" var="staticDomain"/>

<link href="//${staticDomain}/external/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="//${staticDomain}/external/bootstrap/css/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />

<link href="${pageContext.request.contextPath}/static/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/static/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/static/js/bootstrap.min.js" rel="stylesheet" type="text/css" />

