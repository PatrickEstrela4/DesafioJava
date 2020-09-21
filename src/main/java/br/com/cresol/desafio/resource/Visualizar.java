package br.com.cresol.desafio.resource;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cresol.desafio.model.Cliente;
import br.com.cresol.desafio.model.ContratarEmprestimo;
import br.com.cresol.desafio.model.Parcela;
import br.com.cresol.desafio.model.SimularEmprestimo;
import br.com.cresol.desafio.repository.ClienteRepository;
import br.com.cresol.desafio.repository.ContratarEmprestimoRepository;
import br.com.cresol.desafio.repository.ParcelaRepository;
import br.com.cresol.desafio.repository.SimularEmprestimoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api/visualizar")
@Api(value="API REST EMPRESTIMOS")
@CrossOrigin(origins="*")
@Validated
public class Visualizar {
	
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	ContratarEmprestimoRepository contratarEmprestimoRepository;
	@Autowired
	SimularEmprestimoRepository simularEmprestimoRepository;
	@Autowired
	ParcelaRepository parcelaRepository;
	
	@GetMapping("/cliente")
	@ApiOperation(value="Visualizar Todos os Clientes")
	public List<Cliente> visualizarCliente() throws ParseException {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/contratarEmprestimo")
	@ApiOperation(value="Visualizar Todos os Emprestimos Contratados")
	public List<ContratarEmprestimo> visualizarContratarEmprestimo() throws ParseException {
		return contratarEmprestimoRepository.findAll();
	}
	
	@GetMapping("/simularEmprestimo")
	@ApiOperation(value="Visualizar Todos os Emprestimos simulado não contratado")
	public List<SimularEmprestimo> visualizarSimularEmprestimo() throws ParseException {
		return simularEmprestimoRepository.findAll();
	}
	
	@GetMapping("/parcela")
	@ApiOperation(value="Visualizar Todos as Parcelas")
	public List<Parcela> visualizarParcela() throws ParseException {
		return parcelaRepository.findAll();
	}
}
