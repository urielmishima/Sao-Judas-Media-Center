package br.usjt.saojudasmediacenter.dto;

import br.usjt.saojudasmediacenter.model.Feedback;

public class FeedbackIdDTO {

	private String id;
	
	public FeedbackIdDTO(Feedback feedback) {
		this.id = feedback.getId();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
		
}
