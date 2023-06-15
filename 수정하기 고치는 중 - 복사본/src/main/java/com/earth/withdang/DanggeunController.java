package com.earth.withdang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.earth.domain.AddressDongDTO;
import com.earth.domain.AddressSidoDTO;
import com.earth.domain.AddressSigoonDTO;
import com.earth.domain.DanggeunInfoDTO;
import com.earth.domain.DanggeunPageResolver;
import com.earth.domain.DanggeunPhotoDTO;
import com.earth.domain.DanggeunTypeDTO;
import com.earth.domain.DanggeunZzimDTO;
import com.earth.domain.DanggeunSearchItem;
import com.earth.service.DanggeunPhotoService;
import com.earth.service.DanggeunService;
import com.earth.service.LocationService;
import com.earth.upload.S3UploadService;

@Controller
@RequestMapping("/danggeun")
public class DanggeunController {

	private DanggeunService danggeunService;
	private LocationService locationService;
	private S3UploadService s3UploadService;
	private DanggeunPhotoService danggeunPhotoService;
	
	public DanggeunController(DanggeunService danggeunService, LocationService locationService, S3UploadService s3UploadService, DanggeunPhotoService danggeunPhotoService) {
		this.danggeunService = danggeunService;
		this.locationService = locationService;
		this.s3UploadService = s3UploadService;
		this.danggeunPhotoService = danggeunPhotoService;
	}
	
	@GetMapping("/list")
	public String danggeunList(DanggeunSearchItem dsc, Model m, HttpServletRequest request) {
		
		if(!loginCheck(request)) {
			return "redirect:/login";
		}
		
		String login_nickname = (String) request.getSession().getAttribute("nickname");
		
		try {
			int totalCnt = danggeunService.countDanggeunListByOption(dsc);
			DanggeunPageResolver danggeunPageResolver = new DanggeunPageResolver(totalCnt, dsc);
			List<DanggeunInfoDTO> list = danggeunService.readDanggeunListByOption(dsc, login_nickname);
			List<DanggeunTypeDTO> typeList = danggeunService.getTypeList();
			
			m.addAttribute("totalCnt", totalCnt);
			m.addAttribute("dpr", danggeunPageResolver);
			m.addAttribute("list", list);
			m.addAttribute("typeList", typeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "danggeun";
		
	}

	@GetMapping("/write")
	public String write(Model m, HttpServletRequest request) {
		if(!loginCheck(request)) {
			return "redirect:/login";
		}
		try {
			List<DanggeunTypeDTO> typeList = danggeunService.getTypeList();
			m.addAttribute("mode", "new");
			m.addAttribute("typeList", typeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "danggeunwrite";
	}
	
	@PostMapping("/write")
	public String write(DanggeunInfoDTO danggeunInfoDTO, RedirectAttributes rattr, Model m, MultipartHttpServletRequest request) throws Exception {
		List<DanggeunTypeDTO> typeList = null;
	    List<String> addressList = new ArrayList<String>();
	    String category = "danggeun";
		
	    
	    Iterator<String> fileNames = request.getFileNames();
	    while (fileNames.hasNext()) {
	        String paramName = fileNames.next();
	        MultipartFile file = request.getFile(paramName);
	        int sequence = Integer.parseInt(paramName.replace("imgbox", "")) - 1;
	        
	        List<String> upload = s3UploadService.upload(category, Collections.singletonList(file));
	        addressList.add(sequence, upload.get(0));
	    }
	    
		try {
			typeList = danggeunService.getTypeList();
	        
			danggeunService.registerDanggeun(danggeunInfoDTO, addressList);
			
			rattr.addFlashAttribute("msg", "WRT_OK");
			return "redirect:/danggeun/list";
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("typeList", typeList);
			m.addAttribute("mode", "new");
			m.addAttribute("danggeunInfoDTO", danggeunInfoDTO);
			m.addAttribute("msg", "WRT_ERR");
			return "danggeunwrite";
		}
	}

	@GetMapping("/modify")
	public String modify(Integer id, HttpSession session, RedirectAttributes rattr, Model m, HttpServletRequest request) {
		if(!loginCheck(request)) {
			return "redirect:/login";
		}
		String login_nickname = (String) session.getAttribute("nickname");
		try {
			DanggeunInfoDTO danggeunInfoDTO = danggeunService.readDanggeun(id, login_nickname);
			List<DanggeunTypeDTO> typeList = danggeunService.getTypeList();
			List<DanggeunPhotoDTO> photoList = danggeunPhotoService.showPhoto(id);
			
			m.addAttribute("danggeunInfoDTO", danggeunInfoDTO);
			m.addAttribute("photoList", photoList);
			m.addAttribute("mode", "modify");
			m.addAttribute("typeList", typeList);
			return "danggeunwrite";
		} catch (Exception e) {
			e.printStackTrace();
			rattr.addAttribute("msg", "MOD_ERR");
			return "redirect:/danggeun/list";
		}
	}
	
	@PostMapping("/modify")
	public String modify(DanggeunInfoDTO danggeunInfoDTO, RedirectAttributes rattr, Model m, MultipartHttpServletRequest request) throws IOException {
		
		List<DanggeunTypeDTO> typeList = null;
		List<DanggeunPhotoDTO> photoList = null;
		Enumeration<String> parameterNames = request.getParameterNames();
	    Map<Integer, String> addressMap = new HashMap<Integer, String>();
	    String category = "danggeun";
	    
	    while (parameterNames.hasMoreElements()) {
	    	String paramName = parameterNames.nextElement();
	    	if(paramName.startsWith("imgbox")) {
	            String paramValue = request.getParameter(paramName);
	            int sequence = Integer.parseInt(paramName.replace("imgbox", "")) - 1;
	            addressMap.put(sequence, paramValue);
	    	}
        }
	    
	    Iterator<String> fileNames = request.getFileNames();
	    while (fileNames.hasNext()) {
	        String paramName = fileNames.next();
	        MultipartFile file = request.getFile(paramName);
	        int sequence = Integer.parseInt(paramName.replace("imgbox", "")) - 1;
	        List<String> upload = s3UploadService.upload(category, Collections.singletonList(file));
	        addressMap.put(sequence, upload.get(0));
	    }
	    
	    // imageMap을 사용하여 이미지 데이터 처리
	    try {
	    	typeList = danggeunService.getTypeList();
	    	photoList = danggeunPhotoService.showPhoto(danggeunInfoDTO.getId());
			danggeunService.modifyDangguen(danggeunInfoDTO, addressMap);
			rattr.addFlashAttribute("msg", "MOD_OK");
			return "redirect:/danggeun/list";
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("danggeunInfoDTO", danggeunInfoDTO);
			m.addAttribute("photoList", photoList);
			m.addAttribute("mode", "modify");
			m.addAttribute("msg", "MOD_ERR");
			m.addAttribute("typeList", typeList);
			return "danggeunwrite";
		}
	}
	
	@PostMapping("/delete")
	public String delete(Integer id, Integer page, Integer pageSize, String keyword, String option, Integer type_id, String sido_code, String sigoon_code, String dong_code, HttpSession session, RedirectAttributes rattr) {
		String msg = "DEL_OK";
		try {
			danggeunService.removeDanggeun(id);
		} catch (Exception e) {
			e.printStackTrace();
			msg = "DEL_ERR";
		}
		
		rattr.addAttribute("page", page);
		rattr.addAttribute("pageSize", pageSize);
		rattr.addAttribute("keyword", keyword);
		rattr.addAttribute("option", option);
		rattr.addAttribute("type_id", type_id);
		rattr.addAttribute("sido_code", sido_code);
		rattr.addAttribute("sigoon_code", sigoon_code);
		rattr.addAttribute("dong_code", dong_code);
		rattr.addFlashAttribute("msg", msg);
		
		return "redirect:/danggeun/list";
	}
	
	@GetMapping("/view")
	public String view(Integer id, DanggeunSearchItem dsc, Model m, HttpSession session, HttpServletRequest request) {
		if(!loginCheck(request)) {
			return "redirect:/login";
		}
		String login_nickname = (String) session.getAttribute("nickname");
		try {
			DanggeunInfoDTO danggeunInfoDTO = danggeunService.readDanggeun(id, login_nickname);
			List<DanggeunPhotoDTO> photoList = danggeunPhotoService.showPhoto(id);
			m.addAttribute("danggeunInfoDTO", danggeunInfoDTO);
			m.addAttribute("photoList", photoList);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/danggeun/list";
		}
		
		return "danggeunView";
	}

	@PostMapping(value = "/pushzzim", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String> pushzzim(@ModelAttribute DanggeunZzimDTO danggeunZzimDTO) {
		try {
			danggeunService.pushZzim(danggeunZzimDTO.getUser_nickname(), danggeunZzimDTO.getDanggeun_id());
			return new ResponseEntity<String>("SCR_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("SCR_ERR", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/cancelzzim", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String> cancelzzim(@ModelAttribute DanggeunZzimDTO danggeunZzimDTO) {
		try {
			danggeunService.cancelZzim(danggeunZzimDTO.getUser_nickname(), danggeunZzimDTO.getDanggeun_id());
			return new ResponseEntity<String>("CAN_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("CAN_ERR", HttpStatus.BAD_REQUEST);
		}
	}	
	
	@GetMapping("/sido")
	@ResponseBody
	public ResponseEntity<List<AddressSidoDTO>> sido() throws IOException {
		try {
			List<AddressSidoDTO> sidoList = locationService.readSido();
			return new ResponseEntity<List<AddressSidoDTO>>(sidoList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<AddressSidoDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/sigoon")
	@ResponseBody
	public ResponseEntity<List<AddressSigoonDTO>> sigoon(String ctprvn_cd) {
		try {
			List<AddressSigoonDTO> sigoonList = locationService.readSigoon(ctprvn_cd);
			return new ResponseEntity<List<AddressSigoonDTO>>(sigoonList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<AddressSigoonDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/dong")
	@ResponseBody
	public ResponseEntity<List<AddressDongDTO>> dong(String sig_cd) {
		try {
			List<AddressDongDTO> dongList = locationService.readDong(sig_cd);
			return new ResponseEntity<List<AddressDongDTO>>(dongList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<AddressDongDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	private boolean loginCheck(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session != null && session.getAttribute("nickname") != null && session.getAttribute("nickname") != "";
	}
}
