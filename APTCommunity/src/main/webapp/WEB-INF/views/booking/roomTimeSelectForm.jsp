<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방 타입 선택 페이지</title>
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
		<div id="rnlTop">
			<span id="rnlTopMenu">시설예약 &nbsp;&nbsp;> &nbsp;&nbsp;타입선택&nbsp;&nbsp;></span>
		</div>
		<hr class="Mhr" color="#edeff0" noshade="noshade">
		<h4 class="rName">
			현재 선택한 시설 -  <c:if test="${room_Name=='1'}">회의실</c:if>
								<c:if test="${room_Name=='2'}">도서실</c:if>
								<c:if test="${room_Name=='3'}">게스트하우스</c:if>
		</h4>
		<h4 class="center">예약하실 방 타입을 선택하세요.</h4>
		<form id="roomType_form" action="roomTimeSelect.do" method="get">
		<input type="hidden" name="room_Name" value="${room_Name}">
		<!-- 회의실 일 경우 -->
		</form>
	</div>
	<!-- 내용 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>