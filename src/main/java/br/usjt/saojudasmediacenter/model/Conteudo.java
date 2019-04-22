package br.usjt.saojudasmediacenter.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import br.usjt.saojudasmediacenter.enums.TipoAcesso;

@Entity
public class Conteudo {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;
	
	@NotEmpty
	private String titulo;
	
	@Lob
	@NotEmpty
	private String descricao;
	
	@Lob
	@NotEmpty
	private String conteudo;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoAcesso tipo;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;

	private int visualizacoes;

	@OneToMany(mappedBy = "conteudo")
	private List<Sugestao> sugestoes;
	
	@OneToMany(mappedBy = "conteudo")
	private List<Feedback> feedbacks;
	
	@ManyToMany
	private List<Material> materiais;
	
	@ManyToMany
	private List<Categoria> categorias;
	
	@ManyToMany
	private List<Tag> tags;

	public String getId() {
		return id;
	}

	public Conteudo setId(String id) {
		this.id = id;
		return this;
	}

	public String getTitulo() {
		return titulo;
	}

	public Conteudo setTitulo(String titulo) {
		this.titulo = titulo;
		return this;
	}

	public String getDescricao() {
		return descricao;
	}

	public Conteudo setDescricao(String descricao) {
		this.descricao = descricao;
		return this;
	}

	public String getConteudo() {
		return conteudo;
	}

	public Conteudo setConteudo(String conteudo) {
		this.conteudo = conteudo;
		return this;
	}

	public TipoAcesso getTipo() {
		return tipo;
	}

	public Conteudo setTipo(TipoAcesso tipo) {
		this.tipo = tipo;
		return this;
	}

	public Calendar getData() {
		return data;
	}

	public Conteudo setData(Calendar data) {
		this.data = data;
		return this;
	}

	public List<Material> getMateriais() {
		return materiais;
	}

	public Conteudo setMateriais(List<Material> materiais) {
		this.materiais = materiais;
		return this;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public Conteudo setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
		return this;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public Conteudo setTags(List<Tag> tags) {
		this.tags = tags;
		return this;
	}

	public List<Sugestao> getSugestoes() {
		return sugestoes;
	}

	public Conteudo setSugestoes(List<Sugestao> sugestoes) {
		this.sugestoes = sugestoes;
		return this;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public Conteudo setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
		return this;
	}

	public int getVisualizacoes() {
		return visualizacoes;
	}

	public Conteudo setVisualizacoes(int visualizacoes) {
		this.visualizacoes = visualizacoes;
		return this;
	}
}
