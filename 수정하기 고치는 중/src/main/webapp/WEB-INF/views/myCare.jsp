<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<c:set var="loginout" value="${member==null ? 'Login' : 'Logout' }" />
<c:set var="loginoutlink" value="${member==null ? '/login' : '/logout' }" />
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

    <br>
    <article id="mypage">
        <aside class="mypage_aside">
            <div class="mypage__profile">
                <div>
                    <img src="resources/image/profile.png" alt="프로필사진">
                </div>
                <p>${member.user_nickname }님 안녕하세요</p>
                <hr class="profile-line">
                <div class="mypage__list">
                    <ul>
                        <li><a href="/withdang/mypage">내 정보</a></li>
                        <li><a href="#">마이케어</a></li>
                    </ul>
                </div>
            </div>
        </aside>
        <form class="mypage-user">
            <h1 id="mypage-title">마이페이지</h1>
            
            <select id="care-select">
                <option value="댕댕케어 진행상황">댕댕케어 지원상황</option>
                <option value="댕댕케어 진행상황">댕댕케어 진행상황</option>
            </select>

            <div id="mypage-border">
            <p class="user-info"> - 펫시터 지원상황 -</p>
            <div class="step-box">
                <div class="step-state step1">
                  <ul id="inbox">
                    <li><p>접수</p><a class="care-check" href="">1</a></li>
                    <li><p>매칭중</p><a class="care-check" href="">1</a></li>
                    <li><p>매칭완료</p><a class="care-check" href="">1</a></li>
                    <li><p>진행중</p><a class="care-check" href="">1</a></li>
                    <li><p>진행완료</p><a class="care-check" href="">평가하기</a></li>
                  </ul>
                </div>
            </div>

                
            
            <div class="care-box">
                <br>
            </div>
        </div>
        <div id="care-button">
            <a class="care-btn" href="#">이용 내역</a>
            <a class="care-btn" href="#">지원 내역</a>
            </div>
        </form>


        

    </article>

</body>

</html>