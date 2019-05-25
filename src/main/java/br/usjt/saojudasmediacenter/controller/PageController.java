package br.usjt.saojudasmediacenter.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController implements ErrorController{
	
	@GetMapping("/")
	public String home() {
		return "redirect:/conteudos/timeline";
	}
	
	@RequestMapping("/error")
	public String handleError() {
		return "error";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

}
