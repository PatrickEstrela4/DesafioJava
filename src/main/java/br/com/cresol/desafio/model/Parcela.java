package br.com.cresol.desafio.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TB_PARCELA")
public class Parcela {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="parcela_sequence")
	@SequenceGenerator(name="parcela_sequence", sequenceName="parc_seq",allocationSize = 1)
	private long id;
	
	private long numeroContrato;
	private int numeroDaParcela;
	private BigDecimal valorParcela;
	private Date dataVencimento;
	
	@ManyToOne
	private ContratarEmprestimo emprestimo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(long numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public int getNumeroDaParcela() {
		return numeroDaParcela;
	}

	public void setNumeroDaParcela(int numeroDaParcela) {
		this.numeroDaParcela = numeroDaParcela;
	}

	public BigDecimal getValorParcela() {
		return valorParcela;
	}

	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public ContratarEmprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(ContratarEmprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}
	
	
}
