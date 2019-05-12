package br.usjt.saojudasmediacenter.service;

import java.util.List;

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

	public List<Sugestao> findByFeedbackIsNull() {
		return sugestaoRepository.findByFeedbackIsNull();
	}

	public Sugestao findById(String id) {
		return sugestaoRepository.findById(id).get();
	}
}
