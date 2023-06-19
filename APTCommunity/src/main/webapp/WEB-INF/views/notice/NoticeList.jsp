<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/notice.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
</head>
<body>
<div id="wrap">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div class="page-main">
	<ul>
		<!-- 왼쪽 -->
		<li>
		<div class="page-left">
			<jsp:include page="/WEB-INF/views/common/sidebar.jsp"/>
		</div>
		</li>
		<!-- 오른쪽 -->
		<li>
		<div class="page-right">
			<div class="notice-main-img">
				<img alt="" src="${pageContext.request.contextPath}/images/apart.jpg">
			</div>
			<div class="notice-main-search">
				<b>관리사무소 회의 결과</b>
				<input type="search" size="16" name="keyword" id="keyword" value="${param.keyword}" placeholder="검색어를 입력하세요.">
			</div>
			<div class="notice-main-list">
				<ul>
					<li>글번호</li>
					<li>제목</li>
					<li>작성자</li>
					<li>작성일</li>
				</ul>
				<hr color="#edeff0" noshade="noshade">				
				 <c:if test="${ count < 1 || empty count }">
					<div class="result-notice-display">
						게시글이 없습니다.
					</div>
					<hr color="#edeff0" noshade="noshade">
				</c:if>
				
				<%-- <c:if test="${ count > 0 }">
				<table>
					<c:forEach var="notice" items="${ list }">
					<tr>
						<td>${ notice.no_num }</td>
						<td>${ notice.title }</td>
						<td>관리자</td>
						<td>${ notice.reg_date }</td>
					</tr>
					<hr color="#edeff0" noshade="noshade">	
					</c:forEach>
				</table>
				</c:if> --%>
				
				<c:if test="${ count > 0 }">
				<div class="board-article">
				<table class="notice-list">
				<c:forEach var="notice" items="${ list }">
					<tr>
						<td colspan="2" class="td-article">
							<div class="board-number">
								${ notice.no_num }
							</div>
							<div class="board-list">
								<a class="article" href="#">${ notice.title }</a>
							</div>
						</td>
						<td class="board-name">관리자</td>
						<td class="board-date">${ notice.reg_date }</td>
					</tr>
				</c:forEach>
				</table>
				</div>
				</c:if>
			</div>
			<div class="write-btn">
				<span>
					<a href="writeNoticeForm.do">
					<img alt="" src="${pageContext.request.contextPath}/img/write_btn.png">
					글쓰기
					</a>
				</span>
			</div>
		</div>
		</li>
		<!-- 오른쪽 -->
	</ul>
	</div>
	<!-- 내용 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>