package br.usjt.saojudasmediacenter.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.usjt.saojudasmediacenter.enums.TipoAcesso;
import br.usjt.saojudasmediacenter.model.Conteudo;
import br.usjt.saojudasmediacenter.model.Usuario;
import br.usjt.saojudasmediacenter.service.ConteudoService;
import br.usjt.saojudasmediacenter.service.UsuarioService;

@Controller
@RequestMapping("/conteudos")
public class ConteudoController {

	@Autowired ConteudoService conteudoService;
	@Autowired UsuarioService usuarioService;
	
	@GetMapping("/timeline")
	public ModelAndView home(@Nullable Principal principal) {
		final int numeroConteudos = 15;
		
		ModelAndView mav = new ModelAndView("timeline");	
		
		List<Conteudo> conteudos;
		
		if(principal != null) {
			mav.addObject("usuario", usuarioService.findByUsuario(new Usuario().setUsuario(principal.getName())));
			conteudos = conteudoService.findAll();
		} else {
			conteudos = conteudoService.findByTipo(TipoAcesso.PUBLICO);
		}
		
		mav.addObject("maisLidas", conteudoService.maisLidas(conteudos).subList(0, numeroConteudos));
		mav.addObject("maisRecentes", conteudoService.maisRecentes(conteudos).subList(0, numeroConteudos));
		
		return mav;
	}
	
}
