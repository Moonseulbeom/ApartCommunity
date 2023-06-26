<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/manage.js"></script>
<!-- 회원 관리 상세 페이지 -->
<div id="mem_detail">
	<h1>회원 상세 페이지</h1>
	<form action="${ pageContext.request.contextPath }/member/modifyUserForm.do" method="post" id="mem_detail_form">
		<div class="form-mem-detail">  
			<div>
			<label for="name">세대주</label>
			<input type="text" id="name" class="name" value="${ member.name }">
			</div>
			<div>
			<label for="passws">비밀번호</label>
			<input type="password" id="passwd" class="passwd" value="${ member.passwd }">
			</div>
			<div>
			<label for="phone">전화번호</label>
			<input type="text" id="phone" class="phone" value="${ member.phone }">
			</div>
			<div>
			<label for="email">이메일</label>
			<input type="email" id="email" class="email" value="${ member.email }">
			</div>
			<div>
			<label for="carnum">차량번호</label>
			<input type="text" id="carnum" class="carnum" value="${ member.carnum }">								
			</div>
		</div>
	</form>
</div>