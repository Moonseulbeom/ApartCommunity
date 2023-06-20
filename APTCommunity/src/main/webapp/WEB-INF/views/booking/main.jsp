<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약/하자보수 메인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/booking.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
</head>
<body>
<div id="wrap">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div id="mainContainer">
		<ul id="bookingMain">
			<li class="bMainLi"><span class="bText">시설예약</span><br>
				<div class="mOver">
					<a href="${pageContext.request.contextPath}/booking/roomNameList.do">
					<img class="bImg" alt="시설예약" src="${pageContext.request.contextPath}/img/circularwallclock.png">
					</a>
				</div>
			</li>
			<li class="bMainLi"><span class="bText">하자보수 신청</span><br>
				<div class="mOver">
					<a href="${pageContext.request.contextPath}/fix/fixList.do">
					<img class="bImg" alt="하자보수" src="${pageContext.request.contextPath}/img/serviceTool.png">
					</a>
				</div>
			</li>
		</ul>
	</div>
	<!-- 내용 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>
