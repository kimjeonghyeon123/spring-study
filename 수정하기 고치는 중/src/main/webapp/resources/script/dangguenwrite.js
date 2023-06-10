var list = document.getElementById('preview-list');
var count = [0, 0, 0, 0];	// 이미지 반복 업로드 횟수(input 태그 하나당)

function previewImage(input_img, input_num) {	
    var reader = new FileReader();
    var image = document.createElement('img');
    image.setAttribute('id', 'preview' + input_num);
    
    count[input_num]++;			// 업로드 횟수 증가
    
    image.style.width = 'auto';
    image.style.height = '100px';
    image.style.marginRight = '10px';
    
    reader.onload = (function (img) {
    	return function (e) {
        	img.src = e.target.result;
        };
    })(image);
    
    reader.readAsDataURL(input_img.files[0]);
    
    if (count[input_num] >= 2) {		// 업로드 횟수가 2 이상인 경우(같은 input 태그로 여러 번 업로드 했을 경우) preview 이미지 변경
    	var change = document.getElementById('preview' + input_num)
    	change.replaceWith(image)
    } else {
    	list.appendChild(image);
    }
    
}
