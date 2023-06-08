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
    <style type="text/css">
    #find-form {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 60vh;
    margin: 0;
    
    }
    
    #find-id-result {
    justify-content: center;
    align-items: center;
    width: 500px;
    height: 180px;
    padding: 20px;
    color: black;
    text-align: center;
    background-color: #9d8db3;
	border: none;
	border-radius: 10px;  
	color: #fff;
	
}
#find-id-result p {
	font-size:20px;
	font-weight: bold;
}
#find-id-result a {
	font-size:14px;
	font-weight: bold;
	color: #fff;
}

.find-footer {
	color: black;
}

    </style>
    <title>이메일 찾기</title>
</head>

<body>
    <jsp:include page="header.jsp"></jsp:include>
    
<div id="find-form">
<div id="find-id-result">
		<!-- 닉네임이 일치하지 않을 때-->
		<c:if  test="${check == 1}">
		<i class="fa-solid fa-circle-exclamation" style="color: #fffff; font-size: 50px;"></i>

		<br>
            <p>입력하신 정보가 없거나 일치하지 않습니다.</p><br><br>
            <div><a class="find-footer" href="/withdang/login">로그인으로 돌아가기</a> |
            <a class="find-footer" href="/withdang/emailFind">다시 찾기</a></div>
        </c:if>


		<!-- 이름과 비밀번호가 일치 -->
		<c:if test="${check == 0 }">
		<i class="fa-solid fa-circle-check" style="color: #fffff; font-size: 50px;"></i>
		
		<br>
            <p>찾으시는 이메일은' ${email}' 입니다.</p><br><br>
            <div><a class="find-footer" href="/withdang/login">로그인으로 돌아가기</a> |
            <a class="find-footer" href="/withdang/pwdFind">비밀번호 찾기</a></div>
        </c:if>
</div>
</div>

</body>
</html>