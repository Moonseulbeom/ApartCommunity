
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성</title>
<link rel="stylesheet"
	href="${ pageContext.request.contextPath }/css/noticeWrite.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/custom.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/common.js"></script>
<script type="text/javascript">
	$(function() {
		//이벤트 연결
		$('#write_form').submit(function() {
			if ($('#title').val().trim() == '') {
				alert('제목을 입력하세요');
				$('#title').val('').focus();
				return false;
			}
			if ($('#content').val().trim() == '') {
				alert('내용을 입력하세요');
				$('#content').val('').focus();
				return false;
			}
			if ($('#check').is(':checked') == true) {
				$('#status').val(1);
			}
		});
		$(document).on('keyup', 'textarea', function() {
			let inputLength = $(this).val().length;
			if (inputLength > 650) {//300자를 넘어선 경우
				$(this).val($(this).val().substring(0, 650));//300자 다음 글자는 자름
				alert('최대 650자까지 입력가능합니다.');
			} else {//300자 이하인 경우
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
	<div id="wrap">
		<jsp:include page="/WEB-INF/views/common/header.jsp" />
		<!-- 내용 시작 -->
		<div class="main-main">
			<div class="write-title-text">
				<h1>공지사항 글쓰기</h1>
			</div>
			<div class="write-page">
				<form id="write_form" action="modifyCatedoryNotice.do" method="post"
					enctype="multipart/form-data">
					<input type="hidden" name="keyfield_dept" value="4"> 
					<input type="hidden" name="status" id="status" value="${ notice.status }">
					<input type="hidden" name="no_num" value="${ notice.no_num }">
					<ul>
						<li><select name="category_status" class="keyfield">
								<option value="2"
									<c:if test="${notice.category_status==2}">selected</c:if>>자유게시판</option>
								<option value="3"
									<c:if test="${notice.category_status==3}">selected</c:if>>중고거래</option>
								<option value="4"
									<c:if test="${notice.category_status==4}">selected</c:if>>하자보수</option>
								<option value="5"
									<c:if test="${notice.category_status==5}">selected</c:if>>예약(시설)</option>
								<option value="6"
									<c:if test="${notice.category_status==6}">selected</c:if>>1:1문의</option>
						</select></li>
						<li>
							<div class="wirte-title">
								<input type="text" id="title" name="title" placeholder="제목을 입력해주세요." maxlength="20" value="${ notice.title }">
								<label for="checkbox">상단고정</label>
								<input type="checkbox" id="check" name="check" value="1" checked="checked">
							</div>
						</li>
						<li><textarea rows="5" cols="30" id="content" name="content"
								placeholder="내용을 입력해주세요.">${ notice.content }</textarea>
							<div id="re_first">
								<span class="letter-count">650/650</span>
							</div></li>
						<li>
							<input type="file" id="filename" name="filename"
							accept="image/png, image/jpeg, image/gif">
					<c:if test="${!empty notice.filename}">
					<div id="file_detail">
						(${notice.filename})파일이 등록되어 있습니다.
						<input type="button" value="파일삭제" id="file_del">
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
								<input type="submit" value="수정" class="write-btn"> <input
									type="button" value="취소" class="write-btn"
									onclick="history.go(-1)">
							</div>
						</li>
					</ul>
				</form>
			</div>
		</div>
		<!-- 내용 끝 -->
		<jsp:include page="/WEB-INF/views/common/footer.jsp" />
	</div>
</body>
</html>