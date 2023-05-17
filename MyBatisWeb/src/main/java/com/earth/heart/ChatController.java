package com.earth.heart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

	@GetMapping("/chat")
	public void chat(Model model) {
		
		CustomerUser user = new CustomerUser(1, "kim"); 
		model.addAttribute("userid", user.getUsername());
	}
}