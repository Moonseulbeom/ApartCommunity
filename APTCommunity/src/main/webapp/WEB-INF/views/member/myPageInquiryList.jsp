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
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypagefav.css">
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
						<h1 style="font-size: 32px;">나의 문의 목록</h1><hr><br>
						<div class="page-right">
							<div id="myPagd_fav">
								<table id="myFavList">
									<!-- 찜한 게시글이 없는 경우 -->
									<c:if test="${result=='null'}">
										<tr class="no-fav">
											<th>찜한 물품 및 게시글이 없습니다.</th>
										</tr>
									</c:if>
									<tr>
										<th width="10%">글 번호</th>
										<th width="40%">제목</th>
										<th width="20%">작성일</th>
										<th width="10%">답변</th>
									</tr>
									<c:forEach var="al" items="${list}">
									<tr id="myPage_write_list1">
										<td>${al.in_num}</td>
										<td><a href="${pageContext.request.contextPath}/inquiry/detail.do?in_num=${al.in_num}">${al.title}</a></td>
										<td>${al.reg_date}</td>
										<c:if test="${al.cnt > 0}">
											<td>O</td>
										</c:if>
										<c:if test="${al.cnt <= 0}">
											<td>X</td>
										</c:if>
									</tr>
									</c:forEach>
								</table>
								<div class="page-count">${page}</div>
								<div class="paging-button" style="display: none;">
									<input type="button" value="더보기">
								</div>
								<div id="loading" style="display: none;">
									<img src="${pageContext.request.contextPath}/images/loading.gif" width="50" height="50">
								</div>
								<!--  콘텐츠 가두기 끝 -->
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



