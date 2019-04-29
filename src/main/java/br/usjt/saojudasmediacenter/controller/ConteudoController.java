package br.usjt.saojudasmediacenter.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.usjt.saojudasmediacenter.enums.TipoAcesso;
import br.usjt.saojudasmediacenter.model.Conteudo;
import br.usjt.saojudasmediacenter.model.Usuario;
import br.usjt.saojudasmediacenter.service.ConteudoService;
import br.usjt.saojudasmediacenter.service.MaterialService;
import br.usjt.saojudasmediacenter.service.UsuarioService;

@Controller
@RequestMapping("/conteudos")
public class ConteudoController {

	@Autowired ConteudoService conteudoService;
	@Autowired UsuarioService usuarioService;
	@Autowired MaterialService materialService;
	
	@GetMapping("/confeccao")
	@Secured("ROLE_ESTAGIARIO")
	public ModelAndView confeccao() {
		ModelAndView mav = new ModelAndView("confeccao");
		mav.addObject("imagens", materialService.findByTipo("image"));
		mav.addObject("videos", materialService.findByTipo("video"));
		mav.addObject("audios", materialService.findByTipo("audio"));
		mav.addObject("textos", materialService.findByTipo("text"));
		mav.addObject("tipos", TipoAcesso.values());
		return mav;
	}
	
	@PostMapping("/confeccao")
	@Secured("ROLE_ESTAGIARIO")
	public String confeccao(Conteudo conteudo) {
		conteudoService.save(conteudo.setData(Calendar.getInstance()));
		
		return "redirect:/conteudos/confeccao";
	}
	
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
		
		mav.addObject("maisLidas", conteudoService.maisLidas(new ArrayList<Conteudo>(conteudos)).subList(0, numeroConteudos));
		mav.addObject("maisRecentes", conteudoService.maisRecentes(new ArrayList<Conteudo>(conteudos)).subList(0, numeroConteudos));
		System.out.println(mav.getModel().containsKey("maisVisualizadas"));
		
		return mav;
	}
	
	@GetMapping("/live") 
	public ModelAndView live() {
		ModelAndView mav = new ModelAndView();
		
		return mav;
	}
}
