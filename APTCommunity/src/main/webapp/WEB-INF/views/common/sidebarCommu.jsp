<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
	$(function(){
		$.ajax({
			url:'getdongho.do',
			type:'post',
			data:{user_num:${user_num}},
			dataType:'json',
			success:function(param){
				let output = param.dong+'동 '+param.ho+'호';
				$('#output').append(output);
			},
			error:function(){
				alert('네트워크오류발생');
			}
		});
	});
</script>
<div class="sidebar">
	<div class="mem-service">
		<p id="output"></p>
		<div class="service-myPage-button">
			<a href="${pageContext.request.contextPath}/member/myPage.do">MY 페이지</a>
		</div>
		<div class="service-logout-button">
			<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
		</div>
	</div>
	<h2>커뮤니티</h2>
	<div class="menu">
		<ul>
			<li><a href="${pageContext.request.contextPath}/board/boardList.do?dept=1">자유게시판</a></li>
			<li><a href="${pageContext.request.contextPath}/secondhand/seBuyList.do?dept=2">중고구매</a></li>
			<li><a href="${pageContext.request.contextPath}/secondhand/seSaleList.do?dept=3">중고판매</a></li>
		</ul>
	</div>
</div>