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
    <script src="<c:url value='/resources/js/toggle.js' />" defer></script>
    <script src="https://kit.fontawesome.com/cac1ec65f4.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Gaegu&family=Nanum+Gothic:wght@400;700;800&display=swap"
        rel="stylesheet">
    <style type="text/css">

* {
    font-family: 'NanumSquareNeo-Variable';
    margin: 0;
}

a {
    text-decoration: none;
    color: inherit;
}

#card-list {
    width: 90%;
    text-align: center;
    margin: 0% 10% auto; /* Centers the card-list horizontally */
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    box-sizing: border-box;
    
    
}

#list {
    width: 90%;
    display: flex;
    flex-wrap: wrap;
    justify-content: left;
    
}

.card {
    width: calc(25% - 20px);
    width: 250px;
    border: none;
    background-color: #d7e7e491;
    border-radius: 5px;
    padding: 10px;
    margin: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.card img {
    width: 200px;
    height: 200px;
    object-fit: cover;
    border-radius: 5px;
    margin-bottom: 12px;
}

.card h2 {
    margin-bottom: 10px;
    font-size: 18px;
    text-align: center;
}

.card li {
    margin-bottom: 5px;
    font-size: 14px;
    text-align: center;
    list-style-type: none;
}

.card .price {
    font-weight: bold;
    margin-bottom: 10px;
}

.buy-btn {
    width: 80px;
    height: 25px;
    border: none;
    background-color: #8e8bac;
    border-radius: 5px;
    color: #fff;
}

.scrap-btn {
    width: 50px;
    height: 25px;
    border: none;
    background-color: #ed8b9e;
    border-radius: 5px;
    color: #fff;
}

.search-box {
    width: 300px;
    height: 35px;
    border: none;
    margin: 20px;
}

.dangguen-title {
    font-size: 44px;
    font-weight: bolder;
    color: black;
    font-family: 'Gaegu', cursive !important;
    margin-top: 10px;
}

.dangguen-sec {
    width: 90% auto;
    background-color: #d7e7e491;
}

.btn-write {
    border-radius: 10px;
    background-color: #ed8b9e;
    border: none;
    width: 150px;
    height: 35px;
    font-weight: 900;
    color: #fff;
    margin: 10px;
}

.btn-search {
    border-radius: 10px;
    background-color: #8e8bac;
    border: none;
    width: 150px;
    height: 35px;
    font-weight: 900;
    color: #fff;
    margin: 10px;
}

#dangguen-head {
    margin: 0%;
    position: inherit;
    text-align: center;
    width: 100%;
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

.img-sec {
    height: 100px;
    background-color: tomato;
    margin: 3% 5% 1% 5%;
    padding: 5%;
    border: 1px solid #ffffffb0;
    display: inline-flex;
    width: 80%;
}


#imageinput {
    text-align: center;
    align-items: center;
    margin: 0% 15% 0%;
}

#preview {
    margin: 3% 2% 0% 4%;
}

@media (max-width: 786px) {
  #dangguen-img {
    margin: 1% 5%;
  }

  #imageinput {
      text-align: center;
      align-items: center;
      margin: 1% 0% 5% 9%;
      
  }
  
          #card-list {
  width: 100%;
  text-align: center;
  margin: 0% 2% auto; 
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
     
  
  }
  
  .card {
  	width: calc(25% - 20px);
    width: 200px;
    border: none;
    background-color: #d7e7e491;
    border-radius: 5px;
    padding: 10px;
    margin: 10px;
    display: flex;
    flex-direction: column;
    align-items: center;
}
  .btn-search {
    border-radius: 10px;
    background-color: #8e8bac;
    border: none;
    width: 100px;
    height: 35px;
    font-weight: 900;
    color: #fff;
    margin: 10px;
  }
  
    .btn-write {
    width: 320px;
    color: #fff;
    text-decoration: none;
  }
  
  
}

#user-name {
  padding-top: 2%;
  font-size: 14px;
  font-weight: bold;
}
#user-adress {
  font-size: 12px;
  margin: 4%;
}

  
		  
    </style>


    <title>댕근마켓</title>
</head>

<body>

    <jsp:include page="header.jsp"></jsp:include>


<!-- 댕근 헤더 부분 -->
    <section class="dangguen-sec">
        <h2 class="dangguen-title">댕근마켓</h2>
        <div class="alert alert-light" id="dangguen-head" role="alert">

		<form action="<c:url value='/danggeun/list' />" method="get">
            <select class="form-select" aria-label="category" name="type_id">
                    <c:forEach var="DanggeunTypeDTO" items="${typeList}">
                    	<option value="${DanggeunTypeDTO.id}" ${type_id == DanggeunTypeDTO.id || type_id eq null ? "selected" : ""}>${DanggeunTypeDTO.name}</option>
                    </c:forEach>
            </select>

            <!-- 검색박스 추가 -->
            <input type="text" class="search-box" placeholder="지역 및 상품 키워드">
            <button type="submit" class="btn-search" >검색</button>
        
            <button type="button" class="btn-write"><a href="<c:url value='/danggeun/write' />">글쓰기</a></button>
        </div>
        </form>
    </section>
<!-- 댕근 헤더 끝 -->


    
    <!-- 상품 리스트 시작 card-list가 전체 -->
    <form id="card-list">
    
    	<div id="list">
    
       	<div class="card">
           	<img src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="Product Image">
           	<h2><a href="<c:url value='/danggeun/view' />">상품명</a></h2>
           	<li class="price">${danggeundto.price}원</li>
           	<li id="user-name">서초동불주먹</li>
           	<li id="user-adress">서울시 서초동</li>
           	<div>
           		<button class="buy-btn"><a href="<c:url value='/danggeun/chat'/>">구매하기</a></button>
           		<button class="scrap-btn">찜♥</button>
           	</div>
       	</div>
       	<!-- 2번째 상품 -->
       	<div class="card">
           	<img src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="Product Image">
           	<h2><a href="<c:url value='/danggeun/view?id=${danggeundto.id}&type_id=${type_id}' />">${danggeundto.title}</a>상품명</h2>
           	<li class="price">${danggeundto.price}원</li>
           	<li id="">서초동불주먹</li>
           	<li id="user-adress">서울시 서초동</li>
           	<div>
           		<button class="buy-btn"><a href="<c:url value='/danggeun/chat'/>">구매하기</a></button>
           		<button class="scrap-btn">찜♥</button>
           	</div>
       	</div>
       	
       	<div class="card">
           	<img src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="Product Image">
           	<h2><a href="<c:url value='/danggeun/view?id=${danggeundto.id}&type_id=${type_id}' />">${danggeundto.title}</a>상품명</h2>
           	<li class="price">${danggeundto.price}원</li>
           	<li id="">서초동불주먹</li>
           	<li id="user-adress">서울시 서초동</li>
           	<div>
           		<button class="buy-btn">구매하기</button>
           		<button class="scrap-btn">찜♥</button>
           	</div>
       	</div>
       	<div class="card">
           	<img src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="Product Image">
           	<h2><a href="<c:url value='/danggeun/view?id=${danggeundto.id}&type_id=${type_id}' />">${danggeundto.title}</a>상품명</h2>
           	<li class="price">${danggeundto.price}원</li>
           	<li id="">서초동불주먹</li>
           	<li id="user-adress">서울시 서초동</li>
           	<div>
           		<button class="buy-btn">구매하기</button>
           		<button class="scrap-btn">찜♥</button>
           	</div>
       	</div>

       	
       	       	<div class="card">
           	<img src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="Product Image">
           	<h2><a href="<c:url value='/danggeun/view?id=${danggeundto.id}&type_id=${type_id}' />">${danggeundto.title}</a>상품명</h2>
           	<li class="price">${danggeundto.price}원</li>
           	<li id="">서초동불주먹</li>
           	<li id="user-adress">서울시 서초동</li>
           	<div>
           		<button class="buy-btn">구매하기</button>
           		<button class="scrap-btn">찜♥</button>
           	</div>
       	</div>
       	
       	       	<div class="card">
           	<img src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="Product Image">
           	<h2><a href="<c:url value='/danggeun/view?id=${danggeundto.id}&type_id=${type_id}' />">${danggeundto.title}</a>상품명</h2>
           	<li class="price">${danggeundto.price}원</li>
           	<li id="">서초동불주먹</li>
           	<li id="user-adress">서울시 서초동</li>
           	<div>
           		<button class="buy-btn">구매하기</button>
           		<button class="scrap-btn">찜♥</button>
           	</div>
       	</div>
       	
       	       	<div class="card">
           	<img src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="Product Image">
           	<h2><a href="<c:url value='/danggeun/view?id=${danggeundto.id}&type_id=${type_id}' />">${danggeundto.title}</a>상품명</h2>
           	<li class="price">${danggeundto.price}원</li>
           	<li id="">서초동불주먹</li>
           	<li id="user-adress">서울시 서초동</li>
           	<div>
           		<button class="buy-btn">구매하기</button>
           		<button class="scrap-btn">찜♥</button>
           	</div>
       	</div>
       	
       	       	<div class="card">
           	<img src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="Product Image">
           	<h2><a href="<c:url value='/danggeun/view?id=${danggeundto.id}&type_id=${type_id}' />">${danggeundto.title}</a>상품명</h2>
           	<li class="price">${danggeundto.price}원</li>
           	<li id="">서초동불주먹</li>
           	<li id="user-adress">서울시 서초동</li>
           	<div>
           		<button class="buy-btn">구매하기</button>
           		<button class="scrap-btn">찜♥</button>
           	</div>
       	</div>
       	
       	       	<div class="card">
           	<img src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="Product Image">
           	<h2><a href="<c:url value='/danggeun/view?id=${danggeundto.id}&type_id=${type_id}' />">${danggeundto.title}</a>상품명</h2>
           	<li class="price">${danggeundto.price}원</li>
           	<li id="">서초동불주먹</li>
           	<li id="user-adress">서울시 서초동</li>
           	<div>
           		<button class="buy-btn">구매하기</button>
           		<button class="scrap-btn">찜♥</button>
           	</div>
       	</div>
       	
       	</div>
       	
   	</form>
    
    
    
    
<script type="text/javascript">
    $(document).ready(function(){
        $(".storeBtn").on('click', function() {
            var button = $(this); // 버튼 객체를 저장
            var id = $(this).data('id')
            var loginemail = $(this).data('loginemail')
            var writeremail = $(this).data('writeremail')
            
            if(loginemail == writeremail) {
            	return alert("본인 상품을 찜할 수 없습니다.")
            }
            
            $.ajax({
                type: 'POST',
                url: '/jeonghyeonkim/danggeun/togglezzim',
                data: {
                    'danggeun_id': id,
                    'member_email': loginemail
                },
                success: function(response) {
	            	if (response == "added") {
                        button.css('background-color', 'red');
                    } else if (response == "removed") {
                        button.css('background-color', '');
                    }
                },
                error: function(xhr, status, error) {
                    alert("error");
                }
            });
        });
    });
</script>

</body>

</html>