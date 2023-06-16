<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 상단 검은색 메뉴바 -->
	<div class="topNavi">
		<div class="inner">
			<ul class="fr">
				<!--                    소유주 권한 추가-->
				<li><a href="#">로그인</a></li>
				<li><a href="#">회원가입</a></li>
				</li>
			</ul>
		</div>
	</div>
	<!-- 상단 검은색 메뉴바 끝-->
	<!-- 검색바, 메인네임 시작-->
	<div class="gnb">
		<!-- 아파트 로고 사진 시작 -->
		<a href="#">
		<img src="${pageContext.request.contextPath}/img/apt_logo.png" alt="아파트로고" />
		</a>
		<!-- 아파트 로고 사진 끝 -->
		<!-- 검색바 시작 -->
		<div class="fr search_wrap">
			<label for="search_box"> <input type="text" name="search_box"
				id="search_box" value="" title="검색어 입력" class="search_field"
				onkeypress="if(event.keyCode==13) {search_board(); return false;}" />
			</label> <a href="javascript:search_board();" class="top_search_btn">검색</a>
		</div>
		<!-- 검색바 끝 -->
	</div>
	<!-- 검색바, 메인네임 끝-->
	<div class="lnb">
		<div class="inner">
			<!-- 메뉴 전체보기 시작 -->
			<div class="allCate">
				<a href="#" class="allCate_menu">메뉴 전체보기</a>
				<div class="allCate_dimmed" style="display: none"></div>
				<div class="allCate_inner" style="display: none">
					<a href="#" class="allcate_btn_close">창 닫기</a>
					<div class="allmenu">
						<dl>
							<dt>아파트 소개</dt>
							<dd>
								<ul>
									<li><a href="#"> 아파트 정보 </a></li>
									<li><a href="#"> 주민자치회 정보 </a></li>
									<li><a href="#"> 관리사무소 조직도 </a></li>
								</ul>
							</dd>
						</dl>
						<dl>
							<dt>공지사항</dt>
							<dd>
								<ul>
									<li><a href="#"> 관리사무소 공지사항 </a></li>
									<li><a href="#"> 입대의 공지사항 </a></li>
									<li><a href="#"> 건의사항 </a></li>
								</ul>
							</dd>
						</dl>
						<dl>
							<dt>예약/신청</dt>
							<dd>
								<ul>
									<li><a href="#"> 시설예약 </a></li>
									<li><a href="#"> 하자보수 신청 </a></li>
								</ul>
							</dd>
						</dl>
						<dl>
							<dt>커뮤니티</dt>
							<dd>
								<ul>
									<li><a href="#"> 자유게시판 </a></li>
									<li><a href="#"> 중고구매 </a></li>
									<li><a href="#"> 중고판매 </a></li>
								</ul>
							</dd>
						</dl>
						<dl>
							<dt>기타</dt>
							<dd>
								<ul>
									<li><a href="#"> 1:1문의 </a></li>
									<li><a href="#"> 자주묻는 질문 </a></li>
								</ul>
							</dd>
						</dl>
					</div>
				</div>
			</div>
			<!-- 메뉴 전체보기 끝 -->
			<!-- 드롭다운 메뉴 시작 -->
			<div class="cateList">
				<ul>
					<li class="dropdown"><a href="#">아파트 소개</a>
						<ul class="lnb_dp2">
							<li class="sub_dropdown"><a href="#"> 아파트 정보 </a></li>
							<li class="sub_dropdown"><a href="#"> 주민자치회 정보 </a></li>
							<li class="sub_dropdown"><a href="#"> 관리사무소 조직도 </a></li>
						</ul>
					</li>
					<li class="dropdown"><a href="#">공지사항</a>
						<ul class="lnb_dp2">
							<li class="sub_dropdown"><a href="#"> 관리사무소 공지사항 </a></li>
							<li class="sub_dropdown"><a href="#"> 입대의 공지사항 </a></li>
							<li class="sub_dropdown"><a href="#"> 건의사항 </a></li>
						</ul>
					</li>
					<li class="dropdown"><a href="#">예약/신청</a>
						<ul class="lnb_dp2">
							<li class="sub_dropdown"><a href="#"> 시설예약 </a></li>
							<li class="sub_dropdown"><a href="#"> 하자보수 신청 </a></li>
						</ul>
					</li>
					<li class="dropdown"><a href="#">커뮤니티</a>
						<ul class="lnb_dp2">
							<li class="sub_dropdown"><a href="#"> 자유게시판 </a></li>
							<li class="sub_dropdown"><a href="#"> 중고구매 </a></li>
							<li class="sub_dropdown"><a href="#"> 중고판매 </a></li>
						</ul>
					</li>
					<li class="dropdown"><a href="#">기타</a>
						<ul class="lnb_dp2">
							<li class="sub_dropdown"><a href="#"> 1:1문의 </a></li>
							<li class="sub_dropdown"><a href="#"> 자주묻는 질문 </a></li>
						</ul>
					</li>
				</ul>
			</div>
			<!-- 드롭다운 메뉴 끝 -->
		</div>
	</div>
	<div class="inner">
		
		<div id="container" class="inner">
		</div>
<!-- header -->
<!-- header 끝 -->