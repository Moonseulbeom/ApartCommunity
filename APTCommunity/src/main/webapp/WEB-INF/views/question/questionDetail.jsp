<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자주 묻는 질문</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/question.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/question.reply.js"></script>
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
												<a href="questionList.do">Q & A</a>
											</li>
											<li><b>${ question.title }</b></li>
											<li>
												작성자 관리자 |
												작성일 ${ question.reg_date }
											</li>
										</ul>
									</div>
									<hr size="1" width="100%" noshade="noshade" color="#e8e8e8">
									<div class="detail-page-content">
										<c:if test="${ !empty question.filename }">
										<img src="${pageContext.request.contextPath}/upload/${ question.filename }" class="detail-img">
										</c:if>
										<p>${ question.content }</p>
									</div>
								</div><!-- detail-page -->
							</div>

							<div class="detail-btn">
								<div class="detail-btn-div2">
									<input type="button" value="목록" class="bottom-btn" onclick="location.href='questionList.do'" id="fixList_btn">
									<%-- 관리자만 수정, 삭제 가능 --%>
								    <c:if test="${user_auth == 9}">
								    <input type="button" value="수정" class="bottom-btn" onclick="location.href='questionUpdateForm.do?que_num=${question.que_num}'">
								    <input type="button" value="삭제" class="bottom-btn" id="delete_btn">
								    <script type="text/javascript">
								    	let delete_btn =  document.getElementById('delete_btn');
						               	//이벤트 연결
						               	delete_btn.onclick=function(){
						                	let choice = confirm('삭제하겠습니까?');
						               		if(choice){
						               			alert('삭제 완료!');
						                		location.replace('questionDelete.do?que_num=${question.que_num}');
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