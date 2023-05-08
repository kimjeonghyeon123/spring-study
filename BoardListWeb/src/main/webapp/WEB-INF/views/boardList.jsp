<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="loginEmail" value="${sessionScope.email}" />
<c:set var="loginout" value="${sessionScope.email==null ? 'Login' : 'Logout'}" />
<c:set var="loginoutlink" value="${sessionScope.email==null ? '/login/login' : '/login/logout'}" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value='/resources/css/board.css'/>">
    <script src="https://kit.fontawesome.com/cac1ec65f4.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css2?family=Gaegu&family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">

    <title>댕댕커뮤</title>
</head>

<body>
    <header>
        <nav class="navbar">

            <div class="navbar__logo">
                <a href="<c:url value='/' />">with DANG</a>
            </div>

            <ul class="navbar__menu">
                <li><a href="main.html">댕댕여지도</a></li>
                <li><a href="<c:url value='/danggeun/list' />">댕근마켓</a></li>
                <li><a href="dangcare.html">댕댕케어</a></li>
                <li><a href="<c:url value='/board/list' />">댕댕커뮤</a></li>
                <li><a href="dangoffice.html">댕사무소</a></li>
                <li><button class="btnLogin"><a href="<c:url value='${loginoutlink}' />">${loginout}</a></button></li>
                <li><a href="mypage.html"><i class="fa fa-user-o" id="btnMypage" aria-hidden="true"></i></a></li>

            </ul>
            <a href="#" class="navbar__toggleBtn">
                <i class="fas fa-bars" aria-hidden="true"></i>
            </a>
        </nav>
    </header>
    <div class="board_wrap">
        <div class="board_title">
            <strong>댕댕커뮤</strong>
            <p>우리 댕댕이 자랑부터 동네 소식까지!</p>
        </div>
        <div class="board_list_wrap">
            <div class="board_list">
                <div class="top">
                    <div class="num">번호</div>
                    <div class="title">제목</div>
                    <div class="writer">닉네임</div>
                    <div class="date">작성일</div>
                    <div class="count">조회</div>
                </div>
                <c:forEach var="dangBoardDTO" items="${list}">
	                <div>
	                    <div class="num">${dangBoardDTO.bno}</div>
	                    <div class="title">
	                    	<a href="<c:url value='/board/read${pr.sc.queryString}&bno=${dangBoardDTO.bno}' />">${dangBoardDTO.title}</a>
	                    </div>
	                    <div class="writer">${dangBoardDTO.writer}</div>
	                    <div class="date">
							<fmt:formatDate value="${dangBoardDTO.reg_date}" pattern="yyyy-MM-dd" type="date" />
						</div>
	                    <div class="count">${dangBoardDTO.view_cnt}</div>
	                </div>
                </c:forEach>
            </div>
            <div class="board_page">
            	<c:if test="${totalCnt == null || totalCnt == 0}">
            		<div>게시물이 없습니다.</div>
            	</c:if>
            	<c:if test="${totalCnt != null || totalCnt != 0}">
            		<c:if test="${pr.showPrev}">
    					<a class="page" href="<c:url value="/board/list${pr.sc.getQueryString(pr.beginPage-1)}" />">&lt;</a>
    				</c:if>
    				<c:forEach var="i" begin="${pr.beginPage}" end="${pr.endPage}">
    						<a class="page" href="<c:url value="/board/list${pr.sc.getQueryString(i)}" />">${i}</a>
    				</c:forEach>
    				<c:if test="${pr.showNext}">
    					<a class="page" href="<c:url value="/board/list${pr.sc.getQueryString(pr.endPage+1)}" />">&gt;</a>
    				</c:if> 
            	</c:if>
            </div>
            <div class="bt_wrap">
                <a href="write.html" class="on">등록</a>
                <!--<a href="#">수정</a>-->
            </div>
        </div>
    </div>
</body>

</html>