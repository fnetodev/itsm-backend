package br.com.itsm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.itsm.domain.Pessoa;

public interface TecnicoRepository extends JpaRepository<Pessoa, Integer>{

	
	
}
