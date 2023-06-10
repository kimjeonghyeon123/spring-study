<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="loginout" value="${sessionScope.member==null ? 'Login' : 'Logout' }" />
<c:set var="loginoutlink" value="${sessionScope.member==null ? '/login' : '/logout' }" />
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${path }/resources/css/mypage.css">
    <script src="${path }/resources/script/toggle.js" defer></script>
    <script src="https://kit.fontawesome.com/cac1ec65f4.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"
            integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"
            integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ"
            crossorigin="anonymous"></script>

    <title>마이페이지</title>
</head>

<body>
    <jsp:include page="header.jsp"></jsp:include>


<article id="mypage">
    <aside class="mypage_aside">
        <div class="mypage__profile">
            <div>
                <img src="${path }/resources/image/profile.png" alt="프로필사진">
            </div>
            <p>${member.user_nickname }님 안녕하세요</p>
            <hr class="profile-line">
            <div class="mypage__list">
                <ul>
                    <li><a href="/withdang/mypage">내 정보</a></li>
                    <li><a href="#">채팅</a></li>
                    <li><a href="/withdang/myDangguen">마이댕근</a></li>
                    <li><a href="/withdang/myCare">마이케어</a></li>
                </ul>
            </div>
        </div>
    </aside>
    <form class="mypage-chat">
        <div class="chat-list">

    </form>

</article>



</body>

</html>