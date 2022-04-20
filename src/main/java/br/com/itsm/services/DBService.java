package br.com.itsm.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	public void iniciaDB() {
		Tecnico tec1 = new Tecnico(null, "Francisco", "41323315632", "francisco@email.com","francisco", encoder.encode("123"));
		tec1.addPerfis(Perfil.ADMIN);
		Tecnico tec2 = new Tecnico(null, "Tecnico01", "45558622601", "tec1@email.com","Tec01", encoder.encode("123"));
		tec2.addPerfis(Perfil.ADMIN);
		Tecnico tec3 = new Tecnico(null, "Tecnico02", "80828198209", "tec2@email.com","Tec02",encoder.encode("123"));
		Tecnico tec4 = new Tecnico(null, "Tecnico03", "52622813899", "tec3@email.com","Tec03", encoder.encode("123"));
		
		Cliente cli1 = new Cliente(null, "Cliente1", "48433273108", "cliente1@email.com", "Cli01", encoder.encode("123"));
		Cliente cli2 = new Cliente(null, "Cliente2", "83686812002", "cliente2@email.com", "Cli02", encoder.encode("123"));
		Cliente cli3 = new Cliente(null, "Cliente3", "04494853151", "cliente3@email.com", "Cli03", encoder.encode("123"));
		Cliente cli4 = new Cliente(null, "Cliente4", "77367732110", "cliente4@email.com", "Cli04", encoder.encode("123"));
		
		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01","Primeiro chamado.", tec1, cli1);
		Chamado c2 = new Chamado(null, Prioridade.BAIXA, Status.ABERTO, "Chamado 02","Segundo chamado.", tec2, cli2);
		Chamado c3 = new Chamado(null, Prioridade.ALTA, Status.ENCERRADO, "Chamado 03","Terceiro chamado.", tec3, cli3);
		Chamado c4 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 04","Quarto chamado.", tec4, cli4);
		
	
		tecnicoRepository.saveAll(Arrays.asList(tec1,tec2,tec3,tec4));
		clienteRepository.saveAll(Arrays.asList(cli1,cli2,cli3,cli4));
		chamadoRepository.saveAll(Arrays.asList(c1,c2,c3,c4));
	}
}
