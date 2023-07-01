<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1문의 글 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/inquiry.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/inquiry.reply.js"></script>
</head>
<body>
<div id="wrap">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div class="inner">
		<div id="container" class="inner">
   		<!-- 상단링크버튼 -->
   		<div class="service-up">
			<jsp:include page="/WEB-INF/views/common/up_button.jsp"/>
		</div>
   			<div class="page-main">
   				<ul>
   					<!-- 좌측 사이드바 -->
				<li>
				<div class="page-left">
				<div class="sidebar">
					<jsp:include page="/WEB-INF/views/common/sidebar.jsp"/>
					<h2>기타</h2>
				<div class="menu">
					<ul>
						<li><a href="${pageContext.request.contextPath}/inquiry/list.do">1:1문의</a></li>
						<li><a href="${pageContext.request.contextPath}/question/questionList.do">자주 묻는 질문</a></li>
					</ul>
				</div>
				</div>
				</div>
				</li>
					<!-- 오른쪽 -->
					<li>
						<div class="page-right">
							<div class="detail">
								<div class="detail-page">
									<div class="detail-page-title">
										<ul>
											<li>
												<a href="list.do">1:1문의</a>
											</li>
											<li><b>${ inquiry.title }</b></li>
											<li>
												작성자 ${ inquiry.dongho } |
												작성일 ${ inquiry.reg_date }
											</li>
										</ul>
									</div>
									<hr size="1" width="100%" noshade="noshade" color="#e8e8e8">
									<div class="detail-page-content">
										<c:if test="${ !empty inquiry.filename }">
										<img src="${pageContext.request.contextPath}/upload/${ inquiry.filename }" class="detail-img">
										</c:if>
										<p>${ inquiry.content }</p>
									</div>
								</div><!-- detail-page -->
							</div>
							<!-- 댓글 목록 출력 시장 -->
							
							<div id="output"></div>	
							
							<div class="paging-button" style="display:none;">
								<input type="button" value="다음글 보기">
							</div>
							<div id="loading" style="display: none;">
								<img src="${pageContext.request.contextPath}/images/loading.gif" width="50" height="50">
							</div>
							<!-- 댓글 끝 -->
							<!-- 댓글시작 -->
							<div id="comment">
							 	
								<span class="comment-title">문의 답변</span>
								
								<form id="comment_form">
									<input type="hidden" name="in_num" value="${inquiry.in_num}" id="in_num" maxlength="300">
									<textarea rows="3" cols="50" name="comment_content" id="comment_content" class="rep-content"
									<c:if test="${user_auth < 9}">disabled="disabled"</c:if>><c:if test="${user_auth < 9}">관리자만 작성할 수 있습니다.</c:if></textarea>
									<c:if test="${!empty user_num || user_auth < 9}"> <%-- 로그인 된 상태 --%>
									<div id="comment_first">
										<span class="letter-count">300/300</span>
									</div>
									<div id="comment_second" class="align-right">
									<c:if test="${user_auth == 9}">
										<input type="submit" value="전송" disabled="disabled">		
									</c:if>
									</div>
									</c:if>
								</form>
								
								
							</div>
							
							<div class="detail-btn">
								<div class="detail-btn-div2">
									<input type="button" value="목록" class="bottom-btn" onclick="location.href='list.do'" id="fixList_btn">
									<%-- 로그인 한 회원번호와 작성자 회원번호가 일치해야 수정, 삭제 가능 --%>
								    <c:if test="${ user_num == inquiry.mem_num || user_auth == 9}">
								   
								    <input type="button" value="수정" class="bottom-btn" onclick="location.href='updateForm.do?in_num=${inquiry.in_num}'">
								    <input type="button" value="삭제" class="bottom-btn" id="delete_btn">
								    <script type="text/javascript">
								    	let delete_btn =  document.getElementById('delete_btn');
						               	//이벤트 연결
						               	delete_btn.onclick=function(){
						                	let choice = confirm('삭제하겠습니까?');
						               		if(choice){
						                		location.replace('delete.do?in_num=${inquiry.in_num}');
						                  	}
						               }
						              
						               	
						            </script>
						        	</c:if>
						        	
								</div>
							</div>
						</div>	
					</li>
					<!-- 오른쪽 -->
				</ul>
			</div>
		</div>
	</div>
	<!-- 내용 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>