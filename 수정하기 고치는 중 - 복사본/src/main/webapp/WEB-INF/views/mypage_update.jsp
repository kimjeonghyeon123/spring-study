<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<c:set var="loginout" value="${member==null ? 'Login' : 'Logout' }" />
<c:set var="loginoutlink" value="${member==null ? '/login' : '/logout' }" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="resources/css/mypage.css">
    <script src="${path}/resources/script/toggle.js" defer></script>
    
    <script src="https://kit.fontawesome.com/cac1ec65f4.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"
        integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"
        integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ"
        crossorigin="anonymous"></script>
        
    <script
		 src="https://code.jquery.com/jquery-3.4.1.js"
		 integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
		 crossorigin="anonymous"></script>
	
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

    <title>마이페이지</title>
</head>

<body>
    <jsp:include page="header.jsp"></jsp:include>


<br>
    <article id="mypage">
        <aside class="mypage_aside">
            <div class="mypage__profile">
                <div>
                    <img src="resources/image/profile.png" alt="프로필사진">
                </div>
                <p>${member.user_nickname }님 안녕하세요</p>
                <hr class="profile-line">
            <div class="mypage__list">
                <ul>
                    <li><a href="/withdang/mypage">내 정보</a></li>
                    <li><a href="/withdang/mypage_chat">채팅</a></li>
                    <li><a href="/withdang/myDangguen">마이댕근</a></li>
                    <li><a href="/withdang/myCare">마이케어</a></li>
                </ul>
                </div>
            </div>
        </div>
        </aside>
        	<form id="update_form" class="mypage-user" method="post">
                <ul>
                    <p class="user-info"> 내 정보</p>
                    <div>이름 : <input name="user_name" value="${member.user_name }" readonly="readonly"></div>
                    <div>이메일 : <input name="user_email" value="${member.user_email }" readonly="readonly"></div>
                    <div><p>닉네임 : <input type="text" name="user_nickname" value="${member.user_nickname }"></p></div>
                    <div><p>연락처 : <input type="text" name="user_pnum" value="${member.user_pnum }"></p></div>
                    <div><p>생일 : <input type="date" name="user_birth" value="<fmt:formatDate value="${member.user_birth }" pattern="yyyy-MM-dd"/>" /></p></div> 
                    <div>성별:
                    	<select class="form-select" name="user_gender">
	                    	<option value="" selected disabled hidden>성별</option>
	                    	<option value="남자" ${member.user_gender=='남자' ? "selected" : "" }>남자</option>
	                    	<option value="여자" ${member.user_gender=='여자' ? "selected" : "" }>여자</option>
                    	</select>
                   	</div>
                    <div class="address_wrap">
						<div class="address_name">주소</div>
						<div class="address_input_1_wrap">
							<span class="address_input_1_box">
								<input class="address_input_1" name="user_address" value="${member.user_address }" readonly="readonly">
							</span>
							<span class="address_button" onclick="execution_daum_address()">
								<span><button type="button" class="address_button">주소 찾기</button></span>
							</span>
							<!-- </div> -->
							<div class="clearfix"></div>
						</div>
							<div class ="address_input_2_wrap">
								<div class="address_input_2_box">
									<input class="address_input_2" name="user_dtl_address" value="${member.user_dtl_address }" readonly="readonly">
								</div>
							</div>
					</div>      
                </ul>
            <hr />
            <div class="mypage-dog">
                <ul>
                    <p class="dog-info">강아지 정보</p>
                    <p>이름 : <input type="text" name="dog_name" value="${dvo.dog_name }"></p>
                    <p>생일 : <input type="date" name="dog_birth" value="<fmt:formatDate value="${dvo.dog_birth }" pattern="yyyy-MM-dd"/>" /></p>
                    <p>성별 : 
                    <select id="gender-check" class="form-select" name="dog_gender">
                    	<option value="" selected disabled hidden>성별</option>
                    	<option value="수컷" ${dvo.dog_gender=='수컷' ? "selected" : "" }>수컷</option>
                    	<option value="암컷" ${dvo.dog_gender=='암컷' ? "selected" : "" }>암컷</option>
                    </select>
                    </p>
                    <p>중성화 : 
                    <select class="form-select" name="dog_nutd">
                    	<option value="" selected disabled hidden>중성화</option>
                    	<option value="했음" ${dvo.dog_nutd=='했음' ? "selected" : "" }>했음</option>
                    	<option value="안함" ${dvo.dog_nutd=='안함' ? "selected" : "" }>안함</option>
                    </select>
                    </p>
                    <p>견종 : <input type="text" name="dog_breed" value="${dvo.dog_breed }"></p>
                    <p>동물등록번호 : <input type="text" name="dog_regnum" value="${dvo.dog_regnum }"></p>
                    <p>특징 : </p><textarea name="dog_feature">${dvo.dog_feature }</textarea>
                </ul>
            </div>
        	</form>
    </article>
    <footer>
        <div class="mypage__update">
            <br>
            <button type="button" class="update_btn" value="수정하기">수정하기</button>
        </div>
    </footer>
    <br><br><br><br>
    
    <script type="text/javascript">

    	 /* 회원 정보 수정 클릭 메서드 */
	    $(".update_btn").click(function(){
	    	/* 업데이트 메서드 서버 요청 */
	        $("#update_form").attr("action", "/withdang/mypage_update").submit();
	    });
    
	    /* 다음 주소 연동 */
	    function execution_daum_address(){

	    	new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.

	            	// 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var addr = ''; // 주소 변수
	                var extraAddr = ''; // 참고항목 변수

	                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                    addr = data.roadAddress;
	                } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                    addr = data.jibunAddress;
	                }

	                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	                if(data.userSelectedType === 'R'){
	                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                        extraAddr += data.bname;
	                    }
	                    // 건물명이 있고, 공동주택일 경우 추가한다.
	                    if(data.buildingName !== '' && data.apartment === 'Y'){
	                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                    }
	                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                    if(extraAddr !== ''){
	                        extraAddr = ' (' + extraAddr + ')';
	                    }
	                 	// 주소변수 문자열과 참고항목 문자열 합치기
	                    addr += extraAddr;

	                } else {
	                	addr += ' ';
	                }

	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	               	$(".address_input_1").val(addr);
            		//$("[name=memberAddr1]").val(data.zonecode);    // 대체가능
	            	// 상세주소 입력란 disabled 속성 변경 및 커서를 상세주소 필드로 이동한다.
	               	$(".address_input_2").attr("readonly",false);
	                $(".address_input_2").focus();

	            }
	        }).open();

	    }

    </script>
</body>

</html>