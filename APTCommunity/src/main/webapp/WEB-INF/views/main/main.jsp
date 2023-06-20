<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript">
$('#loginBtn').click(function(){

	// dong과 ho 값을 연결
	let dongho = $('#dong').val() + '-' + $('#ho').val();
	
	//서버와의 통신
	$.ajax({
		url:'${pageContext.request.contextPath}/member/login.do',
		type:'post',
		data: { id: dongho, passwd: passwd},
		dataType:'json',
		success:function(param){
				
		},
		error:function(){
			alert('네트워크 오류 발생');
		}
	});
	
});//end of click
</script>
</head>
<body>
	<div id="wrap">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<div class="inner">
		<div id="container" class="inner">
		</div>
		<!-- 내용 시작 -->
		<div class="main_loling_wrap fl">
			<ul class="slide" id="main_slider">
				<li><img
					src="https://d3fixtu11mscj5.cloudfront.net/aptner/apt/A10026403/apt_info/A10026403_slide_KBAGOA5169QC7.png?t=1686985895">
				</li>
			</ul>
		</div>
		<div class="mLogin_box mLogout">
			<p class="loginGreetingt">
				<span>쌍용아파트</span>에 오신 것을 환영합니다.
			</p>
			
			<div class="loginBox">
				<form class="submit" method="post">
				<p>
					<input type="text" class="login_input_text" id="member_dong"
						placeholder="동" maxlength="10">
				</p>
				<p>
					<input type="text" class="login_input_text" id="member_ho"
						placeholder="호" maxlength="10">
				</p>
				<p>
					<input type="password" class="login_input_text" id="member_passwd"
						placeholder="비밀번호" maxlength="30">
				</p>
				<input type="submit" value="로그인" class="loginBtn">
				</form>
			</div>
			
		</div>

	<!-- 내용 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>
