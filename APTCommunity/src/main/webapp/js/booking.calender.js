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
	    $('.yday').removeClass('enable');
	    $(this).addClass('enable');

		$("#t_l").empty();
		let output = "<li>9:00 ~ 11:00</li>";
		output += "<li>11:00 ~ 13:00</li>";
		output += "<li>14:00 ~ 16:00</li>";
		output += "<li>16:00 ~ 18:00</li>";
		output += "<li>18:00 ~ 20:00</li>";
		output += "<li>20:00 ~ 22:00</li>";
		
		$('#t_l').append(output);
		

		
		let isDate = $(this).attr("id");
/*		$.ajax({
			url:'bookingGetTimeList.do',
			type:'post',
			data:{pageNum:pageNum,board_num:$('#board_num').val()},
			dataType:'json',
			success:function(param){},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});*/
	});
});
