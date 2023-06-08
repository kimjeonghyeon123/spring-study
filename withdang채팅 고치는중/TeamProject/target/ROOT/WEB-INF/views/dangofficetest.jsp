<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="loginout" value="${sessionScope.member==null ? 'Login' : 'Logout' }" />
<c:set var="loginoutlink" value="${sessionScope.member==null ? '/login' : '/logout' }" />
<c:set var="imgurl" value="${pt_address}"/>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dangoffice.css">
    <script src="https://kit.fontawesome.com/cac1ec65f4.js" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/resources/script/toggle.js" defer></script>
    <script src="${pageContext.request.contextPath}/resources/script/html2canvas.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/script/dangofficetest.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css2?family=Gaegu&family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
    <script>
    </script>
    <title>댕사무소</title>
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


<br><br>
<form action="" id="idcard-form">
    <div class="idcard">
        <div class="idcard-header">
            <div>
                <input type="text" id="견종" placeholder="견종" required autofocus>
                <span id="견종값"></span>
            </div>
            <div>
                댕댕등록증
            </div>
        </div>
        <div class="idcard-body">
            <div id="imagediv">

                <input id="imageinput" type="file" accept=".jpg, .jpeg, .png"


                       hidden="hidden"
                       required>
                <img id="preview"
<%--                     이미지 불러오지 못했을 경우 보여줄 대체이미지도 필요할듯--%>
<%--                <c:if test="${imgurl!=null}">--%>
                     src="${pt_address}"

<%--                   </c:if>--%>
                     style="width: 220px;height: auto"/>


            </div>
            <div>
                <input type="text" id="등록번호" placeholder="등록번호" required><br>
                <span id="등록번호값"></span>

                <label for="이름" class="attiribute">이름</label>
                <input type="text" id="이름" placeholder="이름" required><br>
                <span id="이름값"></span>

                <label for="생일" class="attiribute">생일</label>
                <input type="date" id="생일" placeholder="생일" required><br>
                <span id="생일값"></span>

                <span class="attiribute">성별</span>
                <span id="성별">
                        <input type="radio" name="성별" id="수컷" checked>
                        <label for="수컷">수컷</label>
                        <input type="radio" name="성별" id="암컷">
                        <label for="암컷">암컷</label><br>
                    </span>
                <span id="암컷값"></span>
                <span id="수컷값"></span>

                <span class="attiribute">중성화</span>
                <span id="중성화여부">
                        <input type="radio" name="중성화" id="중성화함" checked>
                        <label for="중성화함">O</label>
                        <input type="radio" name="중성화" id="중성화안함">
                        <label for="중성화안함">X</label><br>
                    </span>
                <span id="중성화함값"></span>
                <span id="중성화안함값"></span>

                <label for="주소" class="attiribute">주소</label>
                <input type="text" id="주소" placeholder="주소" required><br>
                <span id="주소값"></span>

                <label for="특징" class="attiribute">특징</label><br>
                <textarea name="hi" id="특징" cols="30" rows="10" minlength="10" placeholder="10자 이상 입력"
                          required></textarea>
                <span id="특징값"></span>
            </div>
        </div>
        <div class="idcard-footer">
            <div>(pet in card)</div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/resources/script/dangoffice_print.js" type="module"></script>
    <br>
    <div class="print-sec">
        <button type="submit" id="출력">출 력 하 기</button>
    </div>
</form>





</body>

</html>