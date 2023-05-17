<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <title>Document</title>
</head>
<body>
    <h2>CommentTest</h2>
    comment <input type="text" name="comment">
    <button id="sendBtn">SEND</button>
    <div id="commentList"></div>
    
    <script type="text/javascript">
    	let bno = 1
    	
    	$(document).ready(function() {
    		showList(bno)
    		
			$("#sendBtn").click(function() {
				let cno = $(this).attr("data-cno")
				let comment = $("input[name=comment]").val()
				
				if(comment.trim() == '') {
					alert("댓글을 입력해 주세요.")
					$("input[name=comment]").focus()
					return
				}
				
				$.ajax({
					type: 'POST',
					url: 'heart/comments?bno='+bno,
					headers: {"content-type" : "application/json"}, //요청 헤더
					data: JSON.stringify({
						"bno": bno,
						"comment": comment
					}),
					success: function(result) {
						alert(result)
						showList(bno)
					},
					error: function() {
						alert("error")
					}	
				})
			})
			
			// [send]버튼 클릭하고 나서 [삭제]버튼이 보임(이벤트 비활성화됨)
			$("#commentList").on("click",".delBtn", function() {
				//alert("삭제 버튼 클릭됨")
				let cno = $(this).parent().attr("data-cno")	//<li>태그는 <button>의 부모임.
				let bno = $(this).parent().attr("data-bno")	
				
				$.ajax({
					type: 'DELETE',					//요청 메서드 
					url: '/heart/comments/'+cno+'?bno='+bno,	//요청 URI
					success: function(result) {		//서버로부터 응답이 도착하면 호출될 함수 
						alert(result)				//result 서버가 전송한 데이터 
						showList(bno)
					},
					error: function() { alert("error") } //에러가 발생했을 때 호출될 함수 
							
				})
			})
		})
		
    	let showList = function(bno) {
		
    		$.ajax({
    			type: 'GET',						//요청 메서드 
    			url: '/heart/comments?bno='+bno,	//요청 URI
				success: function(result) {
					$("#commentList").html(toHtml(result))	//result는 서버가 전송한 데이터
				},
				error: function() { alert("error") }
    		})
    		   		
		}
    	
		let toHtml = function(comments) {
			let tmp = "<ul style='display: block;'>"
				
			comments.forEach(function(comment) {
				tmp += '<li data-cno=' + comment.cno
				tmp += ' data-pcon=' + comment.pcon
				tmp += ' data-bno=' + comment.bno + '>'
				tmp += ' comment=<span class="comment">' + comment.comment + '</span>'     
				tmp += ' commenter=<span class="commenter">' + comment.commenter + '</span>'
				tmp += ' <button class="delBtn">삭제</button>'
				tmp += '</li>'
			})	
			
		
			return tmp + "</ul>"
		}  
    </script>
</body>
</html>











