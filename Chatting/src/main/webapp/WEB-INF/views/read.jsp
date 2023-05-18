<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<input type="text" id="msg" value="1212">
	<button id="btnSend">Send Message</button>
	
	<script type="text/javascript">
		var socket = null
	
		function connect() {
			var ws = new WebSocket("ws://localhost:8080/chatting/replyEcho")
			socket = ws
			
			ws.onopen = function() {
				console.log('Info: connection opened.')
			}
			
			ws.onmessage = function() {
				console.log("ReceiveMessage:", event.data+'\n')
			}
			
			ws.onclose = function (event){ 
				console.log('Info: connection closed') 
				setTimeout(function(){connect()}, 1000)	
			}
			ws.onerror = function (err){ console.log('Error: ', err) }
		}
		
		$(document).ready(function(){
	        $("#btnSend").on('click', function() {
	     		if(socket.readyState !== 1) return
	     		let msg = $("input#msg").val()
	     		socket.send(msg)
	        })
		})
		
		connect()
	</script>
</body>
</html>