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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dangguenView.css">
    <script src="https://kit.fontawesome.com/cac1ec65f4.js" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/resources/script/dangguenView.js"></script>
    <script src="${pageContext.request.contextPath}/resources/script/toggle.js" defer></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"
        integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"
        integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ"
        crossorigin="anonymous"></script>
    <style type="text/css">

.danggeun-sec {
    width: 90% auto;
    background-color: #d7e7e491;
}

.danggeun-writing {
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

#danggeun-img {
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
    font-size: 24px;
}

.board_view .info {
    padding: 10px;
    border-bottom: 1px solid #503137;
    font-size: 0;
}

.board_view .info dl {
    position: relative;
    display: inline-block;
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
    font-size: 16px;
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



    .board_view .info dl::before {
        display: none;
    }

    .board_view .info dl dt,
    .board_view .info dl dd {
        font-size: 14px;
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
    
    .board_view .info {
    	font-size: 12px;
    }


}



.mySlides {
    display: none;
    height: 500px;
    width: 100%;
    padding-top: 20px;

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

.danggeun_title {
	text-align: center;
	font-size: 24px;
}

    
    </style>

    <title>댕근마켓</title>
</head>

<body>
    <jsp:include page="header.jsp"></jsp:include>



    <form class="board_wrap" id="form" style="display: flex; flex-direction: column;">
        <div class="board_title">
            <strong>댕근마켓</strong>
        </div>
        
        <div class="slideshow-container">

            <div class="mySlides fade">
                <img src="image/양말1.PNG" >
            </div>

            <div class="mySlides fade">
                <img src="image/양말2.PNG" >
            </div>

            <div class="mySlides fade">
                <img src="image/양말3.PNG" >
            </div>

        </div>




        <div class="board_view_wrap">
            <div class="board_view">
	        <div class="title">
	            <strong>${danggeunInfoDTO.title}</strong>
	
	        </div>
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
                
                <div class="cont">
                    미개봉 새제품 입니다!<br>
                    6시 이후 강남역 직거래 원합니다.<br>
                    메세지주세요!<br><br>
                    
                </div>
            </div>

            <div class="bt_wrap">
                <a href="chat.html" class="on">채팅</a>
                <a href="dangguen.html">목록</a>
            </div>

        </div>
</body>

</html>