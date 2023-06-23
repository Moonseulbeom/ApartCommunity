<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/manager.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#mem_search_form').submit(function(){
			if($('#keyword').val().trim() == ''){
				alert('검색할 내용을 입력하세요');
				$('#key').val('').focus();
				return false;
			}
		})
	})
</script>
</head>
<body>
<div class="wrap">
<!-- 상단 검은색 메뉴바 -->
	<div class="manage-header">
			<ul>
				<li>
					<div class="manage-header-img">
						<a href="${pageContext.request.contextPath}/main/main.do">
						<img src="${pageContext.request.contextPath}/img/apt_logo.png" alt="아파트로고" />
						</a>
					</div>
				</li>
				<li>
					<div class="manage-header-btn">
						[관리자]
						<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
						<a href="${pageContext.request.contextPath}/main/main.do">메인 페이지</a>
					</div>
				</li>
			</ul>
	</div>
<!-- 상단 검은색 메뉴바 끝-->
<!-- 메인 시작 -->
<!-- 왼쪽 사이드 바 시작 -->
	<!-- 내용 시작 -->
	<div class="inner">
		<div id="container" class="inner">
		<div class="manage-page-main">
		<ul>
			<!-- 왼쪽 -->
			<li>
			<div class="manage-page-left">
				<a id="manage-title" href="manageMain.do">Admin</a>
					<div class="manage-menu">
							<div><a href="${pageContext.request.contextPath}/notice/noticeList.do?dept=1"></a>회원관리</div>
							<div><a href="${pageContext.request.contextPath}/notice/noticeList.do?dept=2">공지글 작성</a></div>
							<div><a href="${pageContext.request.contextPath}/notice/noticeList.do?dept=3">머리 공지글 작성</a></div>
							<div><a href="${pageContext.request.contextPath}/notice/noticeList.do?dept=4">1:1문의 관리</a></div>
							<div><a href="${pageContext.request.contextPath}/notice/noticeList.do?dept=4">하자 보수글 관리</a></div>
							<div><a href="${pageContext.request.contextPath}/notice/noticeList.do?dept=4">예약 관리</a></div>
					</div>	  
			</div>
			</li>
			<!-- 오른쪽 -->
			<li>
			<div class="manage-page-right">
				<div class="manage-content">
				<!--  목록 검색창 -->
				<h1>회원목록조회</h1>	
				<form action="manageMain.do" method="get" id="mem_search_form">
				<ul>
					<li>
						<div class="mem-search">
							<select name="mem_select" class="select-member">
								<option value="1" <c:if test="${param.mem_select==1}">selected</c:if>>세대주</option>
								<option value="2" <c:if test="${param.mem_select==2}">selected</c:if>>동</option>
								<option value="3" <c:if test="${param.mem_select==3}">selected</c:if>>호수</option>
							</select>
							<input type="search" placeholder="회원 목록 조회" class="search-member" name="keyword" id="keyword" value="${ param.keyword }">
						</div>
					</li>
					<li>
						<div class="mem-search-btn">
							<input type="submit" value="검색">
						</div>
					</li>
				</ul>
				</form>
				<!--  목록 검색참 끝 -->
				<!-- 회원 목록 -->
				<div class="mem-list">
					<table>
					
						<tr>
							<th>동-호수</th>
							<th>세대주</th>
							<th>전화번호</th>
							<th>가입일</th>
						</tr>
					<c:if test="${ count > 0 }">
					<c:forEach var="mem" items="${ list }">
						<tr>
							<td>${ mem.dongho }</td>
							<td>${ mem.name }</td>
							<td>${ mem.phone }</td>
							<td>${ mem.reg_date }</td>
						</tr>
					</c:forEach>
					</c:if>
					</table>
					<c:if test="${ count > 0 }">
						<div class="mem-page">${ page }</div>
					</c:if>
					<c:if test="${ count < 1 || empty count }">
						<div class="result-display">	
							없는 회원입니다.
						</div>
						<hr color="#edeff0" noshade="noshade">
					</c:if>
					
					
				</div>
				</div>
			</div>
			</li>
			<!-- 오른쪽 -->
		</ul>
		</div>
		</div>
	</div>
	<!-- 내용 끝 -->
<!-- 왼쪽 사이드 바 시작 -->
<!-- 메인 끝 -->

</div>
	
</body>
</html>