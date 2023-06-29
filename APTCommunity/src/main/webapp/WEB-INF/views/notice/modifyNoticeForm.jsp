<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정</title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/noticeWrite.css">
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
			if($('#check').is(':checked')==true){
				$('#status').val(1);
			}else{
				$('#status').val(0);
			}
		});
		$(document).on('keyup','textarea',function(){
			let inputLength = $(this).val().length;
			if(inputLength > 650){//300자를 넘어선 경우
				$(this).val($(this).val().substring(0,650));//300자 다음 글자는 자름
				alert('최대 650자까지 입력가능합니다.');	
			}else{//300자 이하인 경우
				let remain = 650 - inputLength;
				remain += '/650';
				$('#re_first .letter-count').text(remain);
			}
		})
		if($('#status').val() == 0){
			$('input[type=checkbox]').prop('checked',false);
		}
	});
</script>
</head>
<body>
<div id="wrap"><!-- header -->
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
<div class="main-main">
	<div class="write-title-text">
		<h1>공지사항 수정</h1>
	</div>
	<div class="write-page">	
	<form id="write_form" action="modifyNotice.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="category_status" value="1">
	<input type="hidden" name="no_num" value="${ notice.no_num }">
	<input type="hidden" name="status" id="status" value="${ notice.status }">
		<ul>
			<li>
				<select name="keyfield_dept" class="keyfield">
						<option value="1" <c:if test="${notice.dept==1}">selected</c:if>>관리사무소 공지사항</option>
						<option value="2" <c:if test="${notice.dept==2}">selected</c:if>>입대의 공지사항</option>
						<option value="3" <c:if test="${notice.dept==3}">selected</c:if>>건의사항</option>
				</select>
			</li>
			<li>
			<div class="wirte-title">
				<input type="text" id="title" name="title" placeholder="제목을 입력해주세요." value="${ notice.title }" maxlength="20">
					<label for="checkbox">상단고정</label>			
					<input type="checkbox" id="check" name="check" value="1" checked="checked" >
			</div>
			</li>
			<li>
				<textarea rows="5" cols="30" id="content" name="content" placeholder="내용을 입력해주세요.">${ notice.content }</textarea>
				<div id="re_first">
						<span class="letter-count">700/700</span>
				</div>
			</li>
			<li>
				<input type="file" id="filename" name="filename" accept="image/png, image/jpeg, image/gif" >
					<c:if test="${!empty notice.filename}">
					<div id="file_detail">
						(${notice.filename})파일이 등록되어 있습니다.
						<input type="button" value="파일삭제"
						                          id="file_del">
					</div>
					<script type="text/javascript">
						$(function(){
							$('#file_del').click(function(){
								let choice = confirm('삭제하시겠습니까?');
								if(choice){
									$.ajax({
										url:'deleteFile.do',
										type:'post',
										data:{no_num:${notice.no_num}},
										dataType:'json',
										success:function(param){
											if(param.result == 'logout'){
												alert('로그인 후 사용하세요');
											}else if(param.result == 'success'){
												$('#file_detail').hide();
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
				<input type="button" value="취소" class="write-btn" onclick="history.go(-1)">
			</div>
			</li>
		</ul>
	</form>
	</div>
</div>
	<!-- 내용 끝 -->
	<!--  footer -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>