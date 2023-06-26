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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
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
							<li><a href="${pageContext.request.contextPath}/#">마이 페이지</a></li>
							<li><a href="${pageContext.request.contextPath}/#">회원정보 수정</a></li>
						</ul>
						<h2><b>문의내역</b></h2><br>
						<ul>
							<li><a href="${pageContext.request.contextPath}/#">내 문의내역</a></li>
						</ul>
						<h2><b>예약신청현황</b></h2><br>
						<ul>
							<li><a href="${pageContext.request.contextPath}/#">내 예약현황</a></li>
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
					<h1 style="font-size: 32px;">MyPage</h1><hr><br>
					<div class="page-right">
					<div id="myPage_home">
					<!-- 마이페이지 좌측 시작 -->
					<form class="myPage_leftSide">
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
					</form>
					<!-- 마이페이지 좌측 끝 -->
					<!-- 마이페이지 우측 시작 -->
					<form class="myPage_rightSide">
					<h2>찜 목록</h2>
					<table id="myChoice">
						<tr>
							<th>글 번호</th>
							<th>제목</th>
							<th>등록일</th>
							<th>작성자</th>
						</tr>
						<c:forEach var="vo" items="${favList}">
						<tr>
							<td>${vo.se_num}</td>
							<td><a href="${pageContext.request.contextPath}/secondhand/seBuyDetail.do?se_num=${vo.se_num}">${fn:substring(vo.title,0,12)}</a></td>
							<td>${vo.reg_date}</td>
							<td>${vo.dongho}</td>
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



