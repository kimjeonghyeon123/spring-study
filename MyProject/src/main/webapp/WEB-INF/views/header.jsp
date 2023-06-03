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
	@import url('https://fonts.googleapis.com/css2?family=Gaegu:wght@300;400;700&family=Nanum+Gothic:wght@400;700;800&display=swap');
	@font-face {
    font-family: 'NanumSquareNeo-Variable';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_11-01@1.0/NanumSquareNeo-Variable.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}
:root {
    --background-color: #ed8b9e;
    --bodybackground-color: #a1e1dc;
}
body {
    font-family: 'NanumSquareNeo-Variable';
    margin: 0;
    background-color: var(--bodybackground-color);
}
a {
    text-decoration: none;
}
.logo {
	color: #fff;
	font-size: 28px;
	font-weight: 800;
}
.navbar {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: var(--background-color);
    padding: 16px 12px;
    height: 100px;
}
.navbar__logo {
    position: sticky;
    margin-left: 2%;
}
.logo {
    width: 120px;
    margin-left: 30px;
}
.navbar__menu {
    list-style: none;
    display: flex;
    margin-top: 55px;
    background-color: rgb(255, 255, 255);
    border-radius: 30px;
    font-weight: bold;
    font-size: 16px;
    padding: 0px 50px;
}
.navbar__menu li {
    padding: 12px 24px;
}
.navbar__menu a {
    color: #ed8b9e;
}
.navbar__menu li:hover {
    background-image: linear-gradient(transparent 75%, #c4aedb 50%);    
}
.navbar__icons {
    list-style: none;
    display: flex;
    color: #fff;
}
.navbar__icons li {
    padding: 8px 12px;
    margin: 0;
}
.navbar__toggleBtn {
    display: none;
    color: #fff;
    position: absolute;
    margin-top: 12px;
    right: 30px;
    font-size: 20px;
}
#btnLogin {
    margin: 0;
    color: #ed8b9e;
    border: none;
    background-color: #fff;
    font-weight: bold;
}
.btnLogin {
	background-color: #fff;
    border: 0.12rem solid #ed8b9e;
    font-weight: 600;
    font-family: 'NanumSquareNeo-Variable';
    
}
#scroll-top {
    display:scroll;
    position:fixed;
    bottom:10%;
    right:5%;
    width: 50px;
    border-radius: 30px;
    border: none;
    text-align: center;
    padding: 1%;
    font-weight: 800;
    background-color: #b7a4cc;
    color: #fff;
}

@media screen and (max-width: 1024px) {
    .logo {
        width: 80px;
        margin-left: 0px;
        font-size: 16px;
    }
    .navbar__menu {
        list-style: none;
        display: flex;
        padding: 0px;
        font-size: 14px;
    }
    .navbar__menu li {
        text-align: center;
        padding: 12px 18px;

    }
}

/*768 이하로 줄어들면 내비바*/
@media screen and (max-width: 768px) {
    .navbar {
        flex-direction: column;
        align-items: flex-start;
        padding: 20px 30px;
        height: 100%;
    }
    .navbar__logo {
        position: sticky;
        margin-left: 2%;
        width: 80px;
        height: 40px;
        margin: 0px;
    }
    .logo {
        width: 50px;
        margin: 0px;
        height: 50px;
    }
    .navbar__menu {
        display: none;
        flex-direction: column;
        align-items: center;
        width: 100%;
    }
    .navbar__menu li {
        width: 100%;
        text-align: center;
    }
    .navbar__menu li:hover {
        transform: scale(1.1);
        background-image: none;    
    }
    .navbar__icons {
        justify-content: center;
        width: 100%;
    }
    .navbar__toggleBtn {
        display: block;
    }
    #scroll-top {
        display:scroll;
        position:fixed;
        bottom:5%;
        right:2%;
        text-align: center;
        padding: 1%;
        border-radius: 30px;
        border: none;
        font-weight: 800;
        background-color: #b7a4cc;
        color: #fff;
    }
}
.navbar__menu.active,
.navbar__icons.active {
    display: flex;
}

	
	</style>
    <title>위드댕</title>
</head>

<body>
    <header>
        <nav class="navbar">
            <div class="navbar__logo">
                <a href=# class="logo">with DANG</a>
            </div>
            <ul class="navbar__menu">
                <li><a href='/'>댕댕여지도</a></li>
                <li><a href="<c:url value='/danggeun/list' />">댕근마켓</a></li>
                <li><a href='/'>댕댕케어</a></li>
                <li><a href="<c:url value='/board/list' />">댕댕커뮤</a></li>
                <li><a href='/'>댕사무소</a></li>
                <li><button class="btnLogin"><a href="<c:url value='${loginoutlink}' />">${loginout}</a></button></li>
                <li><a href="mypage.html"><i class="fa fa-user-o" id="btnMypage" aria-hidden="true"></i></a></li>
            </ul>
            <a href="#" class="navbar__toggleBtn">
                <i class="fas fa-bars" aria-hidden="true"></i>
            </a>
        </nav>
    </header>

    <a href="#" id="scroll-top">TOP</a>


</body>

</html>