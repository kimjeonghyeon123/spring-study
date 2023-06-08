<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <link rel="stylesheet" href="resources/css/mypage.css">
    <script src="${path}/resources/script/toggle.js" defer></script>
    
    <script src="https://kit.fontawesome.com/cac1ec65f4.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"
        integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"
        integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ"
        crossorigin="anonymous"></script>
        
    <script
		 src="https://code.jquery.com/jquery-3.4.1.js"
		 integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
		 crossorigin="anonymous"></script>
	
    <title>마이페이지</title>
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
                    <li><a href="">강아지 정보</a></li>
                    <li><a href="/withdang/mypage_chat">채팅</a></li>
                    <li><a href="">판매 목록</a></li>
                </ul>
                </div>
            </div>
        </div>
        </aside>
        	<form id="update_form" class="mypage-user" method="post">
                <ul>
                    <p class="user-info"> 내 정보</p>
                    <div>이름 : <input name="user_name" value="${member.user_name }" readonly="readonly"></div>
                    <div>이메일 : <input name="user_email" value="${member.user_email }" readonly="readonly"></div>
                    <div><p>비밀번호 변경 : <input type="password" class="input_pw" name="user_pw" value="${member.user_pw }"></p></div>
                    <div><p>비밀번호 확인 : <input type="password" class="input_pwck" name="user_pw2"></p></div>
                    <!-- <span class="pwck_input_re_1">비밀번호가 일치합니다.</span> -->
                	<!-- <span class="pwck_input_re_2">비밀번호가 일치하지 않습니다.</span> -->
                    <div><p>닉네임 : <input type="text" name="user_nickname" value="${member.user_nickname }"></p></div>
                    <div><p>연락처 : <input type="text" name="user_pnum" value="${member.user_pnum }"></p></div>
                    <div><p>생일 : <input type="date" name="user_birth" value="<fmt:formatDate value="${member.user_birth }" pattern="yyyy-MM-dd"/>" /></p></div> 
                    <div>성별:
                     <select class="form-select" name="user_gender">
                    	<option value="" selected disabled hidden>성별</option>
                    	<option value="남자" ${member.user_gender=='남자' ? "selected" : "" }>남자</option>
                    	<option value="여자" ${member.user_gender=='여자' ? "selected" : "" }>여자</option>
                    </select>
                   	</div>
                    <div><p>주소 : <input type="text" name="user_address" value="${member.user_address }"></p></div>
                        
                </ul>
            <hr />
        	<!-- <form id="dogUpdate_form" method="post"> -->
            <div class="mypage-dog">
                <ul>
                    <p class="dog-info">강아지 정보</p>
                    <p>이름 : <input type="text" name="dog_name" value="${dvo.dog_name }"></p>
                    <p>생일 : <input type="date" name="dog_birth" value="<fmt:formatDate value="${dvo.dog_birth }" pattern="yyyy-MM-dd"/>" /></p>
                    <p>성별 : 
                    <select id="gender-check" class="form-select" name="dog_gender">
                    	<option value="" selected disabled hidden>성별</option>
                    	<option value="수컷" ${dvo.dog_gender=='수컷' ? "selected" : "" }>수컷</option>
                    	<option value="암컷" ${dvo.dog_gender=='암컷' ? "selected" : "" }>암컷</option>
                    </select>
                    </p>
                    <p>중성화 : 
                    <select class="form-select" name="dog_nutd">
                    	<option value="" selected disabled hidden>중성화</option>
                    	<option value="했음" ${dvo.dog_nutd=='했음' ? "selected" : "" }>완료</option>
                    	<option value="안했음" ${dvo.dog_nutd=='안했음' ? "selected" : "" }>안함</option>
                    </select>
                    </p>
                    <p>견종 : <input type="text" name="dog_breed" value="${dvo.dog_breed }"></p>
                    <p>동물등록번호 : <input type="text" name="dog_regnum" value="${dvo.dog_regnum }"></p>
                    <p>주소 : <input type="text" name="dog_address" value="${dvo.dog_address }"></p>
                    <p>특징 : </P><textarea name="dog_feature">${dvo.dog_feature }</textarea> <%-- <input type="text" name="dog_feature" value="${dvo.dog_feature }"></p>
                     --%>
                </ul>
            </div>
        	</form>
    </article>
    <footer>
        <div class="mypage__update">
            <br>
            <button type="button" class="update_btn" value="수정하기">수정하기</button>
        </div>
    </footer>
    <br><br><br><br>
    
    <script type="text/javascript">

    	 /* 회원 정보 수정 클릭 메서드 */
	    $(".update_btn").click(function(){
	    	/* 업데이트 메서드 서버 요청 */
	        $("#update_form").attr("action", "/withdang/mypage_update").submit();
	    });
    
    </script>
</body>

</html>