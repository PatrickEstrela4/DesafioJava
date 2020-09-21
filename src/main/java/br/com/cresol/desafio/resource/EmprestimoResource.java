package br.com.cresol.desafio.resource;

import java.text.ParseException;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cresol.desafio.model.SimularEmprestimo;
import br.com.cresol.desafio.repository.ClienteRepository;
import br.com.cresol.desafio.repository.ContratarEmprestimoRepository;
import br.com.cresol.desafio.repository.ParcelaRepository;
import br.com.cresol.desafio.repository.SimularEmprestimoRepository;
import br.com.cresol.desafio.controller.ContratarEmprestimoControler;
import br.com.cresol.desafio.controller.SimularEmprestimoController;
import br.com.cresol.desafio.model.ContratarEmprestimo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author evandro
 *
 */
@RestController
@RequestMapping(value="/api/emprestimo")
@Api(value="API REST EMPRESTIMOS")
@CrossOrigin(origins="*")
@Validated
public class EmprestimoResource {
	
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	SimularEmprestimoRepository simularEmprestimoRepository;
	@Autowired
	ContratarEmprestimoRepository contratarEmprestimoRepository;
	@Autowired
	ParcelaRepository parcelaRepository;

	@PostMapping("/simular")
	@ApiOperation(value="Simular um emprestimo")
	public SimularEmprestimo simular(@RequestParam("CPF") @CPF @Valid String CPF,
									 @RequestParam("NOME") String nome,
									 @RequestParam("EMAIL") @Email @Valid String email,
									 @RequestParam("VALOR_CONTRATO") String valorEmeprestimo,
									 @RequestParam("QUANTIDADE_PARCELAS")@Min(1) @Max(24) @Valid int numeroParcelas) throws ParseException {
		return new SimularEmprestimoController(clienteRepository,simularEmprestimoRepository).simular(CPF,nome,email,valorEmeprestimo,numeroParcelas);
	}
	
	@PostMapping("/contratar/{numeroContrato}")
	@ApiOperation(value="Contratar um emprestimo")
	public ContratarEmprestimo contratar(@PathVariable(value="numeroContrato") long numeroContrato) throws ParseException {
		return new ContratarEmprestimoControler(contratarEmprestimoRepository,simularEmprestimoRepository,parcelaRepository).contratar(numeroContrato);
	}

}
