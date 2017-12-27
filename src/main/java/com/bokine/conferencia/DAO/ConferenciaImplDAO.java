package com.bokine.conferencia.DAO;

import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.bokine.conferencia.domain.Produto;

@Configuration
public class ConferenciaImplDAO implements ConferenciaDAO {
	//String database = "/home/kleber/Documentos/Repositorios_Java/uteis/Data Base Firebird/BKN00001.fdb";
	String database = "C:/Sys/base/BKN00001";

	ConnectionJDBC conncectionJDBC = new ConnectionJDBC("127.0.0.1", database, "SYSDBA", "masterkey");

	@Override
	public List<Produto> buscarTodos() {
		
		return conncectionJDBC.todasConferencias();
	}

}
