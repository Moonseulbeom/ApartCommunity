<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MY페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypageBooking.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/mypageBooking.js"></script>
</head>
<body>
	<div id="wrap">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<!-- 내용 시작 -->
		<div class="inner">
			<div id="container" class="inner">
				<!-- 상단 링크 버튼 시작 -->
				<div class="service-up">
					<jsp:include page="/WEB-INF/views/common/up_button.jsp" />
				</div>
				<!-- 상단 링크 버튼 끝 -->
				<div class="page-main">
				<ul>
					<!-- 왼쪽 -->
					<li>
					<div class="page-left">
						<div class="sidebar">
							<jsp:include page="/WEB-INF/views/common/sidebar.jsp" />
							<div class="menu">
								<h2><b>회원정보</b></h2><br>
								<ul>
									<li><a href="${pageContext.request.contextPath}/member/myPage.do">마이 페이지</a></li>
									<li><a href="${pageContext.request.contextPath}/member/modifyUserForm.do">회원정보 수정</a></li>
								</ul>
								<h2><b>문의내역</b></h2><br>
								<ul>
									<li><a href="${pageContext.request.contextPath}/#">내 문의내역</a></li>
								</ul>
								<h2><b>예약신청현황</b></h2><br>
								<ul>
									<li><a href="${pageContext.request.contextPath}/member/myPage_booking.do">내 예약현황</a></li>
								</ul>
								<h2><b>나의 활동</b></h2><br>
								<ul>
									<li><a href="${pageContext.request.contextPath}/#">내가 쓴 글</a></li>
									<li><a href="${pageContext.request.contextPath}/#">내가 쓴 댓글</a></li>
									<li><a href="${pageContext.request.contextPath}/#">찜한 목록</a></li>
								</ul>
							</div>
						</div>
					</div>
					</li>
					<!-- 우측 메인 -->
					<li>
						<!-- 수정 해야 합니다. -->
						<h1 style="font-size: 32px;">예약신청현황</h1>
						<hr>
						<br>
						<div class="page-right">
							<div id="myPage_home">
							<!-- 여기에 넣어주시면 됩니다!! -->
								<table id="myBookingList">
									<caption>내 예약 신청 현황</caption>
									<!-- 예약 신청 현황 없는 경우 -->
									<c:if test="${result=='null'}">
										<tr class="no-Booking">
											<th>예약신청한 시설이 없습니다.</th>
										<tr>
									</c:if>
									<c:if test="${result=='success'}">
										<tr>
											<th>예약번호</th>
											<th>시설</th>
											<th>타입</th>
											<th>인원</th>
											<th>예약날짜</th>
											<th>시간</th>
										</tr>
										<c:forEach var="book" items="${list}">
										<tr>
											<td>${book.bk_num}</td>
											<td>${book.room_info.room_name}</td>
											<td>${book.room_info.room_type}</td>
											<td>${book.book_mem}</td>
											<td>${book.bk_date}</td>
											<td>${book.time}</td>
										</tr>
										</c:forEach>
									</c:if>
								</table>
								<hr id="tM" size="1" noshade="noshade">
								<form id="beforeMyBookingListForm" method="post" action="beforeMyBookingList.do">
									<input type="month" id="search_booking" name="search_booking"><small> " 조회하실 날짜를 선택하세요 "</small>
									<hr>
									<table id="BeforeMyBookingList">
										<caption>이전 예약 조회 </caption>
<!-- 										<tr>
											<th>예약번호</th>
											<th>시설</th>
											<th>타입</th>
											<th>인원</th>
											<th>예약날짜</th>
											<th>시간</th>
										</tr>
										<tr>
											<td>4</td>
											<td>회의실</td>
											<td>201A</td>
											<td>3명</td>
											<td>2023-06-26</td>
											<td>14:00 ~ 16:00</td>
										</tr> -->
									</table>
									<div class="paging-button" style="display:none;">
										<input type="button" value="더보기">
									</div>
									<div id="loading" style="display: none;">
										<img src="${pageContext.request.contextPath}/images/loading.gif" width="50" height="50">
									</div>
								</form>
							<!-- 콘텐츠 가두기 끝 -->
							</div>
							<div class="mypage-end"></div>
						</div>
					</li>
				</ul>
				</div>
				<!-- 내용 끝 -->
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>



