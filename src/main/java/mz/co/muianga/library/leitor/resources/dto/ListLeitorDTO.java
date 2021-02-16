package mz.co.muianga.library.leitor.resources.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListLeitorDTO {
	
	@JsonProperty("items")
	private List<LeitorDTO> leitores;

	public List<LeitorDTO> getLeitores() {
		return leitores;
	}

	public void setLeitores(List<LeitorDTO> leitores) {
		this.leitores = leitores;
	}

}
