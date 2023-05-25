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

        #chatting {
            width: 50%;
            height: 100%;
            overflow-y: scroll;
            overflow-y: scroll;
            scrollbar-width: none; /* Firefox에서 기본 스크롤바 감추기 */
            -ms-overflow-style: none; /* IE/Edge에서 기본 스크롤바 감추기 */
            border-left: 1px solid gray;
        }

        #chatting::-webkit-scrollbar {
            display: none; /* Chrome, Safari, Opera에서 기본 스크롤바 감추기 */
        }

        .chatroom {
            height: 100px; /* 적절한 높이 설정 */
            border-bottom: 1px solid #ccc; /* 채팅방 간 구분선 설정 */
        }

        .chatroom:last-child {
            border-bottom: none; /* 마지막 채팅방에는 구분선 제거 */
        }
    </style>
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
    
    <div id="body">
        <div id="chat">
            <div id="chatrooms">
                <div class="chatroom">
					<!-- 채팅방이 계속 로딩되야 함 -->
					<!-- 마지막 메시지 시간 순서에 따라 배치해야 됨 -->
					<!-- 상대가 메시지를 보내면 채팅방이 생성되야 됨 -->
					<!-- 안읽은 메시지 개수를 띄워야 함 -->
					<!-- 클릭하면 채팅방 메시지 내역을 바로 가져와야 됨 -->
					<!-- 메시지가 읽음 상태로 변경 -->
                </div>
                <div class="chatroom">

                </div>
                <div class="chatroom">

                </div>
                <div class="chatroom">

                </div>
                <div class="chatroom">

                </div>
                <div class="chatroom">

                </div>
                <div class="chatroom" style="background-color: red">

                </div>
            </div>
            <div id="chatting">
                
            </div>
        </div>
    </div>    
    
    
   <script type="text/javascript">
		var socket = null
		$(document).ready(function() {
			connectWS()
			showList()
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
					$("#chatting").html(toHtml(result))	//result는 서버가 전송한 데이터
				},
				error: function() { alert("error") }		//에러가 발생할때, 
	    	})	
		}
		var loginId = loginId
		let toHtml = function(chats) {
			let tmp = ''
			chats.forEach(function(chat) {
				tmp += '<p>' + chat.sender_id + ':' + chat.content
				tmp += '</p>'
			})	
			
			return tmp
		}
    </script>    
</body>
</html>