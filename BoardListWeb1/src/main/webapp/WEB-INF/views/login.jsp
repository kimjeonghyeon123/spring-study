<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.net.URLDecoder" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value='/resources/css/login.css'/>">
    
    <title>로그인</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
</head>

<body>
    <div class="container">
        <div class="orangeBg">
            <div class="box signin">
                <h2>이미 계정이 있습니다.</h2>
                <button class="signinbtn">로그인</button>
            </div>
            <div class="box signup">
                <h2>간편 회원 가입</h2>
                <button class="signupbtn">회원가입</button>
            </div>
        </div>
        <div class="form-box">
            <div class="form signinform">
                <form action="<c:url value='/login/login'/>" method="post">
                    <h3>로그인</h3>
			        <div id="msg">
						<c:if test="${not empty param.msg }">
							<i class="fa fa-exclamation-circle">${URLDecoder.decode(param.msg)}</i>
						</c:if>
					</div>
                    <input type="text" name="email" placeholder="Email" value="${cookie.email.value}" autofocus>
                    <input type="password" name="pwd" placeholder="비밀번호">   
                    <label><input type="checkbox" name="rememberId" value="on" ${empty cookie.email.value ? "" : "checked"}>아이디 기억</label><br>
                    <input type="text" name="toURL" value="${param.toURL}" style="display:none;">
                    <input type="submit" value="로그인">
                    <a href="#">비밀번호를 잊으셨습니까?</a>
                </form>
            </div>
            <div class="form signupform">
                <form action="<c:url value='/register'/>" method="post">
                    <h3>회원가입</h3>
       	 			<input type="email" name="email" placeholder="Email">
                    <input type="password" name="pwd" placeholder="비밀번호">
                    <input type="text" name="name" placeholder="이름">
                    <input type="submit" value="가입하기">
                </form>
            </div>
        </div>
    </div>
    <div class="return-main"><a href="/">메인화면으로 돌아가기</a></div>
    <script>
        const signin = document.querySelector(".signinbtn");
        const signup = document.querySelector(".signupbtn");
        const formbox = document.querySelector(".form-box");
        const body = document.querySelector("body");
        signup.onclick = function(){
            formbox.classList.add("active")
            body.classList.add("active")
        }
        signin.onclick = function(){
            formbox.classList.remove("active")
            body.classList.remove("active")
        }
    </script>
    
    
</body>

</html>