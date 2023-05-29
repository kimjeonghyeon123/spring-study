<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="loginId" value="${sessionScope.id }" />
<c:set var="loginout" value="${sessionScope.id==null ? 'Login' : 'id:'+=loginId }" /> 
<c:set var="loginoutlink" value="${sessionScope.id==null ? '/login/login' : '/login/logout' }" /> 

    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/menu.css' />" />
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>    
    <title>게시판 리스트</title>
    
    <style type="text/css">
    	* {
    		font-family: 'Noto Sans KR', sans-serif;
    	}
    	
    	a {
    		text-decoration: none;
    		color: black;
    	}
    	
		button, input {
			border: none;
			outline: none;
		}    	
    	
    	.board-container {
    		width: 60%;
    		height: 1200px;
    		margin: 0 auto;
    	}
    	
		.search-container {
			background-color: rgb(253, 253, 250);
			width: 100%;
			height: 110px;
			border: 1px solid #ddd;
			margin-top: 10px;
			margin-bottom: 30px;
			display: flex;
			justify-content: center;
			align-items: center;
		}    	
    	
    	table {
    		border-collapse: collapse;
    		width: 100%;
    		border-top: 2px solid rgb(39,39,39);
    	}
    	
    	tr:nth-child(even) {
			background-color: #f0f0f070;
		}
		
		th, td {
			width: 300px;
			text-align: center;
			padding: 10px 12px;
			border-bottom: 1px solid #ddd;
		}
		
		.paging-container {
			width: 100%;
			height: 70px;
			display: flex;
			margin-top: 50px;
			margin: auto;
		}
		
		.paging {
			color: black;
			width: 100%;
			align-items: center;
		}
		
		.page {
			color: black;
			padding: 6px;
			margin-right: 10px;
		}
		
		.search-form {
			height: 37px;
			display: flex;
		}
		
		.search-option {
			color: gray;
			width: 100px;
			height: 100%;
			outline: none;
			margin-right: 5px;
			border: 1px solid #ccc;
		}

		.search-input {
			color: gray;
			background-color: white;
			border: 1px solid #ccc;
			height: 100%;
			width: 300px;
			font-size: 15px;
			padding: 5px 7px;
		}
		.search-input::placeholder {
			color: gray;
		}
		.search-button {
			width: 20%;
			height: 100%;
			background-color: rgb(22,22,22);
			color: rgb(209,209,209);
			display: flex;
			align-items: center;
			justify-content: center;
			font-size: 15px;
		}
		.search-button::hover {
			color: rgb(165,165,165);
		}
		.btn-write {
			background-color: rgb(236, 236, 236);
			border: none;
			color: black;
			padding: 6px 12px;
			font-size: 16px;
			cursor: pointer;
			border-radius: 5px;
			margin-left: 30px;
		}
		.chat-overlay {
		    display: none;
		    position: relative;
		    top: -10px;
		    background-color: red;
		    color: white;
		    padding: 5px;
		    border-radius: 5px;
		}	
    </style>
</head>
<body>
    <div id="menu">
    	<ul>
    		<li id="logo">earth</li>
    		<li><a href="<c:url value='/' />">Home</a></li>
    		<li><a href="<c:url value='/chat' />">chat</a></li>
    		<li><a href="<c:url value='/board/list' />">Board</a></li>
    		<li><a href="<c:url value='${loginoutlink}' />">${loginout}</a></li>
    		<li><a href="<c:url value='/register/add' />">SignUp</a></li>
    		<li><a href=""><i class="fas fa-search small"></i></a></li>
    	</ul>
    </div>
    
    <script type="text/javascript">
    	let msg = "${msg}"
    	if(msg == "DEL_OK") alert("성공적으로 삭제되었습니다.")
    	if(msg == "DEL_ERR") alert("삭제되었거나 없는 게시물입니다.")
    	if(msg == "WRT_OK") alert("성공적으로 등록되었습니다.")
    	if(msg == "MOD_OK") alert("성공적으로 수정되었습니다.")
    </script>
    
    <div style="text-align: center;">
    	<div class="board-container">
    		<div class="search-container">
    			<form action="<c:url value='/board/list' />" class="search-form" method="get">
    				<select class="search-option" name="option" >
    					<option value="A" ${pr.sc.option=='A' || pr.sc.option=='' ? "selected" : "" }>제목+내용</option>
    					<option value="T" ${pr.sc.option=='T' ? "selected" : "" }>제목</option>
    					<option value="W" ${pr.sc.option=='W' ? "selected" : "" }>작성자</option>
    				</select>
    				<input type="text" name="keyword" class="search-input" 
    						value="${param.keyword }" placeholder="검색어를 입력해주세요"/>
    				<input type="submit" value="검색" class="search-button"/>		
    			</form>
    			<button id="writeBtn" class="btn-write"
    					onclick="location.href='<c:url value="/board/write" />'">
    			<i class="fa-regular fa-pencil"></i>글쓰기</button>
    		</div>
    		
    		<table>
    			<tr>
    				<th class="no">번호</th>
    				<th class="title">제목</th>
    				<th class="writer">이름</th>
    				<th class="regdate">등록일</th>
    				<th class="viewcnt">조회수</th>
    			</tr>
    			
    			<c:forEach var="boardDto" items="${list }">
    				<tr>
    					<td class="no">${boardDto.bno }</td>
    					<td class="title">
    						<a href="<c:url value="/board/read${pr.sc.queryString}&bno=${boardDto.bno }" />">${boardDto.title }</a>
    					</td>
    					<td class="writer">
					      	<a href="<c:url value="/chat?otherId=${boardDto.writer}" />" class="chat-link">${boardDto.writer}</a>
					      	<span class="chat-overlay">채팅하기</span>
					    </td>
    					<td class="regdate">
    						<fmt:formatDate value="${boardDto.reg_date }" pattern="yyyy-MM-dd" type="date"/>
    					</td>
    					<td class="viewcnt">${boardDto.view_cnt }</td>
    				</tr>
    			</c:forEach>
    		</table>
    		<br>
    		
    		<div class="paging-container">
    			<div class="paging">
    				<c:if test="${totalCnt == null || totalCnt == 0 }">
    					<div>게시물이 없습니다.</div>
    				</c:if>
    				<c:if test="${totalCnt != null || totalCnt != 0 }">
    					<c:if test="${pr.showPrev }">
    						<a class="page" href="<c:url value="/board/list${pr.sc.getQueryString(pr.beginPage-1) }" />">&lt;</a>
    					</c:if>
    					<c:forEach var="i" begin="${pr.beginPage }" end="${pr.endPage }">
    						<a class="page" href="<c:url value="/board/list${pr.sc.getQueryString(i) }" />">${i }</a>
    					</c:forEach>
    					<c:if test="${pr.showNext }">
    						<a class="page" href="<c:url value="/board/list${pr.sc.getQueryString(pr.endPage+1) }" />">&gt;</a>
    					</c:if>    					
    				</c:if>
    			</div>
    		</div>
    	</div>
    </div>
	<script>
	  // writer 클릭 시 채팅하기 문구 띄우기
	  var chatLinks = document.getElementsByClassName("chat-link");
	  for (var i = 0; i < chatLinks.length; i++) {
	    chatLinks[i].addEventListener("mouseenter", function() {
	      var overlay = this.nextElementSibling;
	      overlay.style.display = "inline";
	    });
	    chatLinks[i].addEventListener("mouseleave", function() {
	      var overlay = this.nextElementSibling;
	      overlay.style.display = "none";
	    });
	  }
	</script>
</body>
</html>












