<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <script src="https://kit.fontawesome.com/cac1ec65f4.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src='${pageContext.request.contextPath}/resources/script/dangguenwrite.js' defer></script>
    <link href="https://fonts.googleapis.com/css2?family=Gaegu&family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
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
    <script>
    	let msg = "${msg}"
    	if(msg === "WRT_ERR") alert("작성 실패했습니다.")
    	if(msg === "MOD_ERR") alert("수정 실패했습니다.")
    </script>
    
	<jsp:include page="header.jsp"></jsp:include>
	
    <div class="board_wrap">
        <div class="board_title">
            <strong>댕근마켓</strong>
        </div>
        <form class="board_write_wrap" id="form" action="" method='post' enctype="multipart/form-data" style="display: flex; flex-direction: column;">
            <div class="board_write">
                <div class="title">
                    <dl>
                        <dd><input type="text" name="title" value="${danggeunInfoDTO.title}" placeholder="제목 입력"></dd>
                        <input type="hidden" name="id" value="${danggeunInfoDTO.id}">
                    </dl>
                </div>
                <section class="dangguen-sec">
                    <div class="info">
                        <dl>
                            <dd><input type="number" name="price" value="${danggeunInfoDTO.price}" placeholder="판매 가격"></dd>
                            <input type="text" name="writer_nickname" value="${loginNickname}" style="display: none;">
                        </dl>
		                
			           	<select class="form-select" aria-label="category" name="type_id">
			                    <c:forEach var="DanggeunTypeDTO" items="${typeList}">
			                    	<option value="${DanggeunTypeDTO.id}"  ${danggeunInfoDTO.type_id == DanggeunTypeDTO.id ? "selected" : ""}>${DanggeunTypeDTO.name}</option>
			                    </c:forEach>
			            </select>
			            <select class="form-select" aria-label="category" id="sido_code" name="sido_code">
							<option value="0">전체</option>
						</select>
						<select class="form-select" aria-label="category" id="sigoon_code" name="sigoon_code">
							<option value="0">전체</option>
						</select>
						<select class="form-select" aria-label="category" id="dong_code" name="dong_code">
							<option value="0">전체</option>
						</select>
                        
                        <div class="img-sec">
                            <input id="imageinput0" name="image1" type="file" accept=".jpg, .jpeg, .png" onchange="previewImage(this, 0)">
                            <input id="imageinput1" name="image2" type="file" accept=".jpg, .jpeg, .png" onchange="previewImage(this, 1)">
                            <input id="imageinput2" name="image3" type="file" accept=".jpg, .jpeg, .png" onchange="previewImage(this, 2)">
                            <input id="imageinput3" name="image4" type="file" accept=".jpg, .jpeg, .png" onchange="previewImage(this, 3)">
                            <div id="preview-list"></div>
		                </div>
		                
                        <img id="preview" />

                    </div>
                    <div class="cont">
                        <textarea name="content" placeholder="내용 입력" required>${danggeunInfoDTO.content}</textarea>
                    </div>
                </section>
            </div>
            <div class="bt_wrap">
            	<c:if test="${mode eq 'new'}">
                	<button class="submit" id="writeBtn" type="button">등록</button>
                </c:if>
                <c:if test="${mode eq 'modify'}">
                	<button class="submit" id="modifyBtn" type="button">수정</button>
                </c:if>
            </div>
        </form>
    </div>
    
<script type="text/javascript">    
   	let sido_code = '${danggeunInfoDTO.sido_code}'
    let sigoon_code = '${danggeunInfoDTO.sigoon_code}'
    let dong_code = '${danggeunInfoDTO.dong_code}'
    
	let formCheck = function(frm) {
	    if(frm.find('input[name="title"]').val().trim() === "") {
	        alert('제목을 입력해주세요.');
	        frm.find('input[name="title"]').focus()
	        return false;	    	
	    }		
	    if(frm.find('input[name="price"]').val() === "") {
	        alert('판매 가격을 입력해주세요.');
	        frm.find('input[name="price"]').focus()
	        return false;	    	
	    }
	    if (frm.find('input[name="price"]').val() <= 0) {
	        alert('판매 가격은 0보다 커야 합니다.');
	        frm.find('input[name="price"]').focus()
	        return false;
	    }		
	    if (frm.find('select[name="type_id"]').val() === "0") {
	        alert('타입을 선택해주세요.');
	        return false;
	    }
	    if (frm.find('select[name="dong_code"]').val() === "0") {
	        alert('지역을 선택해주세요.');
	        return false;
	    }
	    if (frm.find('input[name="image1"]').get(0).files.length === 0) {
	        alert('사진을 선택해주세요.');
	        return false;
	    }
	    if (frm.find('textarea[name="content"]').val().trim() === "") {
	        alert('내용을 입력해주세요.');
	        frm.find('textarea[name="content"]').focus()
	        return false;
	    }
	    return true;
	}
	
	$(document).ready(function(){        
        $("#writeBtn").on('click', function(){
        	let form = $('#form')
			form.attr("action", "/withdang/danggeun/write")
			form.attr("method", "post")
		 
			if(formCheck(form)) {
				form.submit()
			}       	
        })
        
        $("#modifyBtn").on('click', function(){
        	let form = $('#form')
			form.attr("action", "/withdang/danggeun/modify")
			form.attr("method", "post")
			if(formCheck(form)) {
				form.submit()
			}       	
        })
        
    	$.ajax({
        	type: 'get',
    		url: '/withdang/danggeun/sido',
    		async: false,
    		headers: { "content-type" : "application/json" },  //요청헤더
    		success: function(result) {
    			let html = "<option value='0'>전체</option>";
    			
    			result.forEach(function(c){
    				let code = c.code 
    				let name = c.name
    			    if (code === sido_code) {
    			        html += '<option value="' + code + '" selected>' + name + '</option>';
    			    } 
    			    else {
    			        html += '<option value="' + code + '">' + name + '</option>';
    			    }
    			})

    			$('#sido_code').html(html);
            },
            error: function() {alert('error')}
        })
        
        $('#sido_code').on('change', function(){
        	let thisVal = $(this).val()
        	if(thisVal === '0') {
        		$('#sigoon_code').html("<option value='0'>전체</option>");
        		$('#dong_code').html("<option value='0'>전체</option>");
        		return
        	}
        	
    		$('#dong_code').html("<option value='0'>전체</option>");
        	
    		$.ajax({
	        	type: 'get',
	    		url: '/withdang/danggeun/sigoon?ctprvn_cd=' + thisVal,
	    		async: false,
	    		headers: { "content-type" : "application/json" },  //요청헤더
	    		success: function(result) {
	    			let html = "<option value='0'>전체</option>";
	    			
	    			result.forEach(function(c){
	    				let code = c.code 
	    				let name = c.name
	    			    if (code === sigoon_code) {
	    			        html += '<option value="' + code + '" selected>' + name + '</option>';
	    			    } 
	    			    else {
	    			        html += '<option value="' + code + '">' + name + '</option>';
	    			    }
	    			})
	
	    			$('#sigoon_code').html(html);
	            },
	            error: function() {alert('error')}
        	})
        })
        
        $('#sigoon_code').on('change', function(){
        	let thisVal = $(this).val()
        	if(thisVal === '0') {
        		$('#dong_code').html("<option value='0'>전체</option>");
        		return
        	}
        	$.ajax({
	        	type: 'get',
	    		url: '/withdang/danggeun/dong?sig_cd=' + thisVal,
	    		async: false,
	    		headers: { "content-type" : "application/json" },  //요청헤더
	    		success: function(result) {
	    			let html = "<option value='0'>전체</option>";
	    			
	    			result.forEach(function(c){
	    				let code = c.code 
	    				let name = c.name
	    			    if (code === dong_code) {
	    			        html += '<option value="' + code + '" selected>' + name + '</option>';
	    			    } 
	    			    else {
	    			        html += '<option value="' + code + '">' + name + '</option>';
	    			    }
	    			})
	
	    			$('#dong_code').html(html);
	            },
	            error: function() {alert('error')}
        	})
        })
           
   		if (sido_code !== '') {
		    $('#sido_code').val(sido_code);
		    $('#sido_code').trigger('change');
		    $('#sigoon_code').val(sigoon_code);
			$('#sigoon_code').trigger('change');
		}
	})
</script>    
</body>

</html>