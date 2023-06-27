$(function(){
//상세 페이지 전환
	//1.회원관리-상세페이지/*
		$('#change-memlist').on('click','.mem-detail-btn',function(){
			mem_num = $(this).find('#mem_num').val();
			$.ajax({
				 type:'get',
				 url:'manage-detail.do?mem_num='+mem_num,
				 dataType:'text',
				 success:function(data){
					 let plus = $('#manage_content').html(data).find('#mem_detail');
					 $('#ma	nage_content').html(plus);
				 },
				 error:function(){
					 alert('1.통신 에러 발생');
				 }
			 })
		})

//회원 목록	
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
			success:function(param){
				$('#change-memlist').empty();
				
				let output = '';
				if(param.list == ""){
					$('.mem-page').empty();
					
					output += '<div class="result-display">존재하지 않는 회원입니다.</div>';
					output += '<hr color="#edeff0" noshade="noshade">';
					$("#change-memlist").append(output);
					
				}else{
					output += '<table id="mem_output">';
					$(param.list).each(function(index,item){
						output += '<tr>';
						output += '<td id="mem_detail_btn" class="mem-detail-btn">';
						output += '<input type="hidden" name="mem_num" id="mem_num" value="'+item.mem_num+'">';
						output += item.dongho+'</td>'
						output += '<td>'+item.name+'</td>';
						output += '<td>'+item.phone+'</td>';
						output += '<td>'+item.reg_date+'</td>';
						output += '</tr>';
					})	
					output += '</table>'
					output += '<div class="mem-page">'+param.page+'</div>';
					$("#change-memlist").append(output); // index가 끝날때까지 
				}
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
//회원목록 검색 끝		
		
//캘린더 호출
buildCalendar();

			
//---------------------------------- 조건 체크 --------------------------------------------//		
	$('#write_manage_form').submit(function(){
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
//----------------------------------회원 상세 수정 조건 체크 --------------------------------------------//	

	$('#mem_detail_form').submit(function(){
		if($('#name').val().trim()==''){
				alert('세대주를 입력하세요');
				$('#title').val('').focus();
				return false;
			}
			if($('#passwd').val().trim()==''){
				alert('비밀번호를 입력하세요');
				$('#content').val('').focus();
				return false;
			}
			if($('#phone').val().trim()==''){
				alert('전화번호를 입력하세요');
				$('#content').val('').focus();
				return false;
			}
			if($('#email').val().trim()==''){
				alert('이메일를 입력하세요');
				$('#content').val('').focus();
				return false;
			}
	})













})