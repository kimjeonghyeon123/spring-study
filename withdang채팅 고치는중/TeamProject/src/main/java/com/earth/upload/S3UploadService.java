package com.earth.upload;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class S3UploadService {
    //버켓명은 properties에서 가져다 쓰도록 세팅해둠
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    //지역과 액세스 키 등이 조합된 아마존 객체
    @Autowired
    private AmazonS3 amazonS3;

    String bucketUrl = "https://withdimagebucket.s3.ap-northeast-2.amazonaws.com/";



    public List<String> upload(String category,List<MultipartFile> multipartFiles) throws IOException {
        List<String> fileNameList = new ArrayList<>();

        multipartFiles.forEach(file->{
        //중복없게 이름을 난수화시키고 앞에 카테고리 / 파일이름 식의 구성으로 만든다
            String s3FileName = createFileName(file.getOriginalFilename(), category);
        //로컬에 저장했다가 업로드 안해도 되게 메타데이터 작성
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());

            try (InputStream inputStream =file.getInputStream()){
                //업로드용 객체 포장. 여기서 접근여부세팅을 걸 수 있는데
                //지금은 CannedAccessControlList.PublicRead 퍼블릭 리드로 셋팅
                PutObjectResult putObjectResult = amazonS3.putObject(new PutObjectRequest(bucket, s3FileName, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
                putObjectResult.getContentMd5();
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"업로드 실패");
            }
            //업로드한 것을 외부에서 불러오기 위해서 붙이는 주소값
            fileNameList.add(bucketUrl+s3FileName);
        });
        return fileNameList;
    }

    public String createFileName(String fileName, String category){
        return category + "/" + UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    //확장자 추출처리
    public String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
//dogProfile/5c014113-9937-4d15-9d61-234a5a7310ff.png    의 형태를 넣어줘야 함
    //업로드시에 버켓 주소를 붙였으니 떼어주는 작업이 필요
    public void deleteFile(String fileName) {
        fileName = fileName.substring(bucketUrl.length(), fileName.length());
//        amazonS3.doesObjectExist()
        amazonS3.deleteObject(bucket, fileName);
    }


}
