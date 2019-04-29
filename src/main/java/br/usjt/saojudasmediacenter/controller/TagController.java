package br.usjt.saojudasmediacenter.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.usjt.saojudasmediacenter.dto.TagUploadDTO;
import br.usjt.saojudasmediacenter.service.TagService;

@RestController
@RequestMapping("/tags")
public class TagController {
	
	@Autowired private TagService tagService;
	
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

}
