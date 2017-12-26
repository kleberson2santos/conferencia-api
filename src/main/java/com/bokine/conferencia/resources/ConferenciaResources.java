package com.bokine.conferencia.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bokine.conferencia.DAO.ConferenciaImplDAO;
import com.bokine.conferencia.domain.Produto;

@RestController
public class ConferenciaResources {
	
	@Autowired
	private ConferenciaImplDAO conferenciaDAO;
	
	@RequestMapping(value = "/conferencias", method=RequestMethod.GET)
	public List<Produto> listar() {
		
		return conferenciaDAO.buscarTodos();
	}

}
