package br.usjt.saojudasmediacenter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.usjt.saojudasmediacenter.model.Categoria;
import br.usjt.saojudasmediacenter.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired private CategoriaRepository categoriaRepository;
	
	public List<Categoria> findAll(){
		return categoriaRepository.findAll();
	}

	public Categoria cadastrar(String nome) {
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
