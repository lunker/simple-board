<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		$("#btnLogin").click(function(){
			alert("로그인 페이지 call");
			/*
			$.ajax({
				type:"GET",
				url:"page/login",
				success:function(args){
					//alert(args);
					$("#main").html(args);
				},
				error:function(e){
					alert("error");
				}
			});
			*/
			
			location.href="page/login";
		});
		
	});
</script>

<ftt:page>

<!-- 
<form action="page/login">
		<button id="btnLogin" type="submit">로그인</button>
	</form>
	
 -->	
 <div id="main">
 	<button id="btnLogin" type="submit">로그인</button>
	main page
 </div>
	
</ftt:page>