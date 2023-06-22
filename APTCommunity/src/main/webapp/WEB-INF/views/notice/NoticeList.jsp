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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript">
	$(function(){
		$('#search_form').submit(function(){
			if($('#keyword').val().trim() == ''){
				alert('검색어를 입력하세요');
				$('#keyword').val('').focus();
				return false;
			}
		})
	})
</script>
</head>
<body>
<div id="wrap">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
<div class="inner">
	<div id="container" class="inner">
	<!-- 상단 링크 버튼 -->
		<div class="service-up">
			<jsp:include page="/WEB-INF/views/common/up_button.jsp"/>
		</div>
	<div class="page-main">
	<ul>
		<!-- 왼쪽 -->
		<li>
		<div class="page-left">
		<div class="sidebar">
			<jsp:include page="/WEB-INF/views/common/sidebar.jsp"/>
			<h2>공지사항</h2>
		<div class="menu">
		<ul>
			<li><a href="${pageContext.request.contextPath}/notice/noticeList.do?dept=1">관리사무소 공지사항</a></li>
			<li><a href="${pageContext.request.contextPath}/notice/noticeList.do?dept=2">입대의 공지사항</a></li>
			<li><a href="${pageContext.request.contextPath}/notice/noticeList.do?dept=3">건의사항</a></li>
			<li><a href="${pageContext.request.contextPath}/notice/noticeList.do?dept=4">기타</a></li>
		</ul>
		</div>
	</div>
		</div>
		</li>
		<!-- 오른쪽 -->
		<li>
		<div class="page-right">
		
			<div class="main-img">
				<img alt="" src="${pageContext.request.contextPath}/img/sideMenuTopImg.jpg">
			</div>
			<div class="main-search">
				<c:if test="${ dept == 1 }">
					<b>관리사무소 공지사항</b>
				</c:if>
				<c:if test="${ dept == 2 }">
					<b>입대의 공지사항</b>
				</c:if>
				<c:if test="${ dept == 3 }">
					<b>건의사항</b>
				</c:if>
				<c:if test="${ dept == 4 }">
					<b>기타</b>
				</c:if>
				<!-- 검색 시작 -->
				<form id="search_form" action="noticeList.do">
				<ul>
					<li>
						<input type="hidden" name="dept" value="${ dept }">
						<input type="search" size="16" name="keyword" id="keyword" value="${param.keyword}" placeholder="검색어를 입력하세요.">
					</li>
					<li>
						<input type="submit" value="검색">
					</li>
				</ul>
				</form>
				<!-- 검색 끝 -->
			</div>
			<div class="main-list">
				<ul>
					<li>글번호</li>
					<li>제목</li>
					<li>작성자</li>
					<li>작성일</li>
				</ul>
				<hr color="#edeff0" noshade="noshade">
				<!-- 고정 게시글 목록 시작 -->
				<div class="board-article-fixed">
				<table class="list-fixed">
				<c:forEach var="fixed" items="${ fixedList }">
					<tr>
						<td colspan="2" class="td-article">
							<div class="board-number-fixed">
								<span>공지</span>
							</div>
							<div class="board-list">
								<a class="article-fixed" href="noticeDetail.do?no_num=${ fixed.no_num }">${ fixed.title }</a>
							</div>
						</td>
						<td class="board-name">관리자</td>
						<td class="board-date">${ fixed.reg_date }</td>
					</tr>
				</c:forEach>
				</table>
				</div>
				<!-- 고정 게시글 목록 끝 -->
				<!-- 게시글이 없을 때 -->				
				 <c:if test="${ count < 1 || empty count }">
					<div class="result-display">
						게시글이 없습니다.
					</div>
					<hr color="#edeff0" noshade="noshade">
				</c:if>
				<!-- 게시글 목록 시작 -->
				<c:if test="${ count > 0 }">
				<div class="board-article">
				<table class="list">
				<c:forEach var="notice" items="${ list }">
					<tr>
						<td colspan="2" class="td-article">
							<div class="board-number">
								${ notice.no_num }
							</div>
							<div class="board-list">
								<a class="article" href="noticeDetail.do?no_num=${ notice.no_num }">${ notice.title }</a>
							</div>
						</td>
						<td class="board-name">관리자</td>
						<td class="board-date">${ notice.reg_date }</td>
					</tr>
				</c:forEach>
				</table>
				</div>
				<div class="page-count">${page}</div>
				</c:if>
				<!-- 게시글 목록 끝 -->
			</div>
			<c:if test="${ user_auth == 9 }">
			<div class="write-btn">
				<span>
					<!-- 공지사항 페이지 글작성 버튼 -->
					<c:if test="${ dept != 4 }">
					<a href="writeNoticeForm.do" >
					<img alt="" src="${pageContext.request.contextPath}/img/write_btn.png">
					글쓰기
					</a>
					</c:if>
					<!-- 기타 페이지 공지사항 글작성 버튼 -->
					<c:if test="${ dept == 4 }">
					<a href="writeCategoryNoticeForm.do" >
					<img alt="" src="${pageContext.request.contextPath}/img/write_btn.png">
					글쓰기
					</a>
					</c:if>
				</span>
			</div>
			</c:if>
		</div>
		</li>
		<!-- 오른쪽 -->
	</ul>
	</div>
		</div>
	</div>
	<!-- 내용 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>