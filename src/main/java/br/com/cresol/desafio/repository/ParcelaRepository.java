package br.com.cresol.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cresol.desafio.model.ContratarEmprestimo;
import br.com.cresol.desafio.model.Parcela;

public interface ParcelaRepository extends JpaRepository<Parcela, Long> {
	List<Parcela> findByemprestimo(ContratarEmprestimo emprestimo);
}
