package br.usjt.saojudasmediacenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.saojudasmediacenter.model.Tag;
import br.usjt.saojudasmediacenter.repository.TagRepository;

@Service
public class TagService {

	@Autowired private TagRepository tagRepository;
	
	public List<Tag> findAll(){
		return tagRepository.findAll();
	}
	
}