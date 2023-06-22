<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1:1문의 상세</title>
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
			<div class="page-main">
				<ul>
					<!-- 왼쪽 -->
					<li>
						<div class="page-left">
							<div class="sidebar">
								<div class="mem-service">
									<p>000동 000호 주민</p>
									<div class="service-myPage-button">
										<a href="${pageContext.request.contextPath}/member/myPage.do">MY 페이지</a>
									</div>
									<div class="service-logout-button">
										<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
									</div>
								</div>
								<h2>예약/신청</h2>
								<div class="menu">
									<ul>
										<li><a href="${pageContext.request.contextPath}/inqruiry/list.do">1:1문의</a></li>
										<li><a href="${pageContext.request.contextPath}/question/list.do">자주묻는 질문</a></li>
									</ul>
								</div>
							</div>
						</div>
					</li>
					<!-- 오른쪽 -->
					<li>
						<div class="page-right">
							<div class="inquiry-detail">
								<div class="inquiry-detail-page">
									<div class="inquiry-detail-page-title">
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
									<div class="inquiry-detail-page-content">
										<c:if test="${ !empty inquiry.filename }">
										<img src="${pageContext.request.contextPath}/upload/${ inquiry.filename }" class="detail-img">
										</c:if>
										<p>${ inquiry.content }</p>
									</div>
								</div>
							</div>
							<!-- 답변 글 시작 -->
			
							<!-- 답변 글 끝 -->
							<!-- 댓글 목록 출력 시장 -->
							<div id="output"></div>	
							<hr class="hLine" size="1" noshade="noshade" width="100%">
							<!-- 댓글시작 -->
							<c:if test="${!empty user_num && user_auth == 9}">
							<div id="manage">
								
								<span class="manage-title">답변 작성</span>
								<form id="manage_form">
									<input type="hidden" name="in_num" value="${inquiry.in_num}" id="in_num">
									<textarea rows="3" cols="50" name="manage_content" id="manage_content" class="rep-content" disabled="disabled">
									<c:if test="${empty user_num}">로그인해야 작성할 수 있습니다.</c:if></textarea>
									<c:if test="${!empty user_num}"> <%-- 로그인 된 상태 --%>
									<div id="manage_first">
										<span class="letter-count">300/300</span>
									</div>
									<div id="manage_second" class="align-right">
										<input type="submit" value="전송">				
									</div>
									</c:if>
								</form>
							</div>
							</c:if>
							
								
							<div class="paging-button" style="display:none;">
								<input type="button" value="다음글 보기">
							</div>
							<div id="loading" style="display: none;">
								<img src="${pageContext.request.contextPath}/images/loading.gif" width="50" height="50">
							</div>
							<!-- 댓글 끝 -->
							<div class="detail-btn">
								<div class="detail-btn-div2">
									<input type="button" value="목록" class="bottom-btn" onclick="location.href='list.do'" id="list_btn">
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