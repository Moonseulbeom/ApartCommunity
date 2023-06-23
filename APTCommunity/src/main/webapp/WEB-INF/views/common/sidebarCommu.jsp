<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar_yyj.css">
<script>
	$(function(){
		$.ajax({
			url:'getdongho.do',
			type:'post',
			data:{user_num:${user_num}},
			dataType:'json',
			success:function(param){
				let out = param.dong+'동 '+param.ho+'호';
				$('#out').append(out);
			},
			error:function(){
				alert('네트워크오류발생');
			}
		});
	});
</script>

	<div class="mem-service">
		<c:if test="${ user_auth ==  1 }">
		<p  id="out">
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



