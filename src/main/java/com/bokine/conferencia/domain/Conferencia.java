package com.bokine.conferencia.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Conferencia {
	
	@JsonInclude(Include.NON_NULL)
	private Long romaneio;
	
	@JsonInclude(Include.NON_NULL)
	private List<Produto> produtos;
	
	public Conferencia() {
	}

	public Conferencia(Long romaneio) {
		super();
		this.romaneio = romaneio;
	}

	public Long getRomaneio() {
		return romaneio;
	}

	public void setRomaneio(Long romaneio) {
		this.romaneio = romaneio;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
