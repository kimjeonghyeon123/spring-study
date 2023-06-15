package com.earth.withdang;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.earth.domain.ComuDTO;
import com.earth.domain.ImageDto;
import com.earth.domain.MemberDto;
import com.earth.domain.PageResolver;
import com.earth.domain.SearchItem;
import com.earth.mapper.ComuMapper;
import com.earth.service.ComuService;
import com.earth.service.ImageService;
import com.earth.upload.S3UploadService;

@Controller
@RequestMapping("/dangcomu")
public class ComuController {

	@Autowired
	ComuService comuService;
	
	@Autowired
	ComuMapper comuMapper;

	@Autowired
	private S3UploadService s3UploadService;

	@Autowired
	ImageService imageService;

	@GetMapping("/list")
	public String list(Integer post_ctgr_id, SearchItem sc, Model m, HttpServletRequest request) {

		if (!loginCheck(request)) {
			m.addAttribute("isLoggedIn", false);
		} else {
			m.addAttribute("isLoggedIn", true);
		}

		try {
			List<ComuDTO> list;
			int totalCnt;

			if (post_ctgr_id == null) {
				totalCnt = comuService.getSearchResultCnt(sc);
				m.addAttribute("totalCnt", totalCnt);
				list = comuService.getSearchSelectPage(sc);
			} else {
				totalCnt = comuService.getCategoryResultCnt(post_ctgr_id, sc);
				m.addAttribute("totalCnt", totalCnt);
				list = comuService.getSearchCategoryPage(post_ctgr_id, sc);
				System.out.println("option = " + sc.getOption());
			}

			PageResolver pageResolver = new PageResolver(totalCnt, sc);

			m.addAttribute("list", list);
			m.addAttribute("pr", pageResolver);

			// 추가적인 모델 속성 및 처리

		} catch (Exception e) {
			e.printStackTrace();
			// 예외 처리
		}

		return "dangcomu";
	}

	@GetMapping("/read")
	public String read(Integer post_id, SearchItem sc, Model m, HttpSession session) {

		try {
			ComuDTO comuDTO = comuService.readPost(post_id);
			String user_email = comuDTO.getUser_email();
			List<ImageDto> images = imageService.callComuPhoto(user_email, post_id, "comuPost");
			m.addAttribute("comuDTO", comuDTO);
			m.addAttribute("images", images);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/dangcomu/list";
		}

		return "view";
	}

	@GetMapping("/post")
	public String post(Model m, HttpServletRequest request) {

		if (!loginCheck(request)) {
			return "redirect:/login";
		}

		return "write";

	}

	@PostMapping("/post")
	public String post(ComuDTO comuDTO, @RequestParam(value = "image1", required = false) List<MultipartFile> image1,
			@RequestParam(value = "image2", required = false) List<MultipartFile> image2,
			@RequestParam(value = "image3", required = false) List<MultipartFile> image3,
			@RequestParam(value = "image4", required = false) List<MultipartFile> image4, RedirectAttributes ra,
			Model m, HttpSession session) {
		
		try {

			MemberDto memberDto = (MemberDto) session.getAttribute("member");
			String user_email = memberDto.getUser_email();
			String user_name = comuMapper.selectUserName(user_email);
			comuDTO.setUser_email(user_email);
			comuDTO.setUser_name(user_name);
			String category = "comuPost";
			
			int post_id = comuService.post(comuDTO);

			if (!image1.get(0).getOriginalFilename().equals("")) {
				List<String> upload = s3UploadService.upload(category, image1);
				imageService.inputComuPhoto(user_email, post_id, upload.get(0), category);
			}

			if (!image2.get(0).getOriginalFilename().equals("")) {
				List<String> upload = s3UploadService.upload(category, image2);
				imageService.inputComuPhoto(user_email, post_id, upload.get(0), category);
			}

			if (!image3.get(0).getOriginalFilename().equals("")) {
				List<String> upload = s3UploadService.upload(category, image3);
				imageService.inputComuPhoto(user_email, post_id, upload.get(0), category);
			}

			if (!image4.get(0).getOriginalFilename().equals("")) {
				List<String> upload = s3UploadService.upload(category, image4);
				imageService.inputComuPhoto(user_email, post_id, upload.get(0), category);
			}

			ra.addFlashAttribute("msg", "WRT_OK");

			return "redirect:/dangcomu/list";

		} catch (Exception e) {
			e.printStackTrace();
			ra.addFlashAttribute("msg", "WRT_ERR");
			return "write";
		}
	}

	@PostMapping("/delete")
	public String delete(Integer post_id, Integer page, Integer pageSize, RedirectAttributes ra, HttpSession session) {

		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		String user_email = memberDto.getUser_email();

		String category = "comuPost";

		try {
			comuService.deletePost(post_id, user_email);
			imageService.deleteComuPhoto(user_email, post_id, category);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ra.addAttribute("page", page);
		ra.addAttribute("pageSize", pageSize);

		return "redirect:/dangcomu/list";

	}

	@GetMapping("/update")
	public String update(Integer post_id, Model m, HttpSession session, HttpServletRequest request) {

		ComuDTO comuDTO;
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		String user_email = memberDto.getUser_email();

		imageService.deleteComuPhoto(user_email, post_id, "comuPost");

		try {
			comuDTO = comuService.readPost(post_id);
			m.addAttribute("comuDTO", comuDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "edit";

	}

	@PostMapping("/update")
	public String update(ComuDTO comuDTO, Integer page, Integer pageSize,
			@RequestParam(value = "image1", required = false) List<MultipartFile> image1,
			@RequestParam(value = "image2", required = false) List<MultipartFile> image2,
			@RequestParam(value = "image3", required = false) List<MultipartFile> image3,
			@RequestParam(value = "image4", required = false) List<MultipartFile> image4, RedirectAttributes ra,
			Model m, HttpSession session) {

		MemberDto member = (MemberDto) session.getAttribute("member");
		String user_email = member.getUser_email();
		comuDTO.setUser_email(user_email);
		String category = "comuPost";

		try {

			int post_id = comuDTO.getPost_id();

			if (!image1.get(0).getOriginalFilename().equals("")) {
				List<String> upload = s3UploadService.upload(category, image1);
				imageService.inputComuPhoto(user_email, post_id, upload.get(0), category);
			}

			if (!image2.get(0).getOriginalFilename().equals("")) {
				List<String> upload = s3UploadService.upload(category, image2);
				imageService.inputComuPhoto(user_email, post_id, upload.get(0), category);
			}

			if (!image3.get(0).getOriginalFilename().equals("")) {
				List<String> upload = s3UploadService.upload(category, image3);
				imageService.inputComuPhoto(user_email, post_id, upload.get(0), category);
			}

			if (!image4.get(0).getOriginalFilename().equals("")) {
				List<String> upload = s3UploadService.upload(category, image4);
				imageService.inputComuPhoto(user_email, post_id, upload.get(0), category);
			}

			if (comuService.updatePost(comuDTO) != 1)
				throw new Exception("Update Fail");

			
			ra.addAttribute("page", page);
			ra.addAttribute("pageSize", pageSize);
			ra.addFlashAttribute("msg", "UPDATE_OK");

			return "redirect:/dangcomu/list";

		} catch (Exception e) {
			e.printStackTrace();

			m.addAttribute("comuDTO", comuDTO);
			m.addAttribute("page", page); 
			m.addAttribute("pageSize", pageSize);
			m.addAttribute("msg", "UPDATE_ERR");

			return "edit";
		}

	}

	// 로그인 체크
	public boolean loginCheck(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session.getAttribute("member") != null;
	}
}