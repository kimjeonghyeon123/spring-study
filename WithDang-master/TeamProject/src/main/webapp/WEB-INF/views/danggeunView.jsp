<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="loginEmail" value="${sessionScope.email}" />
<c:set var="loginNickname" value="${sessionScope.nickname}" />
<c:set var="loginout" value="${sessionScope.email==null ? 'Login' : 'Logout'}" />
<c:set var="loginoutlink" value="${sessionScope.email==null ? '/login/login' : '/login/logout'}" />


<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="<c:url value='/resources/css/danggeun.css' />">
	<script rel="stylesheet" href="<c:url value='/resources/script/dangguenView.js' />"></script>
	<script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <script src="https://kit.fontawesome.com/cac1ec65f4.js" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css2?family=Gaegu&family=Nanum+Gothic:wght@400;700;800&display=swap"
        rel="stylesheet">
	<style type="text/css">
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
    display: scroll;
    position: fixed;
    bottom: 10%;
    right: 5%;
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
        display: scroll;
        position: fixed;
        bottom: 5%;
        right: 2%;
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

/* 내비바*/


.dangguen-sec {
    width: 90% auto;
    background-color: #d7e7e491;
}

.dangguen-writing {
    border-radius: 10px;
    background-color: #ed8b9e;
    border: none;
    width: 150px;
    height: 35px;
    font-weight: 900;
    color: #fff;
    margin: 10px;
}


.form-select {
    width: 150px;
    border: none;
    padding: 0;
    margin: 10px;
    height: 35px;
    border-radius: 10px;
    font-weight: 600;
    font-size: 0.8rem;

}

#dangguen-img {
    margin: 1% 4%;

  }

.board_wrap {
    width: 800px;
    margin: 30px auto;
    color: rgb(32, 32, 32);
    background-color: #ffffff36;
    padding: 1%;
    font-weight: 400;

}

.board_title {
    margin-left: 15px;
    text-align: left;

}

.board_title strong {
    font-size: 3rem;
    font-family: 'Gaegu', cursive !important;

}

.board_title p {
    margin-top: 5px;
    font-size: 1.0rem;
}

.board_view {
    width: 100%;
    text-align: center;
}

.board_view .title {
    padding: 20px 15px;
    font-size: 1.8rem;
}

.board_view .info {
    padding: 15px;
    border-bottom: 1px solid #503137;
    font-size: 0;
}

.board_view .info dl {
    position: relative;
    display: inline-block;
    padding: 0 20px;
}

.board_view .info dl:first-child {
    padding-left: 0;
}

.board_view .info dl::before {
    content: "";
    position: absolute;
    top: 1px;
    left: 0;
    display: block;
    width: 1px;
    height: 13px;
}

.board_view .info dl:first-child::before {
    display: none;
}

.board_view .info dl dt,
.board_view .info dl dd {
    display: inline-block;
    font-size: 1.1rem;
}

/*게시글 뷰 번호 이름 작성일 등 정보*/
.board_view .info dl dd {
    margin-left: 10px;
    color: #777;
    font-weight: 600;
}

.board_view .cont {
    padding: 15px;
    border-bottom: 1px solid #222222;
    line-height: 160%;
    font-size: 1.2rem;
}

.board_write {
    border-top: 2px solid #ed8b9e;
}

.board_write .title,
.board_write .info {
    padding: 15px;
}

.board_write .info {
    /* border-bottom: 1px solid #000; */
    font-size: 0;
}

.board_write .title dl {
    font-size: 0;
}

.board_write .info dl {
    display: inline-block;
    width: 50%;
    vertical-align: middle;
}

.board_write .title dt,
.board_write .title dd,
.board_write .info dt,
.board_write .info dd {
    display: inline-block;
    font-size: 1rem;
    font-weight: 600;
    color: #3e3e3e;
}

.board_write .title dt,
.board_write .info dt {
    width: 100px;
}

.board_write .title dd {
    width: calc(100% - 100px);
}

.board_write .title input[type="text"],
.board_write .info input[type="text"],
.board_write .info input[type="password"] {
    padding: 10px;
    box-sizing: border-box;
    border: none;
    background-color: #e7ffff;

}

.board_write .title input[type="text"] {
    width: 80%;
    border: none;
    background-color: #e7ffff;
}

.board_write .cont {
    border: none;
}

.board_write .cont textarea {
    display: block;
    width: 100%;
    height: 300px;
    padding: 15px;
    box-sizing: border-box;
    border: 0;
    resize: vertical;
    background-color: #e7ffff;
}

@media (max-width: 1000px) {
    .board_wrap {
        width: 100%;
        min-width: 320px;
        padding: 0 30px;
        box-sizing: border-box;
        padding: 20px;
    }

    .board_view .info dl {
        width: 50%;
        padding: 0;
    }

    .board_view .info dl:nth-child(-n+2) {
        margin-bottom: 5px;
    }

    .board_view .info dl::before {
        display: none;
    }

    .board_view .info dl dt,
    .board_view .info dl dd {
        font-size: 1.0rem;
        padding: 2px;
    }

    .board_write .info dl {
        width: 49%;
    }

    .board_write .info dl:first-child {
        margin-right: 2%;
    }

    .board_write .title dt,
    .board_write .info dt {
        display: none;
    }


    .board_write .title input[type="text"],
    .board_write .info input[type="text"],
    .board_write .info input[type="password"] {
        width: 100%;
    }
}



input[type="submit"] {
    background-color: #ed8b9e;
    color: #fff;
    padding: 10px;
    border: none;
    border-radius: 5px;
    width: 130px;
    cursor: pointer;
    font-size: 16px;
    transition: background-color 0.3s ease;
}

input[type="submit"]:hover {
    background-color: #e26880;
}

@media screen and (min-width: 768px) {
    form {
        display: grid;
        grid-template-columns: 1fr 1fr;
        grid-gap: 20px;
    }

    label {
        display: none;
    }

    input[type="text"],
    input[type="email"],
    textarea {
        margin: 0;
    }


}

.mySlides {
    display: none;
    height: 500px;
    width: 100%;
    padding-top: 20px;
    display: flex;
    justify-content: center;
}

.mySlides img {
    max-height: 100%;
    max-width: 100%;
}

.slideshow-container {
    width: 100% auto;
    height: 500px;
    background-color: #697070;
    text-align: center;

}

.mySlides:first-child {
    display: block;
    /* 첫 번째 슬라이드 이미지만 보이게 함 */
}


/* Next & previous buttons */
.prev,
.next {
    cursor: pointer;
    position: absolute;
    top: 50%;
    width: auto;
    padding: 16px;
    margin-top: -22px;
    margin-right: 5%;
    margin-left: -45%;
    color: #fff;
    font-weight: bold;
    font-size: 28px;
    transition: 0.6s ease;
    border-radius: 0 3px 3px 0;
}

/* Position the "next button" to the right */
.next {
    right: 0;
    border-radius: 3px 0 0 3px;
}

/* On hover, add a black background color with a little bit see-through */


/* The dots/bullets/indicators */
.dot {
    cursor: pointer;
    height: 15px;
    width: 15px;
    margin: 0 2px;
    background-color: #e6e6e6;
    border-radius: 50%;
    display: inline-block;
    transition: background-color 0.6s ease;
}

.active,
.dot:hover {
    background-color: #b3b3b3;
}

.slideshow-container {
    height: 100%;
    width: 100%;
    position: relative;

}

.slideshow-container .slides>div {
    position: absolute;
    margin: 0;
    width: 100%;
    height: 100%;
    background-position: center;
    /* 이미지를 최대한 안짤리게 한다. */
    background-size: cover;
    /* 배경 반복 금지 */
    background-repeat: no-repeat;
    opacity: 0;
    transition: opacity 0.5s;
}

@media (max-width:786px) {
    .slideshow-container {
        width: 100%;
    }
}






.bt_wrap {
    margin-top: 30px;
    text-align: center;
}

.bt_wrap a {
    display: inline-block;
    min-width: 80px;
    margin-left: 10px;
    padding: 10px;
    border: 2px solid #ed8b9e;
    border-radius: 20px;
    font-size: 1rem;
    font-weight: 600;
    color: #ed8b9e;
    cursor: pointer;
}

#chat-btn {
    background-color: #ed8b9e;
    color: #fff;
}
	</style>

    <title>댕근마켓</title>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
    
    <form class="board_wrap" id="form" style="display: flex; flex-direction: column;">
        <div class="board_title">
            <strong>${danggeunInfoDTO.title}</strong>

        </div>
        
		<div class="slideshow-container">
		    <c:forEach var="photo" items="${photoList}">
		        <div class="mySlides fade">
		            <img class="img" src="${photo.address}">
		        </div>
		    </c:forEach>
		</div>

        <div class="board_view_wrap">
            <div class="board_view">
                <div class="info">
                    <dl>
                        <dt>글쓴이</dt>
                        <dd>${danggeunInfoDTO.writer_nickname}</dd>
                    </dl>
                    <dl>
                        <dt>작성일</dt>
                        <dd><fmt:formatDate value="${danggeunInfoDTO.reg_date}" pattern="yyyy-MM-dd" type="date"/></dd>
                    </dl>
                    <dl>
                        <dt>위치</dt>
                        <dd>${danggeunInfoDTO.sido_name} ${danggeunInfoDTO.sigoon_name} ${danggeunInfoDTO.dong_name}</dd>
                    </dl>
                    <dl>
                        <dt>조회</dt>
                        <dd>${danggeunInfoDTO.view_cnt}</dd>
                    </dl>
                    <dl>
                        <dt>찜</dt>
                        <dd>${danggeunInfoDTO.zzim_cnt}</dd>
                    </dl>
                </div>
                <br>
                <br>
                <br>
                <br>


                <div class="cont">${danggeunInfoDTO.content}</div>
            </div>

            <div class="bt_wrap">
            	<c:if test="${danggeunInfoDTO.writer_nickname == loginNickname}">
                	<a id="modify-btn" href="<c:url value='/danggeun/modify?id=${danggeunInfoDTO.id}' />" class="on">수정</a>
                	<a id="delete-btn" class="on">삭제</a>
                </c:if>
                <c:if test="${danggeunInfoDTO.writer_nickname != loginNickname}">
                	<a id="chat-btn" href="<c:url value='/chat/chatroom?other_nickname=${danggeunInfoDTO.writer_nickname}' />" class="on">채팅</a>
                </c:if>
                <a id="list-btn" href="<c:url value='/danggeun/list${danggeunSearchItem.queryString}' />">목록</a>
                <c:if test="${danggeunInfoDTO.writer_nickname != loginNickname}">
			        <c:if test="${danggeunInfoDTO.zzimed == true}">
			        	<a class="scrap-btn" data-isScrap="1" data-id="${danggeunInfoDTO.id}" style="background-color: red;">찜♥</a>
			        </c:if>
			        <c:if test="${danggeunInfoDTO.zzimed == false}">
			        	<a class="scrap-btn" data-isScrap="0" data-id="${danggeunInfoDTO.id}" sytle="background-color: #a1e1dc;">찜♥</a>
			        </c:if>
		        </c:if>
            </div>

        </div>
	</form>
	
<script type="text/javascript">
	let id = '${danggeunInfoDTO.id}'
	let loginNickname = '${loginNickname}'
	
    $(document).ready(function(){
    	$("#delete-btn").on('click', function(){
    		if (!confirm("정말로 삭제하시겠습니까?")) return;
			
			let form = $("#form")
			form.attr("action", "/withdang/danggeun/delete${danggeunSearchItem.queryString}&id="+id)
			form.attr("method", "post")
			form.submit()
    	})
    	
    	
        $(".scrap-btn").on('click', function() {
            let button = $(this); // 버튼 객체를 저장
            let writerNickname = $(this).attr("data-writerNickname")
            let isScrap = $(this).attr("data-isScrap")
            
            if(loginNickname === writerNickname) {
            	return alert("본인 상품을 찜할 수 없습니다.")
            }
            
            if(isScrap === "1"){
	            $.ajax({
	                type: 'POST',
	                url: '/withdang/danggeun/cancelzzim',
	                data: {
	                    'danggeun_id': id,
	                    'user_nickname': loginNickname
	                },
	                success: function(response) {
	                	button.attr("data-isScrap", "0")
	                	button.css('background-color', '#ed8b9e')
	                },
	                error: function(error) {
	                    alert("error")
	                }
	            })            	
            }
            else {
	            $.ajax({
	                type: 'POST',
	                url: '/withdang/danggeun/pushzzim',
	                data: {
	                    'danggeun_id': id,
	                    'user_nickname': loginNickname
	                },
	                success: function(response) {
	                	button.attr("data-isScrap", "1")
	                	button.css('background-color', 'red')
	                },
	                error: function(error) {
	                    alert("error")
	                }
	            })
            }
        })
    });
</script>
</body>

</html>