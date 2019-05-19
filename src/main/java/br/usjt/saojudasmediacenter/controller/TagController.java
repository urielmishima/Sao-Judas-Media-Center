package br.usjt.saojudasmediacenter.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.usjt.saojudasmediacenter.dto.TagUploadDTO;
import br.usjt.saojudasmediacenter.enums.TipoAcesso;
import br.usjt.saojudasmediacenter.model.Conteudo;
import br.usjt.saojudasmediacenter.model.Tag;
import br.usjt.saojudasmediacenter.model.Usuario;
import br.usjt.saojudasmediacenter.service.CategoriaService;
import br.usjt.saojudasmediacenter.service.ConteudoService;
import br.usjt.saojudasmediacenter.service.TagService;
import br.usjt.saojudasmediacenter.service.UsuarioService;

@RestController
@RequestMapping("/tags")
public class TagController {
	
	@Autowired private TagService tagService;
	@Autowired private ConteudoService conteudoService;
	@Autowired private CategoriaService categoriaService;
	@Autowired private UsuarioService usuarioService;
	
	@GetMapping
	public List<TagUploadDTO> tags(){
		List<TagUploadDTO> dto = new ArrayList<>();
		tagService.findAll().forEach((tag) -> {
			dto.add(new TagUploadDTO(tag));
		});
		return dto;
	}
	
	@Secured("ROLE_ESTAGIARIO")
	@PostMapping("/cadastrar/{tag}")
	public TagUploadDTO cadastrarTag(@PathVariable("tag") String nome){
		return new TagUploadDTO(tagService.cadastrar(nome));
	}
	
	@GetMapping("/conteudo")
	public ModelAndView buscaConteudos(String tagsParam, @Nullable Principal principal) {
		ModelAndView mav = new ModelAndView("timeline");
		Set<Conteudo> conteudosSet = new HashSet<>();
		if (tagsParam.length() > 0) {
			for (Tag tag : tagService.findByNomeIn(Arrays.asList(tagsParam.split(" ")))) {
				conteudosSet.addAll(tag.getConteudos());
			}
		} else {
			conteudosSet.addAll(conteudoService.findAll());
		}
		
		if (principal == null) {
			conteudosSet.removeIf((conteudo) -> conteudo.getTipo().toString().equals(TipoAcesso.PROTEGIDO.toString()));
		} else {
			mav.addObject("usuario", usuarioService.findByUsuario(new Usuario().setUsuario(principal.getName())));
		}
		
		if (tagsParam.length() > 0) {
			mav.addObject("maisPositivas", conteudoService.maisPositivas(new ArrayList<Conteudo>(conteudosSet)));
			mav.addObject("maisRecentes", conteudoService.maisRecentes(new ArrayList<Conteudo>(conteudosSet)));
		} else {
			mav.addObject("maisPositivas", conteudoService.maisPositivas(new ArrayList<Conteudo>(conteudosSet)).subList(0, 30));
			mav.addObject("maisRecentes", conteudoService.maisRecentes(new ArrayList<Conteudo>(conteudosSet)).subList(0, 30));
		}
		
		mav.addObject("categorias", categoriaService.findAll());
		mav.addObject("tagsParam", tagsParam);
		return mav;
	}

}
