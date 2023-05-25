<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<c:set var="loginId" value="${sessionScope.id }" />
<c:set var="loginout" value="${sessionScope.id==null ? 'Login' : 'id:'+=loginId }" /> 
<c:set var="loginoutlink" value="${sessionScope.id==null ? '/login/login' : '/login/logout' }" /> 
 
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/menu.css' />" />
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>    
    <title>earth</title>
</head>
<body>

	<div id="socketAlert" class="alert alert-success" role="alert" style="display: none;"></div>
    
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
    <div style="text-align: center;">
    	<h1>세상을 바꾸는 HOME!</h1>
    	<h1>다음 세대에게 더 나은 세상을!</h1>
    	<h1>지구를 사랑하는 마음!</h1>
    </div>
    
    <input type="hidden" id="sender_id" name="sender_id" value="${loginId}">
    <input type="text" id="receiverId" name="receiverId">
    <input type="text" id="chatcontent" name="content">
    <button type="button" id="sendBtn">send</button>
    
    <div id="socketAlert" class="alert alert-success" role="alert" style="display: none;"></div>
    <div id="chattingroom"></div>
    
    <script type="text/javascript">
		var socket = null
		
    	$(document).ready(function(){
    		
    		connectWS()
    		showList()
    		
    		$("#sendBtn").on('click', function(){
    			let content = $("input[name=content]").val()
    			let sender_id = $("input[name=sender_id]").val()
    			let receiverId = $("input[name=receiverId]").val()
    			
    			if(receiverId.trim() == '') {
					alert("채팅 상대를 입력해 주세요.")
					$("input[name=receiverId]").focus()
					return
				}
    			if(content.trim() == '') {
					alert("채팅을 입력해 주세요.")
					$("input[name=content]").focus()
					return
				}
    			
    			$.ajax({
    				type: 'post',
    				url: '/korea/chat',
    				headers: { "content-type" : "application/json" },
    				data : JSON.stringify({sender_id:sender_id, content:content}),
    				success : function(result) {
    					showList()
    					if(socket) {
    		    			let socketMsg = "chat," + sender_id + "," + receiverId + "," + content
    		    			socket.send(socketMsg)
    					}
    				},
    				error : function() {
    					alert()
    				}
    			})
    		})
    	})
		
		function connectWS() {
			var ws = new WebSocket("ws://localhost/korea/replyEcho")
			socket = ws
			
			ws.onopen = function() {
				console.log('Info: connection opened.')
			}
			
			ws.onmessage = function(event) {
				console.log("ReceiveMessage:", event.data+'\n')
				
			    let message = JSON.parse(event.data)
			    let cmd = message.cmd
			    
			    if (cmd === "reply") {
			        let replyWriter = message.replyWriter
			        let bno = message.bno
			        let comment = message.comment
			        
					let socketAlert = $('div#socketAlert')
					socketAlert.text(replyWriter + '님이 ' + bno + '번 게시글에 ' + comment +'라고 달았습니다.')
					socketAlert.css('display', 'block')
					
			    } else if (cmd === "chat") {
			        showList()
			    }
			}
			
			ws.onclose = function (event){ 
				console.log('Info: connection closed') 
				//setTimeout(function(){connect()}, 1000)	
			}
			ws.onerror = function (err){ console.log('Error: ', err) }
		}
		

		let showList = function() {
	    	$.ajax({
	    		type: 'GET',						//요청 메서드 
	    		url: '/korea/chat',	//요청 URI
				success: function(result) {
					$("#chattingroom").html(toHtml(result))	//result는 서버가 전송한 데이터
				},
				error: function() { alert("error") }		//에러가 발생할때, 
	    	})		
		}
		
		let toHtml = function(chats) {
			let tmp = '';
			chats.forEach(function(chat) {
				tmp += '<p class="chat">' + chat.sender_id + ': ' + chat.content;
				tmp += '<span class="reg-date">' + formatRegDate(chat.reg_date) + '</span>';
				tmp += '</p>';
			});
			
			return tmp;
		};

		let formatRegDate = function(regDate) {
			let date = new Date(regDate);
			let hours = date.getHours();
			let minutes = date.getMinutes();
			let period = hours >= 12 ? '오후' : '오전';

			// 12시간 단위로 변환
			hours = hours % 12;
			hours = hours ? hours : 12;

			// 분이 한 자리 수인 경우 앞에 0 추가
			minutes = minutes < 10 ? '0' + minutes : minutes;

			return period + ' ' + hours + ':' + minutes;
		};
    </script>
</body>
</html>































