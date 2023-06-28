<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>하자보수 글 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/fixMain.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/fix.reply.js"></script>
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
										<li><a href="${pageContext.request.contextPath}/booking/roomNameList.do">시설예약</a></li>
										<li><a href="${pageContext.request.contextPath}/fix/fixList.do">하자보수 신청</a></li>
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
												<a href="fixList.do">하자보수 신청</a>
											</li>
											<li><b>${ fix.title }</b></li>
											<li>
												작성자 ${ fix.dongHo } |
												작성일 ${ fix.reg_date }
											</li>
										</ul>
									</div>
									<hr size="1" width="100%" noshade="noshade" color="#e8e8e8">
									<div class="detail-page-content">
										<c:if test="${ !empty fix.filename }">
										<img src="${pageContext.request.contextPath}/upload/${ fix.filename }" class="detail-img">
										</c:if>
										<p>${ fix.content }</p>
									</div>
								</div><!-- detail-page -->
							</div>
							<hr class="hLine" size="1" noshade="noshade" width="100%">
							<!-- 댓글시작 -->
							<div id="comment" <c:if test="${user_auth!=9}">style='display: none'</c:if>>
								<span class="comment-title">댓글 달기</span>
								<form id="comment_form">
									<input type="hidden" name="fix_num" value="${fix.fix_num}" id="fix_num">
									<textarea rows="3" cols="50" name="comment_content" id="comment_content" class="rep-content"
									<c:if test="${empty user_num}">disabled="disabled"</c:if>><c:if test="${empty user_num}">로그인해야 작성할 수 있습니다.</c:if></textarea>
									<c:if test="${!empty user_num}"> <%-- 로그인 된 상태 --%>
									<div id="comment_first">
										<span class="letter-count">300/300</span>
									</div>
									<div id="comment_second" class="align-right">
										<input type="submit" value="전송">				
									</div>
									</c:if>
								</form>
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
							<div class="detail-btn">
								<div class="detail-btn-div2">
									<input type="button" value="목록" class="bottom-btn" onclick="location.href='fixList.do'" id="fixList_btn">
									<%-- 로그인 한 회원번호와 작성자 회원번호가 일치해야 수정, 삭제 가능 --%>
								    <c:if test="${ user_num == fix.mem_num || user_auth == 9}">
								    <input type="button" <c:if test="${user_auth == 9}"> style="display: none;" </c:if> value="수정" class="bottom-btn" onclick="location.href='fixUpdateForm.do?fix_num=${fix.fix_num}'">
								    <input type="button" value="삭제" class="bottom-btn" id="delete_btn">
								    <script type="text/javascript">
								    	let delete_btn =  document.getElementById('delete_btn');
						               	//이벤트 연결
						               	delete_btn.onclick=function(){
						                	let choice = confirm('삭제하겠습니까?');
						               		if(choice){
						                		location.replace('fixDelete.do?fix_num=${fix.fix_num}');
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