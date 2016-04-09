package org.rest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.rest.Useful.OperatingResult;
import org.rest.model.Cliente;
import org.rest.model.Pedido;
import org.rest.repository.ClienteRepository;
import org.rest.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	OperatingResult resultado = new OperatingResult();
	
	@RequestMapping(path = "/add/{idCliente}", method = RequestMethod.POST)
	public Pedido add(@PathVariable Long idCliente){
		Pedido pedido = new Pedido();
		Cliente cliente = clienteRepository.findOne(idCliente);
		if (cliente != null){
			pedido.setCliente(cliente);
			pedidoRepository.save(pedido);
			// Lista de links de poss�veis a��es com esse cliente
			resultado.add(linkTo(methodOn(PedidoController.class).getPedido(pedido.getIdPedido())).withRel("Consultar pedido"));
			resultado.add(linkTo(methodOn(PedidoController.class).getPedidos()).withRel("Consultar todos os pedidos"));
			resultado.add(linkTo(methodOn(PedidoController.class).remove(pedido.getIdPedido())).withRel("Remover pedido"));
			
		}else
			throw new RuntimeException("Cliente informado n�o existe! ");
		
		return pedido;
	}
	
	@RequestMapping(path = "/remove/{idPedido}", method = RequestMethod.DELETE)
	public Pedido remove(@PathVariable Long idPedido){
		Pedido pedido = pedidoRepository.findOne(idPedido);
		if (pedido != null){
			pedidoRepository.delete(pedido);
			resultado.add(linkTo(methodOn(PedidoController.class).getPedido(pedido.getIdPedido())).withRel("Consultar pedido"));
			resultado.add(linkTo(methodOn(PedidoController.class).getPedidos()).withRel("Consultar todos os pedidos"));
		}else
			throw new RuntimeException("Pedido n�o encontrado!");
		return pedido;
	}
	
	@RequestMapping(path = "/getPedido/{idPedido}", method = RequestMethod.GET)
	public Pedido getPedido(@PathVariable Long idPedido){
		Pedido pedido = pedidoRepository.findOne(idPedido);
		if (pedido != null){
			resultado.add(linkTo(methodOn(PedidoController.class).remove(pedido.getIdPedido())).withRel("Remover pedido"));
			return pedido;
		}else 
			throw new RuntimeException("Pedido n�o encontrado !");
		
	}
	
	@RequestMapping(path = "/getPedidos", method = RequestMethod.GET)
	public List<Pedido> getPedidos(){
		List<Pedido> pedidos = pedidoRepository.findAll();
		if (!pedidos.isEmpty()){
			for (Pedido pedido : pedidos){
				resultado.add(linkTo(methodOn(PedidoController.class).getPedido(pedido.getIdPedido())).withRel("Consultar pedido"));
				resultado.add(linkTo(methodOn(PedidoController.class).getPedidos()).withRel("Consultar todos os pedidos"));
				resultado.add(linkTo(methodOn(PedidoController.class).remove(pedido.getIdPedido())).withRel("Remover pedido"));
			}
			return pedidos;
		}else 
			throw new RuntimeException("N�o existem pedidos cadastrados! ");
	}
	
	@RequestMapping(path = "/getPedidosCliente/{idCliente}", method = RequestMethod.GET )
	public List<Pedido> getPedidosCliente(@PathVariable Long idCliente){
		Cliente cliente  = clienteRepository.findOne(idCliente);
		if (cliente != null){
			List<Pedido> pedidos = pedidoRepository.findByCliente(cliente);
			if (!pedidos.isEmpty()){
				for (Pedido pedido : pedidos){
					resultado.add(linkTo(methodOn(PedidoController.class).getPedido(pedido.getIdPedido())).withRel("Consultar pedido"));
					resultado.add(linkTo(methodOn(PedidoController.class).getPedidos()).withRel("Consultar todos os pedidos"));
					resultado.add(linkTo(methodOn(PedidoController.class).remove(pedido.getIdPedido())).withRel("Remover pedido"));
				}
				return pedidos;
			}else
				throw new RuntimeException("N�o h� pedidos para este cliente");
		}else
			throw new RuntimeException("Cliente n�o encontrado !");
	}
}
