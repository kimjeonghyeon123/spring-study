<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="loginout" value="${sessionScope.member==null ? 'Login' : 'Logout' }" />
<c:set var="loginoutlink" value="${sessionScope.member==null ? '/login' : '/logout' }" />
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dangguenView.css">
    <script src="https://kit.fontawesome.com/cac1ec65f4.js" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/resources/script/dangguenView.js"></script>
    <script src="${pageContext.request.contextPath}/resources/script/toggle.js" defer></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"
        integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"
        integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ"
        crossorigin="anonymous"></script>

    <title>댕근마켓</title>
</head>

<body>
<header>
    <nav class="navbar">

        <div class="navbar__logo">
            <a href="${pageContext.request.contextPath}/main">with DANG</a>
        </div>

        <ul class="navbar__menu">
            <li><a href="${pageContext.request.contextPath}/main">댕댕여지도</a></li>
            <li><a href="${pageContext.request.contextPath}/dangguen">댕근마켓</a></li>
            <li><a href="${pageContext.request.contextPath}/dangcare">댕댕케어</a></li>
            <li><a href="${pageContext.request.contextPath}/dangcomu/list">댕댕커뮤</a></li>
            <li><a href="${pageContext.request.contextPath}/dangoffice">댕사무소</a></li>
            <c:if test="${ member != null }">
                <li><a href="${pageContext.request.contextPath}/mypage"><i class="fa fa-user-o" id="btnMypage" aria-hidden="true"></i></a></li>
            </c:if>
            <li><button class="btnLogin"><a href="<c:url value='${loginoutlink }' />">${loginout}</a></button></li>

        </ul>

        <a href="#" class="navbar__toggleBtn">
            <i class="fas fa-bars" aria-hidden="true"></i>
        </a>
    </nav>
</header>



<div class="board_wrap">
        <div class="board_title">
            <strong>댕댕이 양말</strong>
        </div>
        <div class="board_view_wrap">
            <div class="board_view">
                <div class="title">
                    강아지 양말 직거래 합니다~
                </div>
                <div class="info">
                    <dl>
                        <dt>글쓴이</dt>
                        <dd>쳇GPT</dd>
                    </dl>
                    <dl>
                        <dt>작성일</dt>
                        <dd>2023.04.14</dd>
                    </dl>
                    <dl>
                        <dt>조회</dt>
                        <dd>1</dd>
                    </dl>
                </div>
                <div class="slideshow-container">

                    <div class="mySlides fade">
                        <img src="${pageContext.request.contextPath}/resources/image/양말1.PNG" style="width:60%">
                    </div>

                    <div class="mySlides fade">
                        <img src="${pageContext.request.contextPath}/resources/image/양말2.PNG" style="width:60%">
                    </div>

                    <div class="mySlides fade">
                        <img src="${pageContext.request.contextPath}/resources/image/양말3.PNG" style="width:60%">
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
                    미개봉 새제품 입니다!<br>
                    6시 이후 강남역 직거래 원합니다.<br>
                    메세지주세요!<br><br>
                    
                </div>
            </div>

            <div class="bt_wrap">
                <a href="chat.html" class="on">채팅</a>
                <a href="dangguen.html">목록</a>
            </div>

        </div>
</body>

</html>