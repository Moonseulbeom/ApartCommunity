<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">	
$(function(){
	//회원 관리 검색참
	$('#mem_search_form').submit(function(event){
		//submit 이벤트 제거
		event.preventDefault();
		//폼 데이터 전부 읽기
		let form_data = $(this).serialize();
		//ajax 통신
		$.ajax({
			url:'manage-serviceList.do',
			type:'post',
			data:form_data,
			dataType:'json',
			success:function(list){
				document.location.href = document.location.href;
				let output = '';
				$.each(list, function(index,item){
					output += '<td>'+item.dongho+'</td>';
					output += '<td>'+item.name+'</td>';
					output += '<td>'+item.phone+'</td>';
					output += '<td>'+item.reg_date+'</td>';
					$("#search").append(output); // index가 끝날때까지 
				})	
			},
			error:function(){
				alert('오류발생');
			}
		})
		//빈 글자 검색 막기
		if($('#keyword').val().trim() == ''){
			alert('검색할 내용을 입력하세요');
			$('#key').val('').focus();
			return false;
		}
	})
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
})
</script>
<!-- 1.회원관리 -->
			<div id="menage-member">
				<!--  목록 검색창 -->
				<h1>회원목록조회</h1>	
				<form action="manage-serviceList.do" method="get" id="mem_search_form">
				<ul>
					<li>
						<div class="mem-search">
							<select name="mem_select" class="select-member">
								<option value="1" <c:if test="${param.mem_select==1}">selected</c:if>>세대주</option>
								<option value="2" <c:if test="${param.mem_select==2}">selected</c:if>>동</option>
								<option value="3" <c:if test="${param.mem_select==3}">selected</c:if>>호수</option>
							</select>
							<input type="search" placeholder="회원 목록 조회" class="search-member" name="keyword" id="keyword" value="${ param.keyword }">
						</div>
					</li>
					<li>
						<div class="mem-search-btn">
							<input type="submit" value="검색">
						</div>
					</li>
				</ul>
				</form>
				<!--  목록 검색참 끝 -->
				<!-- 회원 목록 -->
				<div class="mem-list">
					<table>
					
						<tr>
							<th>동-호수</th>
							<th>세대주</th>
							<th>전화번호</th>
							<th>가입일</th>
						</tr>
					<!-- 검색 전  -->
					<c:if test="${ count > 0 && empty keyword}">
					<c:forEach var="mem" items="${ list }">
						<tr>
							<td id="1">${ mem.dongho }</td>
							<td id="2">${ mem.name }</td>
							<td id="3"> ${ mem.phone }</td>
							<td id="4">${ mem.reg_date }</td>
						</tr>
					</c:forEach>
					</c:if>
					<!-- 검색 후  -->
					<c:if test="${ count > 0 && ! empty keyword}">
					<c:forEach var="mem" items="${ list }">
						<tr id="search">
							<td id="1">${ mem.dongho }</td>
							<td id="2">${ mem.name }</td>
							<td id="3"> ${ mem.phone }</td>
							<td id="4">${ mem.reg_date }</td>
						</tr>
					</c:forEach>
					</c:if>
					
					</table>
					<c:if test="${ count > 0 }">
						<div class="mem-page">${ page }</div>
					</c:if>
					<c:if test="${ count < 1 || empty count }">
						<div class="result-display">	
							없는 회원입니다.
						</div>
						<hr color="#edeff0" noshade="noshade">
					</c:if>
					
					
					</div>
				</div>
				<!-- 1.회원관리 끝-->
				<!--  2.공지사항 글 작성 폼 -->
				<div id="manage-notice">
					<div class="write-title-text">
		<h1>공지사항 글쓰기</h1>
	</div>
	<div class="manage-write-page">	
	<form id="write_form" action="${ pageContext.request.contextPath }/notice/writeNotice.do" method="post" enctype="multipart/form-data">
	<input type="hidden" name="category_status" value="1">
	<input type="hidden" name="status" id="status" value="0">
		<ul>
			<li>
				<select name="keyfield_dept" class="keyfield">
						<option value="1" <c:if test="${param.keyfield_dept==1}">selected</c:if>>관리사무소 공지사항</option>
						<option value="2" <c:if test="${param.keyfield_dept==2}">selected</c:if>>입대의 공지사항</option>
						<option value="3" <c:if test="${param.keyfield_dept==3}">selected</c:if>>건의사항</option>
				</select>
			</li>
			<li>
			<div class="wirte-title">
				<input type="text" id="title" name="title" placeholder="제목을 입력해주세요." maxlength="20">
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
				<!--  2.공지사항 글 작성 폼 -->	