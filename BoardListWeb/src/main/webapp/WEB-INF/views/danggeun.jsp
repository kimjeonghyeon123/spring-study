<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="loginEmail" value="${sessionScope.email}" />
<c:set var="loginout" value="${sessionScope.email==null ? 'Login' : 'Logout'}" />
<c:set var="loginoutlink" value="${sessionScope.email==null ? '/login/login' : '/login/logout'}" />

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value='/resources/css/dangguen.css' />">
    <script src="<c:url value='/resources/js/scroll.js' />"></script>
    <script src="<c:url value='/resources/js/toggle.js' />" defer></script>
    <script src="https://kit.fontawesome.com/cac1ec65f4.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js"
        integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js"
        integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ"
        crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css2?family=Gaegu&family=Nanum+Gothic:wght@400;700;800&display=swap"
        rel="stylesheet">

    <title>위드댕</title>
</head>

<body>
    <header>
        <nav class="navbar">

            <div class="navbar__logo">
                <a href="<c:url value='/' />">with DANG</a>
            </div>

            <ul class="navbar__menu">
                <li><a href="main.html">댕댕여지도</a></li>
                <li><a href="<c:url value='/danggeun/list' />">댕근마켓</a></li>
                <li><a href="dangcare.html">댕댕케어</a></li>
                <li><a href="<c:url value='/board/list' />">댕댕커뮤</a></li>
                <li><a href="dangoffice.html">댕사무소</a></li>
                <li><button class="btnLogin"><a href="<c:url value='${loginoutlink}' />">${loginout}</a></button></li>
                <li><a href="mypage.html"><i class="fa fa-user-o" id="btnMypage" aria-hidden="true"></i></a></li>

            </ul>
            <a href="#" class="navbar__toggleBtn">
                <i class="fas fa-bars" aria-hidden="true"></i>
            </a>
        </nav>
    </header>

    <section class="dangguen-sec">
        <h2 class="dangguen-title">댕근마켓</h2>
        <div class="alert alert-light" id="dangguen-head" role="alert">


            <select class="form-select" aria-label="category">
                <optgroup label="카테고리">
                    <option selected>전체</option>
                    <option value="1">사료/간식</option>
                    <option value="2">영양제</option>
                    <option value="3">산책 용품</option>
                    <option value="4">집/방석</option>
                    <option value="5">옷/악세사리</option>
                    <option value="6">위생 용품</option>
                    <option value="7">기타 용품</option>
            </select>

            <select class="form-select" name="addressRegion" id="addressRegion1"></select>
            <select class="form-select" name="addressDo" id="addressDo1"></select>
            <select class="form-select" name="addressSiGunGu" id="addressSiGunGu1"></select>
            <button type="button" class="btn-search"><a href="#">검색</a></button>
            <button type="button" class="btn-write"><a href="dangguenwrite.html">글쓰기</a></button>

        </div>
    </section>

    <script>
        $(function () {
            areaSelectMaker("select[name=addressRegion]");
        });

        var areaSelectMaker = function (target) {
            if (target == null || $(target).length == 0) {
                console.warn("Unkwon Area Tag");
                return;
            }
            /*업데이트 필요!*/
            var area = {
                "서울특별시": {
                    "종로구": ["청운동", "신교동", "궁정동", "효자동", "창성동", "통인동", "누상동", "누하동", "옥인동", "체부동", "필운동", "내자동", "사직동", "도렴동", "당주동", "내수동", "세종로", "신문로1가", "신문로2가", "청진동", "서린동", "수송동", "중학동", "종로1가", "공평동", "관훈동", "견지동", "와룡동", "권농동", "운니동", "익선동", "경운동", "관철동", "인사동", "낙원동", "종로2가", "팔판동", "삼청동", "안국동", "소격동", "화동", "사간동", "송현동", "가회동", "재동", "계동", "원서동", "훈정동", "묘동", "봉익동", "돈의동", "장사동", "관수동", "종로3가", "인의동", "예지동", "원남동", "연지동", "종로4가", "효젣종", "종로5가", "종로6가", "이화동", "연건동", "충신동", "동숭동", "혜화동", "명륜1가", "명륜2가", "명륜4가", "명륜3가", "창신동", "숭인동", "교남동", "평동", "송월동", "홍파동", "교북동", "행촌동", "구기동", "평창동", "부암동", "홍지동", "신영동", "무악동", "청운효자동", "종로1.2.3.4가동", "종로5.6가동", "창신1동", "창신2동", "창신3동", "숭인1동", "숭인2동"],
                    "중구": ["무교동", "다동태평로1가", "을지로1가", "을지로2가", "남대문로1가", "삼각동", "수하동", "장교동", "수표동", "소공동", "남창동", "북창동", "태평로2가", "남대문로2가", "남대문로3가", "남대문로4가", "남대문로5가", "봉래동1가", "봉래동2가", "회현동1가", "회현동2가", "회현동3가", "충무로1가", "충무로2가", "명동1가", "명동2가", "남산동1가", "남산동2가", "남산동3가", "저동1가", "충무로4가", "충무로5가", "인현동2가", "예관동", "묵정동", "필동1가", "필동2가", "필동3가", "남학동", "주자동", "예장동", "장충동1가", "장충동2가", "광희동1가", "광희동2가", "쌍림동", "을지로6가", "을지로7가", "을지로4가", "을지로5가", "주교동", "방산동", "오장동", "을지로3가", "입정동", "산림동", "충무로3가", "초동", "인현동1가", "저동2가", "신당동", "흥인동", "무학동", "황학동", "서소문동", "정동", "순화동", "의주로1가", "충정로1가", "중림동", "의주로2가", "만리동1가", "만리동2가", "회현동", "명동", "필동", "장충동", "광희동", "을지로동", "다산동", "약수동", "청구동", "신당5동", "동화동"],
                    "용산구": ["후암동", "용산동2가", "용산동4가", "갈월동", "남영동", "용산동1가", "동자동", "서계동", "청파동1가", "청파동2가", "청파동3가", "원효로1가", "원효로2가", "신창동", "산천동", "청암동", "원효로3가", "원효로4가", "효창동", "도원동", "용문동", "문배동", "신계동", "한강로1가", "한강로2가", "용산동3가", "용산동5가", "한강로3가", "이촌동", "이태원동", "한남동", "동빙고동", "서빙고동", "주성동", "용산동6가", "보광동", "용산2가", "동청파동", "원효로1동", "원효로2동", "한강로동", "이촌1동", "이촌2동", "이태원1동", "이태원2동"],
                    "성동구": ["상왕십리동", "하왕십리동", "홍익동", "도선동", "마장동", "사근동", "행당동", "응봉동", "금호동1가", "금호동2가", "금호동3가", "금호동4가", "옥수동", "성수동1가", "성수동2가", "송정동", "용답동", "왕십리2동", "왕십리도선동", "행당1동", "행당2동", "금호1가동", "금호2.3가동", "금호4가동", "성수1가1동", "성수1가2동", "성수2가1동", "성수2가3동"],
                    "광진구": ["중곡동", "능동", "구의동", "광장동", "자양동", "화양동", "군자동", "중곡1동", "중곡2동", "중곡3동", "중곡4동", "자양1동", "자양2동", "자양3동", "자양4동", "구의1동", "구의2동", "구의3동"],
                    "동대문구": ["신설동", "용두동", "제기동", "전농동", "답십리동", "장안동", "청량리동", "회기동", "휘경동", "이문동", "용신동", "전농1동", "전농2동", "답십리1동", "답십리2동", "장안1동", "장안2동", "휘경1동", "휘경2동", "이문1동", "이문2동"],
                    "중랑구": ["면목동", "상봉동", "중화동", "묵동", "망우동", "신내동", "면목2동", "면목4동", "면목5동", "면목본동", "면목7동", "면목3.8동", "상봉1동", "상봉2동", "중화1동", "중화2동", "묵1동", "묵2동", "망우본동", "망우3동", "신내1동", "신내2동"],
                    "성북구": ["성북동", "성북동1가", "돈암동", "동소문동1가", "동소문동2가", "동소문동3가", "동소문동4가", "동소문동5가", "동소문동6가", "동소문동7가", "삼선동1가", "삼선동2가", "삼선동3가", "삼선동4가", "삼선동5가", "동선동1가", "동선동2가", "동선동3가", "동선동4가", "동선동5가", "안암동1가", "안암동2가", "안암동3가", "안암동4가", "안암동5가", "보문동4가", "보문동5가", "보문동6가", "보문동7가", "보문동1가", "보문동2가", "보문동3가", "정릉동", "길음동", "종암동", "하월곡동", "상월곡동", "장위동", "석관동", "삼선동", "동선동", "돈암1동", "돈암2동", "안암동", "보문동", "정릉1동", "정릉2동", "정릉3동", "정릉4동", "길음1동", "길음2동", "월곡1동", "월곡2동", "장위1동", "장위2동", "장위3동"],
                    "강북구": ["미아동", "번동", "수유동", "우이동", "삼양동", "송중동", "송천동", "삼각산동", "번1동", "번2동", "번3동", "수유1동", "수유2동", "수유3동", "인수동"],
                    "도봉구": ["쌍문동", "방학동", "창동", "도봉동", "창1동", "창2동", "창3동", "창4동", "창5동", "도봉1동", "도봉2동", "쌍문1동", "쌍문2동", "쌍문3동", "쌍문4동", "방학1동", "방학2동", "방학3동"],
                    "노원구": ["월계동", "공릉동", "하계동", "상계동", "중계동", "월계1동", "월계2동", "월계3동", "공릉1동", "공릉2동", "하계1동", "하계2동", "중계본동", "중계1동", "중계4동", "중계2.3동", "상계1동", "상계2동", "상계3.4동", "상계5동", "상계6.7동", "상계8동", "상계9동", "상계10동"],
                    "은평구": ["수색동", "녹번동", "불광동", "갈현동", "구산동", "대조동", "응암동", "역촌동", "신사동", "증산동", "진관동", "불광1동", "불광2동", "갈현1동", "갈현2동", "응암1동", "응암2동", "응암3동", "신사1동", "신사2동"],
                    "서대문구": ["충정로2가", "충정로3가", "합동", "미근동", "냉천동", "천연동", "옥천동", "영천동", "현저동", "북아현동", "홍제동", "대현동", "대신동", "신촌동", "봉원동", "창천동", "연희동", "홍은동", "북가좌동", "남가좌동", "충현동", "홍제1동", "홍제3동", "홍제2동", "홍은1동", "홍은2동", "남가좌1동", "남가좌2동", "북가좌1동", "북가좌2동"],
                    "마포구": ["아현동", "공덕동", "신공덕동", "도화동", "용강동", "토정동", "마포동", "대흥동", "염리동", "노고산동", "신수동", "현석동", "구수동", "창전동", "상수동", "하중동", "신정동", "당인동", "서교동", "동교동", "합정동", "망원동", "연남동", "성산동", "중동", "상암동", "서강동", "망원1동", "망원2동", "성산1동", "성산2동"],
                    "양천구": ["신정동", "목동", "신월동", "목1동", "목2동", "목3동", "목4동", "목5동", "신월1동", "신월2동", "신월3동", "신월4동", "신월5동", "신월6동", "신월7동", "신정1동", "신정2동", "신정3동", "신정4동", "신정6동", "신정7동"],
                    "강서구": ["염창동", "등촌동", "화곡동", "가양동", "마곡동", "내발산동", "외발산동", "공항동", "방화동", "개화동", "과해동", "오곡동", "오쇠동", "등촌1동", "등촌2동", "등촌3동", "화곡1동", "화곡2동", "화곡3동", "화곡4동", "화곡본동", "화곡6동", "화곡8동", "가양1동", "가양2동", "가양3동", "발산1동", "우장산동", "방화1동", "방화2동", "방화3동"],
                    "구로구": ["신도림동", "구로동", "가리봉동", "고척동", "개봉동", "오류동", "궁동", "온수동", "천왕동", "항동", "구로1동", "구로2동", "구로3동", "구로4동", "구로5동", "고척1동", "고척2동", "개봉1동", "개봉2동", "개봉3동", "오류1동", "오류2동", "수궁동"],
                    "금천구": ["가산동", "독산동", "시흥동", "독산1동", "독산2동", "독산3동", "독산4동", "시흥1동", "시흥2동", "시흥3동", "시흥4동", "시흥5동"],
                    "영등포구": ["영등포동", "영등포동1가", "영등포동2가", "영등포동3가", "영등포동4가", "영등포동5가", "영등포동6가", "영등포동7가", "영등포동8가", "여의도동", "당산동1가", "당산동2가", "당산동3가", "당산동4가", "당산동5가", "당산동6가", "당산동", "도림동", "문래동1가", "문래동2가", "문래동3가", "문래동4가", "문래동5가", "문래동6가", "양평동1가", "양평동2가", "양평동3가", "양평동4가", "양평동5가", "양평동6가", "양화동", "신길동", "대림동", "양평동", "영등포본동", "여의동", "당산1동", "당산2동", "문래동", "양평1동", "양평2동", "신길1동", "신길3동", "신길4동", "신길5동", "신길6동", "신길7동", "대림1동", "대림2동", "대림3동"],
                    "동작구": ["노량진동", "상도동", "상도1동", "본동", "흑석동", "동작동", "사당동", "대방동", "신대방동", "노량진1동", "노량진2동", "상도2동", "상도3동", "상도4동", "사당1동", "사당2동", "사당3동", "사당4동", "사당5동", "신대방1동", "신대방2동"],
                    "관악구": ["봉천동", "신림동", "남현동", "보라매동", "청림동", "성현동", "행운동", "낙성대동", "청룡동", "은천동", "중앙동", "인헌동", "서원동", "신원동", "서림동", "신사동", "난향동", "조원동", "대학동", "삼성동", "미성동", "난곡동"],
                    "서초구": ["방배동", "양재동", "우면동", "원지동", "잠원동", "반포동", "서초동", "내곡동", "염곡동", "신원동", "서초1동", "서초2동", "서초3동", "서초4동", "반포본동", "반포1동", "반포2동", "반포3동", "반포4동", "방배본동", "방배1동", "방배2동", "방배3동", "방배4동", "양재1동", "양재2동"],
                    "강남구": ["역삼동", "개포동", "청담동", "삼성동", "대치동", "신사동", "논현동", "압구정동", "세곡동", "자곡동", "율현동", "일원동", "수서동", "도곡동", "논현1동", "논현2동", "삼성1동", "삼성2동", "대치1동", "대치2동", "대치4동", "역삼1동", "역삼2동", "도곡1동", "도곡2동", "개포1동", "개포2동", "개포3동", "개포4동", "일원본동", "일원1동"],
                    "송파구": ["잠실동", "신천동", "풍납동", "송파동", "석촌동", "삼전동", "가락동", "문정동", "장지동", "방이동", "오금동", "거여동", "마천동", "풍납1동", "풍납2동", "거여1동", "거여2동", "마천1동", "마천2동", "방이1동", "방이2동", "오륜동", "송파1동", "송파2동", "가락본동", "가락1동", "가락2동", "문정1동", "문정2동", "위례동", "잠실본동", "잠실2동", "잠실3동", "잠실4동", "잠실6동", "잠실7동"],
                    "강동구": ["명일동", "고덕동", "상일동", "길동", "둔촌동", "암사동", "성내동", "천호동", "강일동", "상일1동", "상일2동", "명일1동", "명일2동", "고덕1동", "고덕2동", "암사1동", "암사2동", "암사3동", "천호1동", "천호2동", "천호3동", "성내1동", "성내2동", "성내3동", "둔촌1동", "둔촌2동"],


                },
                "경기도": {
                    "수원장안구": ["파장동", "정자동", "이목동", "율전동", "천천동", "영화동", "송죽동", "조원동", "연무동", "상광교동", "하광교동", "율천동", "정자1동", "정자2동", "정자3동", "조원1동", "조원2동"],
                    "수원권선구": ["세류동", "평동", "고색동", "오목천동", "평리동", "서둔동", "구운동", "탑동", "금곡동", "호매실동", "곡반정동", "권선동", "장지동", "대황교동", "입북동", "당수동", "세류1동", "세류2동", "세류3동", "권선1동", "권선2동", "곡선동"],
                    "수원팔달구": ["팔달로1가", "팔달로2가", "팔달로3가", "남창동", "영동", "중동", "구천동", "남수동", "매향동", "북수동", "신풍동", "장안동", "교동", "매교동", "매산로1가", "매산로2가", "매산로3가", "고등동", "화서동", "지동", "우만동", "인계동", "매산동", "화서1동", "화서2동", "우만1동", "우만2동", "행궁동"],
                    "수원영통구": ["매탄동", "원천동", "이의동", "하동", "영통동", "신동", "망포동", "매탄1동", "매탄2동", "매탄3동", "매탄4동", "영통1동", "영통2동", "영통3동", "망포1동", "망포2동", "광교1동", "광교2동"],
                    "성남수정구": ["신흥동", "태평동", "수진동", "단대동", "산성동", "양지동", "복정동", "창곡동", "신촌동", "오야동", "심곡동", "고등동", "상적동", "둔전동", "시흥동", "금토동", "사송동", "신흥1동", "신흥2동", "신흥3동", "태평1동", "태평2동", "태평3동", "태평4동", "수진1동", "수진2동", "위례동"],
                    "성남중원구": ["성남동", "금광동", "은행동", "상대원동", "여수동", "도촌동", "갈현동", "하대원동", "중앙동", "금광1동", "금광2동", "은행1동", "은행2동", "상대원1동", "상대원2동", "상대원3동"],
                    "성남분당구": ["분당동", "수내동", "정자동", "율동", "서현동", "이매동", "야탑동", "판교동", "삼평동", "백현동", "금곡동", "궁내동", "동원동", "구미동", "운중동", "대장동", "석운동", "하산운동", "수내1동", "수내2동", "수내3동", "정자1동", "정자2동", "정자3동", "서현1동", "서현2동", "이매1동", "이매2동", "야탑1동", "야탑2동", "야탑3동", "구미1동"],
                    "고양덕양구": ["주교동", "원당동", "신원동", "원흥동", "도내동", "성사동", "북한동", "효자동", "지축동", "오금동", "삼송동", "동산동", "용두동", "벽제동", "선유동", "고양동", "대자동", "관산동", "내유동", "토당동", "내곡동", "대장동", "화정동", "강매동", "행주내동", "행주외동", "신평동", "행신동", "화전동", "현천동", "덕은동", "향동동", "원신동", "흥도동", "성사1동", "성사2동", "삼송1동", "삼송2동", "창릉동", "능곡동", "화정1동", "화정2동", "행주동", "행신1동", "행신2동", "행신3동", "행신4동", "대덕동"],
                    "고양일산동구": ["식사동", "중산동", "정발산동", "장항동", "마두동", "백석동", "풍동", "산황동", "사리현동", "지영동", "설문동", "문봉동", "성석동", "중산1동", "중산2동", "풍산동", "백석1동", "백석2동", "마두1동", "마두2동", "장항1동", "장항2동", "고봉동"],
                    "고양일산서구": ["일산동", "주엽동", "탄현동", "대화동", "덕이동", "가좌동", "구산동", "법곳동", "일산1동", "일산2동", "일산3동", "탄현1동", "탄현2동", "주엽1동", "주엽2동", "송포동"],
                    "의정부시": ["의정부동", "호원동", "장암동", "신곡동", "용현동", "민락동", "낙양동", "자일동", "금오동", "녹양동", "고산동", "산곡동", "의정부1동", "의정부2동", "호원1동", "호원2동", "신곡1동", "신곡2동", "송산1동", "송산2동", "송산3동", "자금동", "흥선동"],
                    "안양만안구": ["안양동", "석수동", "박달동", "안양1동", "안양2동", "안양3동", "안양4동", "안양5동", "안양6동", "안양7동", "안양8동", "안양9동", "석수1동", "석수2동", "석수3동", "박달1동", "박달2동"],
                    "안양동안구": ["비산동", "관양동", "평촌동", "호계동", "비산1동", "비산2동", "비산3동", "부흥동", "달안동", "관양1동", "관양2동", "부림동", "평안동", "귀인동", "호계1동", "호계2동", "호계3동", "범계동", "신촌동", "갈산동"],
                    "부천시": ["원미동", "심곡동", "춘의동", "도당동", "약대동", "소사동", "역곡동", "중동", "상동", "소사본동", "심곡본동", "범박동", "괴안동", "송내동", "옥길동", "계수동", "오정동", "여월동", "작동", "원종동", "고강동", "대장동", "삼정동", "내동", "부천동", "신중동", "대산동", "범안동", "성곡동"],
                    "광명시": ["광명동", "철산동", "하안동", "소하동", "노온사동", "일직동", "가학동", "옥길동", "광명1동", "광명2동", "광명3동", "광명4동", "광명5동", "광명6동", "광명7동", "철산1동", "철산2동", "철산3동", "철산4동", "하안1동", "하안2동", "하안3동", "하안4동", "소하1동", "소하2동", "학온동"],
                    "평택시": ["서정동", "장당동", "모곡동", "칠괴동", "칠원동", "도일동", "가재동", "장안동", "이충동", "지산동", "독곡동", "신장동", "평택동", "통복동", "군문동", "유천동", "합정동", "비전동", "동삭동", "세교동", "지제동", "신대동", "소사동", "용이동", "월곡동", "청룡동", "죽백동", "고덕동", "팽성읍", "안중읍", "포승읍", "청북읍", "진위면", "서탄면", "고덕면", "오성면", "현덕면", "중앙동", "송탄동", "송북동", "신장1동", "신장2동", "신평동", "원평동", "비전1동", "비전2동"],
                    "동두천시": ["송내동", "지행동", "생연동", "광암동", "걸산동", "보산동", "동두천동", "안흥동", "상봉암동", "하봉암동", "탑동동", "상패동", "생연1동", "생연2동", "중앙동", "불현동", "소요동"],
                    "안산상록구": ["일동", "이동", "사동", "본오동", "팔곡이동", "양상동", "부곡동", "성포동", "월피동", "팔곡일동", "건건동", "사사동", "수암동", "장상동", "장하동", "사이동", "해양동", "본오1동", "본오2동", "본오3동", "반월동", "안산동"],
                    "안산단원구": ["고잔동", "와동", "신길동", "성곡동", "원시동", "목내동", "초지동", "원곡동", "선부동", "대부동", "동대부북동", "대부남동", "선감동", "풍도동", "화정동", "중앙동", "호수동", "백운동", "선부1동", "선부2동", "선부3동", "대부동"],
                    "과천시": ["관문동", "문원동", "갈현동", "막계동", "과천동", "주암동", "중앙동", "원문동", "별양동", "부림동"],
                    "구리시": ["갈매동", "사노동", "인창동", "교문동", "수택동", "아천동", "토평동", "동구동", "교문1동", "교문2동", "수택1동", "수택2동", "수택3동"],
                    "남양주시": ["호평동", "평내동", "금곡동", "일패동", "이패동", "삼패동", "수석동", "지금동", "도농동", "별내동", "다산동", "와부읍", "진접읍", "화도읍", "진건읍", "오남읍", "퇴계원읍", "별내면", "수동면", "조안면", "양정동", "다산1동", "다산2동"],
                    "오산시": ["오산동", "부산동", "원동", "궐동", "청학동", "가장동", "금암동", "수청동", "은계동", "내삼미동", "외삼미동", "양산동", "세교동", "지곶동", "서랑동", "서동", "벌음동", "두곡동", "탑동", "누읍동", "가수동", "고현동", "청호동", "갈곶동", "중앙동", "남촌동", "신장동", "세마동", "초평동", "대원동"],
                    "시흥시": ["대야동", "신천동", "방산동", "포동", "미산동", "은행동", "안현동", "매화동", "도창동", "금이동", "과림동", "계수동", "화정동", "능곡동", "하중동", "하상동", "광석동", "물왕동", "산현동", "조남동", "논곡동", "목감동", "거모동", "군자동", "장현동", "장곡동", "월곶동", "정왕동", "죽율동", "무지내동", "배곧동", "신현동", "정왕본동", "정왕1동", "정왕2동", "정왕3동", "정왕4동", "배곧1동", "배곧2동", "연성동"],
                    "군포시": ["당동", "당정동", "부곡동", "산본동", "금정동", "둔대동", "속달동", "대야미동", "도마교동", "군포1동", "군포2동", "산본1동", "산본2동", "재궁동", "오금동", "수리동", "궁내동", "대야동", "광정동", "송부동"],
                    "의왕시": ["고천동", "이동", "삼동", "왕곡동", "오전동", "학의동", "내손동", "청계동", "포일동", "월암동", "초평동", "부곡동", "내손1동", "내손2동"],
                    "하남시": ["천현동", "하산곡동", "창우동", "배알미동", "상산곡동", "신장동", "당정동", "덕풍동", "망월동", "풍산동", "미사동", "선동", "감북동", "감일동", "감이동", "학암동", "교산동", "춘궁동", "하사창동", "상사창동", "항동", "초일동", "초이동", "광암동", "신장1동", "신장2동", "덕풍1동", "덕풍2동", "덕풍3동", "위례동", "미사1동", "미사2동"],
                    "용인처인구": ["김량장동", "역북동", "삼가동", "남동", "유방동", "고림동", "마평동", "운학동", "호동", "해곡동", "포곡읍", "모현읍", "이동읍", "남사읍", "원삼면", "백암면", "양지면", "중앙동", "유림동", "동부동"],
                    "용인기흥구": ["신갈동", "구갈동", "상갈동", "하갈동", "보라동", "지곡동", "공세동", "고매동", "농서동", "서천동", "영덕동", "언남동", "마북동", "청덕동", "동백동", "중동", "상하동", "보정동", "영덕1동", "영덕2동", "기흥동", "서농동", "구성동", "동백1동", "동백2동", "동백3동"],
                    "용인수지구": ["풍덕천동", "죽전동", "동천동", "고기동", "신봉동", "성복동", "상현동", "풍덕천1동", "풍덕천2동", "죽전1동", "죽전2동", "죽전3동", "상현1동", "상현2동", "상현3동"],
                    "파주시": ["금촌동", "아동동", "야동동", "검산동", "맥금동", "교하동", "야당동", "다율동", "오도동", "상지석동", "산남동", "동패동", "당하동", "문발동", "송촌동", "목동동", "하지석동", "서패동", "신촌동", "연다산동", "와동동", "금릉동", "문산읍", "파주읍", "법원읍", "조리읍", "월롱면", "탄현면", "광탄면", "파평면", "적성면", "군내면", "장단면", "진동면", "진서면", "금촌1동", "금촌2동", "금촌3동", "운정1동", "운정2동", "운정3동", "운정4동", "운정5동", "운정6동"],
                    "이천시": ["창전동", "관고동", "중리동", "증일동", "율현동", "진리동", "안흥동", "갈산동", "증포동", "송정동", "사음동", "단월동", "대포동", "고담동", "장록동", "장호원읍", "부발읍", "신둔면", "백사면", "호법면", "마장면", "대월면", "모가면", "설성면", "율면"],
                    "안성시": ["봉산동", "숭인동", "영동", "봉남동", "구포동", "동본동", "명륜동", "옥천동", "낙원동", "창전동", "성남동", "신흥동", "인지동", "금산동", "연지동", "대천동", "서인동", "석정동", "아양동", "금석동", "계동", "옥산동", "사곡동", "도기동", "당왕동", "가사동", "가현동", "신건지동", "신소현동", "신모산동", "현수동", "발화동", "중리동", "공도읍", "보개면", "금광면", "서운면", "미양면", "대덕면", "양성면", "원곡면", "일죽면", "죽산면", "삼죽면", "고삼면", "안성1동", "안성2동", "안성3동"],
                    "김포시": ["북변동", "걸포동", "운양동", "장기동", "감정동", "사우동", "풍무동", "마산동", "구래동", "통진읍", "고촌읍", "양촌읍", "대곶면", "월곶면", "하성면", "김포본동", "장기본동"],
                    "화성시": ["진안동", "병점동", "능동", "기산동", "반월동", "반정동", "황계동", "배양동", "기안동", "송산동", "안녕동", "반송동", "석우동", "오산동", "청계동", "영천동", "중동", "신동", "목동", "산척동", "장지동", "송동", "방교동", "금곡동", "새솔동", "봉담읍", "우정읍", "향남읍", "남양읍", "매송면", "비봉면", "마도면", "송산면", "서신면", "팔탄면", "장안면", "양감면", "정남면", "병점1동", "병점2동", "기배동", "화산동", "동탄1동", "동탄2동", "동탄3동", "동탄4동", "동탄5동", "동탄6동", "동탄7동", "동탄8동"],
                    "광주동구": ["대인동", "금남로5가", "충장로5가", "수기동", "대의동", "궁동", "장동", "동명동", "계림동", "산수동", "지산동", "남동", "광산동", "금동", "호남동", "불로동", "황금동", "서석동", "소태동", "용연동", "운림동", "학동", "월남동", "선교동", "내남동", "용산동", "충장로1가", "충장로2가", "충장로3가", "충장로4가", "금남로1가", "금남로2가", "금남로3가", "금남로4가", "충장동", "계림1동", "계림2동", "산수1동", "산수2동", "지산1동", "지산2동", "서남동", "학운동", "지원1동", "지원2동"],
                    "광주서구": ["양동", "농성동", "광천동", "유촌동", "덕흥동", "쌍촌동", "화정동", "치평동", "내방동", "서창동", "세하동", "용두동", "풍암동", "벽진동", "금호동", "마륵동", "매월동", "동천동", "양3동", "농성1동", "농성2동", "유덕동", "상무1동", "상무2동", "화정1동", "화정2동", "화정3동", "화정4동", "금호1동", "금호2동"],
                    "광주남구": ["사동", "구동", "서동", "월산동", "백운동", "주월동", "노대동", "진월동", "덕남동", "행암동", "임암동", "송하동", "양림동", "방림동", "봉선동", "구소동", "양촌동", "도금동", "승촌동", "지석동", "압촌동", "화장동", "칠석동", "석정동", "신장동", "양과동", "이장동", "대지동", "원산동", "월성동", "방림1동", "방림2동", "봉선1동", "봉선2동", "사직동", "월산4동", "월산5동", "백운1동", "백운2동", "주월1동", "주월2동", "효덕동", "송암동", "대촌동"],
                    "광주북구": ["중흥동", "유동", "누문동", "북동", "임동", "신안동", "용봉동", "동림동", "운암동", "우산동", "풍향동", "문흥동", "각화동", "두암동", "오치동", "삼각동", "매곡동", "충효동", "덕의동", "금곡동", "망월동", "청풍동", "화암동", "장등동", "운정동", "본촌동", "일곡동", "양산동", "연제동", "신용동", "용두동", "지야동", "태령동", "수곡동", "효령동", "용전동", "용강동", "생용동", "월출동", "대촌동", "오룡동", "중흥1동", "중흥2동", "중흥3동", "중앙동", "운암1동", "운암2동", "운암3동", "문화동", "문흥1동", "문흥2동", "두암1동", "두암2동", "두암3동", "오치1동", "오치2동", "석곡동", "건국동"],
                    "광주광산구": ["송정동", "도산동", "도호동", "신촌동", "서봉동", "운수동", "선암동", "소촌동", "우산동", "황룡동", "박호동", "비아동", "도천동", "수완동", "월계동", "쌍암동", "산월동", "신창동", "신가동", "운남동", "안청동", "진곡동", "장덕동", "흑석동", "하남동", "장수동", "산정동", "월곡동", "등임동", "산막동", "고룡동", "신룡동", "두정동", "임곡동", "광산동", "오산동", "사호동", "하산동", "유계동", "본덕동", "용봉동", "요기동", "복룡동", "송대동", "옥동", "월전동", "장록동", "송촌동", "지죽동", "용동", "용곡동", "지정동", "명화동", "동산동", "연산동", "도덕동", "송산동", "지평동", "오운동", "삼거동", "양동", "내산동", "대산동", "송학동", "신동", "삼도동", "남산동", "송치동", "산수동", "선동", "지산동", "왕동", "북산동", "명도동", "동호동", "덕림동", "양산동", "동림동", "오선동", "송정1동", "송정2동", "신흥동", "어룡동", "월곡1동", "월곡2동", "첨단1동", "첨단2동", "동곡동", "평동", "본량동"],
                    "양주시": ["유양동", "어둔동", "남방동", "마전동", "산북동", "광사동", "만송동", "삼숭동", "고읍동", "덕정동", "봉양동", "회암동", "율정동", "옥정동", "고암동", "덕계동", "회정동", "백석읍", "은현면", "남면", "광적면", "장흥면", "양주1동", "양주2동", "회천1동", "회천2동", "회천3동", "회천4동"],
                    "포천시": ["신읍동", "어룡동", "자작동", "선단동", "설운동", "동교동", "소흘읍", "군내면", "내촌면", "가산면", "신북면", "창수면", "영중면", "일동면", "이동면", "영북면", "관인면", "화현면", "포천동"],
                    "여주시": ["상동", "홍문동", "창동", "우만동", "단현동", "신진동", "하동", "교동", "월송동", "가업동", "연라동", "상거동", "하거동", "삼교동", "점봉동", "능현동", "멱곡동", "연양동", "매룡동", "천송동", "오학동", "현암동", "오금동", "가남읍", "점동면", "흥천면", "금사면", "세종대왕면", "대신면", "북내면", "강천면", "산북면", "여흥동", "중앙동"],
                    "연천군": ["연천읍", "전곡읍", "군남면", "청산면", "백학면", "미산면", "왕징면", "신서면", "중면", "장남면"],
                    "가평군": ["가평읍", "설악면", "청평면", "상면", "조종면", "북면"],
                    "양평군": ["양평읍", "강상면", "강하면", "양서면", "옥천면", "서종면", "단월면", "청운면", "양동면", "지평면", "용문면", "개군면"],


                },
                "인천광역시": {
                    "중구": ["중앙동1가", "중앙동2가", "중앙동3가", "중앙동4가", "해안동1가", "해안동2가", "해안동3가", "해안동4가", "관동1가", "관동2가", "관동3가", "항동1가", "항동2가", "항동3가", "항동4가", "항동5가", "항동6가", "항동7가", "송학동1가", "송학동2가", "송학동3가", "사동", "신생동", "신포동", "답동", "신흥동1가", "신흥동2가", "신흥동3가", "선화동", "유동", "율목동", "도원동", "내동", "경동", "용동", "인현동", "전동", "북성동1가", "북성동2가", "북성동3가", "선린동", "송월동1가", "송월동2가", "송월동3가", "중산동", "운남동", "운서동", "운북동", "을왕동", "남북동", "덕교동", "무의동", "연안동", "신흥동", "동인천동", "개항동", "영종동", "영종1동", "용유동"],
                    "동구": ["만석동", "화수동", "송현동", "화평동", "창영동", "금곡동", "송림동", "화수1.화평동", "화수2동", "송현1.2동", "송현3동", "송림1동", "송림2동", "송림3.5동", "송림4동", "송림6동", "금창동"],
                    "미추홀구": ["숭의동", "용현동", "학익동", "도화동", "주안동", "관교동", "문학동", "숭의2동", "숭의1.3동", "숭의4동", "용현1.4동", "용현2동", "용현3동", "용현5동", "학익1동", "학익2동", "도화1동", "도화2.3동", "주안1동", "주안2동", "주안3동", "주안4동", "주안5동", "주안6동", "주안7동", "주안8동"],
                    "연수구": ["옥련동", "선학동", "연수동", "청학동", "동춘동", "송도동", "옥련1동", "옥련2동", "연수1동", "연수2동", "연수3동", "동춘1동", "동춘2동", "동춘3동", "송도1동", "송도2동", "송도3동", "송도4동", "송도5동"],
                    "남동구": ["구월동", "간석동", "만수동", "장수동", "서창동", "운연동", "남촌동", "수산동", "도림동", "논현동", "고잔동", "구월1동", "구월2동", "구월3동", "구월4동", "간석1동", "간석2동", "간석3동", "간석4동", "만수1동", "만수2동", "만수3동", "만수4동", "만수5동", "만수6동", "장수서창동", "서창2동", "남촌도림동", "논현1동", "논현2동", "논현고잔동"],
                    "부평구": ["부평동", "십정동", "산곡동", "청천동", "삼산동", "갈산동", "부개동", "일신동", "구산동", "부평1동", "부평2동", "부평3동", "부평4동", "부평5동", "부평6동", "산곡1동", "산곡2동", "산곡3동", "산곡4동", "청천1동", "청천2동", "갈산1동", "갈산2동", "삼산1동", "삼산2동", "부개1동", "부개2동", "부개3동", "십정1동", "십정2동"],
                    "계양구": ["효성동", "계산동", "작전동", "서운동", "임학동", "용종동", "병방동", "방축동", "박촌동", "동양동", "귤현동", "상야동", "하야동", "평동", "노오지동", "선주지동", "이화동", "오류동", "갈현동", "둑실동", "목상동", "다남동", "장기동", "효성1동", "효성2동", "계산1동", "계산2동", "계산3동", "계산4동", "작전1동", "작전2동", "작전서운동", "계양1동", "계양2동", "계양3동"],
                    "서구": ["백석동", "시천동", "검암동", "경서동", "공촌동", "연희동", "심곡동", "가정동", "신현동", "석남동", "원창동", "가좌동", "마전동", "당하동", "원당동", "대곡동", "금곡동", "오류동", "왕길동", "불로동", "청라동", "검암경서동", "청라1동", "청라2동", "청라3동", "가정1동", "가정2동", "가정3동", "석남1동", "석남2동", "석남3동", "신현원창동", "가좌1동", "가좌2동", "가좌3동", "가좌4동", "검단동", "불로대곡동", "오류왕길동", "아라동"],
                    "강화군": ["강화읍", "선원면", "불은면", "길상면", "화도면", "양도면", "내가면", "하점면", "양사면", "송해면", "교동면", "삼산면", "서도면"],
                    "옹진군": ["북도면", "백령면", "대청면", "덕적면", "영흥면", "자월면", "연평면"],
                },

            };

            for (i = 0; i < $(target).length; i++) {
                (function (z) {
                    var a1 = $(target).eq(z);
                    var a2 = a1.next();
                    var a3 = a2.next();



                    //초기화
                    init(a1, true);

                    //권역 기본 생성
                    var areaKeys1 = Object.keys(area);
                    areaKeys1.forEach(function (Region) {
                        a1.append("<option value=" + Region + ">" + Region + "</option>");
                    });

                    //변경 이벤트
                    $(a1).on("change", function () {
                        init($(this), false);
                        var Region = $(this).val();
                        var keys = Object.keys(area[Region]);
                        keys.forEach(function (Do) {
                            a2.append("<option value=" + Do + ">" + Do + "</option>");
                        });
                    });

                    $(a2).on("change", function () {
                        a3.empty().append("<option value=''>선택</option>");
                        var Region = a1.val();
                        var Do = $(this).val();
                        var keys = Object.keys(area[Region][Do]);
                        keys.forEach(function (SiGunGu) {
                            a3.append("<option value=" + area[Region][Do][SiGunGu] + ">" + area[Region][Do][SiGunGu] + "</option>");
                        });
                    });
                })(i);

                function init(t, first) {
                    first ? t.empty().append("<option value=''>선택</option>") : "";
                    t.next().empty().append("<option value=''>선택</option>");
                    t.next().next().empty().append("<option value=''>선택</option>");
                }
            }
        }

    </script>


    <!-- Section-->
    <article class="dangguen-list">
        <div class="container px-4 px-lg-5 mt-5">
            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                <div class="col mb-5">
                    <div class="card h-100">
                        <!-- Product image-->
                        <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                        <!-- Product details-->
                        <div class="card-body p-4">
                            <div class="text-center">
                                <!-- Product name-->
                                <h5 class="fw-bolder"><a href="dangguenView.html">강아지 양말</a></h5>
                                <!-- Product price-->
                                4,000원
                                <li id="user-adress"><a>서울시 강남구</a></li>
                                <li id="user-name"><a>글쓴이</a></li>


                            </div>
                        </div>
                        <!-- Product actions-->
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                            <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="chat.html">구매하기</a>
                                <a class="btnscrap" href="">찜♥</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col mb-5">
                    <div class="card h-100">
                        <!-- Sale badge-->
                        <!-- Product image-->
                        <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                        <!-- Product details-->
                        <div class="card-body p-4">
                            <div class="text-center">
                                <!-- Product name-->
                                <h5 class="fw-bolder"><a href="dangguenView.html">발 크림</a></h5>
                                <!-- Product reviews-->
                                <div class="d-flex justify-content-center small text-warning mb-2">
                                </div>
                                <!-- Product price-->
                                15,000원
                                <li id="user-adress"><a>서울시 강남구</a></li>
                                <li id="user-name"><a>글쓴이</a></li>

                            </div>
                        </div>
                        <!-- Product actions-->
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                            <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="chat.html">구매하기</a>
                                <a class="btnscrap" href="">찜♥</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col mb-5">
                    <div class="card h-100">
                        <!-- Sale badge-->
                        <!-- Product image-->
                        <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                        <!-- Product details-->
                        <div class="card-body p-4">
                            <div class="text-center">
                                <!-- Product name-->
                                <h5 class="fw-bolder"><a href="dangguenView.html">강아지 우비</a></h5>
                                <!-- Product price-->
                                10,000원
                                <li id="user-adress"><a>서울시 강남구</a></li>
                                <li id="user-name"><a>글쓴이</a></li>

                            </div>
                        </div>
                        <!-- Product actions-->
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                            <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="chat.html">구매하기</a>
                                <a class="btnscrap" href="">찜♥</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col mb-5">
                    <div class="card h-100">
                        <!-- Product image-->
                        <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                        <!-- Product details-->
                        <div class="card-body p-4">
                            <div class="text-center">
                                <!-- Product name-->
                                <h5 class="fw-bolder"><a href="dangguenView.html">배변패드</a></h5>
                                <!-- Product reviews-->
                                <div class="d-flex justify-content-center small text-warning mb-2">
                                </div>
                                <!-- Product price-->
                                8,000원
                                <li id="user-adress"><a>서울시 강남구</a></li>
                                <li id="user-name"><a>글쓴이</a></li>

                            </div>
                        </div>
                        <!-- Product actions-->
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                            <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="chat.html">구매하기</a>
                                <a class="btnscrap" href="">찜♥</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col mb-5">
                    <div class="card h-100">
                        <!-- Sale badge-->
                        <!-- Product image-->
                        <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                        <!-- Product details-->
                        <div class="card-body p-4">
                            <div class="text-center">
                                <!-- Product name-->
                                <h5 class="fw-bolder"><a href="dangguenView.html">강아지 리드줄</a></h5>
                                <!-- Product price-->
                                4,500원
                                <li id="user-adress"><a>서울시 강남구</a></li>
                                <li id="user-name"><a>글쓴이</a></li>

                            </div>
                        </div>
                        <!-- Product actions-->
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                            <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="chat.html">구매하기</a>
                                <a class="btnscrap" href="">찜♥</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col mb-5">
                    <div class="card h-100">
                        <!-- Product image-->
                        <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                        <!-- Product details-->
                        <div class="card-body p-4">
                            <div class="text-center">
                                <!-- Product name-->
                                <h5 class="fw-bolder"><a href="dangguenView.html">강아지 옷</a></h5>
                                <!-- Product price-->
                                3,000원
                                <li id="user-adress"><a>서울시 강남구</a></li>
                                <li id="user-name"><a>글쓴이</a></li>

                            </div>
                        </div>
                        <!-- Product actions-->
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                            <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="chat.html">구매하기</a>
                                <a class="btnscrap" href="">찜♥</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col mb-5">
                    <div class="card h-100">
                        <!-- Sale badge-->
                        <!-- Product image-->
                        <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                        <!-- Product details-->
                        <div class="card-body p-4">
                            <div class="text-center">
                                <!-- Product name-->
                                <h5 class="fw-bolder"><a href="dangguenView.html">강아지 방석</a></h5>
                                <!-- Product reviews-->
                                <div class="d-flex justify-content-center small text-warning mb-2">
                                </div>
                                <!-- Product price-->
                                18,000원
                                <li id="user-adress"><a>서울시 강남구</a></li>
                                <li id="user-name"><a>글쓴이</a></li>

                            </div>
                        </div>
                        <!-- Product actions-->
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                            <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="chat.html">구매하기</a>
                                <a class="btnscrap" href="">찜♥</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col mb-5">
                    <div class="card h-100">
                        <!-- Product image-->
                        <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                        <!-- Product details-->
                        <div class="card-body p-4">
                            <div class="text-center">
                                <!-- Product name-->
                                <h5 class="fw-bolder"><a href="dangguenView.html">강아지 옷걸이</a></h5>
                                <!-- Product reviews-->
                                <div class="d-flex justify-content-center small text-warning mb-2">
                                </div>
                                <!-- Product price-->
                                5,000원
                                <li id="user-adress"><a>서울시 강남구</a></li>
                                <li id="user-name"><a>글쓴이</a></li>

                            </div>
                        </div>
                        <!-- Product actions-->
                        <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                            <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="chat.html">구매하기</a>
                                <a class="btnscrap" href="">찜♥</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </article>

</body>

</html>