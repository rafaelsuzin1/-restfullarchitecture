package org.rest;

import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import org.rest.modelo.Cliente;
import org.rest.modelo.ItemPedido;
import org.rest.modelo.Pedido;
import org.rest.modelo.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestClienteApplication {

	public static Long chamarAcoes() {
		return Long.parseLong(
				JOptionPane.showInputDialog("Escolha uma das opções:\n1 - Cliente\n2 - Pedido\n3 - Produto"));
	}

	@Autowired(required = false)
	public static void main(String[] args) {

		RestTemplate restTemplate = new RestTemplate();
		Long opcoesExecucao = 99999L;
		while (opcoesExecucao != null) {
			opcoesExecucao = chamarAcoes();
			if (opcoesExecucao == 1L) {
				Long opcoes = Long.parseLong(JOptionPane.showInputDialog(
						"Escolha uma das opções:\n1 - Cadastrar Cliente\n2 - Consultar cliente\n3 - Consultar todos os clientes\n4 - Remover Cliente"));
				if (opcoes == 1L) {
					Cliente clienteRequest = new Cliente();
					Cliente cliente = restTemplate.postForObject(
							"http://localhost:8080/cliente/add/"
									+ JOptionPane.showInputDialog("Digite o nome do cliente."),
							clienteRequest, Cliente.class);
					System.out.println("Codigo: " + cliente.getIdCliente());
					System.out.println("Nome: " + cliente.getNomeCliente());
					System.out.println("links:" + cliente.getLinks());
				} else if (opcoes == 2L) {
					Cliente cliente = restTemplate.getForObject("http://localhost:8080/cliente/getCliente/"
							+ JOptionPane.showInputDialog("Digite o código do cliente!"), Cliente.class);
					System.out.println("Codigo: " + cliente.getIdCliente());
					System.out.println("Nome: " + cliente.getNomeCliente());
					System.out.println("links:" + cliente.getLinks());
				} else if (opcoes == 3L) {
					ResponseEntity<Cliente[]> ResponseEntity = restTemplate
							.getForEntity("http://localhost:8080/cliente/getClientes", Cliente[].class);// Retornando
																										// uma
																										// lista
																										// de
																										// objetos
					List<Cliente> clientes = Arrays.asList(ResponseEntity.getBody());
					for (Cliente cliente : clientes) {
						System.out.println("Codigo: " + cliente.getIdCliente());
						System.out.println("Nome: " + cliente.getNomeCliente());
						System.out.println("links:" + cliente.getLinks());
					}
				} else if (opcoes == 4L) {
					restTemplate.delete("http://localhost:8080/cliente/remove/"
							+ JOptionPane.showInputDialog("Digite o código do cliente!"));
				} else {
					System.out.println("Opção invalida!");
				}
			} else if (opcoesExecucao == 2L) {
				Long opcoes = Long.parseLong(JOptionPane.showInputDialog(
						"Escolha uma das opções:\n1 - Cadastrar Pedido\n2 - Consultar Pedido\n3 - Consultar todos os Pedidos\n4 - Remover Pedido\n5 - Consultar pedidos do cliente"));
				if (opcoes == 1L) {
					Pedido pedidoRequest = new Pedido();
					Pedido pedido = restTemplate.postForObject(
							"http://localhost:8080/pedido/add/"
									+ JOptionPane.showInputDialog("Digite o codigo do cliente."),
							pedidoRequest, Pedido.class);
					Long opcoesAddItem = 99999L;
					while(opcoesAddItem > 0){
						opcoesAddItem = Long.parseLong(JOptionPane.showInputDialog(
								"Escolha uma das opções:\n1 - Adicionar produto ao pedido\n2 - Remover produto do pedido\n"));
						if (opcoesAddItem == 1L){
							ItemPedido itemPedidoRequest = new ItemPedido();
							ItemPedido itemPedido = restTemplate.postForObject(
									"http://localhost:8080/cliente/add/"
											+ JOptionPane.showInputDialog("Digite o codigo do produto."),
											itemPedidoRequest, ItemPedido.class);
						}else if (opcoesAddItem == 2L){
							restTemplate.delete("http://localhost:8080/itempedido/remove/"
									+ JOptionPane.showInputDialog("Digite o código do item!"));
						}else{
							System.out.println("Opção invalida!");
						}
					}
					System.out.println("Codigo: " + pedido.getIdPedido());
					System.out.println("Cliente: " + pedido.getCliente().getIdCliente() + " - "
							+ pedido.getCliente().getNomeCliente());
					for (ItemPedido item : pedido.getItensPedido()){
					  System.out.println("Item" + item.toString());
					}
					System.out.println("links:" + pedido.getLinks());
				} else if (opcoes == 2L) {
					Pedido pedido = restTemplate.getForObject("http://localhost:8080/pedido/getPedido/"
							+ JOptionPane.showInputDialog("Digite o código do pedido!"), Pedido.class);
					
							
					System.out.println("Codigo: " + pedido.getIdPedido());
					System.out.println("Cliente: " + pedido.getCliente().getIdCliente() + " - "
							+ pedido.getCliente().getNomeCliente());
					System.out.println("Produtos:" );
					System.out.println("links:" + pedido.getLinks());
					
					
				} else if (opcoes == 3L) {
					ResponseEntity<Pedido[]> ResponseEntity = restTemplate
							.getForEntity("http://localhost:8080/pedido/getPedidos", Pedido[].class);// return
																										// list
																										// objects
					List<Pedido> pedidos = Arrays.asList(ResponseEntity.getBody());
					for (Pedido pedido : pedidos) {
						System.out.println("Codigo: " + pedido.getIdPedido());
						System.out.println("Cliente: " + pedido.getCliente().getIdCliente() + " - "
								+ pedido.getCliente().getNomeCliente());
						System.out.println("links:" + pedido.getLinks());
					}
				} else if (opcoes == 4L) {
					restTemplate.delete("http://localhost:8080/pedido/remove/"
							+ JOptionPane.showInputDialog("Digite o código do pedido!"));
				} else if (opcoes == 5L) {
					ResponseEntity<Pedido[]> ResponseEntity = restTemplate
							.getForEntity(
									"http://localhost:8080/pedido/getPedidosCliente/"
											+ JOptionPane.showInputDialog("Digite o codigo do cliente."),
									Pedido[].class);// return
													// list
													// objects
					List<Pedido> pedidos = Arrays.asList(ResponseEntity.getBody());
					for (Pedido pedido : pedidos) {
						System.out.println("Codigo: " + pedido.getIdPedido());
						System.out.println("Cliente: " + pedido.getCliente().getIdCliente() + " - "
								+ pedido.getCliente().getNomeCliente());
						System.out.println("links:" + pedido.getLinks());
					}
				} else {
					System.out.println("Opção invalida!");
				}
			} else if (opcoesExecucao == 3L) {
				Long opcoes = Long.parseLong(JOptionPane.showInputDialog(
						"Escolha uma das opções:\n1 - Cadastrar Produto\n2 - Consultar Produto\n3 - Consultar todos os Produtos\n4 - Remover Produto"));
				if (opcoes == 1L) {
					Produto produtoRequest = new Produto();
					Produto produto = restTemplate.postForObject(
							"http://localhost:8080/produto/add/"
									+ JOptionPane.showInputDialog("Digite o nome do produto."),
							produtoRequest, Produto.class);
					System.out.println("Codigo: " + produto.getIdProduto());
					System.out.println("Nome: " + produto.getDsProduto());
					System.out.println("links:" + produto.getLinks());
				} else if (opcoes == 2L) {
					Produto produto = restTemplate.getForObject("http://localhost:8080/produto/getProduto/"
							+ JOptionPane.showInputDialog("Digite o código do produto!"), Produto.class);
					System.out.println("Codigo: " + produto.getIdProduto());
					System.out.println("Nome: " + produto.getDsProduto());
					System.out.println("links:" + produto.getLinks());
				} else if (opcoes == 3L) {
					ResponseEntity<Produto[]> ResponseEntity = restTemplate
							.getForEntity("http://localhost:8080/produto/getProdutos", Produto[].class);// Retornando
																										// uma
																										// lista
																										// de
																										// objetos
					List<Produto> produtos = Arrays.asList(ResponseEntity.getBody());
					for (Produto produto : produtos) {
						System.out.println("Codigo: " + produto.getIdProduto());
						System.out.println("Nome: " + produto.getDsProduto());
						System.out.println("links:" + produto.getLinks());
					}
				} else if (opcoes == 4L) {
					restTemplate.delete("http://localhost:8080/produto/remove/"
							+ JOptionPane.showInputDialog("Digite o código do produto!"));
				} else {
					System.out.println("Opção invalida!");
				}
			} else {
				System.out.println("Opção invalida!");
			}
		}
	}
}
