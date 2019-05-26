package br.usjt.saojudasmediacenter.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.usjt.saojudasmediacenter.enums.TipoUsuario;
import br.usjt.saojudasmediacenter.model.Usuario;
import br.usjt.saojudasmediacenter.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired UsuarioService usuarioService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/cadastrar")
	public String cadastrar() {
		return "cadastro";
	}
	
	@PostMapping("/cadastrar")
	public String cadastrar(Usuario usuario){
		usuarioService.cadastrar(usuario.setTipo(TipoUsuario.COMUM));
		return login();
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/gerenciar")
	public ModelAndView gerenciar(@Nullable Principal principal) {
		ModelAndView mav = new ModelAndView("gerenciamento");
		mav.addObject("usuarios", usuarioService.findAll());
		mav.addObject("tipos", TipoUsuario.values());
		mav.addObject("usuario", usuarioService.findByUsuario(new Usuario().setUsuario(principal.getName())));
		return mav;
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/gerenciar/{usuario}/{cargo}")
	public ModelAndView gerenciar(@PathVariable("usuario") String idUsuario, @PathVariable("cargo") TipoUsuario tipo, @Nullable Principal principal){
		
		usuarioService.atualizarTipo(idUsuario, tipo);
		
		return gerenciar(principal);
	}
}
