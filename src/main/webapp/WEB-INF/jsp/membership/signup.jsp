<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js" integrity="sha256-eGE6blurk5sHj+rmkfsGYeKyZx3M4bG+ZlFyA7Kns7E=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.0/themes/smoothness/jquery-ui.css">

<script type="text/javascript">

	var idChecked = false;
	var nicknameChecked = false;

	
	function isValidDate(str){
		var str = $("#userBirthDt").val();
		// STRING FORMAT yyyy-mm-dd
		if(str=="" || str==null){
			alert("잘못된 형식의 날짜입니다.");
			return false;
		}								
		
		// m[1] is year 'YYYY' * m[2] is month 'MM' * m[3] is day 'DD'					
		var m = str.match(/(\d{4})-(\d{2})-(\d{2})/);
		
		// STR IS NOT FIT m IS NOT OBJECT
		if( m === null || typeof m !== 'object'){return false;}				
		
		// CHECK m TYPE
		if (typeof m !== 'object' && m !== null && m.size!==3){return false;}
					
		var ret = true; //RETURN VALUE						
		var thisYear = new Date().getFullYear(); //YEAR NOW
		var minYear = 1999; //MIN YEAR
		
		// YEAR CHECK
		if( (m[1].length < 4) || m[1] < minYear || m[1] > thisYear){ret = false;}
		// MONTH CHECK			
		if( (m[2].length < 2) || m[2] < 1 || m[2] > 12){ret = false;}
		// DAY CHECK
		if( (m[3].length < 2) || m[3] < 1 || m[3] > 31){ret = false;}
		
		if(!ret){
			alert("잘못된 형식의 날짜입니다.");
		}
		return ret;			
	}
	
	
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
	
		var userContactNum = $("#userContactNum").val();
		
		if(userContactNum.includes("-")){
			var tmp="";
			var arr = userContactNum.split("-");
			for(idx=0; idx<arr.length; idx++){
				tmp+= arr[idx];
			}
			userContactNum = tmp;
		}
		
		if(userContactNum.length != 11){
			alert("올바른 전화번호를 입력하세요.");
			return false;
		}
		var contactPrefix = userContactNum.slice(0,3);
		if( contactPrefix!= "010" && contactPrefix != "011" && contactPrefix != "016" && contactPrefix != "019" ){
			alert("올바른 번호를 입력하세요.");
			return false;
		}
		
		return true;
	}
	
	function valBirthDt(){
		return true;
	}

	$(function(){
		
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

		$("#userBirthDt").change(function(){
			var birthDt = $("#userBirthDt").val();
			
			if(!isValidDate()){
				$("#userBirthDt").val("");
			}
		});
		
		$("#userNickname").keyup(function(){
			nicknameChecked = false;
		});
		
		/* 회원가입 */
		$("#btnSignup").click(function(){
			
			if(!idChecked){
				alert("아이디 중복확인을 하세요.");
				return ;
			}
			
			if(!nicknameChecked){
				alert("닉네임 중복확인을 하세요.");
				return ;
			}
			
			if( valId() && valPwd() && valContactNum() && isValidDate() ){
				// 모든 유효성 검사 통과 
				
				var userId = $("#userId").val();
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
								
				var userInfo = {
						"userId":userId,
						"userPwd":userPwd,
						"userBirthDt":userBirthDt,
						"userContactNum":userContactNum,
						"userNickname":userNickname
				};
				
				$.ajax({
					type: "POST",
					url: "${pageContext.request.contextPath}/user",
					headers: { 
				        'Accept': 'application/json',
				        'Content-Type': 'application/json' 
				    },
					data: JSON.stringify(userInfo),
					success: function(res){
						alert(res.message);
						//location.href="${pageContext.request.contextPath}/user/login";
						location.replace("${pageContext.request.contextPath}/page/login");
					},
					error: function(err){
						alert("error");
					}
				});
			}
			else{
				return ;
			}
		});// 회원가입
		
		/* 회원가입취소 */
		$("#btnSignupCancel").click(function(){
			location.replace("${pageContext.request.contextPath}/");	
		});
		
		/* 아이디 중복확인 */
		$("#btnCheckIdDup").click(function(){
			
			var userId = $("#userId").val();
			
			if(userId == ""){
				alert("아이디를 입력하세요");
				return ;
			}			
			
			$.ajax({
				type: "GET",
				url: "${pageContext.request.contextPath}/user/checkid",
				beforeSend: function(xhr){
					xhr.setRequestHeader("userId", userId);
				},
				success: function(res){
					if(res.status == "200" ){
						idChecked = true;
						alert(res.message);
					}
					else{
						alert(res.message);
					}
				},
				error: function(err){
					
				}
			});
		});
		
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
		});
	});
</script>

<ftt:page>
	<div class="container">
		<h1>회원가입</h1>
		<hr>
		<form class="form-horizontal">
		  <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">아이디</label>
		    <div class="col-sm-5">
		      <input id="userId" size="5" class="form-control" placeholder="아이디" type="text"/>
		    </div>
   		    <div class="col-sm-2">
		    	<button type="button" class="btn btn-default" id="btnCheckIdDup">중복확인</button>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label">비밀번호</label>
		    <div class="col-sm-5">
		      <input id="userPwd" class="form-control" type="password" placeholder="비밀번호"/>
		    </div>
		  </div>
		   <div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label">비밀번호 확인</label>
		    <div class="col-sm-5">
		      <input id="userRePwd" class="form-control" type="password" placeholder="비밀번호"/>
		    </div>
		  </div>
		   <div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label">닉네임</label>
		    <div class="col-sm-5">
		      <input id="userNickname" class="form-control" type="text" placeholder="닉네임을 입력하세요."/> 
		    </div>
		    <div class="col-sm-2">
		    	<button type="button" class="btn btn-default" id="btnCheckNicknameDup">중복확인</button>
		    </div>
		  </div>
	   	  <div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label">연락처</label>
		    <div class="col-sm-5">
		      <input id="userContactNum" class="form-control" type="text" />
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="inputPassword3" class="col-sm-2 control-label">생년월일</label>
		    <div class="col-sm-5">
		      <input id="userBirthDt" class="form-control" type="text"/>
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10 horizontal" >
				<button type="button" class="btn btn-default" id="btnSignup">회원가입</button>
				<button type="button" class="btn btn-default" id="btnSignupCancel">취소</button>
		    </div>
		  </div>
		</form>
	</div>
</ftt:page>