<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/menu.css' />" />
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>    
    <title>Document</title>
    <style>
        html,
        body {
		  display: flex;
		  flex-direction: column;
		  height: 100vh; /* 화면 전체 높이 */
        }

        #body {
            width: 100%;
            flex: 1;
            background-color: white;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        #chat {
            width: 800px;
            height: 500px;
            display: flex;
            border: 1px solid gray;
        }

        #chatrooms {
            width: 50%;
            height: 100%;
            overflow-y: scroll;
            scrollbar-width: none; /* Firefox에서 기본 스크롤바 감추기 */
            -ms-overflow-style: none; /* IE/Edge에서 기본 스크롤바 감추기 */
        }

        #chatrooms::-webkit-scrollbar {
            display: none; /* Chrome, Safari, Opera에서 기본 스크롤바 감추기 */
        }

        #chattings { 
            width: 50%;
            height: 100%;
            border-left: 1px solid gray;
        }
        
        #chatting {
        	width: 100%;
            height: 80%;
            overflow-y: scroll;
            scrollbar-width: none; /* Firefox에서 기본 스크롤바 감추기 */
            -ms-overflow-style: none; /* IE/Edge에서 기본 스크롤바 감추기 */
        }

        #chatting::-webkit-scrollbar {
            display: none; /* Chrome, Safari, Opera에서 기본 스크롤바 감추기 */
        }
        #sendChat {
		    width: 100%;
		    height: 20%;
		    display: flex;
		}
		#chatInput {
		    width: 80%;
		    padding: 10px;
		}
		#sendBtn {
		    width: 20%;
		    background-color: #4CAF50;
		    color: white;
		    border: none;
		    padding: 10px;
		    cursor: pointer;
		}
        .chatroom {
            height: 100px; /* 적절한 높이 설정 */
            border-bottom: 1px solid #ccc; /* 채팅방 간 구분선 설정 */
            display: flex;
            justify-content: space-between;
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
    
    <div id="body">
        <div id="chat">
            <div id="chatrooms">
            </div>
            <div id="chattings">
            	<div id="chatting"></div>
            	<div id="sendChat">
			        <textarea id="chatInput" placeholder="채팅을 입력하세요"></textarea>
			        <button id="sendBtn">전송</button>
            	</div>
            </div>
        </div>
    </div>
<script type="text/javascript">
    let loginId = '${loginId}'
    let chatroomId = null
    let otherId = null
    let socket = null
    
    $(document).ready(function(){
        connectWS()
        showList()
        
        $("#chatrooms").on('click', '.chatroom', function(){
        	chatroomId = $(this).attr('data-chatroomId')
        	otherId = $(this).attr('data-otherId')
        	showChattingList(chatroomId)
            if(socket) {
		    	let socketMsg = "readchat," + otherId + "," + chatroomId
		      	socket.send(socketMsg)
		    }
        })
        
        $("#sendBtn").on('click', function(){
    		let chat = $('#chatInput').val().trim();
    		
    		if (chat === '') {
    			alert('채팅을 입력해주세요.')
    			$('#chatInput').focus()
    		}
    		else {
    			$.ajax({
    				type: 'post',
    				url: '/korea/sendchatting',
    				headers: { "content-type" : "application/json" },  //요청헤더
    				data : JSON.stringify({chatroomId: parseInt(chatroomId), loginId:loginId, chat: chat}),  //서버로 전송할 데이터, stringify()로 직렬화 필요
    				success: function(result) {
    					showList()
    					showChattingList(chatroomId)
    	                $('#chatInput').val('')
		    			if(socket) {
		                	let socketMsg = "sendchat," + otherId + "," + chatroomId
		                	socket.send(socketMsg)
		                }
    	            },
    	            error: function() {alert('error')}
    			})
    		}
        })
    })

    let showList = function() {
        $.ajax({
            type: 'post',
            url: '/korea/chats',
            headers: { "content-type" : "application/json" },  //요청헤더
			data : JSON.stringify({loginId:loginId}),  //서버로 전송할 데이터, stringify()로 직렬화 필요
			success: function(result) {
                $('#chatrooms').html(toHtml(result))
            },
            error: function() {alert('error')}
        })
    }

    let toHtml = function(chatrooms) {
        let tmp = ''

        chatrooms.forEach(function(chatroom){
            tmp += '<div class="chatroom" data-chatroomId="' + chatroom.id + '" data-otherId="' + chatroom.other_id + '">'
            tmp += '<div><p>' + chatroom.other_id + '</p><br><p>' + chatroom.recent_id + ': ' + chatroom.recent_chat + '</p></div>'
            tmp += '<div><p>' + formatRegDate(chatroom.recent_date) + '</p><br><p>'
            if(chatroom.recent_id !== loginId && chatroom.unread_cnt !== 0) {
                tmp += chatroom.unread_cnt
            }
            tmp += '</p></div></div>'
        })

        return tmp
    }
    
    let showChattingList = function(chatroomId) {
    	$.ajax({
    		type: 'post',
    		url: '/korea/chattings',
            headers: { "content-type" : "application/json" },  //요청헤더
			data : JSON.stringify({chatroomId:parseInt(chatroomId), loginId:loginId}),
    		success: function(result) {
    			$('#chatting').html(chatHtml(result))
    			
                var chattingDiv = document.getElementById('chatting')
                chattingDiv.scrollTop = chattingDiv.scrollHeight
    		},
            error: function() {alert('error')}
    	})
    }
    
    let chatHtml = function(chattings) {
        let tmp = ''

        chattings.forEach(function(chatting){
        	if(chatting.sender_id === loginId) {
            	tmp += '<div class="chatting-right" style="text-align: right; margin: 5px 0;">'
				tmp += '<p>'
	            if(chatting.check_read === false) {
	            	tmp += '<span style="color: yellow;">1</span>'
	            }
				tmp += '<span style="font-size: 12px; padding: 0 3px;">' + formatRegDate(chatting.chat_date) + '</span>'
            	tmp += '<span style="border: 1px solid gray; border-radius: 5px; padding: 0 5px; margin-right: 3px;">' + chatting.chat + '</span></p>'
        	}
        	else {
        		tmp += '<div class="chatting-left" style="text-align: left;">'
				tmp += '<p style="font-size: 20px;">' + chatting.sender_id + '</p>'
				tmp += '<p><span style="border: 1px solid gray; border-radius: 5px; padding: 0 5px; margin-left: 10px;">' + chatting.chat + '</span><span style="font-size: 12px;">' + formatRegDate(chatting.chat_date) + '</span></p>'
        	}
			       	
            tmp += '</div>'
        })

        return tmp
    }
    
    let formatRegDate = function(regDate) {
        let currentDate = new Date() // 현재 날짜와 시간
        let inputDate = new Date(regDate) // 입력된 날짜와 시간을 JavaScript의 Date 객체로 변환

        // 현재 날짜와 입력된 날짜의 차이를 계산합니다.
        let timeDiff = currentDate.getTime() - inputDate.getTime()
        let daysDiff = Math.floor(timeDiff / (1000 * 3600 * 24)) // 일 단위로 변환

        if (daysDiff > 0) {
            // 전날인 경우 월일을 표시합니다.
            let month = inputDate.getMonth() + 1 // getMonth()의 반환값은 0부터 시작하므로 1을 더해줍니다.
            let day = inputDate.getDate()
            return month + '월 ' + day + '일'
        } else {
            // 같은 날인 경우 시간을 표시합니다.
            let hours = inputDate.getHours()
            let minutes = inputDate.getMinutes()
            let period = hours >= 12 ? '오후' : '오전'

            // 12시간 단위로 변환
            hours = hours % 12
            hours = hours === 0 ? 12 : hours // 0시일 경우 12로 표시합니다.

            // 분이 한 자리 수인 경우 앞에 0 추가
            minutes = minutes < 10 ? '0' + minutes : minutes

            return period + ' ' + hours + ':' + minutes
        }
    }
    
	// 소켓
	function connectWS() {
		var ws = new WebSocket("ws://localhost:8080/korea/replyEcho")
		socket = ws
		
		ws.onopen = function() {
			console.log('Info: connection opened.')
		}
		
		ws.onmessage = function(event) {
			console.log("ReceiveMessage:", event.data+'\n')
			
		    let message = JSON.parse(event.data)
		    let cmd = message.cmd
		    
		    if (cmd === "sendchat") {
		    	console.log('message.chatroomId:', message.chatroomId)
		    	console.log('type: ', typeof(message.chatroomId))
		    	console.log('chatroomId:', chatroomId)
		    	console.log('type: ', typeof(chatroomId))
		    	if(String(message.chatroomId) === chatroomId) {
		    		showList()
		    		showChattingList(chatroomId)
		    		if(socket) {
				    	let socketMsg = "readchat," + otherId + "," + chatroomId
				      	socket.send(socketMsg)
				    }
		    	}
		    	else {
		    		showList()
		    	}
		    }
		    else if (cmd === "readchat") {
		    	console.log('message.chatroomId:', message.chatroomId)
		    	console.log('type: ', typeof(message.chatroomId))
		    	console.log('chatroomId:', chatroomId)
		    	console.log('type: ', typeof(chatroomId))
		    	if(String(message.chatroomId) === chatroomId) {
		    		showList()
		    		showChattingList(chatroomId)
		    	}
		    	else {
		    		showList()
		    	}
		    }
		}
		
		ws.onclose = function (event){ 
			console.log('Info: connection closed') 
			//setTimeout(function(){connect()}, 1000)	
		}
		ws.onerror = function (err){ console.log('Error: ', err) }
	}
</script>  
</body>
</html>










