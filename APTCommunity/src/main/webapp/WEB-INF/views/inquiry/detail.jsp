<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/inquiry.reply.js"></script>
</head>
<body>
<div class="page-main">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div class="content-main">
		<h2>${inquiry.title}</h2>
		<hr size="1" noshade="noshade" width="100%">
		<c:if test="${!empty inquiry.filename}">
		<div class="align-center">
			<img src="${pageContext.request.contextPath}/upload/${inquiry.filename}" class="detail-img">
		</div>
		</c:if>
		<p>
			${inquiry.content}
		</p>
		<hr size="1" noshade="noshade" width="100%">
		<ul class="detail-sub">
			<li>
				<c:if test="${!empty inquiry.modify_date}">
				최근 수정일 : ${inquiry.modify_date}
				</c:if>
				작성일 : ${inquiry.reg_date}
				<%-- 로그인한 회원번호와 작성자 회원번호가 
				     일치해야 수정,삭제 가능 --%>
				<c:if test="${user_num == inquiry.mem_num}">
				<input type="button" value="수정" 
				  onclick="location.href='updateForm.do?in_num=${inquiry.in_num}'">
				<input type="button" value="삭제"
				                id="delete_btn">
				<script type="text/javascript">
					let delete_btn = 
						  document.getElementById('delete_btn');
					//이벤트 연결
					delete_btn.onclick=function(){
						let choice = confirm('삭제하겠습니까?');
						if(choice){
							location.replace('delete.do?in_num=${inquiry.in_num}');
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
				<input type="hidden" name="in_num"
				   value="${inquiry.in_num}" id="in_num">
				<textarea rows="3" cols="50"
 				  name="re_content" id="re_content" class="rep-content"
				  <c:if test="${empty user_num}">disabled="disabled"</c:if>
				  ><c:if test="${empty user_num}">로그인해야 작성할 수 있습니다.</c:if></textarea>
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
		<div class="paging-button" 
		                   style="display:none;">
			<input type="button" value="다음글 보기">                   
		</div>
		<div id="loading" style="display:none;">
			<img src="${pageContext.request.contextPath}/images/loading.gif" width="50" height="50">
		</div>
		<!-- 댓글 목록 출력 끝 -->
		<!-- 댓글 끝 -->
	</div>
	<!-- 내용 끝 -->
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>



