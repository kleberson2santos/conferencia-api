package com.bokine.conferencia.DAO;

import java.util.List;

import com.bokine.conferencia.domain.Conferencia;

public interface ConferenciaDAO {

	public abstract List<Conferencia> buscarTodos();

}
