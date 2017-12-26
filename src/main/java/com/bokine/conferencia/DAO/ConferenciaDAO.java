package com.bokine.conferencia.DAO;

import java.util.List;

import com.bokine.conferencia.domain.Produto;

public interface ConferenciaDAO{

	public abstract List<Produto> buscarTodos();

}
