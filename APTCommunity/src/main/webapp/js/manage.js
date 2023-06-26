$(function(){
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
				console.log(param.list);
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
						output += '<td>'+item.dongho+'</td>';
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
		
		
		
//---------------------------------- 조건 체크 --------------------------------------------//		
		//빈 글자 검색 막기
		if($('#keyword').val().trim() == ''){
			alert('검색할 내용을 입력하세요');
			$('#key').val('').focus();
			return false;
		}
	})
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
})