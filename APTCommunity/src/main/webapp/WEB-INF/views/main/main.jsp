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
		
/* 		let origin_status =${order.status};
		$('input[type=radio]').click(function(){
			if(origin_status==1 && $('input[type=radio]:checked').val()!=1){
				$('input[type=text],textarea').parent().hide();
			}else{
				$('input[type=text],textarea').parent()show();
			}
		});//end of click
		 */
	});
</script>
</head>
<body>
<div id="wrap">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<div class="inner">
		<div id="container" class="inner">
		</div>
		<!-- 내용 시작 -->
		<div class="main_loling_wrap fl">
			<ul class="slide" id="main_slider">
				<li><img
					src="https://d3fixtu11mscj5.cloudfront.net/aptner/apt/A10026403/apt_info/A10026403_slide_KBAGOA5169QC7.png?t=1686985895">
				</li>
			</ul>
		</div>
		<div class="mLogin_box mLogout">
			<p class="loginGreetingt">
				<span>쌍용아파트</span>에 오신 것을 환영합니다.
			</p>
			
      		<div class="loginBox">
		        <form id="login_form" >
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
				<input type="button" value="회원가입" class="registerBtn" id="registerBtn" 
					onclick="location.href='<c:url value="/member/registerUserAgree.do" />'">
				</form>
			</div>
		</div>
		
		<div class="mphnum_box">
			<input type="button" value="관리사무소 전화번호" id="mphnum">
			<input type="button" value="팩스 번호" id="faxnum">
		</div>
		<p></p>
		
		
		<div class="board_notice">
		<h4>공지사항</h4>
		<table>
			<c:forEach var="notice" 
			              items="${noticelist}">
				<tr>
					<td><a href="${pageContext.request.contextPath}/notice/noticeDetail.do?no_num=${notice.no_num}">${notice.title}</a></td>
					<td>${notice.dongho}</td>
					<td>${notice.content}</td>
					<td>${notice.reg_date}</td>
				</tr>
			</c:forEach>
		</table>
		</div>
		
		<div class="board_board">
		<h4>자유 게시판</h4>
		<table>
			<c:forEach var="vo" 
			              items="${list}">
				<tr>
					<td><a href="${pageContext.request.contextPath}/board/boardDetail.do?board_num=${vo.board_num}">${vo.title}</a></td>
					<td>${vo.dongho}</td>
					<td>${vo.content}</td>
					<td>${vo.reg_date}</td>
				</tr>
			</c:forEach>
		</table>
		</div>
	<!-- 내용 끝 -->
	</div>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>
