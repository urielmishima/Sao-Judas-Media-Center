package br.usjt.saojudasmediacenter.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

	@GetMapping("/")
	public String home() {
		return "redirect:/conteudos/timeline";
	}
	
	@GetMapping("/dashboard")
	@Secured("ROLE_ESTAGIARIO")
	public ModelAndView dashboard() {
		ModelAndView mav = new ModelAndView("home");
		return mav;
	}
}
