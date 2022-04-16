package br.com.itsm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.itsm.domain.Tecnico;
import br.com.itsm.domain.dto.TecnicoDTO;
import br.com.itsm.services.TecnicoService;

@RestController
@RequestMapping(value ="/tecnicos")
public class TecnicoController {
	
	@Autowired
	private TecnicoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		Tecnico tecnico = service.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
	}

}