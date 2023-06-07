
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<body>
<script>
    const contextPath = "/" + window.location.pathname.split("/")[1] ;
    //최대 업로드 가능 수s
    const MAX_UPLOAD_IMG = 5;
    //이미지 담을 배열
    var imgArr = new Array();
    //파일 선택 이벤트

    $(function () {
        $('input[name=image]').change(function (e) {

            alert("ss");

            var files = e.target.files;
            var filesArr = Array.prototype.slice.call(files);
            //유효성 체크
            if (filesArr.length > MAX_UPLOAD_IMG) {
                alert("이미지는 최대" + MAX_UPLOAD_IMG + "개 까지 업로드 가능합니다.");
                document.getElementById("fileup").value = null;
                return;
            }
            //확장자 검사도?
            //이미지 배열에 저장
            filesArr.forEach(function (f) {
                imgArr.push(f);
            });//foreach
        });//change

    //업로드 수행
        $('#uploadBtn').on('click', function () {
            alert("ss");
            console.log("uploadList" + imgArr);
            //업로드용 폼 객체
            let formData = new FormData($('#update_form')[0])
            for (let i = 0; i < imgArr.length; i++) {
                //폼에 담기
                formData.append("images", imgArr[i]);
            }
            formData.append("category", "test");
            $.ajax({
                type: 'post',
                enctype: "multipart/form-data",
                url: contextPath + '/upload',
                data: formData,
                processData: false,
                contentType: false,
                success: function (data) {
                    alert(data);
                },
                error: function (e) {
                    alert("error:" + e);
                }
            });//ajax
        });//uploadbtn
    });//func

</script>

<h2>다중 업로드</h2>
<form action="/upload" method="post" enctype="multipart/form-data" id="uploadForm">
  <input type="file" id="fileup" name="image" multiple="multiple" accept=".png, .jpg,.jpeg">
  <button type="button" id="uploadBtn">업로드</button>
</form>
<input type="text" id="asd"/>
</body>
</html>
