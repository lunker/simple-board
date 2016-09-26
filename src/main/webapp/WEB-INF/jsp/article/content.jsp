<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>

<!-- <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
<script>tinymce.init({ selector:'textarea' });</script> -->
  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript">

	$(function(){
		
	});
</script>

<ftt:page>
	<div class="container">
	
		<div class="title">
			${response.data.articleTitle} | 자유게시판<br>
			${response.data.articleUserNickname}
		</div>
		
		<div class="content">
		
			<textarea rows="100" cols="100">
				${response.data.articleContent}
			</textarea>
		</div>
	  
	</div>
</ftt:page>