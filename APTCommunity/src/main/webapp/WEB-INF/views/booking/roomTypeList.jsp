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
			현재 선택한 시설 -  ${room_name}
		</h4>
		<h4 class="center">예약하실 방 타입을 선택하세요.</h4>
		<form id="roomType_form" action="roomTimeSelectForm.do" method="get">
		<!-- 회의실 일 경우 -->
			<ul>
				<c:forEach var="room" items="${list}">
					<li class="rnlLi">
						<div class="rnlBox rnlBoxMR">
							<img alt="${room.room_type}" src="${pageContext.request.contextPath}/img/meetingRoom.jpeg">
							<span class="bText">${room.room_type}</span>
							<p class="pCnt">(최대 인원 수 : ${room.total_mem}명)</p>
							<c:if test="${room.room_status==1}">
								<button type="submit" name="room_num" class="disable" value="${room.room_num}" disabled="disabled">선택불가</button>
							</c:if>
							<c:if test="${room.room_status==0}">
								<button type="submit" name="room_num" class="rnlBtn" value="${room.room_num}">선택</button>
							</c:if>
						</div>
					</li>
				</c:forEach>
			</ul>
		</form>
	</div>
	<!-- 내용 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>