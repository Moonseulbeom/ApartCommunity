<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>하자보수 목록 페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/fixMain.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript">
	$(function(){
		$('#search_form').on('submit',function(){
			if($('#keyword').val().trim()==''){
				alert('검색어를 입력하세요');
				$('#keyword').val('').focus();
				return false;
			}
		});
	});
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
						<h2>예약/신청</h2>
						<div class="menu">
							<ul>
								<li><a href="${pageContext.request.contextPath}/booking/roomNameList.do">시설예약</a></li>
								<li><a href="${pageContext.request.contextPath}/fix/fixList.do">하자보수 신청</a></li>
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
					<!-- 검색 시작 -->
					<div class="main-search">
						<b>하자보수 목록</b>
						<form id="search_form" method="get" action="fixList.do">
							<ul>
								<li>
									<input type="hidden" name="keyfield" value="1">
									<input type="search" size="16" name="keyword" id="keyword" value="${param.keyword}" placeholder="제목으로 검색" autocomplete="off">
								</li>
								<li>
									<input type="submit" value="검색">
								</li>
							</ul>
						</form>
					</div>
					<!-- 검색 끝 -->
					<div class="main-list">
						<ul>
							<li>글번호</li>
							<li>제목</li>
							<li>작성자</li>
							<li>작성일</li>
						</ul>
						<hr color="#edeff0" noshade="noshade">
						<!-- 상단 고정 시작 -->
						<div class="board-article-fixed">
						<table class="list-fixed">
						<c:forEach var="fixed" items="${ fixedList }">
							<tr>
								<td colspan="2" class="td-article">
									<div class="board-number-fixed">
										<span>공지</span>
									</div>
									<div class="board-list">
										<a class="article-fixed" href="${pageContext.request.contextPath}/notice/noticeDetail.do?no_num=${ fixed.no_num }">${ fixed.title }</a>
									</div>
								</td>
								<td class="board-name">관리자</td>
								<td class="board-date">${ fixed.reg_date }</td>
							</tr>
						</c:forEach>
						</table>
						</div>
						<!-- 상단 고정 끝 -->
						<!-- 게시글 없는 경우 -->
						<c:if test="${ count < 1 || empty count }">
							<div class="result-fix-display">
								게시글이 없습니다.
							</div>
							<hr color="#edeff0" noshade="noshade">
						</c:if>
						<!-- 게시글 있을때 목록 시작 -->
						<c:if test="${ count > 0 }">
							<div class="board-article">
								<table class="list">
									<c:forEach var="fix" items="${list}">
										<tr>
											<td colspan="2" class="td-article">
												<div class="board-number">
													${fix.fix_num}
												</div>
												<div class="board-list">
													<a class="article" href="fixDetail.do?fix_num=${fix.fix_num}">${fix.title}</a>
												</div>
											</td>
											<td class="board-name">${fix.dongHo}</td>
											<td class="board-date">${fix.reg_date}</td>
										</tr>
									</c:forEach>
								</table>
							</div>
							<div class="page-count">${page}</div>
						</c:if>
						<!-- 게시글 목록 끝 -->
					</div>
					<div class="write-btn">
						<span>
							<a href="writeFixForm.do">
								<img alt="" src="${pageContext.request.contextPath}/img/write_btn.png">글쓰기
							</a>
						</span>
					</div>
				</div>
				</li>
				<!-- 오른쪽 -->
			</ul>
			</div>
		<!-- 내용 끝 -->
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>