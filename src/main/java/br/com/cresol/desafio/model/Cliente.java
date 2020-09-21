package br.com.cresol.desafio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name="TB_CLIENTE")
public class Cliente {


	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cliente_sequence")
	@SequenceGenerator(name="cliente_sequence", sequenceName="cli_seq",allocationSize = 1)
	private long id;
	
	@CPF(message="cpf inválido")
	private String cpf;
	private String nome;
	@Email(message = "O Endereço de Email não é valido")
	private String email;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	/*@OneToOne
	private ContratarEmprestimo emprestimo;*/
	
	
}
