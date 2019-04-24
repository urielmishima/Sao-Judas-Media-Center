package br.usjt.saojudasmediacenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.saojudasmediacenter.model.Categoria;
import br.usjt.saojudasmediacenter.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired private CategoriaRepository categoriaRepository;
	
	public List<Categoria> findAll(){
		return categoriaRepository.findAll();
	}
	
}
