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
						<h2>예약/신청</h2>
						<div class="menu">
							<ul>
								<li><a href="${pageContext.request.contextPath}/booking/roomNameList.do">시설예약</a></li>
								<li><a href="${pageContext.request.contextPath}/fix/list.do">하자보수 신청</a></li>
							</ul>
						</div>
					</div>
				</div>
				</li>
				<!-- 오른쪽 -->
				<li>
				<div class="page-right">
					<div class="notice-main-img">
						<img alt="" src="${pageContext.request.contextPath}/img/sideMenuTopImg.jpg">
					</div>
					<form id="search_form" method="get" action="fixList.do">
						<div class="notice-main-search">
							<b>하자보수신청 목록</b>
							<input type="hidden" name="keyfield" value="1">
							<input type="search" size="16" name="keyword" id="keyword" value="${param.keyword}" placeholder="검색어를 입력하세요.">
						</div>
					</form>
					<div class="notice-main-list">
						<c:if test="${ count > 1 || !empty count }">
						<ul>
							<li>글번호</li>
							<li>제목</li>
							<li>작성자</li>
							<li>작성일</li>
						</ul>
						<hr color="#edeff0" noshade="noshade">
						<c:forEach var="fix" items="${list}">
						<ul>
							<li>${fix.fix_num}</li>
							<li><a href="fixDetail.do?fix_num=${fix.fix_num}">${fix.title}</a></li>
							<li>${fix.dongHo}</li>
							<li>${fix.reg_date}</li>
						</ul>
						<hr color="#edeff0" noshade="noshade">
						</c:forEach>
						</c:if>
						<c:if test="${ count < 1 || empty count }">
							<div class="result-notice-display">
								게시글이 없습니다.
							</div>
							<hr color="#edeff0" noshade="noshade">
						</c:if>
					</div>
					<div class="write-btn">
						<span>
							<a href="writeFixForm.do">
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
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>