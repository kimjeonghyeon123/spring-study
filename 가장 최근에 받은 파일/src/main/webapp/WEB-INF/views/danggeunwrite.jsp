<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="loginEmail" value="${sessionScope.email}" />
<c:set var="loginout" value="${sessionScope.email==null ? 'Login' : 'Logout'}" />
<c:set var="loginoutlink" value="${sessionScope.email==null ? '/login/login' : '/login/logout'}" />

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="<c:url value='/resources/css/danggeun.css' />">
    <script src="https://kit.fontawesome.com/cac1ec65f4.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Gaegu&family=Nanum+Gothic:wght@400;700;800&display=swap"
        rel="stylesheet">
    <style type="text/css">
    @charset "UTF-8";


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
}

#chat-btn {
    background-color: #ed8b9e;
    color: #fff;
}
.submit, .list-btn {
	width: 80px;
    height: 25px;
    border: none;
    background-color: #8e8bac;
    border-radius: 5px;
    color: #fff;
	
	
}
	
    </style>

    <title>댕근마켓</title>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	
<!-- 예전 글쓰기!! -->
<!--     <div class="board_wrap"> -->
<!--         <div class="board_title"> -->
<!--             <h2 class="dangguen-title">댕근마켓</h2> -->
<!--         </div> -->
<!--         <div class="board_write_wrap"> -->
<%--             <form action="<c:url value='/danggeun/write' />" id="form" class="board_write" method="post"> --%>
<!--                 <div class="title"> -->
<!--                     <dl> -->
<%--                         <dd><input type="text" name="title" value="${danggeunDTO.title}" placeholder="제목 입력"></dd> --%>
<!--                     </dl> -->
<!--                 </div> -->
<!--                 <section class="dangguen-sec"> -->
<!--                     <div class="info"> -->
<!--                     	<dl> -->
<%--                     		<dd><input type="text" name="name" value="${loginEmail}" hidden></dd> --%>
<!--                     	</dl> -->
<!--                         <dl> -->
<%--                             <dd> <input type="text" id="price" name="price" value="${danggeunDTO.price}" placeholder="판매 가격" onkeyup="inputNumberFormat(this)" /> --%>
<!--                                 원</dd> -->
<!--                         </dl> -->
<!--                         <div id="dangguen-img"> -->

<!-- 				            <select class="form-select" aria-label="category" name="type_id"> -->
<%-- 				                    <c:forEach var="DanggeunTypeDTO" items="${typeList}"> --%>
<%-- 				                    	<option value="${DanggeunTypeDTO.id}" ${danggeunDTO.type_id == DanggeunTypeDTO.id || danggeunDTO.type_id eq null ? "selected" : ""}>${DanggeunTypeDTO.name}</option> --%>
<%-- 				                    </c:forEach> --%>
<!-- 				            </select> -->
<!-- 				            <input id="adressinput" type="text" placeholder="주소: 서울시 용산구 보광동"> -->
				            
<!--                             <input id="imageinput" type="file" multiple="multiple" accept=".jpg, .jpeg, .png" -->
<!--                                 onchange="previewImage(this)" required> -->
<!--                             <img id="preview" /> -->

<!--                         </div> -->

<!--                     </div> -->
<!--                     <div class="cont"> -->
<%--                         <textarea name="content" placeholder="내용 입력">${danggeunDTO.content}</textarea> --%>
<!--                     </div> -->
<!--             	</section> -->
            	
                
<!--             	<div class="bt_wrap"> -->
<!--                 	<button type="submit" class="write-btn" class="on">등록</button> -->
<%--                 	<button class="list-btn"><a href="<c:url value='/danggeun/list?type_id=${type_id}' />">목록</a></button> --%>
<!--             	</div> -->
<!--         	</form> -->
<!--     	</div> -->
<!--     </div> -->

<!-- 새로운 글쓰기 창  -->

    <div class="board_wrap">
        <div class="board_title">
            <strong>댕근마켓</strong>
        </div>
        <div class="board_write_wrap">
            <div class="board_write">
                <div class="title">
                    <dl>
                        <dd><input type="text" name="title" value="${danggeunDTO.title}" placeholder="제목 입력"></dd>
                    </dl>
                </div>
                <section class="dangguen-sec">
                    <div class="info">
                        <dl>
                            <dd><input type="text" placeholder="판매 가격"></dd>
                        </dl>
                        <div id="dangguen-img">
				            <select class="form-select" aria-label="category" name="type_id">
				                    <c:forEach var="DanggeunTypeDTO" items="${typeList}">
				                    	<option value="${DanggeunTypeDTO.id}" ${danggeunDTO.type_id == DanggeunTypeDTO.id || danggeunDTO.type_id eq null ? "selected" : ""}>${DanggeunTypeDTO.name}</option>
				                    </c:forEach>
				            </select>
                            <input id="adressinput" type="text" placeholder="주소: 서울시 용산구 보광동">
                
                            <input id="imageinput" type="file" multiple="multiple" accept=".jpg, .jpeg, .png" onchange="previewImage(this)" required>
                            <img id="preview" />

                        </div>

                    </div>
                    <div class="cont">
                        <textarea name="content" placeholder="내용 입력">${danggeunDTO.content}</textarea>
                    </div>
            </div>
            <div class="bt_wrap">
                <button href="view.html" class="submit">등록</button>
                <button href="dangcomu.html" class="list-btn">목록</button>
            </div>
        </div>
    </div>

</body>

</html>