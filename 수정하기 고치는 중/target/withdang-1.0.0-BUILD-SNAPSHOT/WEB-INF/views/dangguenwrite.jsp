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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/dangguenwrite.css">
    <script src="https://kit.fontawesome.com/cac1ec65f4.js" crossorigin="anonymous"></script>
    <script src="${pageContext.request.contextPath}/resources/script/toggle.js" defer></script>
    <script src="${pageContext.request.contextPath}/resources/script/dangguenwrite.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>
    <link href="https://fonts.googleapis.com/css2?family=Gaegu&family=Nanum+Gothic:wght@400;700;800&display=swap" rel="stylesheet">

    <title>댕근마켓</title>
</head>

<body>
    <jsp:include page="header.jsp"></jsp:include>



<div class="board_wrap">
    <div class="board_title">
        <strong>댕근마켓</strong>
        <p>우리 댕댕이 용품 중고거래</p>
    </div>
    <div class="board_write_wrap">
        <div class="board_write">
            <div class="title">
                <dl>
                    <dd><input type="text" placeholder="제목 입력"></dd>
                </dl>
            </div>
            <section class="dangguen-sec">
                <div class="info">
                    <dl>
                        <p>가격<dd><input type="text" placeholder="판매 가격"> 원</dd></p>
                    </dl>
                    <div id="dangguen-img">
                        <select class="search-select" aria-label="category">
                            <optgroup label="카테고리">
                                <option selected>카테고리</option>
                                <option value="1">사료/간식</option>
                                <option value="2">영양제</option>
                                <option value="3">산책 용품</option>
                                <option value="4">집/방석</option>
                                <option value="5">옷/악세사리</option>
                                <option value="6">위생 용품</option>
                                <option value="7">기타 용품</option>
                            </optgroup>
                        </select>
                        <select class="form-select" aria-label="adress">
                            <optgroup selected label="지역 설정">
                                <option selected disabled>지역 설정</option>
                                <option value="1">서울특별시</option>
                                <option value="2">경기도</option>
                                <option value="3">인천광역시</option>
                                <option value="4">부산광역시</option>
                                <option value="5">대구광역시</option>
                                <option value="6">광주광역시</option>
                                <option value="7">대전광역시</option>
                                <option value="8">울산광역시</option>
                                <option value="9">세종특별자치시</option>
                                <option value="10">제주도</option>
                                <option value="11">강원도</option>
                                <option value="12">충청북도</option>
                                <option value="13">충청남도</option>
                                <option value="14">전라북도</option>
                                <option value="15">전라남도</option>
                                <option value="16">경상북도</option>
                                <option value="17">경상남도</option>
                            </optgroup>
                        </select>
                        <select class="form-select" aria-label="adress">
                            <optgroup label="동네 설정">
                                <option value="0">전체보기</option>
                                <option value="1">중구</option>
                                <option value="2">용산구</option>
                                <option value="3">성동구</option>
                                <option value="4">광진구</option>
                                <option value="5">동대문구</option>
                                <option value="6">중랑구</option>
                                <option value="7">성북구</option>
                                <option value="8">강북구</option>
                                <option value="9">도봉구</option>
                                <option value="10">노원구</option>
                                <option value="11">은평구</option>
                                <option value="12">서대문구</option>
                                <option value="13">마포구</option>
                                <option value="14">양천구</option>
                                <option value="15">강서구</option>
                                <option value="16">구로구</option>
                                <option value="17">금천구</option>
                                <option value="18">영등포구</option>
                                <option value="19">동작구</option>
                                <option value="20">관악구</option>
                                <option value="21">서초구</option>
                                <option value="22">강남구</option>
                                <option value="23">송파구</option>
                                <option value="24">강동구</option>
                                <option value="24">종로구</option>


                            </optgroup>
                            <optgroup label="하위 그룹">

                            </optgroup>
                        </select>

                        <select class="form-select" aria-label="adress" >
                            <optgroup label="동네 설정">
                                <option value="0">전체보기</option>
                                <option value="1">개포1동</option>
                                <option value="2">개포2동</option>
                                <option value="3">개포4동</option>
                                <option value="4">논현1동</option>
                                <option value="5">논현2동</option>
                                <option value="6">도곡1동</option>
                                <option value="7">도곡2동</option>
                                <option value="8">대치1동</option>
                                <option value="9">대치2동</option>
                                <option value="10">대치4동</option>
                                <option value="11">삼성1동</option>
                                <option value="12">삼성2동</option>
                                <option value="13">신사동</option>
                                <option value="14">압구정동</option>
                                <option value="15">역삼1동</option>
                                <option value="16">역삼2동</option>
                                <option value="17">청담동</option>
                            </optgroup>
                            <optgroup label="">

                            </optgroup>
                        </select>

                        <input id="imageinput" type="file" multiple="multiple" accept=".jpg, .jpeg, .png" onchange="previewImage(this)" required>
                        <img id="preview" />

                    </div>

                </div>
                <div class="cont">

                    <textarea placeholder="내용 입력"></textarea>
                </div>
        </div>
        <div class="bt_wrap">
            <a href="view.html" class="on">등록</a>
            <a href="dangcomu.html">취소</a>
        </div>
    </div>
</div>


</body>

</html>