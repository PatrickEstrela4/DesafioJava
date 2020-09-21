package br.com.cresol.desafio.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import br.com.cresol.desafio.model.ContratarEmprestimo;
import br.com.cresol.desafio.model.Parcela;
import br.com.cresol.desafio.model.SimularEmprestimo;
import br.com.cresol.desafio.repository.ContratarEmprestimoRepository;
import br.com.cresol.desafio.repository.ParcelaRepository;
import br.com.cresol.desafio.repository.SimularEmprestimoRepository;

public class ContratarEmprestimoControler {
	
	ContratarEmprestimoRepository contratarEmprestimoRepository;
	SimularEmprestimoRepository simularEmprestimoRepositor;
	ParcelaRepository parcelaRepository;

	public ContratarEmprestimoControler(ContratarEmprestimoRepository contratarEmprestimoRepository,
			SimularEmprestimoRepository simularEmprestimoRepository, ParcelaRepository parcelaRepository) {
		this.contratarEmprestimoRepository = contratarEmprestimoRepository;
		this.simularEmprestimoRepositor = simularEmprestimoRepository;
		this.parcelaRepository = parcelaRepository;
	}

	public ContratarEmprestimo contratar(long numeroContrato) throws ParseException {
		ContratarEmprestimo contratarEmprestimo;
		if(contratarEmprestimoRepository.existsBynumeroContrato(numeroContrato)) {
			contratarEmprestimo = contratarEmprestimoRepository.findBynumeroContrato(numeroContrato);
			List<Parcela> parcelas = parcelaRepository.findByemprestimo(contratarEmprestimo);
			parcelas = ajustaParcela(parcelas);
			contratarEmprestimo.setParcelas(parcelas);
			return contratarEmprestimo;
		}
		SimularEmprestimo simularEmprestimo = simularEmprestimoRepositor.findBynumeroContrato(numeroContrato);
		contratarEmprestimo = simularToContratar(simularEmprestimo);
		contratarEmprestimoRepository.save(contratarEmprestimo);
		contratarEmprestimo.setParcelas(gerarParcelas(simularEmprestimo));
		simularEmprestimoRepositor.delete(simularEmprestimo);
		List<Parcela> parcelas = parcelaRepository.findByemprestimo(contratarEmprestimo);
		parcelas = ajustaParcela(parcelas);
		contratarEmprestimo.setParcelas(parcelas);
		return contratarEmprestimo;
	}

	private List<Parcela> gerarParcelas(SimularEmprestimo simularEmprestimo) throws ParseException {
		ContratarEmprestimo contratarEmprestimo = contratarEmprestimoRepository.findBynumeroContrato(simularEmprestimo.getNumeroContrato()); 
		List<Parcela> parcelas = new LinkedList<>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(contratarEmprestimo.getDataContratacao());
		Parcela p = new Parcela();
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		for (int i = 1; i <= simularEmprestimo.getQuantidadeParcelas(); i++) {
			p = new Parcela();
			p.setEmprestimo(contratarEmprestimo);
			p.setNumeroContrato(simularEmprestimo.getNumeroContrato());
			p.setNumeroDaParcela(i);
			p.setValorParcela(simularEmprestimo.getValorParcela());
			cal.add(Calendar.MONTH, 1);
			p.setDataVencimento(fmt.parse(fmt.format(cal.getTime())));
			parcelaRepository.save(p);
			parcelas.add(p);
		}
		return parcelas;
	}

	private ContratarEmprestimo simularToContratar(SimularEmprestimo simularEmprestimo) throws ParseException {
		ContratarEmprestimo ce = new ContratarEmprestimo();
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		ce.setDataContratacao(fmt.parse(fmt.format(new Date())));
		ce.setIofContrato(BigDecimal.ZERO);
		ce.setNumeroContrato(simularEmprestimo.getNumeroContrato());
		ce.setQuantidadeParcelas(simularEmprestimo.getQuantidadeParcelas());
		ce.setTaxaJurosEmprestimo(simularEmprestimo.getTaxaJurosEmprestimo());
		ce.setValorContrato(simularEmprestimo.getValorContrato());
		
		return ce;
	}
	
	private List<Parcela> ajustaParcela(List<Parcela> parcelas) throws ParseException {
		List<Parcela> p = parcelas;
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		for (Parcela parcela : p) {
			parcela.setDataVencimento(fmt.parse(fmt.format(parcela.getDataVencimento())));
			parcela.setEmprestimo(null);
		}
		
		
		return p;
	}

}
