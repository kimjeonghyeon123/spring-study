//package com.earth.withdang;
//
//import com.earth.excel.service.ExcelUpload;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.io.File;
//import java.io.IOException;
//
//@Controller
//@RequestMapping("/excel")
//@Slf4j
//public class ExcelUploadController {
//    @Autowired
//    ExcelUpload excelUpload;
//
//    @RequestMapping(value = "/upload" ,method = RequestMethod.GET)
//    public String excelUp() {
//        return "upload";
//    }
//
//
//    @ResponseBody
//    @RequestMapping(value = "/excelUploadAjax.do", method = RequestMethod.POST)
//    public ModelAndView excelUploadAjax(MultipartHttpServletRequest request) {
//        System.out.println("excelUpload");
//        MultipartFile excelFile = request.getFile("excelFile");
//        System.out.println(excelFile);
//        File destFile = new File("c:\\upload\\" + excelFile.getOriginalFilename());
//        try {
//            excelFile.transferTo(destFile);
//        } catch (IOException e) {
//            destFile.delete();
//            throw new RuntimeException(e);
//        }
//        log.info("data:{}",destFile);
//        log.info("data:{}",destFile.length());
//        try {
//            excelUpload.insertDB(destFile);
//        } catch (NullPointerException e) {
//            destFile.delete();
//            throw new NullPointerException();
//        }
//
//        destFile.delete();
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("/upload");
//        System.out.println("uploaded");
//        return mav;
//    }
//
//
//}
////https://eugene-kim.tistory.com/46
////https://daydreamer-92.tistory.com/42