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
			<ul>
				<li class="rnlLi">
					<div class="rnlBox rnlBoxMR">
						<span class="bText">독서실</span>
						<img alt="독서실" src="${pageContext.request.contextPath}/img/Study-Room.png">
						<button class="rnlBtn" value="">선택</button>
					</div>
				</li>
				<li class="rnlLi">
					<div class="rnlBox rnlBoxMR">
						<span class="bText">회의실</span>
						<img alt="회의실" src="${pageContext.request.contextPath}/img/Meeting-Room.png">
						<button type="button" class="rnlBtn" value="">선택</button>
					</div>
				</li>
				<li class="rnlLi">
					<div class="rnlBox">
						<span class="bText">게스트하우스</span>
						<img alt="게스트하우스" src="${pageContext.request.contextPath}/img/Guest-House.png">
						<button class="rnlBtn" value="">선택</button>
					</div>
				</li>
			</ul>
	</div>
	<!-- 내용 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>