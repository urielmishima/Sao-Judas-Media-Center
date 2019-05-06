package br.usjt.saojudasmediacenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.saojudasmediacenter.enums.TipoFeedback;
import br.usjt.saojudasmediacenter.model.Feedback;
import br.usjt.saojudasmediacenter.service.FeedbackService;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {
	
	@Autowired FeedbackService feedbackService;

	@PostMapping("/positivo")
	public String positivo(Feedback feedback) {
		feedback.setFeedback(TipoFeedback.POSITIVO);
		feedbackService.save(feedback);
		return "timeline";
	}
	
	@PostMapping("/negativo")
	public String negativo(Feedback feedback) {
		feedback.setFeedback(TipoFeedback.NEGATIVO);
		feedbackService.save(feedback);
		return "timeline";
	}
}
