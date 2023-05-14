package com.earth.korea.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.earth.korea.domain.Person;


@Controller
public class RestfulController {

	@GetMapping("/ajax")
	public String ajax() {
		return "ajax";
	}
	
	@ResponseBody
	@PostMapping("/send")
	public Person test(@RequestBody Person p) {
		System.out.println("p= " + p);
		p.setName("earth0630");
		p.setAge(p.getAge() + 2023);
		
		return p;
	}
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
}










