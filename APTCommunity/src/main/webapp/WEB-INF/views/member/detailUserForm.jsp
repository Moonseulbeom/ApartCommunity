<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보(관리자 전용)</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<!-- header 시작 -->
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<!-- header 끝 -->

	<!-- 내용 시작 -->
	<div class="page-main">
		<div class="content-main">
			<h2>${member.dongho}의회원정보(관리자 전용)</h2>
			<form action="detailUser.do" method="post" id="detail_form">
				<input type="hidden" name="mem_num" value="${member.mem_num}">
				<ul>
					<li><label>등급</label> <c:if test="${member.auth !=9}">
							<input type="radio" name="auth" value="1" id="auth1"
								<c:if test="${member.auth == 1}">checked</c:if>>일반
					</c:if> <c:if test="${member.auth == 9}">
							<input type="radio" name="auth" value="9" id="auth2" checked>관리
					</c:if></li>
				</ul>
				<div class="align-center">
					<c:if test="${member.auth !=9}">
						<input type="submit" value="수정">
					</c:if>
					<input type="button" value="목록"
						onclick="location.href='memberList.do'">
				</div>
				<ul>
					<li><label>세대주</label>${member.name}</li>
					<li><label>전화번호</label>${member.phone}</li>
					<li><label>이메일</label>${member.email}</li>
					<li><label>차량번호</label>${member.carnum}</li>
				</ul>
			</form>
		</div> <!-- end of content-main -->
	</div> <!-- end of page-main -->
	<!-- 내용 끝 -->


	<!-- footer 시작 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	<!-- footer 끝 -->

</body>
</html>


