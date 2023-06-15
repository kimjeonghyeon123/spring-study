<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="loginout" value="${member==null ? 'Login' : 'Logout' }" />
<c:set var="loginoutlink" value="${member==null ? '/login' : '/logout' }" />
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/care.css">
    <script src="https://kit.fontawesome.com/cac1ec65f4.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"
            integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE"
            crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/resources/script/toggle.js" defer></script>



    <title>댕댕케어</title>
</head>

<body>

    <jsp:include page="header.jsp"></jsp:include>


<section class="caremain">
    <p></p>
    <img src="${pageContext.request.contextPath}/resources/image/caremain.png" class="care-img" width="80%" height="auto" />
    <p></p>
    <div class="petbtn">
        <button type="button" id="petbtn" name="petsitter"><a href="petsitter.html">펫시터 지원합니다.</a></button>
        <button type="button" id="petbtn" name="pet"><a href="petsitter-offer.html">펫시터 구합니다.</a></button>
    </div>
    <div id="care-text" classW="typewriter-effect">
        <div class="text" id="typewriter-text"></div>
    </div>
    <p></p>
    <img src="${pageContext.request.contextPath}/resources/image/process.png" class="care-img" width="80%" height="auto"/>



</section>
<footer>
</footer>



</body>

</html>