package br.com.itsm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.itsm.domain.Chamado;
import br.com.itsm.domain.dto.ChamadoDTO;
import br.com.itsm.services.ChamadoService;

@RestController
@RequestMapping(value ="/chamados")
public class ChamadoController {

	@Autowired
	private ChamadoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
		Chamado chamado = service.findById(id);
		return ResponseEntity.ok().body(new ChamadoDTO(chamado));
		
	}
	
	
}
