package com.bokine.conferencia.resources;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bokine.conferencia.DAO.ConferenciaDAO;
import com.bokine.conferencia.domain.Conferencia;

@RestController
public class ConferenciaResources {
	
	private ConferenciaDAO conferenciaDAO;
	
	@RequestMapping(value = "/conferencias", method=RequestMethod.GET)
	public List<Conferencia> listar() {
		
		return conferenciaDAO.buscarTodos();
	}

}
