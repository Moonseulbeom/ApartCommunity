<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/modifyPasswordForm.css">
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
			    
			    if(items[i].id == 'dongho' && 
				    	 !/^\d{2,3}-\d{3,4}$/.test(
				    	             $('#dongho').val())){
						alert('동-호수 형식 [ ex)000(동)-000(호) ]에 맞게 입력해주세요');
						$('#dongho').val('');
						$('#dongho').focus();
						return false;
					}
			    
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
</head>
<body>

	<!-- header 시작 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<!-- header 끝 -->

	<!-- 내용 시작 -->
	<div class="page-main">
		<div class="content-main">
			<h2 class="sbTitle">비밀번호 수정</h2>
			<div class="modifyPasswordForm">
				<p class="modifyPasswordTit">정보입력</p>
				<form id="password_form" action="modifyPassword.do" method="post">
					<table>
						<colgroup>
							<col width="25%">
							<col width="auto">
						</colgroup>
						<tbody>
							<tr>
								<th>동-호수</th>
								<td><input type="text" name="dongho" id="dongho"
									maxlength="12" placeholder="ex)000-000" autocomplete="off" class="modify_inp"></td>
							</tr>
							<tr>
								<th>현재 비밀번호</th>
								<td><input type="password" name="origin_passwd"
									id="origin_passwd" maxlength="12" class="modify_inp"></td>
							</tr>
							<tr>
								<th>새 비밀번호</th>
								<td><input type="password" name="passwd" id="passwd"
									maxlength="12" class="modify_inp"></td>
							</tr>
							<tr>
								<th>새 비밀번호 확인</th>
								<td><input type="password" name="cpasswd" id="cpasswd"
									maxlength="12" class="modify_inp"></td>
							</tr>
						</tbody>
					</table>

					<div class="btnWrap">
						<input type="submit" value="수정"> <input type="button"
							value="취소"
							onclick="location.href='${pageContext.request.contextPath}/member/myPage.do'">
					</div>
				</form>
			</div> <!-- end of modifyPasswordForm -->
		</div> <!-- end of content-main -->
	</div> <!-- end of page-main -->
	<!-- 내용 끝 -->

	<!-- footer 시작 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<!-- footer 끝 -->

</body>
</html>




