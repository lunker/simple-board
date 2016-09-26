<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script type="text/javascript">


	
	/*	게시글 뷰 페이지 오픈 */
	function openArticle(articleId){
		alert(articleId);
		
		/*
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
	}
	
	/*	게시판 paging */
	function paging(val){
		
		var currentCondition = ${pagingInfo.condition};
		var currentOrder = ${pagingInfo.order};
		var currentPrintNum = ${pagingInfo.printNum};
		var currentPageNum = ${pagingInfo.pageNum}
		
		var defaultPagingInfo = {
				"condition" : currentCondition,
				"order": currentOrder,
				"printNum" : currentPrintNum,
				"pageNum" : currentPageNum
		};
		
		var pagingInfo = Object.assign(defaultPagingInfo,val);
		
		var url = "${pageContext.request.contextPath}/page/board";
		
		url+= "?pageNum=" + pagingInfo.pageNum;
		url+= "&printNum=" + pagingInfo.printNum;
		url+= "&condition=" + pagingInfo.condition;
		url+= "&order=" + pagingInfo.order;
		
		location.href=url;
		return ;
	}
	
	/* JQUERY */
	$(function(){
		
		
		
		$("#selectPrintNum").val("${pagingInfo.printNum}");
		
		$("#pagingS").click(function(){
			// move to first
			paging({pageNum:1});
		});
		
		$("#pagingP").click(function(){

			var endPageNum;
			var maxPageNum;
			var currentPageNum = ${pagingInfo.pageNum};
			var currentPrintNum = ${pagingInfo.printNum};
			var count = ${response.data.count};
			
			if(currentPageNum>=4)
				paging({pageNum: currentPageNum-3});
			
			return ;					
		});
		
		$("#pagingN").click(function(){

			var endPageNum;
			var maxPageNum;
			var currentPageNum = ${pagingInfo.pageNum};
			var currentPrintNum = ${pagingInfo.printNum};
			var count = ${response.data.count};
			
			if(count % currentPrintNum != 0){
				endPageNum = Math.floor(count / currentPrintNum) + 1;
			}
			else{
				endPageNum = Math.floor(count / currentPrintNum);
			}
			maxPageNum = Math.floor(endPageNum / 3) +1;
			
			if(endPageNum - currentPageNum >= 3)
				paging({pageNum: currentPageNum+3});
			return ;			
		});
		
		/* paging: move to end */
		$("#pagingE").click(function(){
			//alert("e" + ${response.data.count});
			
			var endPageNum;
			var currentPageNum = ${pagingInfo.pageNum};
			var currentPrintNum = ${pagingInfo.printNum};
			var count = ${response.data.count};
			
			if(count % currentPrintNum != 0){
				endPageNum = Math.floor(count / currentPrintNum) + 1;
			}
			else{
				endPageNum = Math.floor(count / currentPrintNum);
			}
			
			paging({pageNum: endPageNum});
		});
		
		$("#selectPrintNum").change(function(){
			var selectedVal = $("#selectPrintNum").val();
			
			paging({printNum: selectedVal});
		});
		
		$("#btnWriteArticle").click(function(){
			location.href="${pageContext.request.contextPath}/page/article/write";
		});
	});

</script>

<ftt:page>
<div class="container">
	<h1>자유게시판</h1>
	<hr>
	<div class="col-lg-1 navbar-right" >
		<c:if test="${pagingInfo.printNum}!=null">
			
		</c:if>
		
		<select class="form-control" id="selectPrintNum">
		  <option value="10">10</option>
		  <option value="15">15</option>
		  <option value="20">20</option>
		  <option value="25">25</option>
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
					<c:when test="${ response.data.articles != null}">
						<script>
							console.log(${response.data.count});
						</script>
						<c:forEach items="${response.data.articles}" var="row">
						<tr class="clickable" onclick="openArticle(${row.articleId})">
							<td>${row.articleId}</td>
							<td>${row.articleTitle}</td>
							<td>${row.articleUserNickname}</td>
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
			      <a aria-label="Previous">
			        <span aria-hidden="true" id="pagingS">S</span>
			      </a>
			    </li>
			    <li>
			      <a aria-label="Previous">
			        <span aria-hidden="true" id="pagingP">P</span>
			      </a>
			    </li>
			    
			    <c:choose>
			    	<c:when test="${pagingInfo!=null}">
			    	
			    	<!-- paging 가능 범위 계산  -->
			    		<c:if test="${ (response.data.count - pagingInfo.pageNum*pagingInfo.printNum) > 0}">
			    			<%-- <c:set value="(${response.data.count} - ${pagingInfo.pageNum}*${pagingInfo.printNum})/3" var="limit"/> --%>
			    			<c:set value="${(response.data.count - pagingInfo.pageNum * pagingInfo.printNum)/ pagingInfo.printNum}" var="limit"/>
			    			
			    			<c:if test="${limit>3}">
			    				<c:set value="3" var="limit"></c:set>
			    			</c:if>
			    		</c:if>
			    		
			    		<c:choose>
			    			
			    			<c:when test="${pagingInfo.pageNum ==1 || limit==0}">
			    				
						    	<c:forEach begin="${pagingInfo.pageNum}" end="${pagingInfo.pageNum+limit-1}" varStatus="loop">
						    		
							    		<li><a id="${loop.current}" onclick="paging({pageNum:this.id})">${loop.current}</a></li>
									
						    	</c:forEach>
			    			</c:when>
			    			
			    			<c:otherwise>
			    				
			    				<li><a id="${pagingInfo.pageNum-1}" onclick="paging({pageNum:this.id})">${pagingInfo.pageNum-1}</a></li>
			    				<li><a id="${pagingInfo.pageNum}" onclick="paging({pageNum:this.id})">${pagingInfo.pageNum}</a></li>
			    				<li><a id="${pagingInfo.pageNum+1}" onclick="paging({pageNum:this.id})">${pagingInfo.pageNum+1}</a></li>
			    				<%-- <c:forEach begin="${pagingInfo.pageNum}" end="${pagingInfo.pageNum+limit-1}" varStatus="loop">
						    		
							    		<li><a id="${loop.current}" onclick="paging({pageNum:this.id})">${loop.current}</a></li>
									
						    	</c:forEach> --%>
			    			
			    			</c:otherwise>
			    		</c:choose>
			    		
			    		
			    	</c:when>
			    	
			    	<c:otherwise>
			    	
			    	</c:otherwise>
			    </c:choose>

			    <li>
			      <a aria-label="Next">
			        <span aria-hidden="true" id="pagingN">N</span>
			      </a>
			    </li>
			    <li>
			      <a aria-label="Next">
			        <span aria-hidden="true" id="pagingE">E</span>
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



