package br.usjt.saojudasmediacenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.saojudasmediacenter.model.Sugestao;
import br.usjt.saojudasmediacenter.service.SugestaoService;

@Controller
@RequestMapping("/sugestoes")
public class SugestaoController {

	@Autowired private SugestaoService sugestaoService;
	
	@PostMapping
	public String sugestao(Sugestao sugestao) {
//		@RequestParam("usuario") String usuario, @RequestParam("sugestao") String sugestao, @RequestParam("conteudo") String conteudo
		sugestaoService.save(sugestao);
		return "redirect:/";
	}	
}
