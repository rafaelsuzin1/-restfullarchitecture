package org.rest.modelo;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Pedido extends ResourceSupport {
	
	private Long idPedido;
	
	private Cliente cliente;
	
	private List<ItemPedido> itensPedido;
	
	@JsonCreator
	public Pedido() {

	}

	@JsonCreator
	public Pedido(@JsonProperty("cliente")Cliente cliente) {
		this.cliente = cliente;
}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}

}

