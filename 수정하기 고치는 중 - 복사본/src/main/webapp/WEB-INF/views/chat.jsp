<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="loginEmail" value="${sessionScope.email}" />
<c:set var="loginNickname" value="${sessionScope.nickname}" />
<c:set var="loginout" value="${sessionScope.email==null ? 'Login' : 'Logout'}" />
<c:set var="loginoutlink" value="${sessionScope.email==null ? '/login/login' : '/login/logout'}" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <style type="text/css">
    
  #container{
    box-sizing:border-box;
    width:800px;
    height:700px;
    background:#f0e7e9;
    margin:0 auto;
    margin-top: 20px;
    font-size:0;
    border-radius:10px;
    overflow:hidden;
    align-items: center;
  }
  #chat-list{
    width:300px;
    height:800px;
    background-color:#e5bac2;
    
    display:inline-block;
    font-size:15px;
    vertical-align:top;
    text-align: left;
  }
  #chattings{
    width:500px;
    height:800px;
    display:inline-block;
    font-size:15px;
    vertical-align:top;
  }
  
  #chat-list header{
    padding:20px 20px;
  }
  #chat-list #search-box {
    width:130px;
    height:40px;
    line-height:50px;
    padding:0 50px 0 20px;
    background-color:#f0e7e9;
    border:none;
    border-radius:3px;
    color:#2b2b2b;
    background-repeat:no-repeat;
    background-position:170px;
    background-size:40px;
  }
  #search-btn {
     width:50px;
     height: 37px;
     border: none;
     color: #fff;
     background-color: #9d8db3;    
     margin: 3px;
     border-radius: 10px;
  }
  #chat-list input::placeholder{
    color:#696969;
    font-size: 12px;
  }
  #chat-list ul{
    padding-left:0;
    margin:0;
    list-style-type:none;
    height:690px;
  }
  #chat-list li{
    padding:5px 0;
    height: 50px;
    padding-left: 30px;
    background-color:#9d8db3;
    border-bottom: 2px solid #ffffff5b;
    border-radius: 10px;


  }
  #chat-list li:hover{
    background-color:#9385a5;
  }
  
  .new-msg{
    width:15px;
    height:15px;
    border-radius:50%;
    display:inline-block;
    background-color:tomato;
    font-size: 12px;
    margin-left:4px;
    color: #ffffff;
    text-align: center;

  }
  #chat-header {
    height:10px;
    padding:20px 20px 20px 50px;
    font-weight: bold;
    display: flex;
     justify-content: space-between;
     align-items: center;
    
  }

/* 
	-----------------------------------------------------------
	-----------------------------------------------------------
	-----------------------------------------------------------
	-----------------------------------------------------------
*/
#chatrooms {
	overflow-y:scroll;
	scrollbar-width: thin;
	scrollbar-color: #888 #f9f9f9;
}
  
#chatrooms::-webkit-scrollbar {
    width: 6px;
}

#chatrooms::-webkit-scrollbar-track {
    background: #f9f9f9;
}

#chatrooms::-webkit-scrollbar-thumb {
    background-color: #888;
    border-radius: 3px;
}
/* 
	-----------------------------------------------------------
	-----------------------------------------------------------
	-----------------------------------------------------------
	-----------------------------------------------------------
*/

  #chat{
    padding-left:0;
    margin:0;
    list-style-type:none;
    overflow-y:scroll;
    height:500px;
    border-top:2px solid #fff;
    border-bottom:2px solid #fff;
    padding:10px 30px;

  }
  #chat-time {
    color:#bbb;
    font-size:13px;
    display:inline-block;
    margin-left: 5px;
  }
  .read-status {
    color:#9d8db3;
    font-size:10px;
     margin: 5px;
  }
  #chat-time-list {
     color: #f2f2f2;
     font-size: 12px;
     margin-left: 120px;
     font-weight: normal;
  }
  #chat_other_id {
    font-weight: bold;
    color: #333;
    margin-right: 10px;
  }
  #chat .message{
    padding:10px;
    color:#fff;
    font-size: 14px;
    font-weight: bold;
    line-height:25px;
    max-width:90%;
    display:inline-block;
    text-align:left;
    border-radius:5px;
  }
  #chat #chat-right{
    text-align:right;
  }
  #chat #chat-left .message{
    background-color:#9d8db3;
  }
  #chat #chat-right .message{
    background-color:#ec98a8;
  }
  
  #chat-footer{
    width: 100%;
    height: 100px;
    display: flex;
    padding: 20px 20px 10px 20px;
    
  }
  #send-btn {
    border: none;
    background-color: #ed8b9e;
    font-weight: bold;
    color: #fff;
    width: 80px;
    height: 80px;
    border-radius: 0px 8px 8px 0px;
    margin: 0px;
  }
  #msg-input{
    border:none;
    display:block;
    width:70%;
    height:60px;
    border-radius:8px 0px 0px 8px ;
    padding:10px;
    font-size:13px;
    margin-bottom:0px;
  }
  #msg-input::placeholder{
    color:#cccccc;
  }
  #user-name {
    color: #fff;
    font-weight: bold;
    margin-top:7px;
    
  }
  #last-msg {
     margin-top: 15px;
     font-size: 14px;
     font-weight: normal;
     width: 140px;
  }
  #close-btn {
       margin-left: auto;
       border: none;
       background-color: #383838;
       color: #fff;
       font-weight: bold;
     
  }
    </style>
    <title>chat</title>
</head>
<body>
      <jsp:include page="header.jsp"></jsp:include>
    
    <!-- 채팅 창 ----------------------------->
    <div id="container">
        <aside id="chat-list">
          <header>
            <input id="search-box" type="text" placeholder="닉네임검색">
            <button id="search-btn" type="button">검색</button>
          </header>
          <ul id="chatrooms">
          </ul>
        </aside>
        
        <div id="chattings">
          <div id="chat-header">
            <div id="chat-other_email"></div>
            <button type="button" id="close-btn" style="display: none">나가기</button>
          </div>
          <ul id="chat"></ul>
          <div id="chat-footer">
            <textarea id="msg-input" placeholder="메세지를 입력하세요."></textarea>
            <button id="send-btn">전송</button>
          </div>
        </div>
      </div>

<script type="text/javascript">
   let login_nickname = '${loginNickname}'
   let chatroom_id = '${chatroom_id}'
   let other_nickname = '${other_nickname}'

   let socket = null
   let recent_nickname = null
   let unread_cnt = null
   let cnt = null
   
   let msg = '${msg}'
   if(msg == "CHAT_EXIST") {
	   alert("채팅방이 존재합니다.")
   }
      
   $(document).ready(function(){
      connectWS()
      showListOnly()
      
      if(login_nickname === "") {
    	location.href="/withdang/login"
      }
      
      if(other_nickname != "" && msg != "CHAT_EXIST") {
         $('#chat-other_email').html(other_nickname + "님과의 대화")
      }
      else {
          $('#msg-input').attr('disabled', true) // textarea 비활성화
          $('#send-btn').attr('disabled', true)
      }
      
      $("#chatrooms").on('click', '.chatroom', function(){
          chatroom_id = $(this).attr('data-chatroom_id')
          other_nickname = $(this).attr('data-other_nickname')
          recent_nickname = $(this).attr('data-recent_nickname')
          unread_cnt = $(this).attr('data-unread_cnt')
          
          console.log('chatroom_id : ', chatroom_id)
          console.log('other_nickname : ', other_nickname)
          
          showChattingAndListwithsocket(chatroom_id)
          $('#chat-other_email').html(other_nickname + "님과의 대화")
          $('#close-btn').attr('style', '')
      
          $('#msg-input').attr('disabled', false) // textarea 비활성화
          $('#send-btn').attr('disabled', false)
      })
   
      $("#send-btn").on('click', function(){
         let chat = $('#msg-input').val()
         
         if ($('#msg-input').val().trim() === '') {
            alert('채팅을 입력해주세요.')
            $('#msg-input').focus()
         }
         else {
            $.ajax({
               type: 'post',
               url: '/withdang/chat/sendchatting',
               headers: { "content-type" : "application/json" },  //요청헤더
               data : JSON.stringify({chatroom_id: parseInt(chatroom_id), login_nickname: login_nickname, other_nickname: other_nickname, chat: chat}),  //서버로 전송할 데이터, stringify()로 직렬화 필요
               success: function(result) {
                  chatroom_id = String(result)
                  sendChatting(chatroom_id)
                  $('#msg-input').val('')
               },
               error: function() {alert('error')}
            })
         }
       })
       
      $("#close-btn").on('click', function(){
         $.ajax({
            type: 'post',
            url: '/withdang/chat/deletechatroom',
            headers: { "content-type" : "application/json" },  //요청헤더
            data : JSON.stringify({chatroom_id: parseInt(chatroom_id), login_nickname: login_nickname}),  //서버로 전송할 데이터, stringify()로 직렬화 필요
            success: function(result) {
            	showListOnly()
                chatroom_id = ''
            	other_nickname = ''
            	$('#msg-input').attr('disabled', true) // textarea 비활성화
                $('#send-btn').attr('disabled', true)
                $('#chat').html('')
                $('#chat-other_email').html('')
          		$('#close-btn').attr('style', 'display: none')
            },
            error: function() {alert('error')}
         })
       })        
   })       
   
    let showList = function() {
       return new Promise(function(resolve, reject){
           $.ajax({
               type: 'post',
               url: '/withdang/chat/showchatrooms',
               headers: { "content-type" : "application/json" },  //요청헤더
            data : JSON.stringify({login_nickname: login_nickname}),  //서버로 전송할 데이터, stringify()로 직렬화 필요
            success: function(result) {
               $('#chatrooms').html(toHtml(result))
                   resolve()
               },
               error: function(error) {reject(error)}
           })
       })
    }
   
   let toHtml = function(chatrooms) {
       let tmp = ''

       chatrooms.forEach(function(chatroom) {
           tmp += '<li class="chatroom" data-chatroom_id="' + chatroom.id + '" data-other_nickname="' + chatroom.other_nickname + '" data-recent_nickname="' + chatroom.recent_nickname + '" data-unread_cnt="' + chatroom.unread_cnt + '">'
           tmp += '<div id="user-name">' + chatroom.other_nickname

           tmp += '<span id="chat-time-list">' + formatRegDate(chatroom.recent_date) + '</span>'
           if (chatroom.recent_nickname !== login_nickname && chatroom.unread_cnt !== 0) {
               tmp += '<span class="new-msg">' + chatroom.unread_cnt + '</span>'
           }

           
           var recentChat = chatroom.recent_chat;
           var truncatedChat = recentChat.length > 18 ? recentChat.substring(0, 18) + '...' : recentChat;
           tmp += '<br><span id="last-msg">' + truncatedChat + '</span></div></li>'


       })

       return tmp
   }
    
    let showChattingList = function(chatroom_id) {
       return new Promise(function(resolve, reject){ 
          $.ajax({
             type: 'post',
             url: '/withdang/chat/showchattings',
             headers: { "content-type" : "application/json" },  //요청헤더
             data : JSON.stringify({chatroom_id:parseInt(chatroom_id), login_nickname: login_nickname, other_nickname: other_nickname}),
             success: function(result) {
                $('#chat').html(chatHtml(result))
                $('#chat-other_email').html(other_nickname + '님과의 대화')
                var chattingDiv = document.getElementById('chat')
                chattingDiv.scrollTop = chattingDiv.scrollHeight
                resolve()
             },
             error: function(error) {reject(error)}
          })
       })
    }
    
    let chatHtml = function(chattings) {
        let tmp = ''

        chattings.forEach(function(chatting){
           if(chatting.sender_nickname === login_nickname) {
               tmp += '<li id="chat-right">'
               tmp += '<div id="chat-right-user">'
               tmp += '<p id="chat-time">' + formatRegDate(chatting.chat_date) + '</p></div>'
               if(chatting.check_read === false) {
                  tmp += '<span class="read-status">안읽음</span>'
               }
               tmp += '<div class="message">' + chatting.chat + '</div>'
           }            
           else {
              tmp += '<li id="chat-left">'
              tmp += '<div id="chat_other_id">' + chatting.sender_nickname
              tmp += '<p id="chat-time">' + formatRegDate(chatting.chat_date) + '</p></div>'
              tmp += '<div class="message">' + chatting.chat + '</div>'
           }
            tmp += '</li>'
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
   
    async function showListOnly() {
       try {
           await showList()
       } catch (error) {
          alert('error')
       }
    }          
    
    async function sendChatting(chatroom_id) {
       try {
          await showChattingList(chatroom_id)
          await showList()

         if(socket) {
               let socketMsg = "sendchat," + other_nickname + "," + chatroom_id
               socket.send(socketMsg)
            }
       } catch(error){
          alert('error')
       }
    }   
    
    async function showChattingAndList(chatroom_id) {
       try {
          await showChattingList(chatroom_id)
          await showList()
       } catch(error){
          alert('error')
       }
    }
    
    async function showChattingAndListwithsocket(chatroom_id) {
       try {
          await showChattingList(chatroom_id)
          await showList()
          
          if(socket) {
             let socketMsg = "readchat," + other_nickname + "," + chatroom_id
             socket.send(socketMsg)
           }
       } catch(error){
          alert('error')
       }
    }
    
   function connectWS() {
      var ws = new WebSocket("ws://43.201.236.183:8080/withdang/replyEcho")
      //var ws = new WebSocket("ws://localhost/withdang/replyEcho")
      socket = ws
      
      ws.onopen = function() {
         console.log('Info: connection opened.')
      }
      
      ws.onmessage = function(event) {
         console.log("ReceiveMessage:", event.data+'\n')
         
          let message = JSON.parse(event.data)
          let cmd = message.cmd
          
          if (cmd === "sendchat") {
             if(String(message.chatroom_id) === chatroom_id) {
                showChattingAndListwithsocket(chatroom_id)
             }
             else {
                showListOnly()
             }
          }
          else if (cmd === "readchat") {
             if(String(message.chatroom_id) === chatroom_id) {
                showChattingAndList(chatroom_id)
             }
             else {
                showListOnly()
             }
          }
      }
      
      ws.onclose = function (event){ 
         console.log('Info: connection closed')   
      }
      ws.onerror = function (err){ console.log('Error: ', err) }
   }
</script>
    
    
</body>
</html>





















