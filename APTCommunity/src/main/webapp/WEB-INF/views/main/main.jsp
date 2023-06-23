<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainpage.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/notice.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript">
	$(function() {
		$('#login_form')
				.submit(
						function(event) {
							event.preventDefault();

							if ($('#dong').val().trim() == '') {
								alert('동을 입력하세요');
								$('#dong').val('').focus();
								return false;
							}
							if ($('#ho').val().trim() == '') {
								alert('호를 입력하세요');
								$('#ho').val('').focus();
								return false;
							}
							if ($('#passwd').val().trim() == '') {
								alert('비밀번호를 입력하세요');
								$('#passwd').val('').focus();
								return false;
							}

							// dong과 ho 값을 연결
							let dongho = $('#dong').val() + '-'
									+ $('#ho').val();

							//서버와의 통신
							$
									.ajax({
										type : 'post',
										url : '${pageContext.request.contextPath}/member/ajaxlogin.do',
										data : {
											dongho : dongho,
											passwd : $('#passwd').val()
										},
										dataType : 'json',
										success : function(param) {
											if (param.result == 'success') {
												location.href = 'main.do';
												$('#login_form')
														.append(
																'<span class="loading">로그인 중...</span>');
											} else if (param.result == 'failure') {
												alert('아이디 또는 비밀번호가 틀렸습니다.');
												history.go(-1);
											}
										},
										error : function() {
											alert('네트워크 오류 발생');
										}
									});
						});//end of submit
	});
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp" />
	<div id="wrap">
		<div class="inner">
			<div id="container" class="inner">
				<!-- 상단 링크 버튼 시작-->
				<div class="service-up">
					<jsp:include page="/WEB-INF/views/common/up_button.jsp" />
				</div>
				<!-- 상단 링크 버튼 끝-->
				
				<!-- 내용 시작 -->
				
				<!-- 좌측 상단 사진 시작 -->
				<div class="main_loling_wrap fl">
					<ul class="slide" id="main_slider">
						<li><img width="668" height="380"
							src="${pageContext.request.contextPath}/images/mainpageapt.jpg">
						</li>
					</ul>
				</div>
				<!-- 좌측 상단 사진 끝 -->
				
				<!-- 우측 상단 로그인 박스 시작 -->
				<c:if test="${empty user_auth}">
				<div class="mLogin_box">
					<p class="loginGreetingt">
						<span>쌍용아파트</span>에 오신 것을 환영합니다.
					</p>
					<div class="loginBox">
						<form id="login_form">
							<p>
								<input type="text" class="login_input_text" id="dong"
									placeholder="동" maxlength="10">
							</p>
							<p>
								<input type="text" class="login_input_text" id="ho"
									placeholder="호" maxlength="10">
							</p>
							<p>
								<input type="password" class="login_input_text" id="passwd"
									placeholder="비밀번호" maxlength="30">
							</p>
							<input type="submit" value="로그인" class="loginBtn" id="loginBtn">
							<input type="button" value="회원가입" class="registerBtn"
								id="registerBtn"
								onclick="location.href='<c:url value="/member/registerUserAgree.do" />'">
						</form>
					</div>
				</div>
				</c:if>
				<!-- 로그인 중일때 로그인 박스 시작 -->
				<c:if test="${!empty user_auth}">
				<div class="mLogging_Box">
					<p class="loginGreetingt">
						<span>쌍용아파트</span>에 오신 것을 환영합니다.
						<br><br>
						<c:if test="${user_auth == 1}">등급: <span>입주민</span></c:if>
						<c:if test="${user_auth == 9}">등급: <span>관리자</span></c:if>
						<br><br>
						세대: <span>${user_dongho}</span>
					</p>
					<div class="LoggingBox">
						<form id="login_form">
							<input type="button" value="로그아웃" class="logoutBtn"
								id="logoutBtn"
								onclick="location.href='<c:url value="/member/logout.do" />'">
							<c:if test="${user_auth == 1}">
							<input type="button" value="마이페이지" class="mypageBtn"
								id="mypageBtn"
								onclick="location.href='<c:url value="/member/myPage.do" />'">
							</c:if>
							<c:if test="${user_auth == 9}">
							<input type="button" value="관리자페이지" class="manageBtn"
								id="manageBtn"
								onclick="location.href='<c:url value="#" />'">
							</c:if>
						</form>
					</div>
				</div>
				</c:if>
				<!-- 로그인 중일때 로그인 박스 끝 -->
				<!-- 우측 상단 로그인 박스 끝 -->
				
				<!-- 우측 상단 전화번호 박스 시작 -->
				<div class="mphnum_box">
					<a><span>관리사무소 전화번호</span> 02)123-4567</a>
					<p>
						<a><span>팩스 번호</span> 02)123-4567</a>
				</div>
				<!-- 우측 상단 전화번호 박스 끝 -->
				

				<p></p>

				<!-- 게시판 시작 -->
				<div class="board_all">
					<!-- 좌측 공지사항 게시판 시작 -->
					<div class="board_notice">
						<p class="title">공지사항</p>
						<div class="board_notice1">
						<table>
							<c:forEach var="notice" items="${noticelist}">
								<tr>
									<td><a
										href="${pageContext.request.contextPath}/notice/noticeDetail.do?no_num=${notice.no_num}">${notice.no_num}</a></td>
									<td>
									<c:if test="${!empty user_auth}">${notice.title}</c:if>
									<c:if test="${empty user_auth}">로그인 후 열람 가능합니다.</c:if>
									</td>
									<td>${notice.reg_date}</td>
								</tr>
							</c:forEach>
						</table>
						</div>
					</div>
					<!-- 좌측 공지사항 게시판 끝 -->
					<!-- 우측 자유 게시판 시작 -->
					<div class="board_board">
						<p class="title">자유 게시판</p>
						<table>
							<c:forEach var="vo" items="${boardList}">
								<tr>
									<td><a
										href="${pageContext.request.contextPath}/board/boardDetail.do?board_num=${vo.board_num}">${vo.board_num}</a></td>
									<td>
									<c:if test="${!empty user_auth}">${vo.title}</c:if>
									<c:if test="${empty user_auth}">로그인 후 열람 가능합니다.</c:if>
									</td>
									<td>${vo.reg_date}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
					<!-- 우측 자유 게시판 끝 -->
				</div>
				<!-- 게시판 끝 -->
				
			</div>
		</div>
		<!-- 내용 끝 -->
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>
</html>
