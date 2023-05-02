<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="loginId" value="${sessionScope.id}" />
<c:set var="loginout" value="${sessionScope.id==null ? 'Login' : 'Logout'}" />
<c:set var="loginoutlink" value="${sessionScope.id==null ? '/login/login' : '/login/logout'}" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/menu.css'/>">
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <title>게시판 리스트</title>
    <style type="text/css">
    	* {
    		font-family: 'Noto Sans KR', sans-serif;
    	}
    	
    	.board-container {
    		width: 60%;
    		height: 1200px;
    		margin: 0 auto;
    	}
    </style>
</head>
<body>
	<!-- 헤더 인클루드 -->
	<%-- <%@ include file="header.jsp" %> --%>
	
	<div id="menu">
		<ul>
			<li id="logo">earth</li>
			<li><a href="<c:url value='/' />">Home</a></li>
			<li><a href="<c:url value='/board/list' />">Board</a></li>
			<li><a href="<c:url value='${loginoutlink}' />">${loginout}</a></li>
			<li><a href="<c:url value='/register/add' />">SignUp</a></li>
			<li><a href=""><i class="fas fa-search small"></i></a></li>
		</ul>
	</div>
	
	<div style="text-align:">
		<div class="board-container">
			<div class="search-container">
				
			</div>
			
			<table>
				
			</table>
		</div>
	</div>
</body>
</html>