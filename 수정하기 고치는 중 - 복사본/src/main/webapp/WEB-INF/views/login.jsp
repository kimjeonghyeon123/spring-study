<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="loginout" value="${sessionScope.member==null ? 'Login' : 'Logout' }" />
<c:set var="loginoutlink" value="${sessionScope.member==null ? '/login' : '/logout' }" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="resources/css/login.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
    <script src="/resources/script/login.js"></script>  
    <script
		  src="https://code.jquery.com/jquery-3.4.1.js"
		  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
		  crossorigin="anonymous">
	</script>  
	<script language="javascript">
        function showPopup() { window.open("/withdang/agreement", "회원가입약관", "width=600, height=850, left=350px, top=125px")}
    </script>  
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>
	
    <title>Login</title>
</head>

<body>
    <div class="container">
        <div class="orangeBg">
            <div class="box signin">
                <h2>이미 계정이 있습니다.</h2>
                <button class="signinbtn">로그인</button>
            </div>
            <div class="box signup">
                <h2>간편 회원 가입</h2>
                <button class="signupbtn">회원가입</button>
            </div>
        </div>
        <div class="form-box">
            <div class="form signinform">
                <form id="login_form" method="post">
                    <h3>로그인</h3>
                    <input type="text" name="user_email" placeholder="Email" value="${cookie.email.value }" autofocus>
                    <input id="pwd" type="password" name="user_pw" placeholder="비밀번호">
                    <c:if test = "${result == 0 }">
                    	<div class = "login_warn">이메일 또는 비밀번호를 잘못 입력하셨습니다.</div>
                    </c:if>
                    <div>
                    	<div id="label"><input id="chk" type="checkbox" name="rememberEmail" value="on" ${empty cookie.email.value ? "" : "checked"} />이메일 기억</div>
                    	<input type="button" class="login_button" value="로그인">
                    	<!-- <button type="button" class="login_button" value="로그인">로그인</button> -->
                    </div>
                    <div>
                    	<span><a href="/withdang/emailFind" >이메일 찾기</a></span> |
                    	<span><a href="/withdang/pwdFind" >비밀번호 찾기</a></span>
                    </div>
                </form>
            </div>
            <div class="form signupform">
                <form id="join_form" method="post">
                    <h3>회원가입</h3>
                    <input type="text"  class="input_name" name="user_name" placeholder="이름">
                    <span class="final_name_ck">이름을 입력해 주세요</span>
                    <input type="text"  class="input_nickname" name="user_nickname" placeholder="닉네임 최대 6자">
                    <span class="user_nickname_re_1">사용 가능한 닉네임입니다</span>
                    <span class="user_nickname_re_2">닉네임이 이미 존재합니다</span>
                    <span class="final_nickname_ck">닉네임을 입력해 주세요</span>
                    <span class="nickname_length_ck">닉네임은 최대 6자까지 가능합니다.</span>
                    <input type="email" class="input_email" name="user_email" placeholder="Email">
                    <span class="user_email_re_1">사용 가능한 이메일입니다</span>
					<span class="user_email_re_2">이메일이 이미 존재합니다</span>
					<span class="user_email_re_3">이메일 형식이 맞지 않습니다.</span>
					<span class="final_email_ck">이메일을 입력해 주세요</span>
					<sapn class="mail_input_box_warn"></sapn>
                    <input type="password" class="input_pw"  name="user_pw" placeholder="비밀번호">
                    <span class="final_pw_ck">비밀번호를 입력해 주세요</span>
                    <input id="pwdck" type="password" class="input_pwck" name="user_pw2" placeholder="비밀번호 재확인" ><span id ="confirmMsg"></span>
                    <span class="final_pwck_ck">비밀번호 확인을 입력해 주세요</span>
                    <span class="pwck_input_re_1">비밀번호가 일치합니다.</span>
                	<span class="pwck_input_re_2">비밀번호가 일치하지 않습니다.</span>
                	<div id="register-policy">
                		<input type="checkbox" id="register-check" name="register-check" value="T" onchange="if (this.checked) {showPopup();}" />회원가입 약관에 동의합니다.
                	</div>
                	<input type="button" class="btn" value="가입하기">
                    </form>
                    <div id="return" class="return-main"><a href="/withdang">메인화면으로 돌아가기</a></div>
            </div>
        </div>
    </div>
    
   <script>
    	const signin = document.querySelector(".signinbtn");
        const signup = document.querySelector(".signupbtn");
        const formbox = document.querySelector(".form-box");
        const body = document.querySelector("body");
        signup.onclick = function(){
            formbox.classList.add("active")
            body.classList.add("active")
        }
        signin.onclick = function(){
            formbox.classList.remove("active")
            body.classList.remove("active")
        }
        
        /* 유효성 검사 통과유무 변수 */
	    var emailCheck = false;            // 이메일
	    var emailckCheck = false;            // 이메일 중복 검사
	    var pwCheck = false;            // 비번
	    var pwckCheck = false;            // 비번 확인
	    var pwckcorCheck = false;        // 비번 확인 일치 확인
	    var nameCheck = false;            // 이름
	    var nickNameCheck = false;		 // 닉네임
	    var nickNameLenck = false;		// 닉네임 글자수 체크
	    var nickNameckCheck = false;	// 닉네임 중복
        var pwdCheck = false;			// 비번 정규식 확인	
      	var boxCheck = false;			//약관 체크박스 확인

        $(document).ready(function(){
        	//회원가입 버튼(회원가입 기능 작동)
        	$(".btn").click(function(){
        		
        /* 입력값 변수 */
        var email = $('.input_email').val();          // 이메일 입력란
        var pw = $('.input_pw').val();                // 비밀번호 입력란
        var pwck = $('.input_pwck').val();            // 비밀번호 확인 입력란
        var name = $('.input_name').val();            // 이름 입력란
        var nickname = $('.input_nickname').val();			  // 닉네임 입력란     
        var nickNameLen = $('.input_nickname').val().length;	// 닉네임 길이
        var pwdCheck = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;	// 비밀번호 정규식
        var warnMsg = $(".mail_input_box_warn");    // 이메일 입력 경고글
        var checked = $("#register-check").is(":checked");		// 체크박스 체크 확인

           /* 이메일 유효성검사 */
           if(email == ""){
               $('.final_email_ck').css('display','block');
               $('.user_email_re_3').css("display","none");
               emailCheck = false;
           }else{
               $('.final_email_ck').css('display', 'none');
               emailCheck = true;
           }
        
         /*	비밀번호 유효성 검사 */
          if(pw == ""){
              $('.final_pw_ck').css('display','block');
              pwCheck = false;
          }else{
              $('.final_pw_ck').css('display', 'none');
              pwCheck = true;
			
              
  			/* 비밀번호 정규식 검사 */
            if (!pwdCheck.test(pw) && !pwdCheck.test(pwck)) {
            	swal.fire({
	     			text: '비밀번호는 최소 8 자, 최소 하나의 문자+하나의 숫자 및 하나의 특수 문자 조합으로 사용해야 합니다.',
	     			icon: 'warning',
	     			confirmButtonText: '확인',
	     		});
        	    /* alert("비밀번호는 최소 8 자, 최소 하나의 문자+하나의 숫자 및 하나의 특수 문자 조합으로 사용해야 합니다."); */
        	    pw.focus
        	    pwdCheck = false;
        	  } else {
        		  pwdCheck = true;
        	  }
                
          }

          /* 약관 동의 체크 */
	     	if(!checked) {
	     		swal.fire({
	     			text: '회원가입 약관에 동의해주세요.',
	     			icon: 'warning',
	     			confirmButtonText: '확인',
	     		});
	     		/* alert("회원가입 약관에 동의해주세요.") */
	     		boxCheck = false;
	     	} else {
	     		boxCheck = true;
	     	}


          /* 비밀번호 확인 유효성 검사 */
          if(pwck == ""){
              $('.final_pwck_ck').css('display','block');
              pwckCheck = false;
          }else{
              $('.final_pwck_ck').css('display', 'none');
              pwckCheck = true;
          }
          
          /* 이름 유효성 검사 */
          if(name == ""){
              $('.final_name_ck').css('display','block');
              nameCheck = false;
          }else{
              $('.final_name_ck').css('display', 'none');
              nameCheck = true;
          }
          
          /* 닉네임 유효성 검사 */
          if(nickname == ""){
              $('.final_nickname_ck').css('display','block');
              $('.user_nickname_re_1').css("display", "none");
              nickNameCheck = false;
          }else{
              $('.final_nickname_ck').css('display', 'none');
              $('.nickname_length_ck').css('display','none');
              nickNameCheck = true;
              nickNameLenck = true;
          }
          
          if(nickNameLen > 6) {
				$('.user_nickname_re_1').css("display", "none");
				$('.user_nickname_re_2').css("display", "none");
				$('.nickname_length_ck').css('display',"block");
				nickNameLenck = false;
			} else {
          		nickNameLenck = true;
			}
          
          /* 최종 유효성 검사 */
          if(emailCheck&&emailckCheck&&pwCheck&&pwckCheck&&
        		  pwckcorCheck&&nameCheck&&pwdCheck&&nickNameCheck&&
        		  nickNameckCheck&&boxCheck&&nickNameLenck){
   		
          	$("#join_form").attr("action", "/withdang/join");
      		$("#join_form").submit();
          }    
          	return false;
        		
      });
 });
      	
      	
   		//이메일 형식&중복 검사
		$('.input_email').on("propertychange change keyup paste input", function(){

		/* console.log("keyup 테스트"); */ 	
		var user_email = $('.input_email').val();			// .input_email에 입력되는 값
		var data = {user_email : user_email}				// '컨트롤에 넘길 데이터 이름' : '데이터(.input_email에 입력되는 값)'

		$.ajax({
		type : "post",
		url : "/withdang/emailCheck",
		data : data,
		 success : function(result){
			 if(result == 'success'){
				$('.user_email_re_1').css("display","inline-block");
				$('.user_email_re_2').css("display", "none");	
				$('.user_email_re_3').css("display","none");
				$('.final_email_ck').css("display", "none");
				emailckCheck = true;
				} else if(result == 'fail') {
					$('.user_email_re_2').css("display","inline-block");
					$('.user_email_re_1').css("display", "none");
					$('.user_email_re_3').css("display","none");
					$('.final_email_ck').css('display', 'none');
					emailckCheck = false;
				} else {
					$('.user_email_re_3').css("display","inline-block");
					$('.user_email_re_2').css("display","none");
					$('.user_email_re_1').css("display", "none");	
					$('.final_email_ck').css('display', 'none');
					emailckCheck = false;
				} 
				
			}// success 종료
	}); // ajax 종료	

 });// function 종료
 

		//닉네임 중복검사
		$('.input_nickname').on("propertychange change keyup paste input", function(){	
			var user_nickname = $('.input_nickname').val();			// .input_nickname에 입력되는 값
			var data = {user_nickname : user_nickname}				// '컨트롤에 넘길 데이터 이름' : '데이터(.input_nickname에 입력되는 값)'
				
			$.ajax({
			type : "post",
			url : "/withdang/nickNameCheck",
			data : data,
			 success : function(result){
				 if(result != 'fail'){
					$('.user_nickname_re_1').css("display","inline-block");
					$('.user_nickname_re_2').css("display", "none");
					$('.final_nickname_ck').css('display', 'none');
					$('.nickname_length_ck').css("display","none");
					nickNameckCheck = true;
					} else {
						$('.user_nickname_re_2').css("display","inline-block");
						$('.user_nickname_re_1').css("display", "none");	
						$('.final_nickname_ck').css('display', 'none');
						$('.nickname_length_ck').css("display","none");
						nickNameckCheck = false;
					} 
					
				}// success 종료
			}); // ajax 종료	
	
	});// function 종료
   	
	//input_pw on keyup시 final_pw_ck 안보이기
	$('.input_pw').on("propertychange change keyup paste input", function() {
		var pw = $('.input_pw').val();
		if(pw != "") $('.final_pw_ck').css('display', 'none');
	})
	
   	/* 비밀번호 확인 일치 유효성 검사 */
   	$('.input_pwck').on("propertychange change keyup paste input", function(){
   	        
   		var pw = $('.input_pw').val();
   	    var pwck = $('.input_pwck').val();	    
   	    
   	    if(pwck != "") { 								
   	    	$('.final_pwck_ck').css('display', 'none');
   	    	
   	    	if(pw == pwck){
   	         	$('.pwck_input_re_1').css('display','block');
   	         	$('.pwck_input_re_2').css('display','none');
   	         	pwckcorCheck = true;
   	     	}else{
   	     		$('.pwck_input_re_2').css('display','block');
   	        	$('.pwck_input_re_1').css('display','none');       	
   	         	pwckcorCheck = false;
   	     }         
   	    	
   	    }
   	});
	
	//input_name on keyup시 final_name_ck 안보이기
	$('.input_name').on("propertychange change keyup paste input", function() {
		var name = $('.input_name').val();            // 이름 입력란
		if(name != "") $('.final_name_ck').css('display', 'none');
	});

    $(".login_button").click(function(){
    	
    	/* 로그인 메서드 서버 요청 */
        $("#login_form").attr("action", "/withdang/login");
        $("#login_form").submit(); 
        
    });
    
    
	
	
  </script>
  
  <script type="text/javascript">
  	$(document).ready(function() {
  		let msg = "${msg}"
  		
  			if(msg=="pwUdate") {
  				Swal.fire({
  				  text: '비밀번호가 변경되었습니다.',  // Alert 내용
  				  icon: 'success',                         // Alert 타입
  				  confirmButtonText: '확인',
  				});
  			}
  			
  			if(msg=="joinOK") {
  				Swal.fire({
  				  text: '회원가입이 완료되었습니다.',  // Alert 내용
  				  icon: 'success',                         // Alert 타입
  				  confirmButtonText: '확인',
  				});
  			}
	})
  
  </script>
        
</body>

</html>