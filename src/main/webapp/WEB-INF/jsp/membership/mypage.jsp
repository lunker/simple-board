<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js" integrity="sha256-eGE6blurk5sHj+rmkfsGYeKyZx3M4bG+ZlFyA7Kns7E=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.0/themes/smoothness/jquery-ui.css">


<script type="text/javascript">

	
	var nicknameChecked = false;
	
	function valId(){
		var userId = $("#userId").val();
		
		if(userId == ""){
			alert("아이디를 입력하세요");
			return false;
		}
		if(userId.length > 10){
			alert("10글자 이하로 입력하세요.");
			return false;
		}
		
		return true;
	}
	
	function valPwd(){
		
		var userPwd = $("#userPwd").val();
		var userRePwd = $("#userRePwd").val();
		
		if(userPwd == ""){
			alert("비밀번호를 입력하세요.");
			return false;
		}
		
		if(userRePwd == ""){
			alert("비밀번호를 확인하세요.");
			return false;
		}
		
		if(userPwd != userRePwd){
			alert("재입력 비밀번호가 틀렸습니다.");
			return false;
		}
		
		return true;
	}
	
	function valContactNum(){
		return true;
	}
	
	function valBirthDt(){
		return true;
	}

	$(function(){
		
		// set contact nu
		var tmp = "${userInfo.userContactNum}";
		
		var result = tmp.slice(0,3) + "-" + tmp.slice(3,7) + "-" + tmp.slice(7,11);  
		$("#userContactNum").val(result);
		
		$.datepicker.regional['kr'] = {
			    closeText: '닫기', // 닫기 버튼 텍스트 변경
			    currentText: '오늘', // 오늘 텍스트 변경
			    monthNames: ['1 월','2 월','3 월','4 월','5 월','6 월','7 월','8 월','9 월','10 월','11 월','12 월'], // 개월 텍스트 설정
			    monthNamesShort: ['1 월','2 월','3 월','4 월','5 월','6 월','7 월','8 월','9 월','10 월','11 월','12 월'], // 개월 텍스트 설정
			    dayNames: ['월요일','화요일','수요일','목요일','금요일','토요일','일요일'], // 요일 텍스트 설정
			    dayNamesShort: ['월','화','수','목','금','토','일'], // 요일 텍스트 축약 설정    
			    dayNamesMin: ['월','화','수','목','금','토','일'], // 요일 최소 축약 텍스트 설정
			    dateFormat: 'yy-mm-dd' // 날짜 포맷 설정
			};
		$.datepicker.setDefaults($.datepicker.regional['kr']);
		$("#userBirthDt").datepicker();		
		
		/* 닉네임 중복확인 */
		$("#btnCheckNicknameDup").click(function(){
			
			var userNickname = $("#userNickname").val();
			
			if(userNickname == ""){
				alert("닉네임을 입력하세요");
				return ;
			}			
			
			var data = {userNickname : userNickname};
			
			$.ajax({
				type: "GET",
				url: "${pageContext.request.contextPath}/user/checknickname",
				data: data,
				headers: { 
			        'Accept': 'application/json',
			        'Content-Type': 'application/json' 
			    },
				success: function(res){
					if(res.status == "200" ){
						nicknameChecked = true; // set flag
						alert(res.message);
					}
					else{
						alert(res.message);
					}
				},
				error: function(err){
					alert("시스템 에러" + err);
				}
			});// end ajax
		});// 닉네임 중복확인
		
		/* 회원정보수정 */
		$("#btnModify").click(function(){

			if(confirm("수정하시겠습니까?") == true){
				
				var userId = ${userId};
				var userPwd = $("#userPwd").val();
				var userContactNum = $("#userContactNum").val();
				if(userContactNum.includes("-")){
					var tmp="";
					var arr = userContactNum.split("-");
					for(idx=0; idx<arr.length; idx++){
						tmp+= arr[idx];
					}
					userContactNum = tmp;
				}
				var userNickname = $("#userNickname").val();
				var userBirthDt = $("#userBirthDt").val();
				
				if( "${userNickname}" != userNickname && !nicknameChecked){
					alert("닉네임 중복검사를 하세요.");
					return ;
				}
				
				var userInfo = {
						"userId":userId,
						"userPwd":userPwd,
						"userBirthDt":userBirthDt,
						"userContactNum":userContactNum,
						"userNickname":userNickname
				};
				
				$.ajax({
					type: "PUT",
					url: "${pageContext.request.contextPath}/user",
					headers: { 
				        'Accept': 'application/json',
				        'Content-Type': 'application/json' 
				    },
					data: JSON.stringify(userInfo),
					success: function(res){
						if(res.status == "200" ){
							nicknameChecked = true;
							alert(res.message);
						}
						else{
							alert(res.message);
						}
					},
					error: function(err){
						
					}
				});// end ajax
			}// end if
		});
		
		/* 취소 */
		$("#btnModifyCancel").click(function(){
			location.replace("${pageContext.request.contextPath}/");
		});
		
		/* 탈퇴 */
		$("#btnWithdraw").click(function(){
			if(confirm("정말 탈퇴하시겠습니까?") == true){
				// 탈퇴 진행 
				
				$.ajax({
					type:"DELETE",
					url: "${pageContext.request.contextPath}/user",
					headers: { 
				        'Accept': 'application/json',
				        'Content-Type': 'application/json' 
				    },
				    beforeSend: function(xhr){				    	
					    xhr.setRequestHeader("userId",  ${userInfo.userId});
				    },
					success: function(res){
						if(res.status== "200"){
							alert(res.message);
							location.replace("${pageContext.request.contextPath}/");							
						}
						else{
							
						}
					},
					error: function(err){
						
					}
				});
				
			}// end confirm 
			else {
				return ; 
			}
		});
	});
</script>

<ftt:page>

<div class="container">
	<c:choose>
		<c:when test="${logined == true }">
			<h1>마이페이지</h1>
			<hr>
			
			<form class="form-horizontal">
			  <div class="form-group">
			    <label for="inputEmail3" class="col-sm-2 control-label">아이디</label>
			    <div class="col-sm-5">
			      <input id="userId" size="5" class="form-control" placeholder="아이디" type="text" disabled="disabled" value="${userInfo.userId}" />
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">비밀번호</label>
			    <div class="col-sm-5">
			      <input id="userPwd" class="form-control" type="password"/>
			    </div>
			  </div>
			   <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">비밀번호 확인</label>
			    <div class="col-sm-5">
			      <input id="userRePwd" class="form-control" type="password" />
			    </div>
			  </div>
			   <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">닉네임</label>
			    <div class="col-sm-5">
			      <input id="userNickname" class="form-control" type="text" value="${userInfo.userNickname }" /> 
			    </div>
			    <div class="col-sm-2">
			    	<button type="button" class="btn btn-default" id="btnCheckNicknameDup">중복확인</button>
			    </div>
			  </div>
		   	  <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">연락처</label>
			    <div class="col-sm-5">
			      <input id="userContactNum" class="form-control" type="text"/>
			    </div>
			  </div>
			  
			  <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">생년월일</label>
			    <div class="col-sm-5">
			      <input id="userBirthDt" class="form-control" type="text" value="${userInfo.userBirthDt }"/>
			    </div>
			  </div>
			  
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10 horizontal" >
			    	<button type="button" class="btn btn-default" id="btnWithdraw">탈퇴</button>
					<button type="button" class="btn btn-default" id="btnModify">수정</button>
					<button type="button" class="btn btn-default" id="btnModifyCancel">취소</button>
			    </div>
			  </div>
			</form>
			
		</c:when>
		<c:otherwise> 
			<script>
				alert("로그인하세요."); 
				location.href="${pageContext.request.contextPath}";
			</script> 
		</c:otherwise>
	</c:choose>
	

</div>
	
</ftt:page>