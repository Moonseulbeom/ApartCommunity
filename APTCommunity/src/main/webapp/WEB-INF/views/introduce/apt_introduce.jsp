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
			<div class="introduce">
				<h1>쌍용 아파트 소개</h1>
				<p>쌍용 아파트는 서울특별시 강남구에 위치한 아파트 단지입니다. 1987년 준공되었으며, 27개동, 총
					1,950세대로 이루어져 있습니다. 쌍용 아파트는 강남의 중심부에 위치하고 있어 교통이 매우 편리합니다. 또한, 주변에
					대학교, 병원, 백화점, 영화관 등 편의시설이 잘 갖추어져 있습니다. 쌍용 아파트는 입주자 자치회가 활발히 운영되고
					있으며, 다양한 커뮤니티 활동이 이루어지고 있습니다. 쌍용 아파트는 강남의 대표적인 아파트 단지 중 하나로, 많은
					사람들이 선호하고 있습니다.</p>
				<h2>쌍용 아파트의 장점</h2>
				<ul>
					<li>강남의 중심부에 위치하고 있어 교통이 매우 편리합니다.</li>
					<li>주변에 대학교, 병원, 백화점, 영화관 등 편의시설이 잘 갖추어져 있습니다.</li>
					<li>입주자 자치회가 활발히 운영되고 있으며, 다양한 커뮤니티 활동이 이루어지고 있습니다.</li>
				</ul>
				<h2>쌍용 아파트의 단점</h2>
				<ul>
					<li>아파트가 오래되어 노후화되었습니다.</li>
					<li>세대수가 많아 주차장이 부족합니다.</li>
					<li>아파트가 밀집되어 있어 소음 문제가 발생할 수 있습니다.</li>
				</ul>
			</div>
			<!-- 내용 끝 -->
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>