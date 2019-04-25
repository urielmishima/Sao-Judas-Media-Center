package br.usjt.saojudasmediacenter.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.usjt.saojudasmediacenter.model.Categoria;
import br.usjt.saojudasmediacenter.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired private CategoriaRepository categoriaRepository;
	
	@Secured("ROLE_ESTAGIARIO")
	@PostMapping("/cadastrar/{categoria}")
	public Categoria cadastrarTag(@PathVariable("categoria") String nome){
		String nomeLimpo = StringUtils.capitalize(nome.trim().toLowerCase());
		Categoria categoria = Optional.ofNullable(categoriaRepository.findByNome(nomeLimpo)).orElse(null);
		if(categoria == null) {
			categoria = new Categoria();
			categoria.setNome(nomeLimpo);
			categoriaRepository.save(categoria);
		}
		return categoria;
	}
	
	
}