<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>쿠키를 이용한 팝업</title>
    <style type="text/css">
    	#popup {
    		position: absolute;
    		top: 300px;
    		left: 10px; 
    		color: yellow;
    		width: 300px;
    		height: 100px;
    		background-color: gray;
    	}
    	div#popup > div {
    		position: relative;
    		background-color: #ffffff;
    		top: 0;
    		border: 1px solid gray;
    		padding: 10px;
    		color: black;
    	}
    </style>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
    <script type="text/javascript">
    	$(function() {
			$('#closeBtn').click(function() {	
				$('#popup').hide();

				let chkVal = $("input:checkbox[id=notopentoday]:checked").val();
				
				if(chkVal == 1) {
					$.ajax({
						url : "./popupCookie.jsp",
						type : 'get',
						data : {notopentoday : chkVal},
						dataType : "text",
						success : function(resData) { // 요청 성공 시
							if (resData != '')      // 응답 데이터가 있다면 
								location.reload();	  // 페이지 새로고침
						}
					})
				}
			})
		})
    </script>
</head>
<body>
	<%
		String popupMode = "on"; // 레이어 팝업창을 띄울지 여부
	
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie c : cookies) {
				String cookieName  = c.getName();
				String cookieValue = c.getValue();
				
				if(cookieName.equals("PopupClose")) {
					popupMode = cookieValue; // popupMode의 값을 업데이트 함
				}
			}
		}
		
		for(int i=0;i<10;i++) {
			out.println("현재 팝업 창은 " + popupMode + "상태입니다.<br>");
		}
		
		if(popupMode.equals("on")) {
		%> 
			<div id="popup">
				<h2 align="center">앱 다운받고 사과시계울트라 받아가자!</h2>
				<div align="right">
					<form action="#" name="popFrm">
						<input type="checkbox" id="notopentoday" value="1">하루동안 열지 않음
						<input type="button" id="closeBtn" value="닫기">
					</form>
				</div>
			</div>
		<%
		}
	%>
</body>
</html>