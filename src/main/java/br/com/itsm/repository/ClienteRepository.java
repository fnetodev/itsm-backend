package br.com.itsm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.itsm.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	
	
}
