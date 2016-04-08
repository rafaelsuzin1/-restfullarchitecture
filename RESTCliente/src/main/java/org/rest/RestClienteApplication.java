package org.rest;

import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import org.rest.modelo.Cliente;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestClienteApplication {

	public static void main(String[] args) {

		RestTemplate restTemplate = new RestTemplate();

		Long opcoes = Long.parseLong(JOptionPane.showInputDialog("Escolha uma das opções:\n1 - Cadastrar Cliente\n2 - Consultar cliente\n3 - Consultar todos os clientes\n4 - Remover Cliente"));
		if (opcoes == 1L){
			Cliente clienteRequest = new Cliente();
			Cliente cliente = restTemplate.postForObject("http://localhost:8080/cliente/add/" + JOptionPane.showInputDialog("Digite o nome do cliente."), clienteRequest, Cliente.class);
			System.out.println("Codigo: " + cliente.getIdCliente());
			System.out.println("Nome: " + cliente.getNomeCliente());
			System.out.println("links:" + cliente.getLinks());
		}else if (opcoes == 2L){ 
			Cliente cliente = restTemplate.getForObject("http://localhost:8080/cliente/getCliente/" + JOptionPane.showInputDialog("Digite o código do cliente!"), Cliente.class);
			System.out.println("Codigo: " + cliente.getIdCliente());
			System.out.println("Nome: " + cliente.getNomeCliente());
			System.out.println("links:" + cliente.getLinks());
		}else if (opcoes == 3L){
			ResponseEntity<Cliente[]> ResponseEntity = restTemplate.getForEntity("http://localhost:8080/cliente/getClientes", Cliente[].class);//Retornando uma lista de objetos 
			List<Cliente> clientes = Arrays.asList(ResponseEntity.getBody());
			for (Cliente cliente : clientes){
				System.out.println("Codigo: " + cliente.getIdCliente());
				System.out.println("Nome: " + cliente.getNomeCliente());
				System.out.println("links:" + cliente.getLinks());	
			}
		}else if (opcoes == 4L){
			restTemplate.delete("http://localhost:8080/cliente/remove/" + JOptionPane.showInputDialog("Digite o código do cliente!"));
		}else{
			System.out.println("Opção invalida!");
		}
		
	}
}
