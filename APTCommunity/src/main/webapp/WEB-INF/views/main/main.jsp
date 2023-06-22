<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mainpage.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript">
	$(function(){
		$('#login_form').submit(function(event) {
			event.preventDefault();

			// dong과 ho 값을 연결
			let dongho = $('#dong').val() + '-' + $('#ho').val();
			
			//서버와의 통신
			$.ajax({
				type : 'post',
				url : '${pageContext.request.contextPath}/member/ajaxlogin.do',
				data : {
					dongho : dongho,
					passwd : $('#passwd').val()
				},
				dataType : 'json',
				success : function(param) {
					if(param.result == 'success'){
						location.href='main.do';
						$('#login_form').append('<span class="loading">로그인 중...</span>');
					}else if(param.result == 'failure'){
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
			<!-- 내용 시작 -->
			<div class="main_loling_wrap fl">
				<ul class="slide" id="main_slider">
					<li>
						<img width="668" height="380" src="${pageContext.request.contextPath}/images/mainpageapt.jpg">
					</li>
				</ul>
			</div>
			<div class="mLogin_box mLogout">
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

			<div class="mphnum_box">
				<a><span>관리사무소 전화번호</span>02)123-4567</a>
				<p>
				<a><span>팩스 번호</span>02)123-4567</a>
			</div>

			<p></p>

			<div class="board_all">
				<div class="board_notice">
					<p class="title">공지사항</p>
					<table>
						<c:forEach var="notice" items="${noticelist}">
							<tr>
								<td><a
									href="${pageContext.request.contextPath}/notice/noticeDetail.do?no_num=${notice.no_num}">${notice.no_num}</a></td>
								<td>${notice.title}</td>
								<td>${notice.reg_date}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="board_board">
					<p class="title">자유 게시판</p>
					<table>
						<c:forEach var="vo" items="${boardList}">
							<tr>
								<td><a
									href="${pageContext.request.contextPath}/board/boardDetail.do?board_num=${vo.board_num}">${vo.board_num}</a></td>
								<td>${vo.title}</td>
								<td>${vo.reg_date}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
		</div>
		<!-- 내용 끝 -->
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>
