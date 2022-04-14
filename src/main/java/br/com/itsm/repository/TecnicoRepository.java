package br.com.itsm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.itsm.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer>{

	
	
}
