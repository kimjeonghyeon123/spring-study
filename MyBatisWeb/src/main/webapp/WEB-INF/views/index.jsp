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
    <script type="text/javascript">
		var socket = null
		$(document).ready(function() {
			connectWS()
		})	
		
		function connectWS() {
			var ws = new WebSocket("ws://localhost/korea/replyEcho")
			socket = ws
			
			ws.onopen = function() {
				console.log('Info: connection opened.')
			}
			
			ws.onmessage = function(event) {
				console.log("ReceiveMessage:", event.data+'\n')
				let socketAlert = $('div#socketAlert')
				socketAlert.text(event.data)
				socketAlert.css('display', 'block')
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































