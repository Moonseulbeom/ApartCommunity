$(function(){
	
	//전체 메뉴보기 함수 시작
	$(document).on('click','.allCate_menu',function(){
		$(".allCate_dimmed").css('display','block');
		$(".allCate_inner").css('display','block');
	});
	$(document).on('click','.allcate_btn_close',function(){
		$(".allCate_dimmed").css('display','none');
		$(".allCate_inner").css('display','none');
	});
	//전체 메뉴보기 함수 끝
	
	//드롭박스 시작
	$(window).scroll(function () {
                set = $(document).scrollTop()+"px";
                $('#banner').animate({top:set},{duration:1000,queue:false});
            });

            $(".dropdown").hover(function() {

                $(this).find(".lnb_dp2").slideDown("fast");

            }, function(){
                $(this).find(".lnb_dp2").hide();
            });

            $(".sub_dropdown").hover(function(){
                $(this).addClass("on");
            },function(){
                $(this).removeClass("on");
    });
	//드롭박스 끝
});