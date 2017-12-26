package com.bokine.conferencia.DAO;

import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.bokine.conferencia.domain.Produto;

@Configuration
public class ConferenciaImplDAO implements ConferenciaDAO {

	ConnectionJDBC conncectionJDBC = new ConnectionJDBC("127.0.0.1", "/home/kleber/Documentos/Repositorios_Java/uteis/Data Base Firebird/BKN00001.fdb", "SYSDBA", "masterkey");

	@Override
	public List<Produto> buscarTodos() {
		
		return conncectionJDBC.todasConferencias();
	}

}
