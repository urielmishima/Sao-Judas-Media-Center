package br.usjt.saojudasmediacenter.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.usjt.saojudasmediacenter.dto.CategoriaUploadDTO;
import br.usjt.saojudasmediacenter.dto.ConteudoCategoriaDTO;
import br.usjt.saojudasmediacenter.enums.TipoAcesso;
import br.usjt.saojudasmediacenter.model.Conteudo;
import br.usjt.saojudasmediacenter.model.Usuario;
import br.usjt.saojudasmediacenter.service.CategoriaService;
import br.usjt.saojudasmediacenter.service.ConteudoService;
import br.usjt.saojudasmediacenter.service.UsuarioService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired private CategoriaService categoriaService;
	@Autowired private ConteudoService conteudoService;
	@Autowired private UsuarioService usuarioService;
	
	@Secured("ROLE_ESTAGIARIO")
	@PostMapping("/cadastrar/{categoria}")
	public CategoriaUploadDTO cadastrarTag(@PathVariable("categoria") String nome){
		return new CategoriaUploadDTO(categoriaService.cadastrar(nome));
	}
	
	@GetMapping("/conteudo/{categoria}")
	public ModelAndView buscarConteudo(@PathVariable("categoria") String categoria, @Nullable Principal principal) {
		ModelAndView mav = new ModelAndView("timeline");
		
		List<Conteudo> conteudos = categoriaService.findById(categoria).getConteudos();

		if (principal == null) {
			conteudos.removeIf((conteudo) -> conteudo.getTipo().toString().equals(TipoAcesso.PROTEGIDO.toString()));
		} else {
			mav.addObject("usuario", usuarioService.findByUsuario(new Usuario().setUsuario(principal.getName())));
		}	
		
		ArrayList<ConteudoCategoriaDTO> cCD = new ArrayList<ConteudoCategoriaDTO>();
		for (Conteudo conteudo : conteudos) {
			cCD.add(new ConteudoCategoriaDTO(conteudo));
		}
		
		List<Conteudo> maisPositivas = conteudoService.maisPositivas(new ArrayList<Conteudo>(conteudos));
		List<Conteudo> maisRecentes = conteudoService.maisRecentes(new ArrayList<Conteudo>(conteudos));
		
		if(conteudos.size() >= 50) {
			maisPositivas = maisPositivas.subList(0, 50);
			maisRecentes = maisRecentes.subList(0, 50);
		}
		
		mav.addObject("maisPositivas", maisPositivas);
		mav.addObject("maisRecentes", maisRecentes);
		mav.addObject("categorias", categoriaService.findAll());
		
		return mav;
	}
}