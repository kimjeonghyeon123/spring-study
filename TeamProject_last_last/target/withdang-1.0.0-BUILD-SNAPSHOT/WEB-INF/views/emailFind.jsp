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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
    <script src="https://kit.fontawesome.com/cac1ec65f4.js" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/resources/script/toggle.js" defer></script>
    <script src="${pageContext.request.contextPath}/resources/script/html2canvas.js"></script>
    <script src="${pageContext.request.contextPath}/resources/script/dangoffice.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css2?family=Gaegu&family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous">
    </script>
    <title>이메일 찾기</title>
</head>

<body>
    <jsp:include page="header.jsp"></jsp:include>

<form method="post" class="form-signin" id="Chk-form" name="findform">
	<div id="form-box">
    <div id="forget-input">
    	<h2>이메일 찾기</h2>
        <input type="text" id="text-box" name="user_name" class="input_name" placeholder="이름을 입력해주세요."/><br>
        <input type="text" id="text-box" name="user_nickname" class="input_nickname" placeholder="닉네임을 입력해주세요."/><br>
        <span class="info_ck">정보를 입력해 주세요</span>
        <input id="find-btn" type="button" value="이메일 찾기">
    </div>
    <div id="find-set">
        <a id="find-pwd" href="/withdang/pwdFind">비밀번호 찾기</a>|
        <a id="joinus" href="login.html">회원가입</a>|
        <a id="login" href="login.html">로그인</a>
    </div>
    </div>
    
</form>


<script type="text/javascript">

    var infoCheck = false;

    $(document).ready(function() {
        $("#find-btn").click(function(){
            var nickname = $('.input_nickname').val();          // 닉네임 입력란
            var name = $('.input_name').val();               // 이름 입력란

            if(nickname == "" || name == "") {
                $('.info_ck').css('display','block');
                infoCheck = false;
            }  else {
                $('.info_ck').css('display','none');
                infoCheck = true;
            }

            if(infoCheck) {

                $("#Chk-form").attr("action", "/withdang/emailFindRes");
                $("#Chk-form").submit();
            }

            return false;

        });
    });
</script>






</body>

</html>