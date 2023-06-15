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
	<link rel="stylesheet" href="<c:url value='/resources/css/dangguen.css' />">
    <script src="https://kit.fontawesome.com/cac1ec65f4.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Gaegu&family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <style type="text/css">
    @charset "UTF-8";


	.dangguen-sec {
    width: 90% auto;
    background-color: #d7e7e491;
}



      #container {
        display: flex;
      }
      .imgbox {
        position: relative;
        width: 200px;
        height: 200px;
        border: 1px solid black;
        margin-right: 10px;
      }
      .imgbox img {
        max-width: 100%;
        max-height: 100%;
      }
      .delete-btn {
        position: absolute;
        top: 10px;
        right: 10px;
        font-size: 20px;
        font-weight: bold;
        cursor: pointer;
        color: red;
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
.board_write .info input[type="password"],
.board_write .info input[type="number"] {
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

.btn-upload {
  width: 100px;
  height: 100px;
  background: #fff;
  border: 1px solid rgb(77,77,77);
  border-radius: 10px;
  font-weight: 500;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  &:hover {
    background: rgb(77,77,77);
    color: #fff;
  }
}
#input-img {
	width: 150px;
}
#imageinput {}

#select-address {
	width: 100%;
	text-align: left;
}
#select-ctgr {
	width: 130px;
}
#write-price {
	margin-right: 20px;
}
#sido_code {
	margin-left: 20px;
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
    
    <jsp:include page="websocket.jsp"></jsp:include>
	<jsp:include page="header.jsp"></jsp:include>
	
    <div class="board_wrap">
        <div class="board_title">
            <strong>댕근마켓</strong>
        </div>
        <form class="board_write_wrap" id="form" action="" method='post' enctype="multipart/form-data" style="display: flex; flex-direction: column;">
            <div class="board_write">
                <div class="title">
                    <dl>
                        
                        <select class="form-select" id="select-ctgr" aria-label="category" name="type_id" >
			                    <c:forEach var="DanggeunTypeDTO" items="${typeList}">
			                    	<option value="${DanggeunTypeDTO.id}"  ${danggeunInfoDTO.type_id == DanggeunTypeDTO.id ? "selected" : ""}>${DanggeunTypeDTO.name}</option>
			                    </c:forEach>
			            </select><input type="text" name="title" value="${danggeunInfoDTO.title}" placeholder="제목 입력">
                        <input type="hidden" name="id" value="${danggeunInfoDTO.id}">
                        
                    </dl>
                </div>
                <section class="dangguen-sec">
                    <div class="info">
                    <div id="select-address">
                        
                            <input type="number" name="price" id="write-price" value="${danggeunInfoDTO.price}" placeholder="판매 가격">
                            <input type="text" name="writer_nickname" value="${loginNickname}" style="display: none;">
                        
		                <select class="form-select" aria-label="category" id="sido_code" name="sido_code">
							<option value="0">도/시</option>
						</select>
						<select class="form-select" aria-label="category" id="sigoon_code" name="sigoon_code">
							<option value="0">군/구</option>
						</select>
						<select class="form-select" aria-label="category" id="dong_code" name="dong_code">
							<option value="0">동네</option>
						</select>
						
                     </div>
						<div class="img-sec" style="display: flex;">
						    <input id="fileInput" type="file" accept=".jpg, .jpeg, .png">
						    <button type="button" onclick="addImgBox()">추가</button>
						</div>
						
						<!-- 사진 순서 조정, 추가, 삭제 가능 -->
						<!-- 사진 목록 불러오기 -->
		                <div id="container">
		                	<c:forEach var="photo" items="${photoList}" varStatus="loop">
		                		<div id="imgbox${loop.index+1}" class="imgbox">
								    <img src="${photo.address}">
								    <span class="delete-btn" onclick="deleteImgBox('imgbox${loop.index+1}')">X</span>
								    <input type="hidden" name="imgbox${loop.index+1}" value="${photo.address}">
							    </div>
							</c:forEach>
		                </div>
		                
		                
		                
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
    
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  let loginNickname = '${loginNickname}'
    $(document).ready(function() {
      if(loginNickname === "") {
      	location.href="/withdang/login"
      }
      $("#container").sortable({
        update: function(event, ui) {
          var imgboxes = $("#container .imgbox");
          imgboxes.each(function(index) {
            var imgbox = $(this);
            var img = imgbox.find("img");
            var newImgboxId = "imgbox" + (index + 1);
            imgbox.attr("id", newImgboxId);
            imgbox.find(".delete-btn").attr("onclick", "deleteImgBox('" + newImgboxId + "')");
            imgbox.find("input").attr("name", newImgboxId);
          });
        }
      });
    });
  
    var imgboxCount = container.getElementsByClassName("imgbox").length; // 이미지 박스 개수를 저장하는 변수

    function addImgBox() {
      var fileInput = document.getElementById("fileInput");
      var files = fileInput.files;
      if (files.length > 0) {
        var container = document.getElementById("container");
        var newImgBox = document.createElement("div");
        newImgBox.className = "imgbox";
        
        imgboxCount++; // 이미지 박스 개수 증가
        var newImgboxId = "imgbox" + imgboxCount; // 새로운 아이디 생성
        
        var newImage = document.createElement("img");
        newImage.src = URL.createObjectURL(files[0]); // 첫 번째 파일의 URL을 설정, 파일 객체를 URL로 변환
        newImage.onload = function() { 				  // 이미지가 로드될 때 호출되는 함수 정의
          URL.revokeObjectURL(this.src); // 생성한 URL을 해제, 메모리 누수 방지를 위함
        };
        newImgBox.appendChild(newImage); // newImgBox 요소에 newImage를 자식 요소로 추가합니다.
        
        // 삭제 버튼 생성
        var deleteBtn = document.createElement("span");
        deleteBtn.className = "delete-btn";
        deleteBtn.textContent = "X";
        deleteBtn.onclick = function() {
          deleteImgBox(newImgboxId);
        };
        newImgBox.appendChild(deleteBtn);
        
        container.appendChild(newImgBox);
        newImgBox.id = newImgboxId; // 새로운 아이디 설정
        
        //드래그 앤 드롭 기능 추가, .imgbox를 핸들로 설정하여 이미지 박스 전체를 드래그할 수 있도록 함
        $(newImgBox).sortable({
          handle: ".imgbox"
        });

        // 추가한 이미지가 입력되어 있는 상태로 복사하여 붙임
        var clonedInput = fileInput.cloneNode(true); // fileInput 즉 input 태그를 그대로 복사함
        clonedInput.id = "fileInput-" + newImgboxId; // 고유한 id 부여
        clonedInput.name = newImgboxId;
        clonedInput.style.display = "none";
        newImgBox.appendChild(clonedInput);
        fileInput.value = ""; // 파일 입력 필드 초기화
      }
    }

    function deleteImgBox(imgboxId) {
      var imgbox = document.getElementById(imgboxId);
      if (imgbox) {
        imgbox.remove();
        // 이미지 박스가 삭제되면 아이디 재조정
        var container = document.getElementById("container");
        var imgboxes = container.getElementsByClassName("imgbox");
        for (var i = 0; i < imgboxes.length; i++) {
          var id = "imgbox" + (i + 1);
          imgboxes[i].id = id;
          imgboxes[i].querySelector(".delete-btn").onclick = function() {
            deleteImgBox(id);
          };
          imgboxes[i].querySelector("input").name = id;
        }
        imgboxCount = imgboxCount - 1;
      }
    }
  </script>
    
    
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
	    if (container.getElementsByClassName("imgbox").length === 0) {
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
    			let html = "<option value='0'>도/시</option>";
    			
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
        		$('#sigoon_code').html("<option value='0'>군/구</option>");
        		$('#dong_code').html("<option value='0'>동네</option>");
        		return
        	}
        	
    		$('#dong_code').html("<option value='0'>동네</option>");
        	
    		$.ajax({
	        	type: 'get',
	    		url: '/withdang/danggeun/sigoon?ctprvn_cd=' + thisVal,
	    		async: false,
	    		headers: { "content-type" : "application/json" },  //요청헤더
	    		success: function(result) {
	    			let html = "<option value='0'>군/구</option>";
	    			
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
        		$('#dong_code').html("<option value='0'>동네</option>");
        		return
        	}
        	$.ajax({
	        	type: 'get',
	    		url: '/withdang/danggeun/dong?sig_cd=' + thisVal,
	    		async: false,
	    		headers: { "content-type" : "application/json" },  //요청헤더
	    		success: function(result) {
	    			let html = "<option value='0'>동네</option>";
	    			
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