<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/modify.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript">
	$(function(){		
		//비밀번호 수정 유효성 체크
		$('#password_form').submit(function(){
			let items = document.querySelectorAll(
					   'input[type="text"],input[type="password"]');
			 for(let i=0;i<items.length;i++){
				 
			    if(items[i].value.trim()==''){
					let label = 
						document.querySelector(
					 'label[for="'+items[i].id+'"]');
					alert(label.textContent + ' 항목 필수 입력');
					items[i].value = '';
					items[i].focus();
					return false;
			    }//end of if
			}//end of for
			
			if($('#passwd').val()!=$('#cpasswd').val()){
				alert('새비밀번호와 새비밀번호 확인이 불일치합니다.');
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
		<h2 class="content_h2">비밀번호 수정</h2>
		<form id="password_form" action="modifyPassword.do" method="post">
			<ul id="modify_ul">
				<li id="modify_li">
					<label for="dongho">동-호수</label>
					<input type="text" name="dongho"
					   id="dongho" maxlength="12"
					   autocomplete="off" class="modify_inp">
				</li>
				<li id="modify_li">
					<label for="origin_passwd">현재 비밀번호</label>
					<input type="password" name="origin_passwd"
					  id="origin_passwd" maxlength="12" class="modify_inp">
				</li>
				<li id="modify_li">
					<label for="passwd">새비밀번호</label>
					<input type="password" name="passwd"
					  id="passwd" maxlength="12" class="modify_inp">
				</li>
				<li id="modify_li">
					<label for="cpasswd">새비밀번호 확인</label>
					<input type="password" name="cpasswd"
					  id="cpasswd" maxlength="12" class="modify_inp">
				</li>
			</ul> 
			<div class="align-center">
				<input type="submit" value="수정" id="modify_passwd">
				<input type="button" value="취소"
				 onclick="location.href='${pageContext.request.contextPath}/member/myPage.do'">
				<script type="text/javascript">	
					let modify_passwd = document.getElementById('modify_passwd');
						//이벤트 연결
						modify_passwd.onclick=function(){
							let choice = confirm('비밀번호를 수정하시겠습니까?');
							if(choice){
								location.replace('modifyPasswordForm.do');
							}
						};
				</script>
			</div> 
		</form>
	</div>
	<!-- 내용 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>




