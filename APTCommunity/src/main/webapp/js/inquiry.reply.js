$(function(){
	let currentPage;
	let count;
	let rowCount;
	
	//댓글 목록
	function selectList(pageNum){
		currentPage = pageNum;
		//로딩 이미지 노출
		$('#loading').show();
		
		$.ajax({
			url:'listManage.do',
			type:'post',
			data:{pageNum:pageNum,in_num:$('#in_num').val()},
			dataType:'json',
			success:function(param){
				//로딩이미지 감추기
				$('#loading').hide();
				count = param.count;
				rowCount = param.rowCount;
				
				if(pageNum == 1){
					//처음 호출시는 해당 ID의 div 내부 내용물을 제거
					$('#output').empty();
				}
				
				$(param.list).each(function(index,item){
					let output = '<div class="outputmain">';
					output += '<div class="comment">';
					output += '<h3> 문의 답변<h3>';
					output += '<h4> 작성자 : 관리자</h4>';
					output += '<hr size="1" width="100%" noshade="noshade" color="#e8e8e8">'
					output += '<div class="sub-item">';
					output += '<p>'+item.content+'</p>';
					output += '<div class="sub-down">';
					//날짜
					if(item.modify_date){
						output += '<span class="modify-date">최근 수정일 : '+item.modify_date+'</span>';
					}else{
						output += '<span class="modify-date">등록일 : '+item.reg_date+'</span>';
					}
					
					//수정 삭제 버튼처리
					//로그인한 회원번호와 작성자의 회원번호가 일치 여부 체크
					if(param.user_auth == 9){
						//로그인한 회원번호와 작성자의 회원번호가 일치
						output += ' <input type="button" data-renum="'+item.re_num+'" value="삭제" class="delete-btn">';
						output += ' <input type="button" data-renum="'+item.re_num+'" value="수정" class="modify-btn">';
						
					}
					
					output += '</div>';
					output += '</div>';
					output += '</div>';
					output += '</div>';
					
					//문서 객체에 추가
					$('#output').append(output);
				});
				//page button처리
				if(currentPage>=Math.ceil(count/rowCount)){
					//다음 페이지가 없음
					$('.paging-button').hide();
				}else{
					//다음 페이지가 있음
					$('.paging-button').show();
				}
			},
			error:function(){
				//로딩이미지 감추기
				$('#loading').hide();
				alert('네트워크 오류 발생');
			}
		});
	}
	//페이지 처리 이벤트 연결(다음 댓글 보기 버튼 클릭시 데이터 추가)
	$('.paging-button input').click(function(){
		selectList(currentPage + 1);
	});
	
	//댓글 등록
	$('#comment_form').submit(function(event){
		//기본이벤트 제거
		event.preventDefault();
		
		//댓글내용이 없을 경우 댓글등록 불가
		if($('#comment_content').val().trim()==''){
			alert('내용을 입력하세요');
			$('#comment_content').val('').focus();
			return false;
		}
		
		
		
		//폼 이하의 태그에서 입력한 데이터를 모두 읽어옴
		let form_data = $(this).serialize();
		
		//서버와 통신
		$.ajax({
			url:'writeManage.do',
			type:'post',
			data:form_data,
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 이용하세요');
				}else if(param.result == 'success'){
					//폼 초기화
					initForm();
					//댓글 작성이 성공하면 입력한 댓글을 포함해서 첫번째 페이지의 게시글을 다시 호출(새로고침한것처럼)
					selectList(1);
				}else{
					alert('댓글 등록 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});
	
	//댓글 작성 폼 초기화
	function initForm(){
		$('textarea').val('');
		$('#comment_first .letter-count').text('300/300');
	}
	
	//댓글 수정 버튼 클릭시 수정폼 노출
	$(document).on('click','.modify-btn',function(){
		//댓글 번호
		let re_num = $(this).attr('data-renum');
		//댓글 내용
		let content = $(this).parent().parent().find('p').html().replace(/<br>/gi,'\n');
		/* /<br>/gi,'\n' : <br>을 \n으로 바꿔라
			g : 지정문자열 모두,
			i : 대소문자 무시
		*/
		
		//댓글 수정폼 UI
		let modifyUI = '<form id="mre_form">';
		modifyUI += '<input type="hidden" name="re_num" id="mre_num" value="'+re_num+'">';
										//name->서버전송, id->js나 css에 사용
		modifyUI += '<textarea rows="3" cols="50" name="m_content" id="m_content" class="rep-content">'+content+'</textarea>';
							 //rows="높이" cols="넓이"
		modifyUI += '<div id="m_first"><span class="letter-count">300/300</span></div>';
		modifyUI += '<div id="m_second" class="align-right">';
		modifyUI += ' <input type="submit" value="수정">';
		modifyUI += ' <input type="button" value="취소" class="re-reset">';
		modifyUI += '</div>';
		modifyUI += '</form>';
		
		//이전에 이미 수정하는 댓글이 있을 경우 수정버튼을 클릭하면
		//숨겨져있는 sub-item을 환원하고 수정폼을 초기화함
		initModifyForm();
		
		//데이터가 표시되어 있는 div감추기
		$(this).parent().parent().hide();
		//수정폼을 수정하고자 하는 데이터가 있는 div에 노출
		$(this).parent().parent().parent().append(modifyUI);
		$(this).parent().parent().parent().css({
			"background-color":"#EEE",
			"margin-top":"20px"
		});
		
		//입력한 글자수 세팅
		let inputLength = $('#m_content').val().length;
		let remain = 300 - inputLength;
		remain += '/300';
		
		//문서 객체에 반영
		$('#m_first .letter-count').text(remain);
		
	});
	
	//textarea에 내용 입력시 글자수 체크
	$(document).on('keyup','textarea',function(){
		//입력한 글자수 구함
		let inputLength = $(this).val().length;
		
		if(inputLength>300){//300자를 넘어선 경우
			$(this).val($(this).val().substring(0,300));//->300자까지만 인정, 나머지는 잘라냄
		}else{//300자 이하인 경우
			let remain = 300 - inputLength;
			remain += '/300';
			if($(this).attr('id')=='comment_content'){
				//등록폼 글자수
				$('#comment_first .letter-count').text(remain);
			}else{
				//수정폼 글자수
				$('#mre_first .letter-count').text(remain);
			}
		}
	});
	
	//수정 폼에서 취소 버튼 클릭시 수정폼 초기화
	$(document).on('click','.re-reset',function(){
		initModifyForm();
	});
	
	//댓글 수정폼 초기화
	function initModifyForm(){
		$('.sub-item').parent().css({
			"background-color":"white"
		});
		$('.sub-item').show();
		$('#mre_form').remove();
	}
	
	//댓글 수정
	$(document).on('submit','#mre_form',function(event){
		//기본 이벤트 제거
		event.preventDefault();
		
		//조건체크->댓글내용을 입력하지 않은 경우
		if($('#m_content').val().trim()==''){
			alert('내용을 입력하세요');
			$('#m_content').val('').focus();
			return false;
		}
		
		//폼에 입력한 데이터 반환
		let form_data = $(this).serialize();
		
		//서버와 통신
		$.ajax({
			url:'updateManage.do',
			type:'post',
			data:form_data,
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인해야 수정할 수 있습니다.');
				}else if(param.result == 'success'){
					$('#mre_form').parent().find('p').html($('#m_content').val().replace(/</g,'&lt;').replace(/>/g,'&gt;').replace(/\n/g,'<br>'));
					//날짜,시간
					$('#mre_form').parent().find('.modify-date').text('최근 수정일 : 5초미만');
					//수정폼 삭제 및 초기화
					initModifyForm();
				}else if(param.result == 'wrongAccess'){
					alert('타인의 글을 수정할 수 없습니다.');
				}else{
					alert('댓글 수정 오류');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});
	
	//댓글 삭제
	$(document).on('click','.delete-btn',function(){
		//댓글 번호
		let re_num = $(this).attr('data-renum');
		
		$.ajax({
			url:'deleteManage.do',
			type:'post',
			data:{re_num:re_num},
			dataType:'json',
			success:function(param){
				if(param.result == 'logout'){
					alert('로그인 후 삭제를 이용하세요');
				}else if(param.result == 'success'){
					alert('삭제 완료');
					selectList(1);
				}else if(param.result == 'wrongAccess'){
					alert('타인의 글을 삭제할 수 없습니다.');
				}else{
					alert('댓글 삭제 오류 발생');
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});
	
	//초기 데이터(목록) 호출
	selectList(1);
	
});