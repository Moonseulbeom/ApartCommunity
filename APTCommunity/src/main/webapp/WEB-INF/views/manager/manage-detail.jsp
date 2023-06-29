<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.6.0.min.js"></script>
<script>
$(function(){
	//회원 정보 등록 유효성 체크
	$('#mem_detail_form').submit(function(){
		
		let items = document.querySelectorAll(
				   'input[type="text"],input[type="password"],input[type="email"]');
		 for(let i=0;i<items.length;i++){
			
			if(items[i].id == 'carnum'){
					continue;
			} 
			 
			if (items[i].value.trim() == '') {
				alert(items[i].name + ' 항목 필수 입력');
				items[i].value = '';
				items[i].focus();
				return false;
				}
			
			if(items[i].id == 'id' && 
			    	 !/^[A-Za-z0-9]{3,5}$/.test(
			    	             $('#id').val())){
					alert('숫자 사용, 최소 3자 ~ 최대 5자를 사용하세요');
					$('#dongho').val('');
					$('#dongho').focus();
					return false;
				}
			
		    if(items[i].id == 'passwd' && 
			    	 !/^[A-Za-z0-9]{4,12}$/.test(
			    	             $('#passwd').val())){
					alert('영문 또는 숫자 사용, 최소 4자 ~ 최대 12자를 사용하세요');
					$('#passwd').val('');
					$('#passwd').focus();
					return false;
				}
		    
		    if(items[i].id == 'dongho' && idChecked == 0){
				alert('동-호수 중복 체크 필수');
				return false;
			}
		    
		    if(items[i].id == 'phone' && 
			    	 !/^\d{2,3}-\d{3,4}-\d{4}$/.test(
			    	             $('#phone').val())){
					alert('전화번호 형식에 맞게 입력해주세요');
					$('#phone').val('');
					$('#phone').focus();
					return false;
				}
		    
		    if(items[i].id == 'email' && 
			    	 !/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i.test(
			    	             $('#email').val())){
					alert('이메일 형식에 맞게 입력해주세요');
					$('#email').val('');
					$('#email').focus();
					return false;
				}
		}
	})
})
</script>
<!-- 회원 관리 상세 페이지 -->
<div id="mem_detail">
	<h1>회원 상세 페이지</h1>
	<form action="${ pageContext.request.contextPath }/member/modifyUser.do" method="post" id="mem_detail_form">
		<div class="form-mem-detail">  
			<div>
			<label for="name">세대주</label>
			<input type="text" id="name" name="name" value="${ member.name }">
			</div>
			<div>
			<label for="passwd">비밀번호</label>
			<input type="password" id="passwd" name="passwd" value="${ member.passwd }">
			</div>
			<div>
			<label for="phone">전화번호</label>
			<input type="text" id="phone" name="phone" value="${ member.phone }">
			</div>
			<div>
			<label for="email">이메일</label>
			<input type="email" id="email" name="email" value="${ member.email }">
			</div>
			<div>
			<label for="carnum">차량번호</label>
			<input type="text" id="carnum" name="carnum" value="${ member.carnum }">								
			</div>
		</div>
		<div id="detail_member_btn">
			<input type="submit" value="수정">
			<input type="button" value="삭제" 
			 onclick="location.href='${ pageContext.request.contextPath}/member/deleteUser.do?mem_num=${ member.mem_num }'">
			<input type="button" value="이전" onclick="history.go(0)">
		</div>
	</form>
</div>