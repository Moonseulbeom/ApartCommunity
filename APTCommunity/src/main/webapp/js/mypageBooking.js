$(function(){
	
	//페이지 로딩시 기본값 삽입
	let firstTime = new Date();
	let fMonth = "";
	if(firstTime.getMonth()+1 < 10){
		fMonth = "0" + (firstTime.getMonth()+1);
	}else{
		fMonth = firstTime.getMonth()+1;
	}
	let time = firstTime.getFullYear() + "-" + fMonth;
	$('#search_booking').val(time);
	//페이지 로딩시 기본값 삽입 -- 끝
	
	
	let currentPage;
	let count;
	let rowCount;
	
	console.log("currentPage" + currentPage);
	
	function searchList(pageNum){
		currentPage = pageNum;
		//로딩 이미지 노출
		$('#loading').show();
		
		$.ajax({
			url:'myPage_BeforeBookingList.do',
			type:'post',
			data:{pageNum:pageNum,bk_date:$('#search_booking').val()},
			dataType:'json',
			success:function(param){
				
				count = param.count;
				rowCount = param.rowCount;
				
				if(pageNum == 1){
					//처음 호출시는 해당 ID의 div 내부 내용물을 제거
					$('#BeforeMyBookingList').empty();
				}
				let output = "<caption>이전 예약 조회 </caption>";
				//비어 있지 않으면
				if(param.isEmp == "yes"){
					output += "<tr>";
					output += "<th>예약번호</th>";
					output += "<th>시설</th>";
					output += "<th>타입</th>";
					output += "<th>인원</th>";
					output += "<th>예약날짜</th>";
					output += "<th>시간</th>";
					output += "</tr>";
					//반복 시작
					$(param.list).each(function(index,item){
						output += "<tr>";
						output += "<td>"+item.bk_num+"</td>";
						output += "<td>"+item.room_info.room_name+"</td>";
						output += "<td>"+item.room_info.room_type+"</td>";
						output += "<td>"+item.book_mem+"</td>";
						output += "<td>"+item.bk_date+"</td>";
						output += "<td>"+item.time+"</td>";
						output += "</tr>";
					});
				}else{
					output += "<tr class='no-Booking'><th>예약신청한 시설이 없습니다.</th></th>";
					$('#loading').hide();
					$('.paging-button').hide();
					$('#BeforeMyBookingList').append(output);
					return;
				}
				//출력
				$('#BeforeMyBookingList').append(output);
				$('#loading').hide();
				
				if(currentPage >= Math.ceil(count/rowCount)){
					//다음 페이지가 없음
					$('.paging-button').hide();
				}else{
					//다음 페이지가 있음
					$('.paging-button').show();
				}
			},
			error:function(){
				$('#loading').hide();
				alert('이전 예약 조회 네트워크 오류 발생');
			}
		});// end of ajax------
	}
	//페이지 처리 이벤트 연결(다음 댓글 보기 버튼 클릭시 데이터 추가)
	$('.paging-button input').click(function(){
		searchList(currentPage + 1);
	});
	
	
	$(document).on('change','#search_booking',function(){
		searchList(1);
	});
	
	//초기 데이터(목록) 호출
	searchList(1);
	
});























