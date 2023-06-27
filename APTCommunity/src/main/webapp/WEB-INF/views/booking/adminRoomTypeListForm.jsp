<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방 타입 수정 페이지</title>
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
			<span id="rnlTopMenu">시설예약 &nbsp;&nbsp;> &nbsp;&nbsp;관리자수정페이지&nbsp;&nbsp;></span>
		</div>
		<hr class="Mhr" color="#edeff0" noshade="noshade">
		<h4 class="rName">
			현재 선택한 시설 -  ${room_name}
		</h4>
		<h4 class="center">활성화 - 비활성화 체크후, 확인을 눌러주세요.</h4>
		<form id="roomType_form" action="adminRoomTypeUpdate.do" method="post">
		<input type="hidden" name="room_name" value="${room_name}">
		<!-- 회의실 일 경우 -->
			<ul>
				<c:forEach var="room" items="${list}" varStatus="status">
					<li class="rnlLi">
						<div class="rnlBox rnlBoxMR">
							<input type="hidden" name="room_num${status.index}" value="${room.room_num}">
							<img alt="${room.room_type}" src="${pageContext.request.contextPath}/img/meetingRoom.jpeg">
							<span class="bText">${room.room_type}</span>
							<p class="pCnt">(최대 인원 수 : ${room.total_mem}명)</p>
							<c:if test="${room.room_status==0}">
							<div>
								활성화 <input type="radio" name="room_status${status.index}" value="0" checked="checked">
								비활성화 <input type="radio" name="room_status${status.index}" value="1" >
							</div>
							</c:if>
							<c:if test="${room.room_status==1}">
							<div>
								활성화 <input type="radio" name="room_status${status.index}" value="0" >
								비활성화 <input type="radio" name="room_status${status.index}" value="1" checked="checked">
							</div>
							</c:if>
						</div>
					</li>
				</c:forEach>
			</ul>
			<div class="bottom-btn">
				<button type="submit" class="sub-btn">확인</button>
			</div>
		</form>
	</div>
	<!-- 내용 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>