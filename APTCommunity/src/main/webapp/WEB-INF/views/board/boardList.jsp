<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 목록</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/sidebar.css">
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
      <h2>자유게시판 목록</h2>   
      <!-- 검색창 시작 -->
      
      <div id="sidebar">
	<ul>
		<li>
			<img src="${pageContext.request.contextPath}/img/apt_logo.png" alt="아파트로고" width="280" height="300">
		</li>
	</ul>
	<ul>
		<li>커뮤니티</li>
	</ul>
	<ul>
		<li><a href="#">자유게시판</a></li>
		<li><a href="#">중고구매</a></li>
		<li><a href="#">중고판매</a></li>
	</ul>
	</div>
      
      <form id="search_form" action="list.do" method="get">
         <ul class="search">
            <li>
               <select name="keyfield">
                  <option value="1" <c:if test="${param.keyfield==1}">selected</c:if>>제목</option>
                  <option value="2" <c:if test="${param.keyfield==2}">selected</c:if>>작성자</option>
                  <option value="3" <c:if test="${param.keyfield==3}">selected</c:if>>내용</option>
               </select>
            </li>
            <li>
               <input type="search" size="16" name="keyword" id="keyword" value="${param.keyword}">
            </li>
            <li>
               <input type="submit" value="검색">
            </li>
         </ul>
      </form>
      <!-- 검색창 끝 -->
      <div class="list-space align-right">
         <input type="button" value="글쓰기" onclick="location.href='boardwriteForm.do'">
         <!--       <c:if test="${empty user_num}">disabled="disabled"</c:if>> -->
         <input type="button" value="목록" onclick="location.href='boardList.do'">
         <input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
      </div>
      <c:if test="${count == 0}">
      <div class="result-display">
         표시할 게시물이 없습니다.
      </div>
      </c:if>
      <c:if test="${count > 0}">
      <table>
         <tr>
            <th width="10%">글번호</th>
            <th width="60%">제목</th>
            <th width="15%">작성자</th>
            <th width="15%">작성일</th>
         </tr>
         <c:forEach var="vo" items="${list}">
         <tr>
            <td>${vo.board_num}</td>
            <td><a href="boardDetail.do?board_num=${vo.board_num}">${vo.title}</a></td>
            <td>${vo.dongho}</td>
            <td>${vo.reg_date}</td>
         </tr>            
         </c:forEach>
      </table>
      <div class="align-center">${page}</div>
      </c:if>
   </div>
   <!-- 내용 끝 -->
</div>
</body>
</html>