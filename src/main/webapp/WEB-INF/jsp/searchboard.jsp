<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date"/> 


<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.0/themes/smoothness/jquery-ui.css">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-T8Gy5hrqNKT+hzMclPo118YTQO6cYprQmhrYwIiQ/3axmI1hQomh7Ud2hPOy8SP1" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js" integrity="sha256-eGE6blurk5sHj+rmkfsGYeKyZx3M4bG+ZlFyA7Kns7E=" crossorigin="anonymous"></script>


<script type="text/javascript">
	
	var boardId = ${board.data.boardId};
	var searchCondition=0;
	var searchRange=0;
	
	function onSearchInputKeyDown(){
		if(event.keyCode == 13)
	     {
			$("#btnArticleSearch").click();
	     }
	}
	
	function setSearchCondition(id){
		var val = $("#"+id).val();
		searchCondition = val;
		$("#searchCondition").text($("#"+id).text());
	}

	function setSearchRange(id){
		var val = $("#"+id).val();
		searchRange = val;
		$("#searchRange").text($("#"+id).text());
	}
	
	/*	게시글 뷰 페이지 오픈 */
	function openArticle(articleId){
		location.href="${pageContext.request.contextPath}/page/article?boardId="+boardId +"&articleId=" + articleId;
		return ;
	}
	
	/*	게시판 paging */
	function paging(val){
		
		var currentCondition = ${pagingInfo.condition};
		var currentOrder = ${pagingInfo.order};
		var currentPrintNum = ${pagingInfo.printNum};
		var currentPageNum = ${pagingInfo.pageNum};
		var currentSearchCondition = ${searchingInfo.searchCondition};
		var currentSearchRange = ${searchingInfo.searchRange};
		var currentSearchQuery = "${searchingInfo.searchQuery}";
		
		var defaultPagingInfo = {
				"condition" : currentCondition,
				"order": currentOrder,
				"printNum" : currentPrintNum,
				"pageNum" : currentPageNum,
				"searchCondition": currentSearchCondition,
				"searchRange": currentSearchRange,
				"searchQuery":  currentSearchQuery
		};
		
		var pagingInfo = Object.assign(defaultPagingInfo,val);
		
		var url = "${pageContext.request.contextPath}/page/search?boardId="+boardId;
		
		url+= "&pageNum=" + pagingInfo.pageNum;
		url+= "&printNum=" + pagingInfo.printNum;
		url+= "&condition=" + pagingInfo.condition;
		url+= "&order=" + pagingInfo.order;

		url+= "&searchQuery=" + currentSearchQuery;
		url+= "&searchCondition=" + currentSearchCondition;
		url+= "&searchRange=" + currentSearchRange;
		
		location.href=url;
		return ;
	}
	
	/* JQUERY */
	$(function(){
		
		$("#selectPrintNum").val("${pagingInfo.printNum}");
		
		/* ================================================================= 게시판 페이징  */
		$("#pagingS").click(function(){
			// move to first
			paging({pageNum:0});
		});
		
		$("#pagingP").click(function(){

			var endPageNum;
			var maxPageNum;
			var currentPageNum = ${pagingInfo.pageNum};
			var currentPrintNum = ${pagingInfo.printNum};
			var count = ${count};
			
			var currentPageNumIndex = Math.floor(currentPageNum / 3);
			
			if(currentPageNumIndex==0){
				return;
			}
			
			var nextPageNum = (currentPageNumIndex-1)*3 + 2; 
			paging({pageNum: nextPageNum});
			
			return ;					
		});
		
		$("#pagingN").click(function(){

			var endPageNum;
			var maxPageNum;
			var currentPageNum = ${pagingInfo.pageNum};
			var currentPrintNum = ${pagingInfo.printNum};
			var count = ${count};
			
			if(count % currentPrintNum != 0){
				endPageNum = Math.floor(count / currentPrintNum) + 1;
			}
			else{
				endPageNum = Math.floor(count / currentPrintNum);
			}
			maxPageNum = Math.floor(endPageNum / 3) +1;
						
			var currentPageNumIndex = Math.floor(currentPageNum / 3);
			var nextPageNum = (currentPageNumIndex+1)*3;
			
			if(endPageNum != currentPageNum)
				paging({pageNum: nextPageNum});
			
			return ;			
		});
		
		/* paging: move to end */
		$("#pagingE").click(function(){
			//alert("e" + ${response.data.count});
			
			var endPageNum;
			var currentPageNum = ${pagingInfo.pageNum};
			var currentPrintNum = ${pagingInfo.printNum};
			var count = ${count};
			
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
			
			paging({printNum: selectedVal, pageNum:0});
		});
		
		$("#btnWriteArticle").click(function(){
			location.href="${pageContext.request.contextPath}/page/article/write?boardId="+${board.data.boardId};
		});
		
		/* ============================= 검색 */
		
		$("#btnArticleSearch").click(function(){
			
			var range = searchRange;
			var condition = searchCondition;
			var searchQuery = $("#searchQuery").val();
			
			var url = "${pageContext.request.contextPath}/page/search?boardId="+boardId;
			
			url+= "&pageNum=" + ${pagingInfo.pageNum};
			url+= "&printNum=" + ${pagingInfo.printNum};
			url+= "&condition=" + ${pagingInfo.condition};
			url+= "&order=" + ${pagingInfo.order};
			url+= "&searchQuery=" + searchQuery;
			url+= "&searchCondition=" + condition;
			url+= "&searchRange=" + range;
			
			location.href=url;
		});
	});
</script>

<ftt:page>
<div class="container">

	<c:choose>
		<c:when test="${logined != true }">
			<script type="text/javascript">
				alert("로그인하세요.");
				location.href="${pageContext.request.contextPath}";
			</script>
		</c:when>
	</c:choose>
	
	<h1>${board.data.boardName}</h1>
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
					<th class="col-lg-1">작성일<i class="fa fa-arrow-down" aria-hidden="true" onclick="paging({condition:1})"/></th>
					<th class="col-lg-1">조회수<i class="fa fa-arrow-down" aria-hidden="true" onclick="paging({condition:2})"/></th>
					<th class="col-lg-1">좋아요<i class="fa fa-arrow-down" aria-hidden="true" onclick="paging({condition:3})"/></th>
				</tr>
			</thead>
			<tbody>
			
			<c:choose>
				<c:when test="${notices!=null}">
						<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="nowDate"/>
						
						<c:forEach items="${notices}" var="row">
							<tr class="row-notice">
								<td>공지</td>
								<td> 
									<span>
										<a onclick="openArticle(${row.articleId})"> ${row.articleTitle}</a>
										<a> [${row.articleComments}]</a>
									</span>
								</td>
								
								<td>${row.articleUserNickname}</td>
								<fmt:formatDate pattern="yyyy-MM-dd" value="${row.articleRegDt}" var="rowDate"/>
								<c:choose>
									<c:when test="${rowDate == nowDate}">
										<td><fmt:formatDate pattern="H:m" value="${row.articleRegDt}"/></td>
									</c:when>
									
									<c:otherwise>
										<td><fmt:formatDate pattern="yyyy-MM-dd" value="${row.articleRegDt}"/></td>
									</c:otherwise>
								</c:choose>
								
								<td>${row.articleHits}</td>
								<td>${row.articleLikes}</td>
							</tr>
						</c:forEach>
					</c:when>
			</c:choose>
			
			<c:choose>
					<c:when test="${articles!= null}">
					
						<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="nowDate"/>
						
						<c:forEach items="${articles}" var="row">
							<tr>
								<td>${row.articleId}</td>
								<td> 
									<span>
										<a onclick="openArticle(${row.articleId})"> ${row.articleTitle}</a>
										<a> [${row.articleComments}]</a>
									</span>
								</td>
								
								<td>${row.articleUserNickname}</td>
								<fmt:formatDate pattern="yyyy-MM-dd" value="${row.articleRegDt}" var="rowDate"/>
								<c:choose>
									<c:when test="${rowDate == nowDate}">
										<td><fmt:formatDate pattern="H:0m" value="${row.articleRegDt}"/></td>
									</c:when>
									
									<c:otherwise>
										<td><fmt:formatDate pattern="yyyy-MM-dd" value="${row.articleRegDt}"/></td>
									</c:otherwise>
								</c:choose>
								
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
		
		<div class="right">
			<button type="button" class="btn btn-default" id="btnWriteArticle">글쓰기</button>
		</div>
		
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
				    	<c:set value="${pagingInfo.pageNum/3}" var="pageNumIndex"/>
				    	<c:set value="${pageNumIndex%1}" var="pageNumRemainder"/>
			    		
			    		<c:choose>
			    			<c:when test="${pageNumRemainder>0.5}">
			    				<fmt:formatNumber value="${pageNumIndex-1}" pattern="0" var="pageNumIndex" type="number"/>${pageNumRemainder>0.5}
			    			</c:when>
			    			<c:otherwise>
			    				<fmt:formatNumber value="${pageNumIndex}" pattern="0" var="pageNumIndex" type="number"/>${pageNumRemainder>0.5}
			    			</c:otherwise>
			    		</c:choose>
				    	
			    		<c:forEach begin="1" end="${pagingInfo.limit}" var="index" >
			    			<c:set value="${pageNumIndex*3 }" var="tmpPageNum"/>
			    			<c:choose>
			    				<c:when test="${tmpPageNum + index == pagingInfo.pageNum +1}">
			    					<li class="active"><a id="${tmpPageNum + index}" onclick="paging({pageNum:this.id-1})">${tmpPageNum + index}</a></li>
			    				</c:when>
			    				<c:otherwise>
			    					<li class="not equal"><a id="${tmpPageNum + index}" onclick="paging({pageNum:this.id-1})">${tmpPageNum + index}</a></li>
			    				</c:otherwise>
			    			</c:choose>
			    		</c:forEach>
			    	</c:when>
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
			
			<!-- 검색 -->
			<div class="board-search" style="margin-top: 20px;">
			  <button type="button" class="btn btn-default dropdown-toggle" id="searchRange" data-toggle="dropdown" value="1" aria-haspopup="true" aria-expanded="true">전체기간<span class="caret"></span></button>
			  <ul class="dropdown-menu" aria-labelledby="searchDate">
					<li id="searchRange0" class="" value="0" onclick="setSearchRange(this.id)">전체기간</li>
					<li id="searchRange1" class="" value="1" onclick="setSearchRange(this.id)">1일</li>
					<li id="searchRange2" class="" value="2" onclick="setSearchRange(this.id)">1주</li>
					<li id="searchRange3" class="seljs_mover" value="3" onclick="setSearchRange(this.id)">1개월</li>
					<li role="separator" class="divider"></li>
			 </ul>	
			</div>
			<div class="dropdown">
			  <button type="button" class="btn btn-default dropdown-toggle" id="searchCondition" value="0" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">작성자<span class="caret"></span></button>
			  
			  <ul class="dropdown-menu" aria-labelledby="searchCondition">
			    <li id="searchCondition0" value="0" onclick="setSearchCondition(this.id)"><a>작성자</a></li>
			    <li id="searchCondition1" value="1" onclick="setSearchCondition(this.id)"><a>제목</a></li>
			    <li id="searchCondition2" value="2" onclick="setSearchCondition(this.id)"><a>내용</a></li>
			    <li role="separator" class="divider"></li>
			    <li><a href="#">Separated link</a></li>
			  </ul>
			 	
		    	<div class="board-search board-search-input">
				 	<input id="searchQuery" size="50" class="form-control" placeholder="" type="text" onkeydown="onSearchInputKeyDown()" value="${searchingInfo.searchQuery}"/>
				</div>
				<div class="board-search board-search-input">
					<button id="btnArticleSearch" class="btn btn-default dropdown-toggle" type="button">검색</button>
				</div>
			</div><!--검색 조건 -->
		</div>
			
		</div><!-- END FOOTER -->
	</div>
</div>
</ftt:page>



