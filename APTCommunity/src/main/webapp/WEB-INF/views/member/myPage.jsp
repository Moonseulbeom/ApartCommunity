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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
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
							<li><a href="${pageContext.request.contextPath}/member/myPageInquiryList.do">내 문의내역</a></li>
						</ul>
						<h2><b>예약신청현황</b></h2><br>
						<ul>
							<li><a href="${pageContext.request.contextPath}/member/myPage_booking.do">내 예약현황</a></li>
						</ul>
						<h2><b>나의 활동</b></h2><br>
						<ul>
							<li><a href="${pageContext.request.contextPath}/member/myPageList.do">내가 쓴 글</a></li>
							<li><a href="${pageContext.request.contextPath}/member/myPageReplyList.do">내가 쓴 댓글</a></li>
							<li><a href="${pageContext.request.contextPath}/member/myPageFavList.do">찜한 목록</a></li>
						</ul>
					</div>
					</div>
					</div>
					</li>
					<!-- 우측 메인 -->
					<li>
					<h1 id="title_id">MyPage</h1><hr><br>
					<div class="page-right">
					<div id="myPage_home">
					<!-- 마이페이지 좌측 시작 -->
					<form class="myPage_leftSide">
					<h2>회원정보</h2>
					<div class="mypage-div">
						<ul>
							<li>세대주 : ${member.name}</li>
							<li>동 - 호수 : ${member.dongho}</li>
							<li>전화번호 : ${member.phone}</li>
							<li>이메일 : ${member.email}</li>
							<c:if test="${!empty member.carnum}">
							<li>차량번호 : ${member.carnum}</li>
							</c:if>
							<li>가입일 : ${member.reg_date}</li>								
							<c:if test="${!empty member.modify_date}">
							<li>최근 정보 수정일 : ${member.modify_date}</li>
							</c:if>
						</ul>
					</div>
					<%-- end of .mypage-div --%>
					<div class="mypage-div">
						<h3>
							회원정보 수정 <input type="button" value="회원정보 수정"
								onclick="location.href='modifyUserForm.do'" id="click_memInfo">
						</h3>
						<h3>
							비밀번호 수정 <input type="button" value="비밀번호 수정"
								onclick="location.href='modifyPasswordForm.do'" id="click_memInfo">
						</h3>
						<h3>
							회원탈퇴 <input type="button" value="회원탈퇴"
								onclick="location.href='deleteUserForm.do'" id="click_memInfo">
						</h3>
					</div>
					</form>
					<!-- 마이페이지 좌측 끝 -->
					<!-- 마이페이지 우측 시작 -->
					<form class="myPage_rightSide">
						<h2>찜 목록&nbsp;&nbsp;&nbsp;<input type="button" value="더보기" id="more_btn" 
							onclick="location.href='myPageFavList.do'"></h2>
						<table id="myChoice">
							<tr>
								<th>글 번호</th>
								<th>제목</th>
								<th>등록일</th>
								<th>비고</th>
							</tr>
							<c:forEach var="vo" items="${favList}">
								<tr>
									<td>${vo.se_num}</td>
									<td><a href="${pageContext.request.contextPath}/secondhand/seBuyDetail.do?se_num=${vo.se_num}">${fn:substring(vo.title,0,12)}</a></td>
									<td>${vo.reg_date}</td>
									<td></td>
								</tr>
							</c:forEach>
						</table>
						<h2>내가 쓴 글&nbsp;&nbsp;&nbsp;<input type="button" value="더보기" id="more_btn" 
							onclick="location.href='myPageList.do'"></h2>
						<table id="myChoice">
							<tr>
								<th>글 번호</th>
								<th>제목</th>
								<th>등록일</th>
								<th>비고</th>
							</tr>
							<c:forEach var="hohohoho" items="${myList}">
								<tr>
									<td>${hohohoho.num}</td>
									<td><a href="#">${hohohoho.title}</a></td>
									<td>${hohohoho.reg_date}</td>
									<td></td>
								</tr>
							</c:forEach>
						</table>
						<h2>예약 현황&nbsp;&nbsp;&nbsp;<input type="button" value="더보기" id="more_btn" 
							onclick="location.href='myPage_booking.do'"></h2>
						<table id="myChoice">
							<tr>
								<th>예약 번호</th>
								<th>시설명</th>
								<th>예약일자</th>
								<th>예약인원</th>
							</tr>
							<c:forEach var="bk" items="${bkList}">
								<tr>
									<td>${bk.bk_num}</td>
									<td><a href="#">${bk.room_name}</a></td><!-- 시설이름?? -->
									<td>${bk.bk_date}</td>
									<td>${bk.book_mem}</td>
								</tr>
							</c:forEach>
						</table>
						<h2>나의 문의사항&nbsp;&nbsp;&nbsp;<input type="button" value="더보기" id="more_btn" 
							onclick="location.href='myPageInquiryList.do'"></h2>
						<table id="myChoice">
							<tr>
								<th>글 번호</th>
								<th>문의사항</th>
								<th>등록일</th>
								<th>비고</th>
							</tr>
							<c:forEach var="in" items="${inList}">
								<tr>
									<td>${in.in_num}</td>
									<td><a href="#">${in.title}</a></td>
									<td>${in.reg_date}</td>
									<td></td>
								</tr>
							</c:forEach>
						</table>
					</form>
					<!-- 마이페이지 우측 끝 -->
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



