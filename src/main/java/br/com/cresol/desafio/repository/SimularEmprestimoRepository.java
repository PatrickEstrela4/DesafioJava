package br.com.cresol.desafio.repository;

import br.com.cresol.desafio.model.SimularEmprestimo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author evandro
 *
 */
public interface SimularEmprestimoRepository extends JpaRepository<SimularEmprestimo, Long> {
	SimularEmprestimo findTopByOrderByIdDesc();
	SimularEmprestimo findBynumeroContrato(long numeroContrato);
	@Query(value = "SELECT setval('se_seq',nextval('se_seq')-1)", nativeQuery = true)
    Long getNextSeriesId();


}
