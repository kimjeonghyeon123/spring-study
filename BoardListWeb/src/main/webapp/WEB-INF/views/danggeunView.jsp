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
    <script src="https://kit.fontawesome.com/cac1ec65f4.js" crossorigin="anonymous"></script>
    <script src="<c:url value='/resources/js/danggeun.js' />"></script>
    <script src="<c:url value='/resources/js/toggle.js' />" defer></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"
        integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"
        integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ"
        crossorigin="anonymous"></script>
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
                        <dd>${danggeunDTO.writer}</dd>
                    </dl>
                    <dl>
                        <dt>작성일</dt>
                        <dd>${danggeunDTO.reg_date}</dd>
                    </dl>
                    <dl>
                        <dt>조회</dt>
                        <dd>${danggeunDTO.view_cnt}</dd>
                    </dl>
                    <dl>
                        <dd>${danggeunDTO.local_id}</dd>
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
                <a href="chat.html" class="on">채팅</a>
                <a href="<c:url value='/danggeun/list' />">목록</a>
            </div>

        </div>
</body>

</html>