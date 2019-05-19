package br.usjt.saojudasmediacenter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.usjt.saojudasmediacenter.model.Tag;
import br.usjt.saojudasmediacenter.repository.TagRepository;

@Service
public class TagService {

	@Autowired private TagRepository tagRepository;
	
	public List<Tag> findAll(){
		return tagRepository.findAll();
	}
	
	public Tag cadastrar(String nome) {
		String nomeLimpo = StringUtils.capitalize(nome.trim());
		Tag tag = Optional.ofNullable(tagRepository.findByNome(nomeLimpo)).orElse(null);
		if(tag == null) {
			tag = new Tag();
			tag.setNome(nomeLimpo);
			tagRepository.save(tag);
		}
		return tag;
	}

	public List<Tag> findByNomeIn(List<String> tags) {
		return tagRepository.findByNomeIn(tags);
	}
	
}