package br.usjt.saojudasmediacenter.dto;

import br.usjt.saojudasmediacenter.model.Tag;

public class TagUploadDTO {

	private String id;
	private String nome;
	
	public TagUploadDTO(Tag tag) {
		this.setId(tag.getId());
		this.setNome(tag.getNome());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
