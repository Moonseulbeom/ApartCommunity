<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시설 선택 페이지</title>
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
		<h4 class="center">예약하실 방 타입을 선택하세요.</h4>
		<form id="roomType_form" action="roomTimeSelect.do" method="get">
		<input type="hidden" name="room_Name" value="${room_Name}">
		<!-- 회의실 일 경우 -->
		<c:if test="${room_Name=='1'}">
			<ul>
				<li class="rnlLi">
					<div class="rnlBox rnlBoxMR">
						<span class="bText">A타입</span>
						<img alt="A타입" src="${pageContext.request.contextPath}/img/meetingRoom.jpeg">
						<button type="submit" class="rnlBtn" value="A">선택</button>
					</div>
				</li>
				<li class="rnlLi">
					<div class="rnlBox rnlBoxMR">
						<span class="bText">B타입</span>
						<img alt="B타입" src="${pageContext.request.contextPath}/img/meetingRoom.jpeg">
						<button type="submit" class="rnlBtn" value="B">선택</button>
					</div>
				</li>
				<li class="rnlLi">
					<div class="rnlBox">
						<span class="bText">C타입</span>
						<img alt="C타입" src="${pageContext.request.contextPath}/img/meetingRoom.jpeg">
						<button type="submit" class="rnlBtn" value="C">선택</button>
					</div>
				</li>
			</ul>
		</c:if>
		<c:if test="${room_Name=='2'}">
			<ul>
				<li class="rnlLi">
					<div class="rnlBox rnlBoxMR">
						<span class="bText">A타입</span>
						<img alt="A타입" src="${pageContext.request.contextPath}/img/study.jpeg">
						<button type="submit" class="rnlBtn" value="A">선택</button>
					</div>
				</li>
				<li class="rnlLi">
					<div class="rnlBox rnlBoxMR">
						<span class="bText">B타입</span>
						<img alt="B타입" src="${pageContext.request.contextPath}/img/study.jpeg">
						<button type="submit" class="rnlBtn" value="B">선택</button>
					</div>
				</li>
				<li class="rnlLi">
					<div class="rnlBox">
						<span class="bText">C타입</span>
						<img alt="C타입" src="${pageContext.request.contextPath}/img/study.jpeg">
						<button type="submit" class="rnlBtn" value="C">선택</button>
					</div>
				</li>
			</ul>
		</c:if>
		<c:if test="${room_Name=='3'}">
			<ul>
				<li class="rnlLi">
					<div class="rnlBox rnlBoxMR">
						<span class="bText">A타입</span>
						<img alt="A타입" src="${pageContext.request.contextPath}/img/guestHouse.jpg">
						<button type="submit" class="rnlBtn" value="A">선택</button>
					</div>
				</li>
				<li class="rnlLi">
					<div class="rnlBox rnlBoxMR">
						<span class="bText">B타입</span>
						<img alt="B타입" src="${pageContext.request.contextPath}/img/guestHouse.jpg">
						<button type="submit" class="rnlBtn" value="B">선택</button>
					</div>
				</li>
				<li class="rnlLi">
					<div class="rnlBox">
						<span class="bText">C타입</span>
						<img alt="C타입" src="${pageContext.request.contextPath}/img/guestHouse.jpg">
						<button type="submit" class="rnlBtn" value="C">선택</button>
					</div>
				</li>
			</ul>
		</c:if>
		</form>
	</div>
	<!-- 내용 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>