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
    <link rel="stylesheet" href="${path}/resources/css/mypage.css">
    <script src="${path}/resources/script/toggle.js" defer></script>
    <script src="https://kit.fontawesome.com/cac1ec65f4.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"
        integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"
        integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ"
        crossorigin="anonymous"></script>
    <title>마이페이지</title>
</head>

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
                    <li><a href="#">내 정보</a></li>
                    <li><a href="/withdang/mypage_chat">채팅</a></li>
                    <li><a href="">마이댕근</a></li>
                    <li><a href="">마이케어</a></li>
                </ul>
            </div>
            </div>
        </aside>
        <form class="mypage-user">
            <h1 id="mypage-title">마이페이지</h1>
               
                   		<p class="user-info"> 내 정보</p>
	                    <div><span>이름</span> ${member.user_name}</div>
	                    <div><p>이메일 ${member.user_email }</p></div>
	                    <div><p>닉네임 ${member.user_nickname }</p></div>
	                    <div><p>연락처 ${member.user_pnum }</p></div>
	                    <div><p>생일 <fmt:formatDate value="${member.user_birth }" pattern="yyyy-MM-dd" type="date"/></p></div>
	                    <div><p>성별 ${member.user_gender }</p></div>
	                    <div><p>주소 ${member.user_address }</p></div>
	                
                    <a class="reset-pwd" href="">비밀번호 재설정</a>
                   </form>
            
         </article>
         
         <footer>
        <div class="mypage__update">
            <br>
            <a class="update_btn" href="/withdang/mypage_update">수정하기</a>
        </div>
    	</footer>
    	<br><br><br><br>
    	
    	<script type="text/javascript">
		let msg = "${msg}"
		if(msg=="memberCheck") alert("신규 회원님 환영 합니다. 마이페이지에서 필수 회원 정보를 입력해주세요")
	</script>
    	
	</body>

</html>