package br.com.cresol.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cresol.desafio.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	Cliente findByCpf(String cpf);
	boolean existsByCpf(String cpf);
}
