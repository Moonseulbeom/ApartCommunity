	var today = new Date();
	
	//달력 생성하는 함수
	function buildCalendar(){
		let row = null;
		let cnt = 0;
		let realDate = new Date();
		//현재 날짜 숫자형태 (yyyymm)
		let realYM = realDate.getFullYear()*100 + (realDate.getMonth()+1);
		//선택한 날짜 숫자형태 (yyyymm)
		let currentYM = today.getFullYear()*100 + (today.getMonth()+1);
		//id값에 yyyy-mm-dd 형식을 집어넣기 위함.
		let fmM = (today.getMonth()+1);
		if(fmM<10)
			fmM = "0" + fmM;
		let fmYM = today.getFullYear() + "-" + fmM + "-";
		
		//캘린더 테이블 불러오기
		let calendar = document.getElementById("calendar");
		let calendarTitle = document.getElementById("calendarTitle");
		//년 월 삽입하기 
		calendarTitle.innerHTML = "<label>"+today.getFullYear()+"년 "+(today.getMonth()+1)+"월</label>";
		
		//현 달력의 첫째날
		let firstDate = new Date(today.getFullYear(), today.getMonth(), 1);
		//현 달력의 마지막 날
		let lastDate = new Date(today.getFullYear(), today.getMonth()+1, 0);
		
		//작성할 테이블을 초기화(타이틀, 요일표시를 제외한 나머지 제거)
		while(calendar.rows.length > 2){
			calendar.deleteRow(calendar.rows.length -1);
		}
		
		//이전 달의 빈 셀 생성하기
		row = calendar.insertRow();
		for(i = 0; i < firstDate.getDay(); i++){
			cell = row.insertCell();
			cell.setAttribute('class', 'disable');
			cnt += 1;
		}
		
		//달력에 요일 채우기
		for(i = 1; i <= lastDate.getDate(); i++){
			cell = row.insertCell();
			cnt += 1;
		    
			let fmYMD = "";
			if(i<10){
				fmYMD = fmYM + "0" + i;
			}else{
				fmYMD = fmYM + i;
			}
		   	cell.setAttribute('id', fmYMD);
		   	cell.setAttribute('class', 'current');

			//오늘 이전의 날짜들은 모두 비활성화 / else 면 활성화 
		   	if (today.getDate()>i) {
				cell.className += ' disable';
			}else{
				cell.className += ' yday';
			}
		   	if (today.getDate()==i && today.getMonth()) {
				cell.className += ' today';
			}
			
			cell.innerHTML = i;
		    
			if (cnt % 7 == 0){
				row = calendar.insertRow();
			}
		}
		//마지막날 뒤 빈칸 채우기
		if(cnt % 7 != 0){
			for(i = 0; i < 7 - (cnt % 7); i++){
				cell = row.insertCell();
				cell.setAttribute('class', 'disable');
			}
		}
		
		
		let currentDays = document.getElementsByClassName("current");
		//현재 날짜보다 선택한 날짜가 더 크면 모두 활성화
		if(realYM < currentYM){
			for(let i=0; i<currentDays.length; i++){
				currentDays[i].className = "current yday";
			}
		}else if(realYM === currentYM){//같은 연도+ 같은 달 이면 아무것도 안함.
		}else{
			//현재 날짜보다 선택한 날짜가 더 낮으면 모두 비활성화
			for(let i=0; i<currentDays.length; i++){
				currentDays[i].className = "current disable";
			}
		}
		
	};// end of buildCalendar()
	
	
	//다음달 생성하는 함수
	function nextCalendar(){
		today = new Date(today.getFullYear(), today.getMonth()+1, today.getDate());
		buildCalendar();
	}
	
	//이전달 생성하는 함수
	function prevCalendar(){
		today = new Date(today.getFullYear(), today.getMonth()-1, today.getDate());
		buildCalendar();
	}
	
	
	
$(function(){
	
	//예약 가능한 날짜 클릭시 주황색으로 변경하기
	$(document).on('click','.yday',function() {
		$('.book-list').hide();//목록 숨기기
		$('#book_output').empty();//table 내용 비우기
		$('#book_non').remove();
		$('#book_non_hr').remove();
	    $('.yday').removeClass('enable');
	    $(this).addClass('enable');

		let isDate = $(this).attr('id');
		let room_name = $('select[name=room_select] > option:selected').val();
		let room_type = $('select[name=room_select2] > option:selected').val();
		let room_type_name = $('select[name=room_select2] > option:selected').text();
		//console.log(room_name);
		//console.log(room_type);
		//console.log(isDate);
		
		//h1 글씨 바꾸기
		$('#book_title').html(isDate);
		
		$.ajax({
			url:'manage-serviceList.do',
			type:'post',
			data:{room_type:room_type, bk_date:isDate},
			dataType:'json',
			success:function(param){
				let output = '';
				//alert(param.book_list.length);
				if(param.book_list.length == 0){
					output += '<div class="result-display" id="book_non">현재 예약이 없습니다.</div>';
					output += '<hr color="#edeff0" noshade="noshade" id="book_non_hr">';
					$("#change-booklist").append(output);
					$('.book-list').show(); 
				}else{
					$(param.book_list).each(function(index,item){
						//console.log(param.book_list);
						output += '<tr>';
						output += '<td>'+item.dongho+'</td>';
						output += '<td>'+room_name+'</td>';
						output += '<td>'+room_type_name+'</td>';
						output += '<td>'+item.book_mem+'</td>';
						output += '<td>'+item.start_time+'-'+item.end_time+'</td>';
						output += '</tr>';
					})	
					$("#book_output").append(output); // index가 끝날때까지
					$('.book-list').show(); 
				}
			},
			error:function(){
				alert('네트워크 오류 발생');
				$("#timeList_content").empty();
				let output ="<h1 class='right-text'>오류 발생</h1>";
				$('#timeList_content').append(output);
			}
		});//-----.ajax
	
	//임시 휴관 버튼 누를시
	$('.book-list').on('click','#noBook_btn',function(){
		//alert(room_type);
		$.ajax({
			url:'manage-serviceList.do',
			type:'post',
			data:{room_type:room_type, bk_date:isDate, book_check:'1'},
			dataType:'json',
			success:function(param){
				//휴관 완료한 날짜 색 변경
				$(this).addClass('success-block');
			},
			error:function(){
				alert('휴관 버튼 네트워크 오류 발생');
			}
		})
	})
		
	});// end of 예약 가능한 날짜 클릭()-----
	
	//클래스선택자를 .ytime 으로 바꿀 예정 
	$(document).on('click','.ytime',function(){
		$('.ytime').removeClass('enable-Li');
		$(this).addClass('enable-Li');
		let time = $(this).attr('id');
		let start = "";
		let end = "";
		if(time.length==12){
			start = time.substr(0,4);
		}else{
			start = time.substr(0,5);
		}
		end = time.slice(-5);
		//console.log("앞자리 자르기 : "+time.substr(0,5));
		//console.log("뒷자리 자르기 : "+time.slice(-5));
		//time.substr(0,5);
		//time.slice(-5)
		$('#start_time').val(start);
		$('#end_time').val(end);
	});
	
	
	//인원수 값 변경시 hidden 값에 적용하기
	$(document).on('change','#peoples',function(){
		$('#book_mem').val($(this).val());
		
	});
});
