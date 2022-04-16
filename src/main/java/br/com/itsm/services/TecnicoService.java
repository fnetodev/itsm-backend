package br.com.itsm.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.itsm.domain.Tecnico;
import br.com.itsm.repository.TecnicoRepository;
import br.com.itsm.services.exceptions.ObjectnotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id ));

	}

	public List<Tecnico> findAll() {
		
		return repository.findAll();
	}
}
