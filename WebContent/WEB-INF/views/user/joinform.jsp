<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/user.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/mysite/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
$(function(){
	$("#join-form").submit(function(){
		// 1. 이름 유효성 체크(validation)
		if($("#name").val() == ""){
			alert("이름은 필수 요소입니다.");
			$("#name").focus();
			return false;
		}
		// 2. 이메일 유효성 체크
		if($("#email").val() == ""){
			alert("이메일은 필수 요소입니다.");
			$("#email").focus();
			return false;
		}
		// 						api
		if($("#img-checkemail").is(":visible")==false){	// 안 보이면
			alert("이메일 중복체크를 해야 합니다.");
			return false;
		}
		// 3.패스워드 유효성 체크
		if($("#password").val() == ""){
			alert("패스워드는 필수 요소입니다.");
			$("#password").focus();
			return false;
		}
		
		// 4.약관 체크유무(과제) - jQuery checkbox, jQuery is checked
		if($("input:checkbox[id='agree-prov']").is(":checked")==false){
			alert("약관 체크를 해주십시오.");	
			return false;
		}
		
		
		//return true;
		alert("여기가 보이면 폼을 submit하세요.");
		return false;
	});
	$("#email").change(function(){
		$("#btn-checkemail").show();
		$("#img-checkemail").hide();
	});
	
	$("#btn-checkemail").click(function(){		
		var email = $("#email").val();
		if(email==""){
			return;
		}
		console.log(email);
		
		$.ajax({
			url:"/mysite/user?a=checkemail&email="+email,	//요청URL
			type:"get",							//통신 방식 get/post
			dataType:"json",					//수신 데이터 타입
			data:"",							//post방식인 경우 서버에 전달할 파라미터 데이터 
												//ex) a=checkemail&email=aaa@gmail.com
			//contentType:"application/json",	//보내는 데이터가 JSON형식인 경우
												//반드시 post방식인 경우
												//"{"a":"checkemail","email":"aaa@gmail.com"}"
			success:function(response){			//성공 시 callback
				if(response.result != "success"){
					return;
				}
			
				if(response.data == false){
					alert("이미 존재하는 이메일입니다. 다른 이메일을 사용해 주세요.");
					$("#email").val("").focus();
					return;
				}
				//사용 가능한 이메일
				$("#btn-checkemail").hide();
				$("#img-checkemail").css("height","15px").show();
			},							
			error:function(jqXHR,status,error){	//실패 시 callback
				console.error(jqXHR+":"+status+":"+error);
			}
		});
		
	});
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp"/>
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post" action="/mysite/user">
					<input type="hidden" name="a" value="join">
					
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="">

					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="">
					<input id ="btn-checkemail" type="button" value="id 중복체크">
					<img id="img-checkemail" style="display:none" src="/mysite/assets/images/check.png">
					
					<label class="block-label">패스워드</label>
					<input id="password" name="password" type="password" value="">
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="F" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="M">
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기">
					
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp"/>
		<c:import url="/WEB-INF/views/include/footer.jsp"/>
	</div>
</body>
</html>