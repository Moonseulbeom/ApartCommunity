<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1 문의</title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/inquiry.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
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
			<div class="sidebar">
	<div class="mem-service">
		<p>000동 000호 주민</p>
		<button onclick="location.href='#'">MY 페이지</button>
		<button onclick="location.href='#'">로그아웃</button>	
	</div>
	<h2>공지사항</h2>
	<div class="menu">
		<ul>
			<li><a href="${pageContext.request.contextPath}/inquiry/list.do">1:1 문의</a></li>
			<li><a href="${pageContext.request.contextPath}/question/list.do">자주 묻는 질문</a></li>
		</ul>
	</div>
</div>
		</div>
		</li>
		<!-- 오른쪽 -->
		<li>
		<div class="page-right">
			<div class="notice-main-img">
				<img alt="" src="${pageContext.request.contextPath}/images/apart.jpg">
			</div>
			<div class="notice-main-search">
				<b>1:1 문의 목록</b>
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
				<c:forEach var="inquiry" items="${list}">
					<tr>
						<td colspan="2" class="td-article">
							<div class="board-number">
								${inquiry.in_num}
							</div>
							<div class="board-list">
								<a class="article" href="detail.do?in_num=${inquiry.in_num}">${inquiry.title }</a>
							</div>
						</td>
						<td class="board-name">관리자</td>
						<td class="board-date">${inquiry.reg_date}</td>
					</tr>
				</c:forEach>
				</table>
				</div>
				</c:if>
			</div>
			<div class="write-btn">
				<span>
					<a href="writeForm.do">
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