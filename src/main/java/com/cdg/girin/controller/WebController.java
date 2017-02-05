package com.cdg.girin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

	@RequestMapping("/index")
	public String index(Model model) {
		
		model.addAttribute("data", "hello world");
		
		return "index";
	}
	
	@RequestMapping("/blog")
	public String blog(Model model) {
		
		return "blog";
	}
}
