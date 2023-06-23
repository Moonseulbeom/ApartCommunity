<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registerUserForm.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function() {
	//0:중복 체크 미실시, 동-호수 중복
	//1:동-호수 미중복
	let idChecked = 0;
	
	//동-호수 중복 체크
	$('#dongho_check').click(function(){
		
		if(!/^[0-9]{3,10}$/.test(
				            $('#dong').val())){
			alert('숫자 사용');
			$('#dong').val('');
			$('#dong').focus();
			return false;
		}
		if(!/^[0-9]{3,10}$/.test(
				            $('#ho').val())){
			alert('숫자 사용');
			$('#ho').val('');
			$('#ho').focus();
			return false;
			}
		
		// dong과 ho 값을 연결
		let dongho = $('#dong').val() + '-' + $('#ho').val();
		
		//서버와의 통신
		$.ajax({
			url:'checkDuplicatedId.do',
			type:'post',
			data: { id: dongho },
			dataType:'json',
			success:function(param){
				if(param.result == 'idNotFound'){
					//id 미중복
					idChecked = 1;
					$('#message_dongho').css('color','green')
					                .text('등록 가능 동-호수');
				}else if(param.result == 'idDuplicated'){
					//id 중복
					idChecked = 0;
					$('#message_dongho').css('color','red')
					                .text('중복된 동-호수');
					$('#dongho').val('').focus();
				}else{
					idChecked = 0;
					alert('동-호수 중복 체크 오류 발생');
				}
			},
			error:function(){
				idChecked = 0;
				alert('네트워크 오류 발생');
			}
		});
		
	});//end of click
	
	//아이디 중복 안내 메시지 초기화 및 아이디
	//중복 값 초기화
	$('#register_form #dongho').keydown(function(){
		idChecked = 0;
		$('#message_dongho').text('');
	});//end of keydown
	
	//회원 정보 등록 유효성 체크
	$('#register_form').submit(function(){
		let items = document.querySelectorAll(
				   'input[type="text"],input[type="password"],input[type="email"]');
		 for(let i=0;i<items.length;i++){
			
			if(items[i].id == 'carnum'){
					continue;
			} 
			if(items[i].id == 'search_box'){
				continue;
			} 
			 
		    if(items[i].value.trim()==''){
				let label = 
					document.querySelector(
				 'label[for="'+items[i].id+'"]');
				alert(label.textContent + ' 항목 필수 입력');
				items[i].value = '';
				items[i].focus();
				return false;
		    }
		    
		    if(items[i].id == 'id' && 
		    	 !/^[A-Za-z0-9]{3,5}$/.test(
		    	             $('#id').val())){
				alert('숫자 사용, 최소 3자 ~ 최대 5자를 사용하세요');
				$('#dongho').val('');
				$('#dongho').focus();
				return false;
			}
		    
		    if(items[i].id == 'dongho' && 
		    		            idChecked == 0){
				alert('동-호수 중복 체크 필수');
				return false;
		    }
		}
		
		
	});
	
});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<div class="page-main">
	<!-- 내용 시작 -->
	<div class="sbLogin">
		<h2 class="sbTitle">회원 가입</h2>
		<div class="join_flow">
			<ul>
				<li class="jo_01"><span>약관동의</span></li>
				<li class="jo_02 jo_o2_on"><span>회원정보입력</span></li>
				<li class="jo_03"><span>가입완료</span></li>
			</ul>
		</div>
		<form id="register_form" action="registerUser.do" method="post">
			<ul>
				<li>
					<label for="dong">동</label> 
					<input type="text" name="dong" id="dong" maxlength="12" autocomplete="off">
				</li>
				<li>
					<label for="ho">호</label>
					<input type="text" name="ho" id="ho" maxlength="12" autocomplete="off">
				</li>
				<li>
					<input type="button" value="동-호수 중복체크" id="dongho_check">
					<span id="message_dongho"></span>
				</li>
				<li>
					<label for="name">세대주</label>
					<input type="text" name="name"
					  id="name" maxlength="10">
				</li>
				<li>
					<label for="passwd">비밀번호</label>
					<input type="password" name="passwd"
					  id="passwd" maxlength="12">
				</li>
				<li>
					<label for="phone">전화번호</label>
					<input type="text" name="phone"
					  id="phone" maxlength="15">
				</li>
				<li>
					<label for="email">이메일</label>
					<input type="email" name="email"
					  id="email" maxlength="50">
				</li>
				<li>
					<label for="carnum">차량번호</label>
					<input type="text" name="carnum"
					  id="carnum" maxlength="15">
				</li>
			</ul> 
			<div class="align-center">
				<input type="submit" value="등록">
				<input type="button" value="홈으로"
				 onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
			</div> 
		</form>
	</div>
	<!-- 내용 끝 -->
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>




