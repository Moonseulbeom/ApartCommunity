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
//ajax 통신 이용 로그인 박스에 <form>씌우기

</script>
</head>
<body>
	<div id="wrap">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
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
				<p>
					<input type="text" class="login_input_text" id="member_dongho"
						value="" placeholder="동-호수" maxlength="30"
						onkeypress="if(event.keyCode==13) {member_login(); return false;}">
				</p>
				<p>
					<input type="password" class="login_input_text" id="member_passwd"
						value="" placeholder="비밀번호" maxlength="30"
						onkeypress="if(event.keyCode==13) {member_login(); return false;}">
				</p>
				<p>
					<label><input type="checkbox" class="login_checkbox"
						id="autologin">자동로그인</label>
				</p>
				<button type="button" class="loginBtn" onclick="member_login();">로그인</button>
			</div>
			
		</div>

	<!-- 내용 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>
