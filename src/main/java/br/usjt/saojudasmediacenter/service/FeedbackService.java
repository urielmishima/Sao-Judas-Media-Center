package br.usjt.saojudasmediacenter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.saojudasmediacenter.enums.TipoFeedback;
import br.usjt.saojudasmediacenter.model.Feedback;
import br.usjt.saojudasmediacenter.model.Usuario;
import br.usjt.saojudasmediacenter.repository.FeedbackRepository;

@Service
public class FeedbackService {

	@Autowired private FeedbackRepository feedbackRepository;
	
	public void save(Feedback feedback) {
		feedbackRepository.save(feedback);
	}
	
	public Feedback getFeedbackByUsuarioAndConteudo(Feedback feedback) {
		return Optional.ofNullable(
				feedbackRepository.findByUsuarioAndConteudo(
						feedback.getUsuario(), feedback.getConteudo()
						)
				).orElse(feedback);
	}

	public List<Feedback> findByUsuarioAndFeedback(Usuario usuario, TipoFeedback feedback) {
		return feedbackRepository.findByUsuarioAndFeedback(usuario, feedback);
	}
}
