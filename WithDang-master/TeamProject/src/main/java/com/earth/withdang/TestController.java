package com.earth.withdang;

import com.earth.domain.ImageDto;
import com.earth.domain.MemberDto;
import com.earth.service.ImageServiceImpl;
import com.earth.upload.S3UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TestController {
    @Autowired
    private final S3UploadService s3UploadService;
    @Autowired
    ImageServiceImpl service;
//업로드 폴더명
    //js에서 설정하도록 구성을 해두긴했으나 생각해보니 각 컨트롤러에서 세팅해도 별 차이 없을듯하여 따로 만들어둠
    private String category = "dogProfile";

    @GetMapping("/dangofficetest")
    public String dangoffice(Model model,HttpSession session) {
        //세션에서 id를 가져온다. 
        //지금의 경우 객체가 올라가 있으니 객체를 가져와서 뽑아냄
        //id없으면=로그인 안했으면 로그인 화면으로 
        MemberDto member = (MemberDto) session.getAttribute("member");
        if (member == null) {
            return "redirect:/login";
        }
        String userEmail = member.getUser_email();
        //화면에 표시될 사진이 있는지 db 검색 있으면 주소값을 가져와서 model 에 넣어준다
        //없으면 패스
        ImageDto imageDto = service.callDogProf(userEmail, category);
        if (imageDto!= null) {
        model.addAttribute("pt_address", imageDto.getPt_adres());
            return "dangofficetest";
        }
        return "dangofficetest";
    }
    //requestParam으로 변수명 지정. ajax서 데이터 보낼때 이걸로 세팅해야 함
    @ResponseBody
    @PostMapping("/dangofficetest/upload")
    public ResponseEntity<List<String>> uploadFile(@RequestParam("images") List<MultipartFile> multipartFile, HttpSession session) throws IOException {
        //소유자 기록을 위해 세션에서 아이디를 가져온다.
        MemberDto dto = (MemberDto) session.getAttribute("member");
        //업로드 작업 주소를 담은 리스트가 반환된다
        List<String> upload =s3UploadService.upload(category, multipartFile);
        //db에 업로드. 필요한 것은 id, 카테고리, 주소. 여기선 단일 업로드이기때문에 get(0) 다수 업로드시엔 for문
        service.inputDogProf(dto.getUser_email(), upload.get(0), category);
        //주소 리스트를 ajax로 반환
        return ResponseEntity.ok(upload);
    }
}
