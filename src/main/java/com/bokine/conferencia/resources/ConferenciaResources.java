package com.bokine.conferencia.resources;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bokine.conferencia.DAO.ConferenciaImplDAO;
import com.bokine.conferencia.domain.Produto;

@RestController
public class ConferenciaResources {
	
	@Autowired
	private ConferenciaImplDAO conferenciaDAO;
	
	@RequestMapping(value = "/conferencias", method=RequestMethod.GET, produces= {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Produto>> listar() {
		CacheControl cacheControl = CacheControl.maxAge(5, TimeUnit.MINUTES);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(conferenciaDAO.buscarTodos());
	}

}
