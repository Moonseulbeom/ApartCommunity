<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성</title>
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
			}
		});
		$(document).on('keyup','textarea',function(){
			let inputLength = $(this).val().length;
			if(inputLength > 700){//300자를 넘어선 경우
				$(this).val($(this).val().substring(0,700));//300자 다음 글자는 자름
				alert('최대 700자까지 입력가능합니다.');
			}else{//300자 이하인 경우
				let remain = 700 - inputLength;
				remain += '/700';
				$('#re_first .letter-count').text(remain);
			}
		})
	});
</script>
</head>
<body>
<div id="wrap">
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	<!-- 내용 시작 -->
<div class="main-main">
	<div class="write-title-text">
		<h1>공지사항 글쓰기</h1>
	</div>
	<div class="write-page">	
	<form id="write_form" action="writeNotice.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="keyfield_dept" value="4">
	<input type="hidden" name="status" id="status" value="0">
		<ul>
			<li>
				<%-- <select name="keyfield_dept" class="keyfield">
						<option value="1" <c:if test="${param.keyfield_dept==1}">selected</c:if>>관리사무소 공지사항</option>
						<option value="2" <c:if test="${param.keyfield_dept==2}">selected</c:if>>입대의 공지사항</option>
						<option value="3" <c:if test="${param.keyfield_dept==3}">selected</c:if>>건의사항</option>
				</select> --%>
				<select name="keyfield_cate" class="keyfield">
						<option value="2" <c:if test="${param.keyfield_cate==2}">selected</c:if>>자유게시판</option>
						<option value="3" <c:if test="${param.keyfield_cate==3}">selected</c:if>>중고거래</option>
						<option value="4" <c:if test="${param.keyfield_cate==3}">selected</c:if>>하자보수</option>
						<option value="5" <c:if test="${param.keyfield_cate==3}">selected</c:if>>예약(시설)</option>
				</select>
			</li>
			<li>
			<div class="wirte-title">
				<input type="text" id="title" name="title" placeholder="제목을 입력해주세요.">
					<label for="checkbox">상단고정</label>			
					<input type="checkbox" id="check" name="check" value="1">
			</div>
			</li>
			<li>
				<textarea rows="5" cols="30" id="content" name="content" placeholder="내용을 입력해주세요."></textarea>
				<div id="re_first">
						<span class="letter-count">700/700</span>
				</div>
			</li>
			<li>
				<input type="file" id="filename" name="filename" accept="image/png, image/jpeg, image/gif" >
			</li>
			<li>
			<div class="write-btn-div">
				<input type="submit" value="등록" class="write-btn">
				<input type="button" value="취소" class="write-btn" onclick="history.go(-1)">
			</div>
			</li>
		</ul>
	</form>
	</div>
</div>
	<!-- 내용 끝 -->
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</div>
</body>
</html>