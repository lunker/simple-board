<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ftt" tagdir="/WEB-INF/tags/"%>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js" integrity="sha256-eGE6blurk5sHj+rmkfsGYeKyZx3M4bG+ZlFyA7Kns7E=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.0/themes/smoothness/jquery-ui.css">


<script type="text/javascript">

	var idChecked = false;
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
		var userContactNum1 = $("#userContactNum1").val();
		var userContactNum2 = $("#userContactNum2").val();
		var userContactNum3 = $("#userContactNum3").val();
		
		if(userContactNum1.length != 3 || userContactNum2.length != 4 || userContactNum3.length != 4){
			alert("전화번호를 모두 입력하세요.");
			return false;	
		}
		
		if(userContactNum1 != "010" && userContactNum1 != "011" && userContactNum1 != "016" && userContactNum1 != ""){
			alert("올바른 번호를 입력하세요.");
			return false;	
		}
		
		/*
		if(userContactNum2[0] == "0" || userContactNum3[0] == "0"){
			alert("올바른 번호를 입력하세요.");
			return false;
		}
		*/
		
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
		
		
		$("#userContactNum1").keyup(function(){
			var userContactNum1 = $("#userContactNum1").val();
			if(userContactNum1.length == 3){
				$("#userContactNum2").focus();
			}
		});
		
		$("#userContactNum2").keyup(function(){
			var userContactNum2 = $("#userContactNum2").val();
			if(userContactNum2.length == 4){
				$("#userContactNum3").focus();
			}
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
			
			if( valId() && valPwd() && valContactNum() && valBirthDt() ){
				// 모든 유효성 검사 통과 
				
				var userId = $("#userId").val();
				var userPwd = $("#userPwd").val();
				var userContactNum = $("#userContactNum").val();
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
					if(res.status == "100" ){
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
					if(res.status == "100" ){
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
		
		<div class="col-lg-10">
			아이디 <input id="userId" type="text" /> <button id="btnCheckIdDup">중복확인</button><br>	
			비밀번호 <input id="userPwd" type="password" /><br>
			비밀번호 확인 <input id="userRePwd" type="password" /><br>
			닉네임 <input id="userNickname" type="text" /> <button id="btnCheckNicknameDup">중복확인</button><br>
			연락처 <input id="userContactNum1" type="text" size="3" maxlength="3"/><input id="userContactNum2" type="text" size="4" maxlength="4"/><input id="userContactNum3" type="text" size="4" maxlength="4"/><br>
			생년월일 <input id="userBirthDt" type="text" /><br>
			
			<button type="button" id="btnSignup">회원가입</button>
			<button type="button" id="btnSignupCancel">취소</button>
			
		</div>
		
	</div>
</ftt:page>