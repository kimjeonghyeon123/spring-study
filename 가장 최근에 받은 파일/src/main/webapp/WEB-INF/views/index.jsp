<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="loginout" value="${sessionScope.email==null ? 'Login' : 'Logout'}" />
<c:set var="loginoutlink" value="${sessionScope.email==null ? '/login/login' : '/login/logout'}" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6eb28aa20d7222d5529f51952b8be3c3"></script>
    <script src="https://kit.fontawesome.com/cac1ec65f4.js" crossorigin="anonymous"></script>
	<script src="<c:url value='/resources/js/main.js'/>" defer></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css2?family=Gaegu&family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
	<style type="text/css">
	



#main {
    width: 100%;
    display: flex;
    justify-content: center;
}

.main-map {
    margin-top: 0px;
    width: 1000px;
    height: 700px auto;

}

.dogicon {
    display: flex;
    position: absolute;
    width: 10%;
    margin: -20px 2%;
    height: 210px;
}
.dogicon:hover {
    transform: scale(1.05);
    transition: transform .5s;
}


.main-title {
    font-size: 60px;
    font-weight: bolder;
    color: #fff;
    font-family: 'Gaegu', cursive !important;
    background-color: #ed8b9e;
    border-radius: 50px;
    margin: 18px -500px;
    width: 110%;
    text-align: right;
    padding-right: 300px;
}

.main-side {
    width: 100%;
    height: 130px;
    display: flex;
    justify-content: space-around;
    margin: 40px 0 10px 0;
}

.main-pin {
    text-align: center;

}

.main-pin>a>img {
    width: 120px;
    transform: scale(1.0);
    /* 이미지 확대 */
    transition: transform .5s;
    /* 시간 설정 */
}

.main-pin>a>img:hover {
    transform: scale(1.5);
    /* 이미지 확대 */
    transition: transform .5s;
    /* 시간 설정 */
}

@media screen and (max-width: 1024px) {
    .main-title {
        font-size: 50px;
        font-weight: bolder;
        color: #fff;
        font-family: 'Gaegu', cursive !important;
        background-color: #ed8b9e;
        border-radius: 50px;
        margin: 18px -500px;
        width: 1000px;
        text-align: right;
        padding-right: 200px;
    }
}

/* 모바일 화면 */
@media screen and (max-width: 768px) {
    #main {
        display: block;
        /* border-bottom: 5px solid #ed8b9e; */
        height: 580px;
    }
    .dogicon {
        display: flex;
        position: absolute;
        width: 10%;
        margin: -20px 56%;
        height: 170px;
    }

    .main-map {
        width: 100%;
        height: auto;

    }
    .main-side {
        width: 100%;
        height: auto;
        display: flex;
        justify-content: space-around;
    }

    .main-title {
        position: static;
        text-align: center;
        font-size: 33px;
        font-weight: 700;
        color: #fff;
        width: 700px;
        padding-right: 120px;
        text-align: right;
        background-color: #b7a4cc;
    }

    .main-pin {
        text-align: center;
        display: inline-flex;
        /* 변경된 부분 */
    }

    .main-pin>a>img {
        width: 80px;
        transform: scale(1.0);
        transition: transform .5s;
    }

    .main-pin>a>img:hover {
        transform: scale(1.5);
        transition: transform .5s;
    }
}
	
	
	</style>
    <title>위드댕</title>
</head>

<body>
    <jsp:include page="header.jsp"></jsp:include>
    <a href="#" id="scroll-top">TOP</a>
    
    <section id="main">
        <div class="main-map">
            <div onclick="play()">
                <audio id='audio_play' src='resources/sound/dog_sound.mp3'></audio>
                <a class="dogicon" style="z-index: 2"><img src="resources/img/dogicon.png" /></a>
            </div>
            
            <br><br>
            <p class="main-title"> 댕댕여지도</p>
            <div id="map" class="map" style="width: auto; height: 430px;z-index: 1">
            </div>



            <div class="main-side">
                <div class="main-pin">
                    <a href="#"><img src="resources/img/pin1.png" alt="핀1"></a>
                </div>
                <div class="main-pin">
                    <a href="#"><img src="resources/img/pin2.png" alt="핀2"></a>
                </div>
                <div class="main-pin">
                    <a href="#"><img src="resources/img/pin3.png" alt="핀3"></a>
                </div>
                <div class="main-pin">
                    <a href="#"><img src="resources/img/pin4.png" alt="핀4"></a>
                </div>
                <div class="main-pin">
                    <a href="#"><img src="resources/img/pin6.png" alt="핀6"></a>
                </div>
            </div>
        </div>

    </section>
    <br><br>

    <script type="text/javascript">
        function play() {
            var audio = document.getElementById('audio_play');
            if (audio.paused) {
                audio.play();
            } else {
                audio.pause();
                audio.currentTime = 0
            }
        } 
    </script>



</body>

</html>