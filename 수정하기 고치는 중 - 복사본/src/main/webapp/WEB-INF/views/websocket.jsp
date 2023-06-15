<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>

	<div id="notification" class="notification"></div>
	
<script type="text/javascript">
	let socket = null

	$(document).ready(function(){
		connectWS()
	})
   
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
        	  var notification = document.getElementById('notification');
              notification.textContent = message.chatroom_id + '번 방에 채팅이 왔습니다.';
              notification.style.display = 'block';
              
              setTimeout(function() {
                 notification.style.display = 'none';
              }, 3000);
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