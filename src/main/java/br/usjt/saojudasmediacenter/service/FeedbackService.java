package br.usjt.saojudasmediacenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.saojudasmediacenter.model.Feedback;
import br.usjt.saojudasmediacenter.repository.FeedbackRepository;

@Service
public class FeedbackService {

	@Autowired private FeedbackRepository feedbackRepository;
	
	public void save(Feedback feedback) {
		feedbackRepository.save(feedback);
	}
}
