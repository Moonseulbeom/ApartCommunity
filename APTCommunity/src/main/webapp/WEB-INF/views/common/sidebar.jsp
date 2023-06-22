<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
$(function(){
	$.ajax({
		url:'getMemname.do',
		type:'post',
		data:{user_num:${user_num}},
		dataType:'json',
		success:function(param){
			let output = param.dong+'동 '+param.ho+'호 주민';
			$('#output').append(output);
		},
		error:function(){
			alert(${ dong });
			alert('네트워크 오류 발생')
		}
	})
})
</script>
<div class="sidebar">
	<div class="mem-service">
		<c:if test="${ user_auth ==  1 }">
		<p  id="output">
		</p>
		<div class="service-myPage-button">
			<a href="${pageContext.request.contextPath}/member/myPage.do">MY 페이지</a>
		</div>
		</c:if>
		<c:if test="${ user_auth == 9 }">
		<p>관리자</p>
		<div class="service-manager-button">
			<ul>
				<li>
					<div>
					<a href="${pageContext.request.contextPath}/member/memberList.do">회원관리	</a>
					</div>
				</li>
				<li>
					<div class="manage-btn">
					<a href="${pageContext.request.contextPath}/member/myPage.do">관리자페이지</a>
					</div>
				</li>
			</ul>
		</div>
		</c:if>
		<div class="service-logout-button">
			<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
		</div>
	</div>
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