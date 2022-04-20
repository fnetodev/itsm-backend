package br.com.itsm.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.itsm.domain.Cliente;
import br.com.itsm.domain.Pessoa;
import br.com.itsm.domain.dto.ClienteDTO;
import br.com.itsm.repository.ClienteRepository;
import br.com.itsm.repository.PessoaRepository;
import br.com.itsm.services.exceptions.DataIntegrityViolationException;
import br.com.itsm.services.exceptions.ObjectnotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));

	}

	public List<Cliente> findAll() {

		return repository.findAll();
	}

	public Cliente create(ClienteDTO clienteDTO) {
		clienteDTO.setId(null);
		clienteDTO.setSenha(encoder.encode(clienteDTO.getSenha()));
		validaPorCpfEEmail(clienteDTO);
		Cliente cliente = new Cliente(clienteDTO);
		return repository.save(cliente);
	}

	public Cliente update(Integer id, @Valid ClienteDTO ClienteDTO) {

		ClienteDTO.setId(id);
		ClienteDTO.setSenha(encoder.encode(ClienteDTO.getSenha()));
		Cliente oldCliente = findById(id);
		validaPorCpfEEmail(ClienteDTO);
		oldCliente = new Cliente(ClienteDTO);
		return repository.save(oldCliente);
	}

	public void delete(Integer id) {
		Cliente Cliente = findById(id);
		if (Cliente.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
		}
		repository.deleteById(id);
	}

	private void validaPorCpfEEmail(ClienteDTO ClienteDTO) {
		Optional<Pessoa> pessoa = pessoaRepository.findByCpf(ClienteDTO.getCpf());
		if (pessoa.isPresent() && pessoa.get().getId() != ClienteDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
		}
		pessoa = pessoaRepository.findByEmail(ClienteDTO.getEmail());
		if (pessoa.isPresent() && pessoa.get().getId() != ClienteDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema");
		}
	}

}
