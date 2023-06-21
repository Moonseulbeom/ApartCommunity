<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 글 작성</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/commuWrite.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript">
	$(function(){
		//이벤트 연결
		$('#write_form').submit(function(){
			if($('#title').val().trim()==''){
				alert('제목을 입력하세요');
				$('#title').val('').focus();
				return false;
			}
			if($('#content').val().trim()==''){
				alert('내용을 입력하세요');
				$('#content').val('').focus();
				return false;
			}
			/* 이건 관리자인가? */
			if($('#check').is(':checked')==true){
				$('#status').val(1);
			}
		});
	});
</script>
</head>
<body>
<div class="wrap">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
	<div class="inner">
		<div id="container" class="inner">
			<div class="main-page">
				<h1>게시판 글 쓰기</h1>
				<div class="write-page">
				<form id="write_form" action="boardwrite.do" method="post" enctype="multipart/form-data">
					<ul>
						<li>
						</li>
						
						<li><!-- 제목 -->
							<div class="write-title">
								<input type="text" id="<c:if test="${user_auth==1}">title</c:if><c:if test="${user_auth==9}">adminTitle</c:if>" name="title" placeholder="제목을 입력해주세요.">
								<c:if test="${user_auth == 9}">
									<label for="checkbox">상단고정</label>
									<input type="checkbox" id="check" name="check" value="1">
								</c:if>
							</div>
						</li>
						
						<li><!-- 내용 -->
							<textarea rows="5" cols="30" id="content" name="content" placeholder="내용을 입력해주세요."></textarea>
						</li>
						
						<li><!-- 파일 -->
							<input type="file" id="filename" name="filename" accept="image/png, image/jpeg, image/gif" >
						</li>
						<li>
							<div class="write-btn-div">
								<input type="submit" value="등록" class="write-btn">
								<input type="button" value="취소" onclick="location.href='boardList.do'" class="write-btn">
							</div>
						</li>
					</ul>
				</form>
				</div>
			</div>
		</div>
	</div>
	<!-- 내용 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>