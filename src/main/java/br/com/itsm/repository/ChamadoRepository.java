package br.com.itsm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.itsm.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer>{


	
}
