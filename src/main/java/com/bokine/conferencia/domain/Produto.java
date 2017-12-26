package com.bokine.conferencia.domain;

import java.io.Serializable;

public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String codigoDeBarras;
	
	public Produto() {
	}
	
	public Produto(String nome, String codigoDeBarras) {
		this.nome = nome;
		this.codigoDeBarras = codigoDeBarras;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCodigoDeBarras() {
		return codigoDeBarras;
	}
	public void setCodigoDeBarras(String codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}

	@Override
	public String toString() {
		return "Produto [nome=" + nome + ", codigoDeBarras=" + codigoDeBarras + "]";
	}
	
	
	
}
