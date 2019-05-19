package br.usjt.saojudasmediacenter.dto;

import java.util.ArrayList;
import java.util.List;

import br.usjt.saojudasmediacenter.enums.TipoAcesso;
import br.usjt.saojudasmediacenter.model.Conteudo;
import br.usjt.saojudasmediacenter.model.Tag;

public class ConteudoCategoriaDTO {
	
	private String id;
	private String titulo;
	private String descricao;
	private String conteudo;
	private TipoAcesso tipo;
	private String data;
	private int visualizacoes;
	private List<TagUploadDTO> tags;	
	
	public ConteudoCategoriaDTO(Conteudo conteudo) {
		this.id = conteudo.getId();
		this.titulo = conteudo.getTitulo();
		this.descricao = conteudo.getDescricao();
		this.conteudo = conteudo.getConteudo();
		this.tipo = conteudo.getTipo();
		this.data = conteudo.getFormatedData();
		this.visualizacoes = conteudo.getVisualizacoes();
		this.tags = new ArrayList<>();
		for (Tag tag : conteudo.getTags()) {
			tags.add(new TagUploadDTO(tag));
		}
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public TipoAcesso getTipo() {
		return tipo;
	}
	public void setTipo(TipoAcesso tipo) {
		this.tipo = tipo;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getVisualizacoes() {
		return visualizacoes;
	}
	public void setVisualizacoes(int visualizacoes) {
		this.visualizacoes = visualizacoes;
	}
	public List<TagUploadDTO> getTags() {
		return tags;
	}
	public void setTags(List<TagUploadDTO> tags) {
		this.tags = tags;
	}
}
