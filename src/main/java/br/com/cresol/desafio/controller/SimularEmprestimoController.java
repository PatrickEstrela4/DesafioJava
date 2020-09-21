package br.com.cresol.desafio.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.cresol.desafio.model.Cliente;
import br.com.cresol.desafio.model.SimularEmprestimo;
import br.com.cresol.desafio.repository.ClienteRepository;
import br.com.cresol.desafio.repository.SimularEmprestimoRepository;

public class SimularEmprestimoController{

	ClienteRepository  clienteRepository;
	SimularEmprestimoRepository simularEmprestimoRepository;
	
	public SimularEmprestimoController(ClienteRepository clienteRepository, SimularEmprestimoRepository simularEmprestimoRepository) {
		super();
		this.clienteRepository = clienteRepository;
		this.simularEmprestimoRepository = simularEmprestimoRepository;
	}

	public SimularEmprestimo simular(String cpf, String nome, String email, String valorEmeprestimo, int numeroParcelas) throws ParseException {
		BigDecimal valorEmprestimo_ = new BigDecimal(valorEmeprestimo);
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		Date dataSimulacao,datavalidade;
		Cliente cli;
		long numeroContrato;
		if (clienteRepository.existsByCpf(cpf)) {
			cli = clienteRepository.findByCpf(cpf);
		}else {
			cli = new Cliente();
			cli.setCpf(cpf);
			cli.setNome(nome);
			cli.setEmail(email);
			clienteRepository.save(cli);
		}
		SimularEmprestimo emprestimo = new SimularEmprestimo();
		BigDecimal taxa = calcTaxaJuros(valorEmprestimo_,numeroParcelas);
		BigDecimal valParcela = calcValorParcelas(emprestimo,valorEmprestimo_,numeroParcelas,taxa);
		dataSimulacao = getDataSimulacao();
		datavalidade = getDataValidade(dataSimulacao);
		numeroContrato = gerarNumeroContrato(dataSimulacao);
		
		//-----------------
		emprestimo.setDataSimulacao(fmt.parse(fmt.format(dataSimulacao)));
		emprestimo.setDataValidadeSimulacao(fmt.parse(fmt.format(datavalidade)));
		emprestimo.setNumeroContrato(numeroContrato);
		emprestimo.setQuantidadeParcelas(numeroParcelas);
		emprestimo.setTaxaJurosEmprestimo(taxa);
		emprestimo.setValorContrato(valorEmprestimo_);
		emprestimo.setValorParcela(valParcela);
 
		simularEmprestimoRepository.save(emprestimo);
		
		return emprestimo;
	}

	private long gerarNumeroContrato(Date dataSimuacao) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String s;
		s = formatter.format(dataSimuacao) + String.format("%06d",
						simularEmprestimoRepository.getNextSeriesId());	
		
		return Long.parseLong(s);
	}

	private Date getDataValidade(Date dataSimuacao) {
		Calendar c = Calendar.getInstance();
		c.setTime(dataSimuacao);
		c.add(Calendar.DAY_OF_MONTH, +30);
		return c.getTime();
	}

	private Date getDataSimulacao() {
	    Date date = new Date();  
		return date;
	}

	private BigDecimal calcValorParcelas(SimularEmprestimo emprestimo, BigDecimal valorEmeprestimo, int numeroParcelas, BigDecimal taxa) {
		BigDecimal valParcela;
		
		BigDecimal aux = taxa;

		valParcela= aux.multiply(new BigDecimal(numeroParcelas))
						.add(BigDecimal.ONE)
						.multiply(valorEmeprestimo)
						.divide(new BigDecimal(numeroParcelas));
		
		return valParcela;
	}

	private BigDecimal calcTaxaJuros(BigDecimal valorEmeprestimo, int numeroParcelas) {
		BigDecimal taxa;
		taxa = (valorEmeprestimo.compareTo(new BigDecimal("1000")) < 0) ? new BigDecimal("0.018") : new BigDecimal("0.03");
		taxa = (numeroParcelas > 12) ? taxa.add(new BigDecimal("0.005")) : taxa;
		return taxa;
	}


}
