<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
<script>tinymce.init({ selector:'textarea' });</script> -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript">

	function onKeyDown(){
		if(event.keyCode == 13)
	     {
			$("#btnCommentWrite").click();
	     }
	}
	
	function loadLikesComponent(){

		var resource = "?articleId=" + ${response.data.articleId} + "&userNumId="+ ${userNumId};
		
		$.ajax({
			type: "GET",
			url: "${pageContext.request.contextPath}/page/article/likes"+resource,
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
			success: function(data){
				//console.log(data);
				$("#load_likes").html(data);
			},
			error: function(err){
				alert("에러");
			}
		});		
	}

	function loadCommentComponent(articleId, commentPageNum, commentPrintNum){
		
		console.log(articleId);
		console.log(commentPageNum);
		console.log(commentPrintNum);
		var resource="";
		if(articleId != null && articleId != "" && typeof articleId != "undefined"){
			
		}
		
		if(commentPageNum != null && commentPageNum != "" && typeof commentPageNum != "undefined"){
			var resource = "?commentPageNum=" + commentPageNum + "&commentPrintNum="+commentPrintNum;	
			// load comments on load 
			$.ajax({
				type: "GET",
				url: "${pageContext.request.contextPath}/comment/"+${response.data.articleId}+"/" + resource,
				headers: { 
			        'Accept': 'application/json',
			        'Content-Type': 'application/json' 
			    },
				success: function(data){
					//console.log(data);
					$("#load_comments").html(data);
				},
				error: function(err){
					alert("에러");
				}
			});		
		}

		if(commentPrintNum != null && commentPrintNum != "" && typeof commentPrintNum != "undefined"){
			
		}
		// load comments on load 
		$.ajax({
			type: "GET",
			url: "${pageContext.request.contextPath}/comment/"+${response.data.articleId},
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
			success: function(data){
				//console.log(data);
				$("#load_comments").html(data);
			},
			error: function(err){
				alert("에러");
			}
		});		
	
		
	}
	
	/*
		댓글 수정 toggle
	*/
	function toggleComment(commentId){
		//alert('adf');
		//console.log('<c:out value="${test}"/>');
		//$('#comment-modify-content'+commentId).val();
		$('.comment-content'+commentId).toggle();
		
		//${response.data[commentId].commentContent}
//		$('#comment-modify-content'+commentId).val(${response.data[commentId].commentContent});
	}
	

	
	function deleteComment(articleId, commentId){
		
		if(confirm("삭제하시겠습니까?") == false){
			return ;
		}
		
		var resource = "?articleId="+articleId + "&commentId="+commentId;
		
		$.ajax({
			type: "DELETE",
			url: "${pageContext.request.contextPath}/comment" + resource,
			headers: { 
		        'Accept': 'application/json'
		    },
			success: function(res){
				//$("#load_comments").html(data);
				
				if(res.status=="200"){
					console.log("댓글 삭제 성공");
					
					// 댓글 목록 업데이트 
					loadCommentComponent();
					
					// input clear
					$("#commentContent").val("");
				}
			},
			error: function(err){
				alert("error");
			}
		});
	}// end func
	
	$(function() {
		loadLikesComponent(); // 초기 좋아요 로드
		loadCommentComponent(); // 초기 댓글 로드
		
		$("#btnCommentWrite").click(function(){
			
				var commentContent = $("#commentContent").val();
				
				if(commentContent == ""){
					alert("내용을 입력하세요.");
					return ;
				}
			
				var commentInfo = {
					"articleId": ${response.data.articleId},
					"commentUserNumId": ${userNumId},
					"commentContent": commentContent 
				};
				
				$.ajax({
					type: "POST",
					url: "${pageContext.request.contextPath}/comment",
					headers: { 
				        'Accept': 'application/json',
				        'Content-Type': 'application/json' 
				    },
					data: JSON.stringify(commentInfo),
					success: function(res){
						//$("#load_comments").html(data);
						
						if(res.status=="200"){
							console.log("댓글 작성 성공");
							
							// 댓글 목록 업데이트 
							loadCommentComponent();
							
							// input clear
							$("#commentContent").val("");
						}
					},
					error: function(err){
						alert("error");
					}
				});
		});
		
		
		$("#btnArticleModify").click(function(){
			location.href="${pageContext.request.contextPath}/page/article/modify/"+${response.data.articleId};
		});
		
		$("#btnArticleDelete").click(function(){
			
			if(confirm("삭제하시겠습니까?") == false){
				
				return ;
			}
			
			$.ajax({
				type: "DELETE",
				url: "${pageContext.request.contextPath}/article/"+${response.data.articleId},
				headers: { 
			        'Accept': 'application/json',
			        'Content-Type': 'application/json' 
			    },
				success: function(res){
					//$("#load_comments").html(data);
					
					if(res.status=="200"){
						alert(res.message);
						location.href="${pageContext.request.contextPath}/page/board";
					}
				},
				error: function(err){
					alert("error");
				}
			});
		});
		
		$("#btnArticleList").click(function(){
			location.href="${pageContext.request.contextPath}/page/board/" + ${response.data.boardId};
		});
	});
</script>

<ftt:page>
	<div class="container">
		<hr>
		<div class="col-lg-12">
		
			<div class="content-title col-lg-3 ">${response.data.articleTitle} | 자유게시판</div>
			<div class="col-lg-3 col-lg-offset-5">
				<fmt:formatDate pattern="yyyy-MM-dd H:m"
					value="${response.data.articleRegDt}" />
			</div>
			<div class="col-lg-5">${response.data.articleUserNickname}</div>
		</div>

		<div class="content-body col-lg-12">
			<textarea rows="20" cols="100" disabled="disabled">${response.data.articleContent}</textarea>
		</div>

		<div class="content-footer col-lg-12">
			조회수 ${response.data.articleHits}   
			
			<div id="load_likes"></div>
		</div>
		
		<div class="content-footer col-lg-12">
			<div id="load_comments"></div>
			<input id="commentContent" class="form-control" type="text" placeholder="내용을 입력해주세요" onKeyDown="onKeyDown()"/> <button type="button" class="btn btn-default" id="btnCommentWrite">등록</button>
		</div>
		<hr>
		
		<div class="content-footer col-lg-12">
			<c:if test="${response.data.articleUserNumId == userNumId}">
				<button type="button" class="btn btn-default" id="btnArticleModify">수정</button>
				<button type="button" class="btn btn-default" id="btnArticleDelete">삭제</button>
			</c:if>
			<button type="button" class="btn btn-default" id="btnArticleList">목록</button>
		</div>
		
	</div>
</ftt:page>