<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>하자보수 목록 페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/fixMain.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
</head>
<body>
<div id="wrap">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div class="topM"></div>
	<div id="mainContainer">
		<div id="sideBar">
			<ul>
				<li>
					<div id="sideLogin">
					<%-- user_num 세션을 체크해서, 로그인했을 경우와 안했을 경우 내용물을 변경 --%>
						로그인 박스
					</div>
				</li>
				<li>
					<h3 id="sideMenuTitle">예약 신청</h3>				
				</li>
				<li>
					<div id="sideMenu">
						<ul>
							<li class="first">
								<a href="${pageContext.request.contextPath}/booking/roomNameList.do">시설 예약</a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/fix/list.do">하자보수 신청</a>
							</li>
						</ul>
					</div>
				</li>
			</ul>
		</div>
		<div id="sideBox">
			<img id="sideMenuTop" alt="탑이미지" src="${pageContext.request.contextPath}/img/sideMenuTopImg.jpg">
			<div id="sideBox_inner">
				<h2>하자보수 신청</h2>
				<!-- 검색창 시작 -->
				<form id="fix_search_form" action="fix_list.do" method="get">
					<ul class="search">
						<li>
							<%-- 항상 제목 기준으로  --%>
							<input type="hidden" name="keyfield" value="1">
							<input type="search" size="16" name="keyword" id="keyword" value="">
						</li>
						<li>
							<input type="submit" value="검색">
						</li>
					</ul>
				</form>
				<!-- 검색창 끝 -->
			</div>
			<!-- 글 목록 시작 -->
			<table id="list_table">
				<tr>
					<th>No.</th>
					<th>제목</th>
					<th>작성자</th>
				</tr>
				<tr>
					<td>1</td>
					<td>테스트용 제목</td>
					<td>홍길동</td>
				</tr>
			</table>
			<div class="align-center">
			</div>
			<!-- 글 목록 끝 -->
		</div>
	</div>
	<!-- 내용 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>