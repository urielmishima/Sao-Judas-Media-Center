package br.usjt.saojudasmediacenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.saojudasmediacenter.model.Sugestao;
import br.usjt.saojudasmediacenter.repository.SugestaoRepository;

@Service
public class SugestaoService {

	@Autowired private SugestaoRepository sugestaoRepository;
	
	public Sugestao save(Sugestao sugestao) {
		return sugestaoRepository.save(sugestao);
	}
}
