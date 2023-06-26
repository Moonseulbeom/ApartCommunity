<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방 타입 선택 페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/booking.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/booking_calender.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script src="${pageContext.request.contextPath}/js/booking.calender.js"></script>
<script type="text/javascript">
	$(function() {
	  	buildCalendar();
	});
</script>
</head>
<body>
	<div id="wrap">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<!-- 내용 시작 -->
		<div id="mainContainer">
			<div id="rnlTop">
				<span id="rnlTopMenu">시설예약 &nbsp;&nbsp;>&nbsp;&nbsp;타입선택&nbsp;&nbsp;>&nbsp;&nbsp;날짜선택&nbsp;&nbsp;></span>
			</div>
			<hr class="Mhr" color="#edeff0" noshade="noshade">
			<h4 class="rName">
				현재 선택한 시설 - ${room.room_name} - ${room.room_type}
			</h4>
			<h4 class="center">예약 날짜와 시간을 선택하세요.</h4>
			<!-- 합친 div -->
			<div id="cols_wrap">
				<!-- 왼쪽 영역 시작 -->
				<table id="calendar" >
					<tr>
						<td><label class="go-prev" onclick="prevCalendar()"> ◀ </label></td>
						<td id="calendarTitle" colspan="5" align="center" ><label>invalid</label></td>
						<td><label class="go-next" onclick="nextCalendar()"> ▶ </label></td>
					</tr>
					<tr class="weeks">
						<td class="holiday">일</td>
						<td>월</td>
						<td>화</td>
						<td>수</td>
						<td>목</td>
						<td>금</td>
						<td class="saturday">토</td>
					</tr>
				</table>
				<!-- 왼쪽영역 끝 -->
				<div id="timeList">
					<form id="roomTimeSelectForm" action="roomTimeSelect.do" method="post">
						<div id="timeList_content">
							<h1 class="right-text">날짜를 선택하시면 시간목록이 나옵니다</h1>
						</div>
						<input type="hidden" name="bk_date" id="bk_date" value="">
						<input type="hidden" name="start_time" id="start_time" value="">
						<input type="hidden" name="end_time" id="end_time" value="">
						<input type="hidden" name="book_mem" id="book_mem" value="">
						<input type="hidden" name="room_name" id="room_name" value="${room.room_name}">
						<input type="hidden" name="room_type" id="room_type" value="${room.room_type}">
						<input type="hidden" name="room_num" id="room_num" value="${room.room_num}">
						<input type="hidden" id="total_mem" value="${room.total_mem}">
					</form>
				</div>
				<!-- 오른쪽 시작 -->
			</div>
			<!-- 합친 div -->
		</div>
		<div class="clear"></div>
		<!-- 내용 끝 -->
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</div>
</body>
</html>