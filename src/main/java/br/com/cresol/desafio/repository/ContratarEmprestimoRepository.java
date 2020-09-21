package br.com.cresol.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cresol.desafio.model.ContratarEmprestimo;

public interface ContratarEmprestimoRepository extends JpaRepository<ContratarEmprestimo, Long>{
	ContratarEmprestimo findBynumeroContrato(long numeroContrato);
	boolean existsBynumeroContrato(long numeroContrato);
}
