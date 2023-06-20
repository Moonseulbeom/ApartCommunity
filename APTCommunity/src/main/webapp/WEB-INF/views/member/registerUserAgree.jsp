<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>이용약관</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registerUserAgree.css">
</head>
<body>
<div class="page-main">
   <jsp:include page="/WEB-INF/views/common/header.jsp"/>
   		<!-- 내용 시작 -->
		<h1>이용약관</h1>

		<p>본 이용약관은 (주)JSP전자(이하 "회사"라 함)가 제공하는 인터넷 관련 서비스(이하 "서비스"라 함)를
			이용함에 있어 회원과 회사의 권리·의무 및 책임사항을 규정함을 목적으로 합니다.</p>

		<p>① 회원은 본 약관에 동의함으로써 회사가 제공하는 서비스를 이용할 수 있는 자격을 얻습니다.</p>

		<p>② 회사는 본 약관을 변경할 수 있으며, 변경된 약관은 회사의 홈페이지에 게시하여 효력을 발생합니다.</p>

		<p>③ 회원은 변경된 약관에 동의하지 않으면 서비스 이용을 중단할 수 있습니다.</p>

		<p>본 약관은 2023년 6월 19일부터 시행됩니다.</p>

		<input type="button" value="다음"
			onclick="location.href='registerUserForm.do';">
		<!-- 내용 끝 -->
   	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>