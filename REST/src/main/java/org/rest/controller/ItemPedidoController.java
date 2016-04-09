package org.rest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.rest.Useful.OperatingResult;
import org.rest.model.Cliente;
import org.rest.model.ItemPedido;
import org.rest.model.Pedido;
import org.rest.model.Produto;
import org.rest.repository.ItemPedidoRepository;
import org.rest.repository.PedidoRepository;
import org.rest.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itempedido")
public class ItemPedidoController {
	@Autowired // Inje��o de dependencias
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;

	@RequestMapping(value = "/add/{idPedido}/{idProduto}", method = RequestMethod.POST) // Mapeando o m�todo add para o servico rest
	public ItemPedido add(@PathVariable Long idPedido, @PathVariable Long idProduto) {
		OperatingResult resultado = new OperatingResult();

		ItemPedido itemPedido = new ItemPedido();
		try {
			Pedido pedido = pedidoRepository.findOne(idPedido);
			Produto produto = produtoRepository.findOne(idProduto);
			
			if (pedido != null && produto != null){
				itemPedido.setPedido(pedido);
				itemPedido.setProduto(produto);
				itemPedidoRepository.save(itemPedido);				
			}else
				throw new RuntimeException("Pedidou/Produto n�o encontrado!");

			// Lista de links de poss�veis a��es com esse cliente

		} catch (Exception e) {
			resultado.setMessage(e.getMessage());
			resultado.setStatus(OperatingResult.FAILURE);
			e.printStackTrace();
		}
		return itemPedido;
	}

	@RequestMapping(path = "/remove/{id}", method = RequestMethod.DELETE)
	public ItemPedido remove(@PathVariable Long id) {
		OperatingResult resultado = new OperatingResult();
		ItemPedido itemPedido = itemPedidoRepository.findOne(id);
		

		try {
			if (itemPedido != null) {
				itemPedidoRepository.delete(itemPedido);
			} else{
				throw new RuntimeException("Pedido n�o encontrado."); 
			}

		} catch (Exception e) {
			resultado.setMessage(e.getMessage());
			resultado.setStatus(OperatingResult.FAILURE);
			e.printStackTrace();
		}
		System.out.println("Pedido c�digo " + id + " removido com sucesso."); 
		return itemPedido;
	}

}
