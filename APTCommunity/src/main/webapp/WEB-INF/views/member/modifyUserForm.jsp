<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/modify.css">
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
			}
		});
	});
</script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div class="content-main">
		<h2 class="content_h2">회원정보 수정</h2>
		<form id="modify_form" action="modifyUser.do" method="post">
			<ul id="modify_ul">
				<li id="modify_li">
					<label for="name">세대주</label>
					<input type="text" name="name"
					  value="${member.name}"
					  id="name" maxlength="10" class="modify_inp">
				</li>
				<li id="modify_li">
					<label for="phone">전화번호</label>
					<input type="text" name="phone"
					  value="${member.phone}"
					  id="phone" maxlength="15" class="modify_inp">
				</li>
				<li id="modify_li">
					<label for="email">이메일</label>
					<input type="email" name="email"
					  value="${member.email}"
					  id="email" maxlength="50" class="modify_inp">
				</li>
				<li id="modify_li">
					<label for="carnum">차량 번호</label>
					<input type="text" name="carnum"
					  value="${member.carnum}"
					  id="carnum" maxlength="10" class="modify_inp">
				</li>
			</ul> 
			<div class="align-center">
				<input type="submit" value="수정" id="modify_info">
				<input type="button" value="취소"
				 onclick="location.href='${pageContext.request.contextPath}/member/myPage.do'">
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
			</div> 
		</form>
	</div>
	<!-- 내용 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>




