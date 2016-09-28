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
	
	/*	게시글 뷰 페이지 오픈 */
	function openArticle(articleId){
		location.href="${pageContext.request.contextPath}/page/article/" + articleId;
		return ;
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
			var count = ${response.data.count};
			
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
			var count = ${response.data.count};
			
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
			var count = ${response.data.count};
			
			if(count % currentPrintNum != 0){
				endPageNum = Math.floor(count / currentPrintNum) + 1;
			}
			else{
				endPageNum = Math.floor(count / currentPrintNum);
			}
			
			paging({pageNum: endPageNum-1});
		});
		
		
		$("#selectPrintNum").change(function(){
			var selectedVal = $("#selectPrintNum").val();
			
			paging({printNum: selectedVal, pageNum:1});
		});
		
		$("#btnWriteArticle").click(function(){
			location.href="${pageContext.request.contextPath}/page/article/write";
		});
		
		/* ============================= 검색 */
		$("#btn-board-search-date").click(function(){
			$("#board-search-date-option").toggle();
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
					<th class="col-lg-1">작성일<i class="fa fa-arrow-down" aria-hidden="true" onclick="paging({condition:1})"/></th>
					<th class="col-lg-1">조회수<i class="fa fa-arrow-down" aria-hidden="true" onclick="paging({condition:2})"/></th>
					<th class="col-lg-1">좋아요<i class="fa fa-arrow-down" aria-hidden="true" onclick="paging({condition:3})"/></th>
				</tr>
			</thead>
			
			<tbody>
			<c:choose>
					<c:when test="${ response.data.articles != null}">
					
						<fmt:formatDate value="${now}" pattern="yyyy-MM-dd" var="nowDate"/>
						
						<c:forEach items="${response.data.articles}" var="row">
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
				    	
			    		<c:forEach begin="1" end="3" var="index">
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
				<div class="board-search board-search-date dropdown">
				  <button class="btn btn-default dropdown-toggle" type="button" id="btn-board-search-date" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
				    검색기간
				    <span class="caret"></span>
				  </button>
				</div>
				
			  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">제목+내용
			    <span class="caret"></span>
			  </button>
			  
			  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
			    <li><a>제목+내용</a></li>
			    <li><a>제목만</a></li>
			    <li><a>글작성자</a></li>
			    <li><a>댓글내용</a></li>
			    <li><a >댓글작성자</a></li>
			    <li role="separator" class="divider"></li>
			    <li><a href="#">Separated link</a></li>
			  </ul>
			 	
		    	<div class="board-search board-search-input">
				 	<input id="userId" size="5" class="form-control" placeholder="" type="text"/>
				</div>
				<div class="board-search board-search-input">
					<button id="btnArticleSearch" class="btn btn-default dropdown-toggle" type="button">검색</button>
				</div>
			</div><!--검색 조건 -->
			
			<div id="board-search-date-option" style="display: none; width: 300px;">
				<ul class="ul-board-search-date-option">
					<li class="">전체기간</li>
					<li class="">1일</li>
					<li class="">1주</li>
					<li class="seljs_mover">1개월</li>
					<li class="">6개월</li>
					<li class="">1년</li>
					<li role="separator" class="divider"></li>
					<li class="">
						<fieldset class="" style="display: inline;"><label for="period">기간 입력</label><input type="text" id="input_1" maxlength="10" class="seljs_text" style="border-color: rgb(211, 211, 211) !important; background-color: rgb(255, 255, 255) !important; width: 62px;"> ~ <input type="text" id="input_2" maxlength="10" class="seljs_text" style="border-color: rgb(211, 211, 211) !important; background-color: rgb(255, 255, 255) !important; width: 62px;"><input type="image" src="http://cafeimgs.naver.net/cafe4/btn_setting.gif" alt="설정" style="margin-left: 5px; border: 0pt none !important;">
						</fieldset>
					</li>
				</ul>
			</div>	
		</div>
			
		</div><!-- END FOOTER -->
	</div>
</div>
</ftt:page>



