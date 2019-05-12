package br.usjt.saojudasmediacenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.saojudasmediacenter.enums.TipoAcesso;
import br.usjt.saojudasmediacenter.model.Conteudo;
import br.usjt.saojudasmediacenter.model.Tag;
import br.usjt.saojudasmediacenter.repository.ConteudoRepository;

@Service
public class ConteudoService {

	@Autowired ConteudoRepository conteudoRepository;

	public List<Conteudo> findAll() {
		return conteudoRepository.findAll();
	}

	public List<Conteudo> findByTipo(TipoAcesso tipo) {
		return conteudoRepository.findByTipo(tipo);
	}

	public List<Conteudo> maisLidas(List<Conteudo> conteudos) {
		conteudos.sort((Conteudo o1, Conteudo o2) -> o2.getVisualizacoes() - o1.getVisualizacoes());
		return conteudos;
	}

	public List<Conteudo> maisRecentes(List<Conteudo> conteudos) {
		conteudos.sort((Conteudo o1, Conteudo o2) -> o2.getData().compareTo(o1.getData()));
		return conteudos;
	}

	public Conteudo save(Conteudo conteudo) {
		return conteudoRepository.save(conteudo);
	}

	public void cadastrarTag(Conteudo conteudo) {
		Conteudo antigo = conteudoRepository.findById(conteudo.getId()).orElse(null);
		List<Tag> tags = antigo.getTags();
		Tag sugestao = conteudo.getTags().get(0);
		if(!tags.contains(sugestao)) {
			tags.add(sugestao);
			conteudoRepository.save(antigo);
		}
	}
	
}
