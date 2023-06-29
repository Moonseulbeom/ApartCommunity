<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/modifyUserForm.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript">
	$(function(){
		//회원 정보 등록 유효성 체크
		$('#modify_form').submit(function(){
			let items = document.querySelectorAll(
					   'input[type="text"],input[type="email"]');
			 for(let i=0;i<items.length;i++){
				 
			    if(items[i].value.trim()==''){
					let label = 
						document.querySelector(
					 'label[for="'+items[i].id+'"]');
					alert(label.textContent + ' 항목 필수 입력');
					items[i].value = '';
					items[i].focus();
					return false;
			    }
			    
			    if(items[i].id == 'name' &&  
				    	 !/^[가-힣]+$/.test(
				    	             $('#name').val())){
						alert('세대주는 한글만 입력가능합니다.');
						$('#name').val('');
						$('#name').focus();
						return false;
					}
			    
			    if(items[i].id == 'phone' && 
				    	 !/^\d{2,3}-\d{3,4}-\d{4}$/.test(
				    	             $('#phone').val())){
						alert('전화번호 [ ex)000-0000-0000 ] 형식에 맞게 입력해주세요');
						$('#phone').val('');
						$('#phone').focus();
						return false;
					}
			    
			    if(items[i].id == 'email' && 
				    	 !/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i.test(
				    	             $('#email').val())){
						alert('이메일 형식 [ ex)test@test.com ] 에 맞게 입력해주세요');
						$('#email').val('');
						$('#email').focus();
						return false;
					}
			}
		});
	});
</script>
		<script type="text/javascript">
					let modify_info = document.getElementById('modify_info');
					//이벤트 연결
					modify_info.onclick=function(){
						let choice = confirm('회원 정보를 수정하시겠습니까?');
						if(choice){
							location.replace('modifyUserForm.do');
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
			<h2 class="sbTitle">회원정보 수정</h2>
			<div class="modifyForm">
				<p class="modifyTit">정보입력</p>
				<form id="modify_form" action="modifyUser.do" method="post">
					<table>
						<colgroup>
							<col width="25%">
							<col width="auto">
						</colgroup>
						<tbody>
							<tr>
								<th>세대주</th>
								<td><input type="text" name="name" id="name"
									value="${member.name}" maxlength="30"></td>
							</tr>
							<tr>
								<th>전화번호</th>
								<td><input type="text" name="phone" id="phone"
									value="${member.phone}" maxlength="15"></td>
							</tr>
							<tr>
								<th>이메일</th>
								<td><input type="email" name="email" id="email"
									value="${member.email}" maxlength="50"></td>
							</tr>
							<tr>
								<th>차량번호</th>
								<td><input type="text" name="carnum" id="carnum"
									value="${member.carnum}" maxlength="15"></td>
							</tr>
						</tbody>
					</table>

					<div class="btnWrap">
						<input type="submit" value="수정"> <input type="button"
							value="취소"
							onclick="location.href='${pageContext.request.contextPath}/member/myPage.do'">
					</div>
				</form>
			</div> <!-- end of modifyForm -->
		</div> <!-- end of content-main -->
	</div> <!-- end of page-main -->
	<!-- 내용 끝 -->

	<!-- footer 시작 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<!-- footer 끝 -->

</body>
</html>




