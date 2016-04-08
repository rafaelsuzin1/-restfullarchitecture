package org.rest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.rest.Useful.OperatingResult;
import org.rest.model.Cliente;
import org.rest.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController // digo aqui que esta classe é de controle do serviço REST para
				// clientes
@RequestMapping("/cliente") // Passando diretório para esta classe.
public class ClienteController {

	@Autowired // Injeção de dependencias
	private ClienteRepository clienteRep;

	@RequestMapping(value = "/add/{nome}", method = RequestMethod.POST) // Mapeando o método add para o servico rest
	public Cliente adicionarCliente(@PathVariable String nome) {
		OperatingResult resultado = new OperatingResult();
		Cliente cliente = new Cliente(nome);
		try {
			clienteRep.save(cliente);

			// Lista de links de possíveis ações com esse cliente
			resultado.add(linkTo(methodOn(ClienteController.class).getClientes()).withRel("Consultar todos os Clientes"));
			resultado.add(linkTo(methodOn(ClienteController.class).getCliente(cliente.getIdCliente())).withRel("Consultar Cliente"));
			resultado.add(linkTo(methodOn(ClienteController.class).update(cliente.getIdCliente(), cliente.getNomeCliente())).withRel("Alterar Cliente"));
			resultado.add(linkTo(methodOn(ClienteController.class).remove(cliente.getIdCliente())).withRel("Remover Cliente"));

		} catch (Exception e) {
			resultado.setMessage(e.getMessage());
			resultado.setStatus(OperatingResult.FAILURE);
			e.printStackTrace();
		}
		return cliente;
	}

	@RequestMapping(path = "/remove/{id}", method = RequestMethod.DELETE)
	public Cliente remove(@PathVariable Long id) {
		OperatingResult resultado = new OperatingResult();
		Cliente cliente = clienteRep.findOne(id);

		try {
			if (cliente != null) {
				clienteRep.delete(cliente);
			} else{
				throw new RuntimeException("Cliente não encontrado."); 
			}
			resultado.add(linkTo(methodOn(ClienteController.class).getClientes()).withRel("Consultar todos os Clientes"));

		} catch (Exception e) {
			resultado.setMessage(e.getMessage());
			resultado.setStatus(OperatingResult.FAILURE);
			e.printStackTrace();
		}
		System.out.println("Cliente código " + id + " removido com sucesso."); 
		return cliente;
	}

	@RequestMapping(path = "/update/{id}/{nome}", method = RequestMethod.PUT)
	public Cliente update(@PathVariable Long id, @PathVariable String nome) {
		OperatingResult resultado = new OperatingResult();
		Cliente cliente = clienteRep.findOne(id);
		try {
			if (cliente != null) {
				cliente.setNomeCliente(nome);
				clienteRep.save(cliente);
			}

			resultado.add(linkTo(methodOn(ClienteController.class).getClientes()).withRel("Consultar todos os Clientes"));
			resultado.add(linkTo(methodOn(ClienteController.class).getCliente(cliente.getIdCliente())).withRel("Consultar Cliente"));
			resultado.add(linkTo(methodOn(ClienteController.class).update(cliente.getIdCliente(), cliente.getNomeCliente())).withRel("Alterar Cliente"));
			resultado.add(linkTo(methodOn(ClienteController.class).remove(cliente.getIdCliente())).withRel("Remover Cliente"));

		} catch (Exception e) {
			resultado.setMessage(e.getMessage());
			resultado.setStatus(OperatingResult.FAILURE);
			e.printStackTrace();
		}
		return cliente;
	}

	@RequestMapping(path = "/getCliente/{idCliente}", method = RequestMethod.GET)
	public Cliente getCliente(@PathVariable Long idCliente) {
		OperatingResult result = new OperatingResult();
		Cliente cli = clienteRep.findOne(idCliente);
		
		result.add(linkTo(methodOn(ClienteController.class).getCliente(cli.getIdCliente())).withRel("Consultar Cliente"));
		result.add(linkTo(methodOn(ClienteController.class).update(cli.getIdCliente(), cli.getNomeCliente())).withRel("Alterar Cliente"));
		result.add(linkTo(methodOn(ClienteController.class).remove(cli.getIdCliente())).withRel("Remover Cliente"));
		result.add(linkTo(methodOn(ClienteController.class).getClientes()).withRel("Consultar todos os Clientes"));

		return cli;

	}

	@RequestMapping(path = "/getClientes", method = RequestMethod.GET)
	public List<Cliente> getClientes() {
		OperatingResult result = new OperatingResult();
		List<Cliente> clientes = clienteRep.findAll();
		for (Cliente cli : clientes) {
			result.add(linkTo(methodOn(ClienteController.class).getCliente(cli.getIdCliente())).withRel("Consultar Cliente"));
			result.add(linkTo(methodOn(ClienteController.class).update(cli.getIdCliente(), cli.getNomeCliente())).withRel("Alterar Cliente"));
			result.add(linkTo(methodOn(ClienteController.class).remove(cli.getIdCliente())).withRel("Remover Cliente"));
		}
		return clientes;
	}
}