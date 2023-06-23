<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/manager.css">
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/noticeWrite.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		
/* 		//회원 관리 검색참
		$('#mem_search_form').submit(function(){
			if($('#keyword').val().trim() == ''){
				alert('검색할 내용을 입력하세요');
				$('#key').val('').focus();
				return false;
			}
		}) */
		//화면 전환
		
		$('#mem_btn').on('click',function(){
			 $.ajax({
				 type:'get',
				 url:'manage-serviceList.do',
				 dataType:'text',
				 success:function(data){
					 let plus = $('#manage_content').html(data).find('#menage-member');
					 console.log(plus);
					 $('#manage_content').html(plus);
				 },
				 error:function(){
					 alert('통신 에러 발생');
				 }
			 })
		})
		$('#no_btn').on('click',function(){
			 $.ajax({
				 type:'get',
				 url:'manage-serviceList.do',
				 dataType:'text',
				 success:function(data){
					 let plus = $('#manage_content').html(data).find('#manage-notice');
					 console.log(plus);
					 $('#manage_content').html(plus);
				 },
				 error:function(){
					 alert('통신 에러 발생');
				 }
			 })
		})
	})
</script>
</head>
<body>
<div class="wrap">
<!-- 상단 검은색 메뉴바 -->
	<div class="manage-header">
			<ul>
				<li>
					<div class="manage-header-img">
						<a href="${pageContext.request.contextPath}/main/main.do">
						<img src="${pageContext.request.contextPath}/img/apt_logo.png" alt="아파트로고" />
						</a>
					</div>
				</li>
				<li>
					<div class="manage-header-btn">
						[관리자]
						<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
						<a href="${pageContext.request.contextPath}/main/main.do">메인 페이지</a>
					</div>
				</li>
			</ul>
	</div>
<!-- 상단 검은색 메뉴바 끝-->
<!-- 메인 시작 -->
<!-- 왼쪽 사이드 바 시작 -->
	<!-- 내용 시작 -->
	<div class="inner">
		<div id="container" class="inner">
		<div class="manage-page-main">
		<ul>
			<!-- 왼쪽 -->
			<li>
			<div class="manage-page-left">
				<a id="manage-title" href="manageMain.do">Admin</a>
					<div class="manage-menu">
							<div id="mem_btn">회원관리</div>
							<div id="no_btn">공지글 작성</div>
							<div><a href="${pageContext.request.contextPath}/notice/noticeList.do?dept=3">머리 공지글 작성</a></div>
							<div><a href="${pageContext.request.contextPath}/notice/noticeList.do?dept=4">1:1문의 관리</a></div>
							<div><a href="${pageContext.request.contextPath}/notice/noticeList.do?dept=4">하자 보수글 관리</a></div>
							<div><a href="${pageContext.request.contextPath}/notice/noticeList.do?dept=4">예약 관리</a></div>
					</div>	  
			</div>
			</li>
			<!-- 오른쪽 -->
			<li>
			<div class="manage-page-right">
				<div class="manage-content" id="manage_content">
					
				</div>			
			</div>
			</li>
			<!-- 오른쪽 -->
		</ul>
		</div>
		</div>
	</div>
	<!-- 내용 끝 -->
<!-- 왼쪽 사이드 바 시작 -->
<!-- 메인 끝 -->

</div>
	
</body>
</html>