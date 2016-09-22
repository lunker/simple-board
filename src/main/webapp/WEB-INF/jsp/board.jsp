<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>



<script type="text/javascript">

	$(function(){
		
		
	});

</script>

<div>
	<h1>게시판 이름</h1>
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
		<table class="table table-bordered">
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
		</table>
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
		    <li><a href="#">4</a></li>
		    <li><a href="#">5</a></li>
		    <li>
		      <a href="#" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
		      </a>
		    </li>
		  </ul>
		</nav>	
	</div>
	
</div>


