package com.earth.danggeun.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
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

import com.earth.danggeun.domain.DangMemberDTO;
import com.earth.danggeun.domain.DanggeunDTO;
import com.earth.danggeun.domain.DanggeunPageResolver;
import com.earth.danggeun.domain.DanggeunSearchItem;
import com.earth.danggeun.domain.DanggeunTypeDTO;
import com.earth.danggeun.domain.DongDTO;
import com.earth.danggeun.domain.SidoDTO;
import com.earth.danggeun.domain.SigoonDTO;
import com.earth.danggeun.domain.ZzimDanggeunDTO;
import com.earth.danggeun.service.DangMemberService;
import com.earth.danggeun.service.DanggeunService;
import com.earth.danggeun.service.LocationService;

@Controller
@RequestMapping("/danggeun")
public class DanggeunController {

	private DanggeunService danggeunService;
	private DangMemberService dangMemberService;
	private LocationService locationService;
	
	public DanggeunController(DanggeunService danggeunService, DangMemberService dangMemberService, LocationService locationService) {
		this.danggeunService = danggeunService;
		this.dangMemberService = dangMemberService;
		this.locationService = locationService;
	}
	
	@GetMapping("/list")
	public String danggeunList(DanggeunSearchItem dsc, Model m, HttpServletRequest request) {
		if(!loginCheck(request)) {
			return "redirect:/login/login?toURL=" + request.getRequestURL();
		}
		String login_email = (String) request.getSession().getAttribute("email");

		try {
			int totalCnt = danggeunService.countDanggeunListByOption(dsc);
			DanggeunPageResolver danggeunPageResolver = new DanggeunPageResolver(totalCnt, dsc);
			List<DanggeunDTO> list = danggeunService.readDanggeunListByOption(dsc, login_email);
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

	@GetMapping("/chat")
	public String write(DanggeunSearchItem dsc, Model m) {
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
	public String write(DanggeunDTO danggeunDTO, RedirectAttributes rattr, Model m) {
		List<DanggeunTypeDTO> typeList = null;
		try {
			typeList = danggeunService.getTypeList();
			danggeunService.registerDanggeun(danggeunDTO);
			rattr.addFlashAttribute("msg", "WRT_OK");
			return "redirect:/danggeun/list";
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("typeList", typeList);
			m.addAttribute("mode", "new");
			m.addAttribute("danggeunDTO", danggeunDTO);
			m.addAttribute("msg", "WRT_ERR");
			return "danggeunwrite";
		}
	}
	
	@GetMapping("/modify")
	public String modify(Integer id, HttpSession session, RedirectAttributes rattr, Model m) {
		String login_email = (String) session.getAttribute("email");
		try {
			DanggeunDTO danggeunDTO = danggeunService.readDanggeun(id, login_email);
			List<DanggeunTypeDTO> typeList = danggeunService.getTypeList();
			m.addAttribute("danggeunDTO", danggeunDTO);
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
	public String modify(DanggeunDTO danggeunDTO, HttpSession session, RedirectAttributes rattr, Model m) {
		try {
			danggeunService.modifyDangguen(danggeunDTO);
			
			rattr.addFlashAttribute("msg", "MOD_OK");
			return "redirect:/danggeun/list";
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("danggeunDTO", danggeunDTO);
			m.addAttribute("msg", "MOD_ERR");
			return "danggeunView";
		}
	}
	
	@PostMapping("/delete")
	public String delete(Integer id, Integer page, Integer pageSize, String keyword, String option, Integer type_id, String sido_code, String sigoon_code, String dong_code, String login_email, HttpSession session, RedirectAttributes rattr) {
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
		rattr.addAttribute("login_email", login_email);
		rattr.addFlashAttribute("msg", msg);
		
		return "redirect:/danggeun/list";
	}
	
	@GetMapping("/view")
	public String view(Integer id, DanggeunSearchItem dsc, Model m, HttpSession session) {
		String login_email = (String) session.getAttribute("email");
		try {
			DanggeunDTO danggeunDTO = danggeunService.readDanggeun(id, login_email);
			m.addAttribute("danggeunDTO", danggeunDTO);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/danggeun/list";
		}
		
		return "danggeunView";
	}
	
	@PostMapping(value = "/pushzzim", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String> pushzzim(@ModelAttribute ZzimDanggeunDTO zzimDanggeunDTO) {
		try {
			danggeunService.pushZzim(zzimDanggeunDTO.getMember_email(), zzimDanggeunDTO.getDanggeun_id());
			return new ResponseEntity<String>("SCR_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("SCR_ERR", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/cancelzzim", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<String> cancelzzim(@ModelAttribute ZzimDanggeunDTO zzimDanggeunDTO) {
		try {
			danggeunService.cancelZzim(zzimDanggeunDTO.getMember_email(), zzimDanggeunDTO.getDanggeun_id());
			return new ResponseEntity<String>("CAN_OK", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("CAN_ERR", HttpStatus.BAD_REQUEST);
		}
	}
	
	private boolean loginCheck(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session != null && session.getAttribute("email") != null && session.getAttribute("email") != "";
	}
	
	@GetMapping("/sido")
	@ResponseBody
	public ResponseEntity<List<SidoDTO>> sido() throws IOException {
		try {
			List<SidoDTO> sidoList = locationService.readSido();
			return new ResponseEntity<List<SidoDTO>>(sidoList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<SidoDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/sigoon")
	@ResponseBody
	public ResponseEntity<List<SigoonDTO>> sigoon(String ctprvn_cd) {
		try {
			List<SigoonDTO> sigoonList = locationService.readSigoon(ctprvn_cd);
			return new ResponseEntity<List<SigoonDTO>>(sigoonList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<SigoonDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/dong")
	@ResponseBody
	public ResponseEntity<List<DongDTO>> dong(String sig_cd) {
		try {
			List<DongDTO> dongList = locationService.readDong(sig_cd);
			return new ResponseEntity<List<DongDTO>>(dongList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<DongDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
