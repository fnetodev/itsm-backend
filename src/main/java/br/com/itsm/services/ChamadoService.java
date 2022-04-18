package br.com.itsm.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.itsm.domain.Chamado;
import br.com.itsm.repository.ChamadoRepository;
import br.com.itsm.services.exceptions.ObjectnotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository respository;
	
	public Chamado findById(Integer id) {
		Optional<Chamado> chamado = respository.findById(id);
		return chamado.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! ID: " + id));
 	}
}
