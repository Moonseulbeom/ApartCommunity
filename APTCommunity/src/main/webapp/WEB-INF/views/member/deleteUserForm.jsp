<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/deleteUserForm.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){		
		//회원탈퇴 유효성 체크
		$('#delete_form').submit(function(){
			let items = document.querySelectorAll(
					   'input[type="text"],input[type="password"],input[type="email"]');
			 for(let i=0; i<items.length; i++){
			    if(items[i].value.trim()==''){
					let label = document.querySelector('label[for="'+items[i].id+'"]');
					alert(label.textContent + ' 항목 필수 입력');
					items[i].value = '';
					items[i].focus();
					return false;
			    }
			    
			    if(items[i].id == 'dongho' && 
				    	 !/^\d{2,3}-\d{3,4}$/.test(
				    	             $('#dongho').val())){
						alert('동-호수 형식에 맞게 입력해주세요');
						$('#dongho').val('');
						$('#dongho').focus();
						return false;
				}
			    
			    if(items[i].id == 'email' && 
				    	 !/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i.test(
				    	             $('#email').val())){
						alert('이메일 형식에 맞게 입력해주세요');
						$('#email').val('');
						$('#email').focus();
						return false;
				}//end of if
			    
			}//end of for
			
			if($('#passwd').val()!=$('#cpasswd').val()){
				alert('비밀번호와 비밀번호 확인이 불일치합니다.');
				$('#passwd').val('').focus();
				$('#cpasswd').val('');
				return false;
			}
		});//end of submit
	});
</script>
<script type="text/javascript">
	let delete_account = document.getElementById('delete_account');
	//이벤트 연결
	delete_account.onclick=function(){
		let choice = confirm('회원을 탈퇴하시겠습니까?');
		if(choice){
			location.href = 'deleteUser.do';
		}
	};
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div class="content-main">
		<h2 class="sbTitle">회원탈퇴</h2>
		<div class="deleteForm">
			<p class="deleteTit">정보입력</p>
		<form id="delete_form" action="deleteUser.do" method="post">
			<table>
				<colgroup>
					<col width="25%">
					<col width="auto">
				</colgroup>
				<tbody>
					<tr>
						<th>동-호수</th>
						<td>
							<input type="text" name="dongho" id="dongho" maxlength="12" autocomplete="off">
						</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>
							<input type="email" name="email" id="email" maxlength="50">
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td>
							<input type="password" name="passwd" id="passwd" maxlength="12">
						</td>
					</tr>
					<tr>
						<th>비밀번호 확인</th>
						<td>
							<input type="password" name="cpasswd" id="cpasswd" maxlength="12">
						</td>
					</tr>
				</tbody>
			</table>
			
			<div class="btnWrap">
				<input type="submit" value="회원 탈퇴">
				<input type="button" value="MY페이지"
				 onclick="location.href='${pageContext.request.contextPath}/member/myPage.do'">
			</div> 
		</form>
		</div>
	</div>
	<!-- 내용 끝 -->
</div>
</body>
</html>




