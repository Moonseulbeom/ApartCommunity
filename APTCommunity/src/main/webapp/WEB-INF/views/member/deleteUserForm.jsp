<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/modify.css">
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
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div class="content-main">
		<h2 class="content_h2">회원탈퇴</h2>
		<form id="delete_form" action="deleteUser.do" method="post">
			<ul>
				<li id="delete_li">
					<label for="id">아이디</label>
					<input type="text" name="id"
					   id="id" maxlength="12"
					   autocomplete="off" class="delete_inp">
				</li>
				<li id="delete_li">
					<label for="email">이메일</label>
					<input type="email" name="email"
					   id="email" maxlength="50" class="delete_inp">
				</li>
				<li id="delete_li">
					<label for="passwd">비밀번호</label>
					<input type="password" name="passwd"
					  id="passwd" maxlength="12" class="delete_inp">
				</li>
				<li id="delete_li">
					<label for="cpasswd">비밀번호 확인</label>
					<input type="password" name="cpasswd"
					  id="cpasswd" maxlength="12" class="delete_inp">
				</li>
			</ul> 
			<div class="align-center">
				<input type="submit" value="회원탈퇴" id="delete_account">
				<input type="button" value="MY페이지" onclick="location.href='myPage.do'">
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
			</div> 
		</form>
	</div>
	<!-- 내용 끝 -->
</div>
</body>
</html>




