<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <script src='${pageContext.request.contextPath}/resources/script/toggle.js' defer></script>
    <link href="https://fonts.googleapis.com/css2?family=Gaegu&family=Nanum+Gothic:wght@400;700;800&display=swap"
        rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"
        integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"
        integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ"
        crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
	
    <title>댕댕커뮤</title>
    
    <style type="text/css">
    .comment-list li {
		  padding: 10px;
		  margin-bottom: 10px;
		  background-color: #f1f1f1;
		  border-radius: 5px;
		}
		
		.comment-list li strong {
		  font-weight: bold;
		}
		
		.comment-list li em {
		  font-style: italic;
		}
    </style>
</head>

<body>
    <jsp:include page="header.jsp"></jsp:include>

<script type="text/javascript">
	$(document).ready(function() {
		let post_id = $("input[name=post_id]").val()
		
		$("#commentButton").click(function() {
			let cmt_content = $("textarea[name=comment]").val()
			
			$.ajax({
				type: 'post',
				url: '/withdang/dangcomu/comments/' + post_id,
				headers: { "content-type" : "application/json" }, 
				data : JSON.stringify({post_id: post_id, cmt_content: cmt_content}),  
				success : function(result) {	
					alert("success")
					showList(post_id)
				},
				error : function(jqXHR, textStauts, errorThrown) {
					alert("error")
					if (jqXHR.status == 403) {
						alert("로그인이 필요합니다.")
						window.location.href = "http://localhost:8080/withdang/login"
					}
				}  
			})
			
		})	
		
		$(".deleteButton").click(function() {
			let cmt_id = $(this).parent().attr("cmt_id")	
			let post_id = $(this).parent().attr("post_id")	
			
			$.ajax({
				type: 'DELETE',					
				url: '/withdang/dangcomu/comments/' + post_id + "/" + cmt_id,
				success: function(result) {		
					alert(result),				
					showList(post_id)
				},
				error: function() { alert("error") } 		
			})
		})	
		
    	let showList = function(post_id) {
    		$.ajax({
    			type: 'GET',					
    			url: '/withdang/dangcomu/comments/' + post_id,	
				success: function(result) {
					alert("showList success")
					$("#commentList").html(toHtml(result))	
				},
				error: function() { alert("error") }		 
    		})
		}
		
		let toHtml = function(comments) {
		    let tmp = '<ul style="display: block;">';

		    comments.forEach(function(comment) {
		        let date = new Date(comment.cmt_created_time)
		        tmp += '<li style="">';
		        tmp += ' 닉네임 : ' + comment.user_nickname + '<br>';  // 닉네임 줄바꿈 추가
		        tmp += ' 댓글내용 : <span class="cmt_content">' + comment.cmt_content + '</span><br>';  // 댓글 내용 줄바꿈 추가
		        tmp += ' 작성날짜 : <span class="user_email">' + date + '</span><br>';  // 작성 날짜 줄바꿈 추가
		        tmp += ' <button class="deleteButton">삭제</button>';
		        tmp += '</li>';
		    });

		    return tmp + "</ul>";
		}

		showList(post_id);
	})
	
</script>

<div class="board_wrap">
        <div class="board_title">
            <strong>댕댕커뮤</strong>
            <p>우리 댕댕이 자랑부터 동네 소식까지!</p>
        </div>
        <div class="board_view_wrap">
            <div class="board_view">
            	<input type="hidden" name="post_id" value="${comuDTO.post_id}"/>
                <div class="title">
                    ${comuDTO.post_title}  
                </div>  
                <div class="info">
                    <dl>
                        <dt>번호</dt>
                        <dd>${comuDTO.post_id}</dd>
                    </dl>
                    <dl>
                        <dt>글쓴이</dt>
                        <dd>${comuDTO.user_name}</dd>
                    </dl>
                    <dl>
                        <dt>작성일</dt>
                        <dd><fmt:formatDate value="${comuDTO.post_created_time}" pattern="yyyy-MM-dd" type="date"/></dd>
                    </dl>
                    <dl>
                        <dt>조회</dt>
                        <dd>${comuDTO.post_view_count}</dd>
                    </dl>
                </div>
                <div class="cont">
                   ${comuDTO.post_content}
                </div>
            </div>

            <!-- 댓글 기능 -->
            <br>
            <div class="comment-form">
                <textarea id="commentInput" placeholder="댓글 내용" name="comment" required></textarea>
                <button type="button" id="commentButton">댓글 달기</button>
            </div>
            <ul class="comment-list" id="commentList">
                <!-- 댓글 리스트 -->
            </ul>

            <div class="bt_wrap">  
                <a href="${pageContext.request.contextPath}/dangcomu/update?post_id=${comuDTO.post_id}" class="on">수정</a>  
                <a href="${pageContext.request.contextPath}/dangcomu/list">목록</a>  
            </div>
            <br>
        </div>
    </div>

</body>

</html>