<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<span id="rnlTopMenu">시설예약 &nbsp;&nbsp;></span>
		</div>
		<hr class="Mhr" color="#edeff0" noshade="noshade">
		<form id="roomName_form" action="roomTypeList.do" method="get">
			<ul>
			<!-- room_name = 1:회의실, 2:도서실, 3:게스트하우스 -->
				<li class="rnlLi">
					<div class="rnlBox rnlBoxMR">
						<img alt="독서실" src="${pageContext.request.contextPath}/img/Study-Room.png">
						<span class="bText">도서실</span>
						<button type="submit" class="rnlBtn" value="2" name="lib">선택</button>
					</div>
				</li>
				<li class="rnlLi">
					<div class="rnlBox rnlBoxMR">
						
						<img alt="회의실" src="${pageContext.request.contextPath}/img/Meeting-Room.png">
						<span class="bText">회의실</span>
						<button type="submit" class="rnlBtn" value="1" name="meet">선택</button>
					</div>
				</li>
				<li class="rnlLi">
					<div class="rnlBox">
						<img alt="게스트하우스" src="${pageContext.request.contextPath}/img/Guest-House.png">
						<span class="bText">게스트하우스</span>
						<button type="submit" class="rnlBtn" value="3" name="house">선택</button>
					</div>
				</li>
			</ul>
		</form>
	</div>
	<!-- 내용 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>