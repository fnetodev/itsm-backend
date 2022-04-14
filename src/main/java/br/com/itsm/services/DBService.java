package br.com.itsm.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.itsm.domain.Chamado;
import br.com.itsm.domain.Cliente;
import br.com.itsm.domain.Tecnico;
import br.com.itsm.domain.enums.Perfil;
import br.com.itsm.domain.enums.Prioridade;
import br.com.itsm.domain.enums.Status;
import br.com.itsm.repository.ChamadoRepository;
import br.com.itsm.repository.ClienteRepository;
import br.com.itsm.repository.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	public void iniciaDB() {
		Tecnico tec1 = new Tecnico(null, "Francisco", "41323315632", "francisco@email.com", "123");
		tec1.addPerfis(Perfil.ADMIN);
		
		Cliente cli1 = new Cliente(null, "Linus Torvals", "58495648539", "linus@email.com", "123");
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01","Primeiro chamado.", tec1, cli1);
	
	
		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}
}
