package br.usjt.saojudasmediacenter.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.usjt.saojudasmediacenter.enums.TipoFeedback;

@Entity
public class Sugestao {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	@Lob
	@NotEmpty
	private String sugestao;
	
	@Enumerated(EnumType.STRING)
	private TipoFeedback feedback;

	@ManyToOne
	@JsonIgnoreProperties("sugestoes")
	private Usuario usuario;

	@ManyToOne
	@JsonIgnoreProperties("sugestoes")
	private Conteudo conteudo;

	public Sugestao() {

	}

	public Sugestao(String sugestao, Usuario usuario, Conteudo conteudo) {
		this.sugestao = sugestao;
		this.usuario = usuario;
		this.conteudo = conteudo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSugestao() {
		return sugestao;
	}

	public Sugestao setSugestao(String sugestao) {
		this.sugestao = sugestao;
		return this;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Sugestao setUsuario(Usuario usuario) {
		this.usuario = usuario;
		return this;
	}

	public Conteudo getConteudo() {
		return conteudo;
	}

	public Sugestao setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
		return this;
	}

	public TipoFeedback getFeedback() {
		return feedback;
	}

	public void setFeedback(TipoFeedback feedback) {
		this.feedback = feedback;
	}
}
