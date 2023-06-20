<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#search_form').submit(function(){
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
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div class="content-main">
		<h2>회원목록(관리자 전용)</h2>
		<!-- 검색창 시작 -->  
		<form id="search_form" action="memberList.do"
		                          method="get">
			<ul class="search">
				<li>
					<select name="keyfield">
						<option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>세대주</option>
						<option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>이름</option>
						<option value="3" <c:if test="${param.keyfield==3}">selected</c:if>>email</option>
					</select>
				</li>
				<li>
					<input type="search" size="16" 
					   name="keyword" id="keyword"
					                    value="${param.keyword}">
				</li>
				<li>
					<input type="submit" value="검색">
				</li>
			</ul>                          
		</form>
		<!-- 검색창 끝 -->
		<div class="list-space align-right">
			<input type="button" value="목록"
			  onclick="location.href='memberList.do'"> 
			<input type="button" value="홈으로"
			  onclick="location.href='${pageContext.request.contextPath}/main/main.do'">  
		</div>
		<c:if test="${count == 0}">
		<div class="result-display">
			표시할 회원정보가 없습니다.
		</div>
		</c:if>
		<c:if test="${count > 0}">
		<table>
			<tr>
				<th>동-호수</th>
				<th>세대주</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>가입일</th>
				<th>등급</th>
			</tr>
			<c:forEach var="member" items="${list}">
			<tr>
				<td>
					<c:if test="${member.auth > 0}">
					<a href="detailUserForm.do?mem_num=${member.mem_num}">${member.dongho}</a>
					</c:if>
					<c:if test="${member.auth == 0}">
					${member.dongho}
					</c:if>
				</td>
				<td>${member.name}</td>
				<td>${member.email}</td>
				<td>${member.phone}</td>
				<td>${member.reg_date}</td>
				<td>
					<c:if test="${member.auth == 1}">일반</c:if>
					<c:if test="${member.auth == 9}">관리</c:if>
				</td>
			</tr>
			</c:forEach>
		</table>
		<div class="align-center">${page}</div>
		</c:if>
	</div>
	<!-- 내용 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>