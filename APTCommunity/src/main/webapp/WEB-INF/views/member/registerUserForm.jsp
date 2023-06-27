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
	
	$('#dong').keyup(function(){
 		if($(this).val() == 0){
 			$(this).val('');
 		}
 	});
	
	$('#ho').keyup(function(){
 		if($(this).val() == 0){
 			$(this).val('');
 		}
 	});
	
	//동-호수 중복 체크
	$('#dongho_check').click(function(){
	 		
		if(!/^[0-9]{3}$/.test(
				            $('#dong').val())){
			alert('동 (101동 ~ 115동)이 유효하지 않습니다.');
			$('#dong').val('');
			$('#dong').focus();
			return false;
		}
		
		// 동이 101동~115동 사이인지 확인합니다.
		if (101 <= +$('#dong').val() && +$('#dong').val() <= 115) {
			// 유효합니다.
		} else {
			alert('동 (101동 ~ 115동)이 유효하지 않습니다.');
			$('#dong').val('');
			$('#dong').focus();
			return false;
		}
		
		
		if(!/^[0-9]{3,4}$/.test(
				            $('#ho').val())){
			alert('호가 유효하지 않습니다.(호수는 101호~2506호까지)');
			$('#ho').val('');
			$('#ho').focus();
			return false;
			}
		
 		if($('#ho').val().length==3 && ($('#ho').val().substring(0,1)==0 || $('#ho').val().substring(1) == 0 || $('#ho').val().substring(1)>6)){
 			alert('호가 유효하지 않습니다.(호수는 101호~2506호까지)');
 			$('#ho').val('');
			$('#ho').focus();
			return false;
 		}
 		if($('#ho').val().length==4 && ($('#ho').val().substring(0,2)==0 || $('#ho').val().substring(0,2)>25 || $('#ho').val().substring(2) == 0 || $('#ho').val().substring(2)>6)){
 			alert('호가 유효하지 않습니다.(호수는 101호~2506호까지)');
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
			 
			if (items[i].value.trim() == '') {
				alert(items[i].name + ' 항목 필수 입력');
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
			
		    if(items[i].id == 'passwd' && 
			    	 !/^[A-Za-z0-9]{4,12}$/.test(
			    	             $('#passwd').val())){
					alert('영문 또는 숫자 사용, 최소 4자 ~ 최대 12자를 사용하세요');
					$('#passwd').val('');
					$('#passwd').focus();
					return false;
				}
		    
		    if(items[i].id == 'dongho' && idChecked == 0){
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
		<div class="joinForm">
			<p class="joinTit">정보입력</p>
		<form id="register_form" action="registerUser.do" method="post">
			<table>
				<colgroup>
					<col width="25%">
					<col width="auto">
				</colgroup>
				<tbody>
					<tr>
						<th>동 <span>(필수)</span></th>
						<td>
							<input type="text" name="동" id="dong" maxlength="12" placeholder="숫자만 입력" autocomplete="off">
						</td>
					</tr>
					<tr>
						<th>호 <span>(필수)</span></th>
						<td>
							<input type="text" name="호" id="ho" maxlength="12" placeholder="숫자만 입력" autocomplete="off">
							<input type="button" value="동-호수 중복체크" id="dongho_check" class="dongho_check">
								<span id="message_dongho"></span>
						</td>
					</tr>
					<tr>
						<th>세대주 <span>(필수)</span></th>
						<td>
							<input type="text" name="세대주" id="name" placeholder="문자만 입력" maxlength="10">
						</td>
					</tr>
					<tr>
						<th>비밀번호 <span>(필수)</span></th>
						<td>
							<input type="password" name="비밀번호" id="passwd" placeholder="영문자 포함(4~12자리)" maxlength="12">
						</td>
					</tr>
					<tr>
						<th>전화번호 <span>(필수)</span></th>
						<td>
							<input type="text" name="전화번호" id="phone" placeholder="ex)000-0000-0000" maxlength="15">
						</td>
					</tr>
					<tr>
						<th>이메일 <span>(필수)</span></th>
						<td>
							<input type="email" name="이메일" id="email" placeholder="ex)test@test.com" maxlength="50">
						</td>
					</tr>
					<tr>
						<th>차량번호 <span class="sp_ch">(선택)</span></th>
						<td>
							<input type="text" name="차량번호" id="carnum" placeholder="ex)00가0000" maxlength="15">
						</td>
					</tr>
				</tbody>
			</table>
			
			<div class="btnWrap">
				<input type="submit" value="등록">
			</div> 
		</form>
		</div><!-- end of joinform -->
	</div>
	<!-- 내용 끝 -->
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>




