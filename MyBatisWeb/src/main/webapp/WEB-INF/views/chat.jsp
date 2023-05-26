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
            display: flex;
            justify-content: space-between;
        }

        .chatroom:last-child {
            border-bottom: none; /* 마지막 채팅방에는 구분선 제거 */
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
            <div id="chatting">
                
            </div>
        </div>
    </div>
<script type="text/javascript">
    let loginId = '${loginId}'

    $(document).ready(function(){
        showList(loginId)
    })

    let showList = function(login_id) {
        $.ajax({
            type: 'get',
            url: '/korea/chats?login_id='+login_id,
            success: function(result) {
                $('#chatrooms').html(toHtml(result))
            },
            error: function() {alert('error')}
        })
    }

    let toHtml = function(chatrooms) {
        let tmp = ''

        chatrooms.forEach(function(chatroom){
            tmp += '<div class="chatroom">'
            tmp += '<div><p>' + chatroom.other_id + '</p><br><p>' + chatroom.recent_id + ': ' + chatroom.recent_chat + '</p></div>'
            tmp += '<div><p>' + formatRegDate(chatroom.recent_date) + '</p><br><p>'
            if(chatroom.recent_id !== loginId) {
                tmp += chatroom.unread_cnt
            }
            tmp += '</p></div></div>'
        })

        return tmp
    }

    let formatRegDate = function(regDate) {
        let currentDate = new Date(); // 현재 날짜와 시간
        let inputDate = new Date(regDate); // 입력된 날짜와 시간을 JavaScript의 Date 객체로 변환

        // 현재 날짜와 입력된 날짜의 차이를 계산합니다.
        let timeDiff = currentDate.getTime() - inputDate.getTime();
        let daysDiff = Math.floor(timeDiff / (1000 * 3600 * 24)); // 일 단위로 변환

        if (daysDiff > 0) {
            // 전날인 경우 월일을 표시합니다.
            let month = inputDate.getMonth() + 1; // getMonth()의 반환값은 0부터 시작하므로 1을 더해줍니다.
            let day = inputDate.getDate();
            return month + '월 ' + day + '일';
        } else {
            // 같은 날인 경우 시간을 표시합니다.
            let hours = inputDate.getHours();
            let minutes = inputDate.getMinutes();
            let period = hours >= 12 ? '오후' : '오전';

            // 12시간 단위로 변환
            hours = hours % 12;
            hours = hours === 0 ? 12 : hours; // 0시일 경우 12로 표시합니다.

            // 분이 한 자리 수인 경우 앞에 0 추가
            minutes = minutes < 10 ? '0' + minutes : minutes;

            return period + ' ' + hours + ':' + minutes;
        }
    };
</script>  
</body>
</html>










