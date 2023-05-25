<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="loginEmail" value="${sessionScope.email}" />
<c:set var="loginout" value="${sessionScope.email==null ? 'Login' : 'Logout'}" />
<c:set var="loginoutlink" value="${sessionScope.email==null ? '/login/login' : '/login/logout'}" />

<!DOCTYPE html>
<html lang="ko">
<head>
   <meta charset="UTF-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value='/resources/css/danggeun.css' />">
    <script src="<c:url value='/resources/js/toggle.js' />" defer></script>
    <script src="https://kit.fontawesome.com/cac1ec65f4.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Gaegu&family=Nanum+Gothic:wght@400;700;800&display=swap"
        rel="stylesheet">
    <style type="text/css">
        #card-list {
        width: 100%;
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        }
        .card {
          width: calc(25% - 20px);
            width: 300px;
            border: none;
            background-color: #d7e7e491;
            border-radius: 5px;
            padding: 10px;
            margin: 15px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .card img {
            width: 200px;
            height: 200px;
            object-fit: cover;
            border-radius: 5px;
            margin-bottom: 12px;
        }

        .card h2 {
            margin-bottom: 10px;
            font-size: 18px;
            text-align: center;
        }

        .card li {
            margin-bottom: 5px;
            font-size: 14px;
            text-align: center;
        }

        .card .price {
            font-weight: bold;
            margin-bottom: 10px;
        }
        .buy-btn {
           width: 80px;
           height:25px;
           border: none;
           background-color: #8e8bac;
           border-radius: 5px;
           color: #fff;
           cursor: pointer;
        }
        .scrap-btn {
           width: 50px;
           height: 25px;
           border: none;
           background-color: #ed8b9e;
           border-radius: 5px;
           color: #fff;
           cursor: pointer;
        }
        .search-box {
           width: 160px;
           height: 35px;
           border: none;
           margin: 10px;
        }    
          /*글쓰기 버튼*/
        .btn-write {
          border-radius: 10px;
          background-color: #ed8b9e;
          border: none;
          width: 150px;
          height: 35px;
          font-weight: 900;
          color: #fff;
          margin: 10px;
          cursor: pointer;
        }
          /*검색 버튼*/
        .btn-search {
          border-radius: 10px;
          background-color: #8e8bac;
          border: none;
          width: 150px;
          height: 35px;
          font-weight: 900;
          color: #fff;
          margin: 10px;
          cursor: pointer;
        }    
        .paging-container {
        	width: 100%;
        	height: 70px;
        	display: flex;
        	margin: auto;
        }
        .paging {
        	color: black;
        	width: 100%;
        	text-align: center;
        	padding: 15px;
        }
        .page {
        	width: 20px;
        	height: 20px;
        	border: 1px solid black;
        	background-color: pink;
        	color: black;
        	padding: 6px;
        	margin-right: 10px;
        	cursor: pointer;
        }
    </style>


    <title>댕근마켓</title>
</head>

<body>
    <header>
        <nav class="navbar">

            <div class="navbar__logo">
                <a href="<c:url value='/' />">with DANG</a>
            </div>

            <ul class="navbar__menu">
                <li><a href="main.html">댕댕여지도</a></li>
                <li><a href="<c:url value='/danggeun/list' />">댕근마켓</a></li>
                <li><a href="dangcare.html">댕댕케어</a></li>
                <li><a href="<c:url value='/board/list' />">댕댕커뮤</a></li>
                <li><a href="dangoffice.html">댕사무소</a></li>
                <li><button class="btnLogin"><a href="<c:url value='${loginoutlink}' />">${loginout}</a></button></li>
                <li><a href="mypage.html"><i class="fa fa-user-o" id="btnMypage" aria-hidden="true"></i></a></li>

            </ul>
            <a href="#" class="navbar__toggleBtn">
                <i class="fas fa-bars" aria-hidden="true"></i>
            </a>
        </nav>
    </header>

<!-- 댕근 헤더 부분 -->
    <section class="dangguen-sec">
        <h2 class="dangguen-title">댕근마켓</h2>
        <div class="alert alert-light" id="dangguen-head" role="alert">

      <form action="<c:url value='/danggeun/list' />" method="get">
            <select class="form-select" aria-label="category" name="danggeun_type">
                    <c:forEach var="DanggeunTypeDTO" items="${typeList}">
                       <option value="${DanggeunTypeDTO.id}" ${pr.sc.danggeun_type == DanggeunTypeDTO.id ? "selected" : ""}>${DanggeunTypeDTO.name}</option>
                    </c:forEach>
            </select>

            <select class="form-select" name="addressRegion" id="addressRegion1"></select>
            <select class="form-select" name="addressDo" id="addressDo1"></select>
            <select class="form-select" name="addressSiGunGu" id="addressSiGunGu1"></select>
            <!-- 검색박스 추가 -->
            <select class="form-select" name="option">
            	<option value="A" ${pr.sc.option=='A' || pr.sc.option=='' ? "selected" : ""}>제목+내용</option>
            	<option value="T" ${pr.sc.option=='T' ? "selected" : ""}>제목</option>
            	<option value="W" ${pr.sc.option=='W' ? "selected" : ""}>작성자</option>
            </select>
            <input type="text" class="search-box" placeholder="상품 키워드" name="keyword" value="${pr.sc.keyword}">
            <button type="submit" class="btn-search" >검색</button>
        </form>
            <button type="button" class="btn-write"><a href="<c:url value='/danggeun/write' />">글쓰기</a></button>
        </div>
    </section>
<!-- 댕근 헤더 끝 -->

    <script>
        $(function () {
            areaSelectMaker("select[name=addressRegion]");
        });

        var areaSelectMaker = function (target) {
            if (target == null || $(target).length == 0) {
                console.warn("Unkwon Area Tag");
                return;
            }
/*삭제*/
            var area = {
                "서울특별시": {
                    "종로구": ["청운동", "신교동", "궁정동", "효자동", "창성동", "통인동", "누상동", "누하동", "옥인동", "체부동", "필운동", "내자동", "사직동", "도렴동", "당주동", "내수동", "세종로", "신문로1가", "신문로2가", "청진동", "서린동", "수송동", "중학동", "종로1가", "공평동", "관훈동", "견지동", "와룡동", "권농동", "운니동", "익선동", "경운동", "관철동", "인사동", "낙원동", "종로2가", "팔판동", "삼청동", "안국동", "소격동", "화동", "사간동", "송현동", "가회동", "재동", "계동", "원서동", "훈정동", "묘동", "봉익동", "돈의동", "장사동", "관수동", "종로3가", "인의동", "예지동", "원남동", "연지동", "종로4가", "효젣종", "종로5가", "종로6가", "이화동", "연건동", "충신동", "동숭동", "혜화동", "명륜1가", "명륜2가", "명륜4가", "명륜3가", "창신동", "숭인동", "교남동", "평동", "송월동", "홍파동", "교북동", "행촌동", "구기동", "평창동", "부암동", "홍지동", "신영동", "무악동", "청운효자동", "종로1.2.3.4가동", "종로5.6가동", "창신1동", "창신2동", "창신3동", "숭인1동", "숭인2동"],


                },
                "경기도": {
                    "수원장안구": ["파장동", "정자동", "이목동", "율전동", "천천동", "영화동", "송죽동", "조원동", "연무동", "상광교동", "하광교동", "율천동", "정자1동", "정자2동", "정자3동", "조원1동", "조원2동"],


                },

            };

            for (i = 0; i < $(target).length; i++) {
                (function (z) {
                    var a1 = $(target).eq(z);
                    var a2 = a1.next();
                    var a3 = a2.next();



                    //초기화
                    init(a1, true);

                    //권역 기본 생성
                    var areaKeys1 = Object.keys(area);
                    areaKeys1.forEach(function (Region) {
                        a1.append("<option value=" + Region + ">" + Region + "</option>");
                    });

                    //변경 이벤트
                    $(a1).on("change", function () {
                        init($(this), false);
                        var Region = $(this).val();
                        var keys = Object.keys(area[Region]);
                        keys.forEach(function (Do) {
                            a2.append("<option value=" + Do + ">" + Do + "</option>");
                        });
                    });

                    $(a2).on("change", function () {
                        a3.empty().append("<option value=''>선택</option>");
                        var Region = a1.val();
                        var Do = $(this).val();
                        var keys = Object.keys(area[Region][Do]);
                        keys.forEach(function (SiGunGu) {
                            a3.append("<option value=" + area[Region][Do][SiGunGu] + ">" + area[Region][Do][SiGunGu] + "</option>");
                        });
                    });
                })(i);

                function init(t, first) {
                    first ? t.empty().append("<option value=''>선택</option>") : "";
                    t.next().empty().append("<option value=''>선택</option>");
                    t.next().next().empty().append("<option value=''>선택</option>");
                }
            }
        }

    </script>
    
    <!-- 성공, 오류 메세지 출력 -->
    <script>
    	let msg = "${msg}"
    	if(msg == "DEL_OK") alert("성공적으로 삭제되었습니다.")
    	if(msg == "DEL_ERR") alert("삭제되었거나 없는 게시물입니다.")
    	if(msg == "WRT_OK") alert("성공적으로 등록되었습니다.")
    	if(msg == "MOD_OK") alert("성공적으로 수정되었습니다.")
    </script>
    
    <!-- 상품 리스트 시작 card-list가 전체 -->
	<form id="card-list">
    	<c:forEach var="danggeundto" items="${list}">
        	<div class="card">
            	<img src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="Product Image">
              	<h2><a href="<c:url value='/danggeun/view${pr.sc.queryString}&id=${danggeundto.id}' />">${danggeundto.title}</a>상품명</h2>
              	<li class="price">${danggeundto.price}원</li>
              	<li id="">${danggeundto.writer_name}</li>
              	<li id="user-adress">서울시 서초동</li>
              	<div>
                 	<button class="buy-btn">구매하기</button>
                    <button type="button" class="scrap-btn storeBtn" data-id="${danggeundto.id}" data-writeremail="${danggeundto.writer_email}" data-loginemail="${loginEmail}" style="${danggeundto.isStoreByCurrentMember ? 'background-color: red;' : ''}">찜♥</button>
                </div>
        	</div>
    	</c:forEach>
	</form>
    
    <div class="paging-container">
    	<div class="paging">
    		<c:if test="${totalCnt == null || totalCnt == 0}">
    			<div>게시물이 없습니다.</div>
    		</c:if>
    		<c:if test="${totalCnt != null && totalCnt != 0}">
    			<c:if test="${pr.showPrev}">
    				<a class="page" href="<c:url value='/danggeun/list${pr.sc.getQueryString(pr.beginPage-1)}' />">&lt;</a>
    			</c:if>
    			<c:forEach var="i" begin="${pr.beginPage}" end="${pr.endPage}">
    				<a class="page" href="<c:url value='/danggeun/list${pr.sc.getQueryString(i)}' />" style="${pr.sc.page == i ? 'text-decoration: 2px underline; text-underline-offset : 3px;' : ''}">${i}</a>
    			</c:forEach>
    			<c:if test="${pr.showNext}">
    				<a class="page" href="<c:url value='/danggeun/list${pr.sc.getQueryString(pr.endPage+1)}' />">&gt;</a>
    			</c:if>
    		</c:if>
    	</div>
    </div>
    
    
    
<script type="text/javascript">
    $(document).ready(function(){
        $(".storeBtn").on('click', function() {
            var button = $(this); // 버튼 객체를 저장
            var id = $(this).data('id')
            var loginemail = $(this).data('loginemail')
            var writeremail = $(this).data('writeremail')
            
            if(loginemail == writeremail) {
               return alert("본인 상품을 찜할 수 없습니다.")
            }
            
            $.ajax({
                type: 'POST',
                url: '/jeonghyeonkim/danggeun/togglezzim',
                data: {
                    'danggeun_id': id,
                    'member_email': loginemail
                },
                success: function(response) {
                  if (response == "added") {
                        button.css('background-color', 'red');
                    } else if (response == "removed") {
                        button.css('background-color', '');
                    }
                },
                error: function(xhr, status, error) {
                    alert("error");
                }
            });
        });
    });
</script>