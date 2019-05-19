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

import br.usjt.saojudasmediacenter.dto.CategoriaUploadDTO;
import br.usjt.saojudasmediacenter.dto.ConteudoCategoriaDTO;
import br.usjt.saojudasmediacenter.enums.TipoAcesso;
import br.usjt.saojudasmediacenter.model.Conteudo;
import br.usjt.saojudasmediacenter.model.Tag;
import br.usjt.saojudasmediacenter.service.CategoriaService;
import br.usjt.saojudasmediacenter.service.ConteudoService;
import br.usjt.saojudasmediacenter.service.TagService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired private CategoriaService categoriaService;
	@Autowired private ConteudoService conteudoService;
	@Autowired private TagService tagService;
	
	@Secured("ROLE_ESTAGIARIO")
	@PostMapping("/cadastrar/{categoria}")
	public CategoriaUploadDTO cadastrarTag(@PathVariable("categoria") String nome){
		return new CategoriaUploadDTO(categoriaService.cadastrar(nome));
	}
	
	@GetMapping("/conteudo/{categoria}")
	public List<ConteudoCategoriaDTO> buscarConteudo(@PathVariable("categoria") String categoria, @Nullable Principal principal, @Nullable String tagsParam) {
		List<Conteudo> conteudos = conteudoService.maisRecentes(categoriaService.findById(categoria).getConteudos());

		if (principal == null) {
			conteudos.removeIf((conteudo) -> conteudo.getTipo().toString().equals(TipoAcesso.PROTEGIDO.toString()));
		}
		
		if(tagsParam != null && tagsParam.length() > 0) {
			Set<Conteudo> conteudosSet = new HashSet<>();
			for (Tag tag : tagService.findByNomeIn(Arrays.asList(tagsParam.split(" ")))) {
				conteudosSet.addAll(tag.getConteudos());
			}
			conteudos.retainAll(conteudosSet);
		}		
		
		ArrayList<ConteudoCategoriaDTO> cCD = new ArrayList<ConteudoCategoriaDTO>();
		for (Conteudo conteudo : conteudos) {
			cCD.add(new ConteudoCategoriaDTO(conteudo));
		}
		
		return cCD;
	}
}