package com.earth.withdang;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.earth.domain.CommentDTO;
import com.earth.domain.MemberDto;
import com.earth.service.ComuService;

@Controller
@RequestMapping("/dangcomu")
public class CommentController {
	
	@Autowired
	private ComuService comuService;
	
	@GetMapping("/comments/{post_id}")
	public ResponseEntity<List<CommentDTO>> getComments(@PathVariable("post_id") Integer post_id, HttpServletRequest request) {
		try {
			List<CommentDTO> comments = comuService.getCommentsByPostId(post_id);
			return new ResponseEntity<>(comments, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/comments/{post_id}")
	public ResponseEntity<?> createComment(@PathVariable("post_id") Integer post_id, @RequestBody CommentDTO commentDTO, HttpServletRequest request) {
		
		if (!loginCheck(request))
			return new ResponseEntity<String>("Not Login", HttpStatus.FORBIDDEN); // 403 에러코드를 넘기고, ajax로 에러코드를 받아서 리다이렉트 
		
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		String user_email = memberDto.getUser_email();
		commentDTO.setUser_email(user_email);
		
		try {
			if (comuService.comment(commentDTO) == 1) 
				return new ResponseEntity<>("Comment created successfully", HttpStatus.OK);
			else
				return new ResponseEntity<>("Failed to create comment", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to create comment", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@DeleteMapping("/comments/{post_id}/{cmt_id}")
	public ResponseEntity<String> deleteComment(@PathVariable("post_id") Integer post_id, @PathVariable("cmt_id") Integer cmt_id, HttpSession session) {
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		String user_email = memberDto.getUser_email();
		
		try {
			if (comuService.deleteComment(cmt_id, user_email) == 1)
				return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
			else 
				return new ResponseEntity<>("Failed to delete comment", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Failed to delete comment", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 로그인 체크
	public boolean loginCheck(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return session.getAttribute("member") != null;
	}
	
	// 유저 이메일을 가져오는 함수
	@RequestMapping("/comments/getUserEmail")
	public ResponseEntity<String> getUserEmail(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberDto memberDto = (MemberDto) session.getAttribute("member");
		
		if (memberDto == null)
			return new ResponseEntity<String>("Not Login", HttpStatus.OK);
		
		String user_email = memberDto.getUser_email();
		return new ResponseEntity<String>(user_email, HttpStatus.OK);
	}
}
