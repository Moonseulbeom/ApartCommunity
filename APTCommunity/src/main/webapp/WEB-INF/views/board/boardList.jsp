<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/commuList.css">
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
		<!-- 상단링크버튼 -->
		<div class="service-up">
			<jsp:include page="/WEB-INF/views/common/up_button.jsp"/>
		</div>
		<div class="page-main">
		<ul>
			<!-- 좌측 사이드바 -->
			<li>
			<div class="page-left">
			<div class="sidebar">
				<jsp:include page="/WEB-INF/views/common/sidebar.jsp"/>
				<h2>커뮤니티</h2>
			<div class="menu">
				<ul>
					<li><a href="${pageContext.request.contextPath}/board/boardList.do">자유게시판</a></li>
					<li><a href="${pageContext.request.contextPath}/secondhand/seBuyList.do">중고구매</a></li>
					<li><a href="${pageContext.request.contextPath}/secondhand/seSaleList.do">중고판매</a></li>
				</ul>
			</div>
			</div>
			</div>
			</li>
			<!-- 우측메인 -->
			<li>
			<div class="page-right">
			<div class="main-search">
				<!-- 검색 시작 -->
				<form id="search_form" method="get" action="boardList.do">
					<div class="commu-main-search">
						<b> 자유게시판 목록 </b>
						<ul class="search">
							<li>
								<select name="keyfield" style="height: 30px;">
									<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>제목</option>
									<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>작성자</option>
									<option value="3" <c:if test="${param.keyfield==3}">selected</c:if>>내용</option>
								</select>
								<input type="search" size="10" name="keyword" id="keyword" 
												value="${param.keyword}" placeholder="검색어를 입력하세요">
								<input type="submit" value="검색" style="height: 30px;">
							</li>
						</ul>
					</div>
				</form>
				</div>
				<div class="commu-main-list">
					<c:if test="${ count > 1 || !empty count }">
					<hr color="#edeff0" noshade="noshade">
					<!-- 고정 게시글 목록 시작 -->
					<div class="board-article-fixed">
					<table class="list-fixed">
					<c:forEach var="fixed" items="${fixedList}">
						<tr>
							<td colspan="2" class="td-article">
								<div class="board-number-fixed">
									<span>공지</span>
								</div>
								<div class="board-list">
									<a class="article-fixed" href="noticeDetail.do?no_num=${fixed.no_num}">${fixed.title}</a>
								</div>
							</td>
							<td class="board-name">관리자</td>
							<td class="board-date">${fixed.reg_date}</td>
						</tr>
					</c:forEach>
					</table>
					</div>
						<!-- 고정 게시글 목록 시작 -->
						<!-- 게시글이 없을 경우 -->
						<c:if test="${ count < 1 || empty count }">
							<div class="result-commu-display">
								게시글이 없습니다.
							</div>
							<hr color="#edeff0" noshade="noshade">
						</c:if>
						<!-- 게시글 목록 시작 -->
						<c:if test="${count > 0}">
						<ul>
							<li>글번호</li>
							<li>제목</li>
							<li>작성자</li>
							<li>작성일</li>
						</ul>
						<div class="board-article">
						<table class="list">
						<c:forEach var="vo" items="${list}">
							<tr>
								<td colspan="2" class="td-article">
									<div class="board-number">
										${vo.board_num}
									</div>
									<div class="board-list">
										<a href="boardDetail.do?board_num=${vo.board_num}">${vo.title}</a>
									</div>
								</td>
								<td class="board-name">${vo.dongho}</td>
								<td class="board-date">${vo.reg_date}</td>
							</tr>
						</c:forEach>
						</table>
						</div><br>
						<div class="page-count">${page}</div>
						</c:if>
						<!-- 게시물 목록 끝 -->
						</c:if><!-- 글목록을 볼수있는 if문 -->
						
					</div>
					<div class="write-btn">
						<!-- 글작성버튼 -->
						<span>
							<a href="boardwriteForm.do">
							<img alt="" src="${pageContext.request.contextPath}/img/write_btn.png">글쓰기</a>
						</span>
						<!-- 자유게시판목록버튼 -->
						<span>
							<c:if test="${empty user_num}">disabled="disabled"</c:if>
							<a href="boardList.do">목록</a>
						</span>
						<!-- 메인페이지버튼 -->
						<span>
							<a href="location.href='${pageContext.request.contextPath}/main/main.do'">메인페이지</a>
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