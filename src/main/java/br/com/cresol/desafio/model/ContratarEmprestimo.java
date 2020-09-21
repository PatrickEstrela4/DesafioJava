package br.com.cresol.desafio.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author evandro
 *
 */
@Entity
@Table(name="TB_CONTRATAREMPRESTIMO")
public class ContratarEmprestimo {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="contratarEmprestimo_sequence")
	@SequenceGenerator(name="contratarEmprestimo_sequence", sequenceName="ce_seq",allocationSize = 1)
	private long id;
	
	private long numeroContrato;
	private Date dataContratacao;
	private BigDecimal valorContrato;
	private int quantidadeParcelas;
	private BigDecimal iofContrato;
	private BigDecimal taxaJurosEmprestimo;
	
	@OneToMany
	private List<Parcela> parcelas;
	
	/*@OneToOne
	private Cliente cliente;*/

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

	public Date getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(Date dataContratacao) {
		this.dataContratacao = dataContratacao;
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

	public BigDecimal getIofContrato() {
		return iofContrato;
	}

	public void setIofContrato(BigDecimal iofContrato) {
		this.iofContrato = iofContrato;
	}

	public BigDecimal getTaxaJurosEmprestimo() {
		return taxaJurosEmprestimo;
	}

	public void setTaxaJurosEmprestimo(BigDecimal taxaJurosEmprestimo) {
		this.taxaJurosEmprestimo = taxaJurosEmprestimo;
	}

	public List<Parcela> getParcelas() {
		return parcelas;
	}

	public void setParcelas(List<Parcela> parcelas) {
		this.parcelas = parcelas;
	}
	
	
	
}
