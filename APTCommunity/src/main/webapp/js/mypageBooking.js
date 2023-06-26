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
	
/*	function searchList(pageNum){
		currntPage = pageNum;
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
				$(param.list).each(function(index,item){
					let output = ''
				});
			},
			error:function(){
				$('#loading').hide();
				alert('이전 예약 조회 네트워크 오류 발생');
			}
		});// end of ajax------
	}*/
	
	//searchList(1);
	$(document).on('change','#search_booking',function(){
		//searchList(pageNum);
	});

	
});























