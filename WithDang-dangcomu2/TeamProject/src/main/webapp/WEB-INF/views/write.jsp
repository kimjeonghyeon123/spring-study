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
    <link rel="stylesheet" href='${pageContext.request.contextPath}/resources/css/comu2.css'>
    <script src="https://kit.fontawesome.com/cac1ec65f4.js" crossorigin="anonymous"></script>
    <script src='${pageContext.request.contextPath}/resources/script/dangguenwrite.js' defer></script>
    <script src='${pageContext.request.contextPath}/resources/script/toggle.js' defer></script>
    <link href="https://fonts.googleapis.com/css2?family=Gaegu&family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"
        integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"
        integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ"
        crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <style>
    .board_write_wrap {
        width: 100%;
       	display: flex;
        flex-direction: column;
    }
    	
    </style>
    <title>댕댕커뮤</title>
</head>

<body>
<header>
    <nav class="navbar">

        <div class="navbar__logo">
            <a href="${pageContext.request.contextPath}/main">with DANG</a>
        </div>

        <ul class="navbar__menu">
            <li><a href="${pageContext.request.contextPath}/main">댕댕여지도</a></li>
            <li><a href="${pageContext.request.contextPath}/danggeun/list">댕근마켓</a></li>
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
        
	<form action="<c:url value='/dangcomu/post${searchItem.queryString}'/>" id="form" class="board_write_wrap" enctype="multipart/form-data" method="post">
		<div class="board_wrap">
	        <div class="board_title">
	            <strong>댕댕커뮤</strong>
	            <p>우리 댕댕이 자랑부터 동네 소식까지!</p>
	        </div>
	        <div class="board_write_wrap">
	            <div class="board_write">
	                <div class="title">
	                    <dl>
	                        <dd><input id="post_title" name="post_title" type="text" placeholder="제목 입력" required></dd>
	                    </dl>
	                </div>
	                <section class="dangguen-sec">
	                    <div class="info">
	                        <dl>
	                        </dl>
	                        <div id="dangguen-img">
	                            <select id="post_ctgr" name="post_ctgr_id" class="form-select" aria-label="category" required>
                                    <option value="">카테고리</option>
                                    <option value="2">반려소식</option>
                                    <option value="3">반려일상</option>
                                    <option value="4">반려질문</option>
                                    <option value="5">펫과사전</option>
                                    <option value="6">육아꿀팁</option>
                                    <option value="7">기타</option>
	                            </select>
		                        <div class="img-sec">
		                            <input id="imageinput0" name="image1" type="file" accept=".jpg, .jpeg, .png" onchange="previewImage(this, 0)">
		                            <input id="imageinput1" name="image2" type="file" accept=".jpg, .jpeg, .png" onchange="previewImage(this, 1)">
		                            <input id="imageinput2" name="image3" type="file" accept=".jpg, .jpeg, .png" onchange="previewImage(this, 2)">
		                            <input id="imageinput3" name="image4" type="file" accept=".jpg, .jpeg, .png" onchange="previewImage(this, 3)">
		                            <div id="preview-list"></div>
		                        </div>
	                        </div>
	                    </div>
	                    <div class="cont">
	                        <textarea id="post_content" name="post_content" placeholder="내용 입력" required></textarea>
	                    </div>
	            </div>
	            <div class="bt_wrap">
	                <button type="submit" id="postButton" class="on">등록</button>
	                <a href="${pageContext.request.contextPath}/dangcomu/list">취소</a>
	            </div>
	        </div>
	    </div>
	</form>
</body>

</html>