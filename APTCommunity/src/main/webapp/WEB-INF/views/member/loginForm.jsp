<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginform.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#login_form').submit(function(){
			if($('#dong').val().trim()==''){
				alert('동을 입력하세요');
				$('#dong').val('').focus();
				return false;
			}
			if($('#ho').val().trim()==''){
				alert('호를 입력하세요');
				$('#ho').val('').focus();
				return false;
			}
			if($('#passwd').val().trim()==''){
				alert('비밀번호를 입력하세요');
				$('#passwd').val('').focus();
				return false;
			}
	});
</script>
</head>
<body>
<div class="page-main">
   <jsp:include page="/WEB-INF/views/common/header.jsp"/>
   <!-- 내용 시작 -->
   <div class="content-main">
      <h2>로그인</h2>
      <form id="login_form" action="login.do" method="post">
         <ul>
            <li>
            <label for="dong">동</label>
	            <input type="text" id="dong" name="dong" placeholder="동" maxlength="12" autocomplete="off">
            </li>
             <li>
            <label for="ho">호</label>
            <input type="text" id="ho" name="ho" placeholder="호" maxlength="12" autocomplete="off">
            </li>
            <li>
               <label for="passwd">비밀번호</label>
              	<input type="password" id="passwd" name="passwd" placeholder="비밀번호">
            </li>
         </ul>
         <div class="align-center">
            <input type="submit" value="로그인">
            <input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}'">
         </div>
      </form>
   </div>
   <!-- 내용 끝 -->
   <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>