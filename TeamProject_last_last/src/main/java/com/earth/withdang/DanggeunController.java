package com.earth.withdang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
	
	private boolean loginCheck(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session != null && session.getAttribute("nickname") != null && session.getAttribute("nickname") != "";
	}

	@GetMapping("/write")
	public String write(Model m) {
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
	public String write(DanggeunInfoDTO danggeunInfoDTO, @RequestParam(value = "image1", required = false) List<MultipartFile> image1,
			@RequestParam(value = "image2", required = false) List<MultipartFile> image2,
			@RequestParam(value = "image3", required = false) List<MultipartFile> image3, RedirectAttributes rattr, Model m) {
		
		List<DanggeunTypeDTO> typeList = null;
		List<String> addressList = new ArrayList<String>();
		String category = "danggeun";
		
		try {
			typeList = danggeunService.getTypeList();
			
			if (!image1.get(0).getOriginalFilename().equals("")) {
				List<String> upload = s3UploadService.upload(category, image1);
				addressList.add(upload.get(0));
			}
			
			if (!image2.get(0).getOriginalFilename().equals("")) {
				List<String> upload = s3UploadService.upload(category, image2);
				addressList.add(upload.get(0));
			}

			if (!image3.get(0).getOriginalFilename().equals("")) {
				List<String> upload = s3UploadService.upload(category, image3);
				addressList.add(upload.get(0));
			}
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
	public String modify(Integer id, HttpSession session, RedirectAttributes rattr, Model m) {
		String login_nickname = (String) session.getAttribute("nickname");
		try {
			DanggeunInfoDTO danggeunInfoDTO = danggeunService.readDanggeun(id, login_nickname);
			List<DanggeunTypeDTO> typeList = danggeunService.getTypeList();
			List<DanggeunPhotoDTO> photoList = danggeunPhotoService.showPhoto(id);
			int photoCnt = photoList.size();
			
			m.addAttribute("danggeunInfoDTO", danggeunInfoDTO);
			m.addAttribute("photoList", photoList);
			m.addAttribute("photoCnt", photoCnt);
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
	public String modify(DanggeunInfoDTO danggeunInfoDTO, @RequestParam(value = "image1", required = false) List<MultipartFile> image1,
			@RequestParam(value = "image2", required = false) List<MultipartFile> image2,
			@RequestParam(value = "image3", required = false) List<MultipartFile> image3,
			@RequestParam(value = "image4", required = false) List<MultipartFile> image4, HttpSession session, RedirectAttributes rattr, Model m) {
		List<DanggeunTypeDTO> typeList = null;
		List<String> addressList = new ArrayList<String>();
		String category = "danggeun";
		
		try {
			typeList = danggeunService.getTypeList();
			
			if (!image1.get(0).getOriginalFilename().equals("")) {
				List<String> upload = s3UploadService.upload(category, image1);
				addressList.add(upload.get(0));
			}
			
			if (!image2.get(0).getOriginalFilename().equals("")) {
				List<String> upload = s3UploadService.upload(category, image2);
				addressList.add(upload.get(0));
			}

			if (!image3.get(0).getOriginalFilename().equals("")) {
				List<String> upload = s3UploadService.upload(category, image3);
				addressList.add(upload.get(0));
			}

			if (!image4.get(0).getOriginalFilename().equals("")) {
				List<String> upload = s3UploadService.upload(category, image4);
				addressList.add(upload.get(0));
			}
			
			danggeunService.modifyDangguen(danggeunInfoDTO, addressList);
			
			rattr.addFlashAttribute("msg", "MOD_OK");
			return "redirect:/danggeun/list";
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("danggeunInfoDTO", danggeunInfoDTO);
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
	public String view(Integer id, DanggeunSearchItem dsc, Model m, HttpSession session) {
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
}
