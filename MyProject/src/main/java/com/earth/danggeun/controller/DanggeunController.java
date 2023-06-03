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

	@GetMapping("/write")
	public String write(DanggeunSearchItem dsc, Model m) {
		try {
			List<DanggeunTypeDTO> typeList = danggeunService.getTypeList();
			m.addAttribute("typeList", typeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "danggeunwrite";
	}
	
//	@PostMapping("/write")
//	public String write(DanggeunDTO danggeunDTO, String location1, String location2, String location3) {
//		danggeunDTO.setLocation(location3.split(",")[0]);
//		String writer_email = danggeunDTO.getWriter_email();
//		
//		String location_name = location1.split(",")[1] + " " + location2.split(",")[1] + " " + location3.split(",")[1] + " "; 
//		danggeunDTO.setLocation_name(location_name);
//		try {
//			DangMemberDTO dangMemberDTO = dangMemberService.readDangMember(writer_email);
//			String writer_name = dangMemberDTO.getName();
//			danggeunDTO.setWriter_name(writer_name);
//			danggeunService.registerDanggeun(danggeunDTO);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return "redirect:/danggeun/list";
//	}
	
	@PostMapping("/delete")
	public String delete() {
		return "delete";
	}
	
	@GetMapping("/view")
	public String view(Integer id, String login_email, Model m) {
		
		try {
			DanggeunDTO danggeunDTO = danggeunService.readDanggeun(id, login_email);
			m.addAttribute("danggeunDTO", danggeunDTO);
		} catch (Exception e) {
			e.printStackTrace();
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
