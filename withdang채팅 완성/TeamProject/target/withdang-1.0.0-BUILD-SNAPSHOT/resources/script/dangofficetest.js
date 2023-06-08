const contextPath = "/" + window.location.pathname.split("/")[1] ;
//최대 업로드 가능 수s
const MAX_UPLOAD_IMG = 5;
//이미지 파일
var file;
//최대용량 mb
var MaxSize=0.001;

//사진div선택시 hidden해둔 input 클릭 이벤트
window.onload=function (){
    const input = document.querySelector('#imageinput');
    const divc = document.getElementById('imagediv');
    divc.addEventListener('click', () => input.click());
}

//업로드는 input 내용이 change 되었을 때를 기준으로 했음
//제출시에 업로드해도 됨 편한대로 수정
$(function () {
    $(document).on("change", '#imageinput', function (e) {
        //이미지 표현할 곳을 가져오기
        var image = document.getElementById('preview')
        //input의 파일. 지금은 파일 하나라 files[0] 다수 업로드일땐 array만들어서 넣어야함. uploadImage.jsp 참조
        file = e.target.files[0];
        //사이즈 체크 용량은 js 최상단에서 설정
        if (!checkFileSize(file, MaxSize)) {
            document.getElementById("imageinput").value = null;
            return;
        }
        var reader = new FileReader();
        //로딩 다 되면 프리뷰에 사진 표시되게
        reader.onload = function (e) {
            $("#preview").attr("src", e.target.result);
        };
        reader.readAsDataURL(file);
        image.style.width = '220px';
        image.style.height = 'auto';

        //확장자 검사도?
        //-------------------------------------------
        //업로드
        console.log("uploadList" + file);
        //업로드용 폼 객체
        let formData = new FormData();
        //폼에 담기
        //controller에서 지정해둔 이름대로
        formData.append("images", file);
        formData.append("category", "dogPhoto");
        $.ajax({
            type: 'post',
            enctype: "multipart/form-data",
            url: contextPath + '/dangofficetest/upload',
            data: formData,
            processData: false,
            contentType: false,
            success: function (data) {
                //이미지 주소 출력됨
                alert(data)
            },
            error: function (e) {
                alert("error:" + e);
            }
        });//ajax
    });//change

    //업로드 수행
    // $(document).on('click', '#출력', function () {
    //     console.log("uploadList" + file);
    //     //업로드용 폼 객체
    //     let formData = new FormData();
    //     //폼에 담기
    //
    //     formData.append("images", file);
    //     formData.append("category", "dogPhoto");
    //     $.ajax({
    //         type: 'post',
    //         enctype: "multipart/form-data",
    //         url: contextPath + '/dangofficetest/upload',
    //         data: formData,
    //         processData: false,
    //         contentType: false,
    //         success: function (data) {
    //             alert(data)
    //         },
    //         error: function (e) {
    //             alert("error:" + e);
    //         }
    //     });//ajax
    // });//uploadbtn
});

function checkFileSize(file, size) {
    if (file.size > 1024 * 1024 * size) {
        alert(size+"MB이하 파일만 등록 가능")
        return false;
    }
    return true;
}


  