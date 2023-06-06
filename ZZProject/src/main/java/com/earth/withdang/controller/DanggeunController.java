package com.earth.withdang.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.earth.withdang.domain.AddressDongDTO;
import com.earth.withdang.domain.AddressSidoDTO;
import com.earth.withdang.domain.AddressSigoonDTO;
import com.earth.withdang.domain.DanggeunInfoDTO;
import com.earth.withdang.domain.DanggeunPageResolver;
import com.earth.withdang.domain.DanggeunTypeDTO;
import com.earth.withdang.domain.DanggeunZzimDTO;
import com.earth.withdang.domain.DanggeunSearchItem;
import com.earth.withdang.service.DanggeunService;
import com.earth.withdang.service.LocationService;

@Controller
@RequestMapping("/danggeun")
public class DanggeunController {

	private DanggeunService danggeunService;
	private LocationService locationService;
	
	public DanggeunController(DanggeunService danggeunService, LocationService locationService) {
		this.danggeunService = danggeunService;
		this.locationService = locationService;
	}
	
	@GetMapping("/list")
	public String danggeunList(DanggeunSearchItem dsc, Model m, HttpServletRequest request) {
		if(!loginCheck(request)) {
			return "redirect:/login/login?toURL=" + request.getRequestURL();
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
	
	private boolean loginCheck(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session != null && session.getAttribute("email") != null && session.getAttribute("email") != "";
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
	public String write(DanggeunInfoDTO danggeunInfoDTO, RedirectAttributes rattr, Model m) {
		List<DanggeunTypeDTO> typeList = null;
		try {
			typeList = danggeunService.getTypeList();
			danggeunService.registerDanggeun(danggeunInfoDTO);
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
			m.addAttribute("danggeunInfoDTO", danggeunInfoDTO);
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
	public String modify(DanggeunInfoDTO danggeunInfoDTO, HttpSession session, RedirectAttributes rattr, Model m) {
		List<DanggeunTypeDTO> typeList = null;
		try {
			danggeunService.modifyDangguen(danggeunInfoDTO);
			typeList = danggeunService.getTypeList();
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
			m.addAttribute("danggeunInfoDTO", danggeunInfoDTO);
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
