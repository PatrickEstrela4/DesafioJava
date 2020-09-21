package br.com.cresol.desafio.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author evandro
 *
 */
@Entity
@Table(name="TB_SIMULAREMPRESTIMO")
public class SimularEmprestimo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="simularEmprestimo_sequence")
	@SequenceGenerator(name="simularEmprestimo_sequence", sequenceName="se_seq",allocationSize = 1)
	private long id;
	
	private long numeroContrato;
	private Date dataSimulacao;
	private Date dataValidadeSimulacao;
	private BigDecimal valorContrato;
	@Min(1)
	@Max(24)
	private int quantidadeParcelas;
	private BigDecimal valorParcela;
	private BigDecimal taxaJurosEmprestimo;
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
	public Date getDataSimulacao() {
		return dataSimulacao;
	}
	public void setDataSimulacao(Date dataSimulacao) {
		this.dataSimulacao = dataSimulacao;
	}
	public Date getDataValidadeSimulacao() {
		return dataValidadeSimulacao;
	}
	public void setDataValidadeSimulacao(Date dataValidadeSimulacao) {
		this.dataValidadeSimulacao = dataValidadeSimulacao;
	}
	public BigDecimal getValorContrato() {
		return valorContrato;
	}
	public void setValorContrato(BigDecimal valorContrato) {
		this.valorContrato = valorContrato;
	}
	public int getQuantidadeParcelas() {
		return quantidadeParcelas;
	}
	public void setQuantidadeParcelas(int quantidadeParcelas) {
		this.quantidadeParcelas = quantidadeParcelas;
	}
	public BigDecimal getValorParcela() {
		return valorParcela;
	}
	public void setValorParcela(BigDecimal valorParcela) {
		this.valorParcela = valorParcela;
	}
	public BigDecimal getTaxaJurosEmprestimo() {
		return taxaJurosEmprestimo;
	}
	public void setTaxaJurosEmprestimo(BigDecimal taxaJurosEmprestimo) {
		this.taxaJurosEmprestimo = taxaJurosEmprestimo;
	}
	
	
}
