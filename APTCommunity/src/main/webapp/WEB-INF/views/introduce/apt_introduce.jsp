<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아파트 소개</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/notice.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp" />
<div id="wrap">
	<div class="inner">
		<div id="container" class="inner">
			<!-- 상단 링크 버튼 시작-->
			<div class="service-up">
				<jsp:include page="/WEB-INF/views/common/up_button.jsp" />
			</div>
			<!-- 상단 링크 버튼 끝-->

			<!-- 내용 시작 -->
			<h1>쌍용 아파트 소개</h1>
			<div class="text-align">
				<img src="../images/introduce.gif">
			</div>
			<!-- 내용 끝 -->
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>