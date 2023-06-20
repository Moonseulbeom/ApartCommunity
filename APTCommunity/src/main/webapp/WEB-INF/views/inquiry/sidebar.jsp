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
			<li><a href="${pageContext.request.contextPath}/inquiry/list.do">1:1 문의</a></li>
			<li><a href="${pageContext.request.contextPath}/question/list.do">자주 묻는 질문</a></li>
		</ul>
	</div>
</div>