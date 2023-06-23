<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MY페이지</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/custom.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/mypage.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/notice.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div id="wrap">
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
										<h2><b>회원정보</b></h2>
										<ul>
											<li>
												<a href="${pageContext.request.contextPath}/#">마이 페이지</a>
											</li>
											<li>
												<a href="${pageContext.request.contextPath}/#">회원정보 수정</a>
											</li>
										</ul>
										<h2><b>문의내역</b></h2>
										<ul>
											<li>
												<a href="${pageContext.request.contextPath}/#">내 문의내역</a>
											</li>
										</ul>
										<h2><b>예약신청현황</b></h2>
										<ul>
											<li>
												<a href="${pageContext.request.contextPath}/#">내 예약현황</a>
											</li>
										</ul>
										<h2><b>나의 활동</b></h2>
										<ul>
											<li>
												<a href="${pageContext.request.contextPath}/#">내가 쓴 글</a>
											</li>
											<li>
												<a href="${pageContext.request.contextPath}/#">내가 쓴 댓글</a>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</li>
						<div class="content-main">
							<h2>회원정보</h2>
							<div class="mypage-div">
								<ul>
									<li>세대주 : ${member.name}</li>
									<li>전화번호 : ${member.phone}</li>
									<li>이메일 : ${member.email}</li>
									<li>차량번호 : ${member.carnum}</li>
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
										onclick="location.href='modifyUserForm.do'">
								</h3>
								<h3>
									비밀번호 수정 <input type="button" value="비밀번호 수정"
										onclick="location.href='modifyPasswordForm.do'">
								</h3>
								<h3>
									회원탈퇴 <input type="button" value="회원탈퇴"
										onclick="location.href='deleteUserForm.do'">
								</h3>
							</div>
							<div class="mypage-end"></div>
						</div>
						<!-- 내용 끝 -->
					</ul>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>



