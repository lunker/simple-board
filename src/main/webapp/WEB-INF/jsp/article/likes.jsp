<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.0/themes/smoothness/jquery-ui.css">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-T8Gy5hrqNKT+hzMclPo118YTQO6cYprQmhrYwIiQ/3axmI1hQomh7Ud2hPOy8SP1" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js" integrity="sha256-eGE6blurk5sHj+rmkfsGYeKyZx3M4bG+ZlFyA7Kns7E=" crossorigin="anonymous"></script>

<script type="text/javascript">
	
	function doLike(){

		var likeInfo = {
				"articleId": ${result.articleId},
				"likeUserNumId": ${userNumId}
		}
		
		$.ajax({
			type: "POST",
			url: "${pageContext.request.contextPath}/like/do",
			data: JSON.stringify(likeInfo),
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
			success: function(data){
				console.log(data.message);
				loadLikesComponent();
			},
			error: function(err){
				alert("에러");
			}
		});		
	}
	
	function undoLike(){
		
		var resource = "?articleId=" + ${result.articleId} + "&likeUserNumId="+${userNumId};
		
		var likeInfo = {
				"articleId": ${result.articleId},
				"likeUserNumId": ${userNumId}
		}
		
		$.ajax({
			type: "POST",
			url: "${pageContext.request.contextPath}/like/undo",
			data: JSON.stringify(likeInfo),
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
			success: function(data){
				console.log(data.message);
				loadLikesComponent();
			},
			error: function(err){
				alert("에러");
			}
		});		
	}
	
	$(function() {
	
	});
</script>

<div>
	좋아요 ${result.likeCount } 
	
	<c:choose>
		<c:when test="${result.isLiked == true}">
			<i class="fa fa-heart" aria-hidden="true" onclick="undoLike()"></i>
		</c:when>
		<c:otherwise>
			<i class="fa fa-heart-o" aria-hidden="true" onclick="doLike()"></i>	
		</c:otherwise>
	</c:choose>
	
</div>