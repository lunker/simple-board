<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript">

	var currentPrintNum = 10;
	var currentPageNum = 1;
	var order = "desc"; // 1: desc, 2: asc;	

	
	function openArticle(articleId){
		alert(articleId);
		
		$.ajax({
			type:"GET",
			url: "${pageContext.request.contextPath}/article/list",
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
		    data: data,
			success: function(res){
				alert(res.message);
				if(res.status== "200"){
					console.log(res);
					$("#articleTable").html(res.data);
				}
				else{
					
				}
			},
			error: function(err){
				alert("시스템에러")	
			}
		});
		
	}
	$(function(){
		
		/*
		var data = {
				"condition" : "article_reg_dt",
				"order": "desc",
				"printNum" : currentPrintNum,
				"pageNum" : 1
		}
		$.ajax({
			type:"GET",
			url: "${pageContext.request.contextPath}/article/list",
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
		    data: data,
			success: function(res){
				alert(res.message);
				if(res.status== "200"){
					console.log(res);
					$("#articleTable").html(res.data);
				}
				else{
					
				}
			},
			error: function(err){
				alert("시스템에러")	
			}
		});
		*/
		
		$("#btnWriteArticle").click(function(){
			location.href="${pageContext.request.contextPath}/page/article/write";
		});
	});

</script>

<ftt:page>
<div class="container">
	<h1>자유게시판</h1>
	<hr>
	<div class="col-lg-1 navbar-right">
		<select class="form-control">
		  <option>10</option>
		  <option>15</option>
		  <option>20</option>
		  <option>25</option>
		</select>
	</div>
	<!-- TABLE -->
	<div class="col-lg-12">
		<table class="table table-bordered" id="articleTable">
			<thead>
				<tr>
					<th class="col-lg-1">No</th>
					<th class="col-lg-5">제목</th>
					<th class="col-lg-1">작성자</th>
					<th class="col-lg-1">작성일</th>
					<th class="col-lg-1">조회수</th>
					<th class="col-lg-1">좋아요</th>
				</tr>
			</thead>
			
			<tbody>
				<c:choose>
					<c:when test="${ response.data != null}">
						<c:forEach items="${response.data}" var="row">
						<tr class="clickable" onclick="openArticle(${row.articleId})">
							<td>${row.articleId}</td>
							<td>${row.articleTitle}</td>
							<td>${row.articleUserNumId}</td>
							<td>${row.articleRegDt}</td>
							<td>${row.articleHits}</td>
							<td>${row.articleLikes}</td>
						</tr>
						</c:forEach>
					</c:when>
						
					<c:otherwise>
					
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<!-- TABLE FOOTER -->
		<div class="horizontal">
				<!-- PAGING -->
			<nav aria-label="Page navigation" class="center">
			  <ul class="pagination">
			    <li>
			      <a href="#" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
			    <li><a href="#">1</a></li>
			    <li><a href="#">2</a></li>
			    <li><a href="#">3</a></li>
			    <li>
			      <a href="#" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
			  </ul>
			</nav>
			
			<div class="right">
				<button type="button" class="btn btn-default" id="btnWriteArticle">글쓰기</button>
			</div>	
		</div><!-- END FOOTER -->
	</div>
	
</div>
</ftt:page>



