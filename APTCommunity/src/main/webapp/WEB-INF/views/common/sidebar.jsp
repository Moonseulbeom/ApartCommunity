<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="sidebar">
	<div class="mem-service">
		<p>000동 000호 주민</p>
		<button onclick="location.href='#'">MY 페이지</button>
		<button onclick="location.href='#'">로그아웃</button>	
	</div>
	<h2>공지사항</h2>
	<div class="menu">
		<ul>
			<li><a href="${pageContext.request.contextPath}/notice/noticeList.do?dept=1">관리사무소 공지사항</a></li>
			<li><a href="${pageContext.request.contextPath}/notice/noticeList.do?dept=2">입대의 공지사항</a></li>
			<li><a href="${pageContext.request.contextPath}/notice/noticeList.do?dept=3">건의사항</a></li>
		</ul>
	</div>
</div>