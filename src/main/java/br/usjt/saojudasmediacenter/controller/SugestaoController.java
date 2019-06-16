package br.usjt.saojudasmediacenter.controller;

import java.security.Principal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.usjt.saojudasmediacenter.enums.TipoFeedback;
import br.usjt.saojudasmediacenter.model.Conteudo;
import br.usjt.saojudasmediacenter.model.Sugestao;
import br.usjt.saojudasmediacenter.model.Tag;
import br.usjt.saojudasmediacenter.model.Usuario;
import br.usjt.saojudasmediacenter.service.ConteudoService;
import br.usjt.saojudasmediacenter.service.SugestaoService;
import br.usjt.saojudasmediacenter.service.TagService;
import br.usjt.saojudasmediacenter.service.UsuarioService;

@Controller
@RequestMapping("/sugestoes")
public class SugestaoController {

	@Autowired private SugestaoService sugestaoService;
	@Autowired private ConteudoService conteudoService;
	@Autowired private TagService tagService;
	@Autowired private UsuarioService usuarioService;
	
	@PostMapping
	public String sugestao(String sugestaoTxt, String usuario, String conteudo) {
		for (String sugestao : sugestaoTxt.split(" ")) {
			sugestaoService.save(new Sugestao().setSugestao(sugestao)
												.setUsuario(new Usuario().setId(usuario))
												.setConteudo(new Conteudo().setId(conteudo)));
		}
		return "redirect:/";
	}	
	

	@Secured("ROLE_ESTAGIARIO")
	@PostMapping("/positivo/{id}")
	public String sugestaoAceitar(@PathVariable String id) {
		Sugestao sugestao = sugestaoService.findById(id);
		sugestao.setFeedback(TipoFeedback.POSITIVO);
		Tag tag = tagService.cadastrar(sugestao.getSugestao());
		conteudoService.cadastrarTag(
				new Conteudo().setTags(Arrays.asList(tag))
							  .setId(sugestao.getConteudo().getId())
				);
		sugestaoService.save(sugestao);
		return "redirect:/";
	}
	

	@Secured("ROLE_ESTAGIARIO")
	@PostMapping("/negativo/{id}")
	public String sugestaoRecusar(@PathVariable String id) {
		Sugestao sugestao = sugestaoService.findById(id);
		sugestao.setFeedback(TipoFeedback.NEGATIVO);
		sugestaoService.save(sugestao);
		return "redirect:/";
	}
	
	@GetMapping("/dashboard")
	@Secured("ROLE_ESTAGIARIO")
	public ModelAndView dashboard(@Nullable Principal principal) {
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("sugestoes", sugestaoService.findByFeedbackIsNull());
		mav.addObject("usuario", usuarioService.findByUsuario(new Usuario().setUsuario(principal.getName())));
		return mav;
	}
}
