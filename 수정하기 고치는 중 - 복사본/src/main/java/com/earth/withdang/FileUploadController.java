package com.earth.withdang;

import com.earth.upload.S3UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FileUploadController {
    @Autowired
    private final S3UploadService s3UploadService;

    @GetMapping("/upload")
    public String uploadGet() {
        return "uploadImage";
    }
    //다중업로드 기준임
    @ResponseBody
    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadFile(@RequestParam("images") List<MultipartFile> multipartFile, @RequestParam(name = "category") String category) throws IOException {
        System.out.println("category:"+category);
        System.out.println(multipartFile.size());
        System.out.println(multipartFile.get(0).getOriginalFilename());
        List<String> upload = s3UploadService.upload(category, multipartFile);
        for (int i = 0; i < upload.size(); i++) {
            System.out.println(upload.get(i));
        }
        return ResponseEntity.ok(upload);
    }

    @DeleteMapping("/deleteFile")
    @ResponseBody
    public ResponseEntity<String> deleteFile(@RequestParam String fileName) {
        s3UploadService.deleteFile(fileName);
        System.out.println("deleted: "+fileName);
        return ResponseEntity.ok(fileName);
    }
}
