<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
<style type="text/css">
	#back{
		font-weight: bold;
		float: right;
		position: relative;
  		border: 0;
  		padding: 15px 25px;
  		text-align: center;
 		color: black;
	}
	#back:active{
		top: 2px;
	}
	
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/board.reply.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<!-- 내용 시작 -->
	<div class="content-main">
	<input type="button" value="뒤로가기" onclick="location.href='boardList.do'" id="back"><br>
		<h2>${vo.title}</h2> 
		<hr size="1" noshade="noshade" width="100%">
		<ul class="detail-info">
			<li>
			${vo.dongho}
			</li>
		</ul>
		<hr size="1" noshade="noshade" width="100%">
		<c:if test="${!empty vo.filename}">
			<div class="align-center">
				<img src="${pageContext.request.contextPath}/upload/${vo.filename}" class="detail-img">
			</div>
		</c:if>
		<p style="text-decoration: none;">
			${vo.content}
		</p>
		<hr size="1" noshade="noshade" width="100%">
		<ul class="detail-sub">
			<li>
				<c:if test="${!empty vo.modify_date}">
					최근 수정일 : ${vo.modify_date}
				</c:if>
				작성일 : ${vo.reg_date}
				<%-- 로그인한 회원번호와 작성자 회원번호가 일치해야 수정삭제 가능 --%>
				<c:if test="${user_num == vo.mem_num}"> 
					<%-- user_num : 로그인한 회원번호 --%>
					<%-- vo.mem_num : 작성한 회원번호 --%>
					<input type="button" value="수정" onclick="location.href='boardUpdateForm.do?board_num=${vo.board_num}'">
					<%-- vo.board_num : 해당글 번호 --%>
					<input type="button" value="삭제" id="delete_btn">
					<script type="text/javascript">
						let delete_btn = document.getElementById('delete_btn');
						//이벤트 연결
						delete_btn.onclick = function(){
							let choice = confirm('삭제하시겠습니까?');
							if(choice){
								location.replace('boardDelete.do?board_num=${vo.board_num}');
							}
						};
					</script>
				</c:if>
			</li>
		</ul>
		<!-- 댓글 시작 -->
		<div id="reply_div">
			<span class="re-title">댓글 달기</span>
			<form id="re_form">
				<input type="hidden" name="board_num" value="${vo.board_num}" id="board_num">
				<textarea rows="3" cols="50" name="re_content" id="re_content" class="rep-content"
				<c:if test="${empty user_num}">disabled="disabled"</c:if>
				><c:if test="${empty user_num}">로그인 후 이용하세요</c:if></textarea>
				<c:if test="${!empty user_num}">
					<div id="re_first">
						<span class="letter-count">300/300</span>
					</div>
					<div id="re_second" class="align-right">
						<input type="submit" value="전송">
					</div>
				</c:if>
			</form>
		</div>
		<!-- 댓글 목록 출력 시작 -->
		<div id="output"></div>
		<div class="paging-button" style="display:none;">
			<input type="button" value="다음 글 보기">
		</div>
		<div id="loading" style="display:none;">
			<img src="${pageContext.request.contextPath}/images/loading.gif" width="50" height="50">
		</div>
		<!-- 댓글 목록 출력 끝 -->
		<!-- 댓글 끝 -->
	</div>
	<!-- 내용 끝 --> 
</div>
</body>
</html>