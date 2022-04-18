package br.com.itsm.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.itsm.domain.Pessoa;
import br.com.itsm.domain.Tecnico;
import br.com.itsm.domain.dto.TecnicoDTO;
import br.com.itsm.repository.PessoaRepository;
import br.com.itsm.repository.TecnicoRepository;
import br.com.itsm.services.exceptions.DataIntegrityViolationException;
import br.com.itsm.services.exceptions.ObjectnotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));

	}

	public List<Tecnico> findAll() {

		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO tecnicoDTO) {
		tecnicoDTO.setId(null);
		validaPorCpfEEmail(tecnicoDTO);
		Tecnico tecnico = new Tecnico(tecnicoDTO);
		return repository.save(tecnico);
	}

	public Tecnico update(Integer id, @Valid TecnicoDTO tecnicoDTO) {

		tecnicoDTO.setId(id);
		Tecnico oldTecnico = findById(id);
		validaPorCpfEEmail(tecnicoDTO);
		oldTecnico = new Tecnico(tecnicoDTO);
		return repository.save(oldTecnico);
	}

	public void delete(Integer id) {
		Tecnico tecnico = findById(id);
		if(tecnico.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Ténico possui ordens de serviço e não pode ser deletado!");
		}
		repository.deleteById(id);
	}

	private void validaPorCpfEEmail(TecnicoDTO tecnicoDTO) {
		Optional<Pessoa> pessoa = pessoaRepository.findByCpf(tecnicoDTO.getCpf());
		if (pessoa.isPresent() && pessoa.get().getId() != tecnicoDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
		}
		pessoa = pessoaRepository.findByEmail(tecnicoDTO.getEmail());
		if (pessoa.isPresent() && pessoa.get().getId() != tecnicoDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema");
		}
	}

}
