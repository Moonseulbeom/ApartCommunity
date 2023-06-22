<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 글 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/commuWrite.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript">
	$(function(){
		//이벤트 연결
		$('#update_form').submit(function(){
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
				<h1>자유게시판 글 수정</h1>
				<div class="write-page">
				<form id="update_form" action="boardUpdate.do" method="post" enctype="multipart/form-data">
				<input type="hidden" name="board_num" value="${vo.board_num}">
					<ul>
					<li>
					</li>
						
					<li><!-- 제목 --><!-- 관리자페이지가 필요하면 사용 / 현재는 사용자,관리자 동일함 -->
						<div class="write-title">
							<c:if test="${user_auth==1}">
								<input type="text" id="title" name="title" value="${vo.title}">
							</c:if>
							<c:if test="${user_auth==9}">
								<input type="text" id="adminTitle" name="title">${vo.title}
							</c:if>
						</div>
					</li>
						
					<li><!-- 내용 -->
						<textarea rows="5" cols="30" id="content" name="content">${vo.content}</textarea>
					</li>
						
					<li><!-- 파일 -->
					<input type="file" name="filename" id="filename" accept="image/gif,image/png,image/jpeg">
					<c:if test="${!empty vo.filename}">
					<div id="file_detail">
						(${vo.filename})파일이 등록되어 있습니다.
						<input type="button" value="파일삭제" id="file_del" class="size30"><!-- 파일삭제버튼 -->
					</div>
					<script type="text/javascript">
					$(function(){
						$('#file_del').click(function(){
							let choice = confirm('삭제하시겠습니까?');
							if(choice){
								$.ajax({
									url:'deleteFile.do',
									type:'post',
									data:{board_num:${vo.board_num}},
									dataType:'json',
									success:function(param){
										if(param.result == 'logout'){
											alert('로그인 후 사용하세요');
										}else if(param.result == 'success'){
											$('#file_detail').hide();
										}else if(param.result == 'wrongAccess'){
											alert('잘못된 접속입니다.');
										}else{
											alert('파일 삭제 오류 발생');
										}
									},
									error:function(){
										alert('네트워크 오류 발생');
									}
								});
							}
						});
					});
					</script>
					</c:if>
					</li>
					<li>
						<div class="write-btn-div">
							<input type="submit" value="수정" class="write-btn">
							<input type="button" value="취소" onclick="location.href='boardDetail.do?board_num=${vo.board_num}'" class="write-btn">
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