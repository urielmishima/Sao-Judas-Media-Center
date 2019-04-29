package br.usjt.saojudasmediacenter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FacebookLiveResponseDTO {

	private Long id;
	private String stream_url;
	private String secure_stream_url;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStream_url() {
		return stream_url;
	}
	public void setStream_url(String stream_url) {
		this.stream_url = stream_url;
	}
	public String getSecure_stream_url() {
		return secure_stream_url;
	}
	public void setSecure_stream_url(String secure_stream_url) {
		this.secure_stream_url = secure_stream_url;
	}
}
