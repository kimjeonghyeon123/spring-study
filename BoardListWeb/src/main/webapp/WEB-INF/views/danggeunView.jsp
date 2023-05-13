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
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <script src="https://kit.fontawesome.com/cac1ec65f4.js" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css2?family=Gaegu&family=Nanum+Gothic:wght@400;700;800&display=swap"
        rel="stylesheet">


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
    
    <div class="board_wrap">
        <div class="board_title">
            <h2 class="dangguen-title">댕근마켓</h2>
        </div>
        <div class="board_view_wrap">
            <div class="board_view">
                <div class="title">
                    ${danggeunDTO.title}
                </div>
                <div class="info">
                    <dl>
                        <dt>글쓴이</dt>
                        <dd>${danggeunDTO.writer_name}</dd>
                    </dl>
                    <dl>
                        <dt>작성일</dt>
                        <dd>${danggeunDTO.reg_date}</dd>
                    </dl>
                    <dl>
                        <dt>조회수</dt>
                        <dd>${danggeunDTO.view_cnt}</dd>
                    </dl>
                    <dl>
                   		<dt>찜 수</dt>
                        <dd>${danggeunDTO.zzim_cnt}</dd>
                    </dl>
                    <dl>
                    	<c:if test="${danggeunDTO.writer_email ne loginEmail}">
		                    <c:if test="${danggeunDTO.isStoreByCurrentMember == true}">
								<button type="button" class="btnscrap storeBtn" data-id="${danggeunDTO.id}" data-writeremail="${danggeunDTO.writer_email}" data-loginemail="${loginEmail}" style="background-color: red;">찜♥</button>
							</c:if>
							<c:if test="${danggeunDTO.isStoreByCurrentMember == false}">
								<button type="button" class="btnscrap storeBtn" data-id="${danggeunDTO.id}" data-writeremail="${danggeunDTO.writer_email}" data-loginemail="${loginEmail}">찜♥</button>
							</c:if>
						</c:if>
					</dl>
                </div>
                <div class="slideshow-container">

                    <div class="mySlides fade">
                        <img src="양말2.PNG" style="width:60%">
                    </div>

                    <div class="mySlides fade">
                        <img src="양말2.PNG" style="width:60%">
                    </div>

                    <div class="mySlides fade">
                        <img src="양말2.PNG" style="width:60%">
                    </div>

                    <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
                    <a class="next" onclick="plusSlides(1)">&#10095;</a>

                </div>
                <br>
                <br>
                <br>
                <br>


                <div style="text-align:center">
                    <span class="dot" onclick="currentSlide(1)"></span>
                    <span class="dot" onclick="currentSlide(2)"></span>
                    <span class="dot" onclick="currentSlide(3)"></span>
                </div>
                <div class="cont">
                    ${danggeunDTO.content}
                </div>
            </div>

            <div class="bt_wrap">
            	<c:if test="${danggeunDTO.writer_email eq loginEmail}">
                    <button type="button" id="modifyBtn">수정</button>
                </c:if>
                <c:if test="${danggeunDTO.writer_email ne loginEmail}">
                	<a href="chat.html" class="on">채팅</a>	
                </c:if>
                <a href="<c:url value='/danggeun/list' />">목록</a>
            </div>

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
        
        $("#modifyBtn").on('click', function(){
        	location.href = "<c:url value='/danggeun/write?id=${danggeunDTO.id}' />"
        })
    });
</script>
</body>

</html>