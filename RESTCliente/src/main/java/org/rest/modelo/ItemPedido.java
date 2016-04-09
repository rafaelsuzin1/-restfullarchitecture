package org.rest.modelo;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ItemPedido {
	
	private Long idItem;
	
	private Pedido pedido;
	
	private Produto produto;

	@JsonCreator
	public ItemPedido() {

	}

	public Long getIdItem() {
		return idItem;
	}

	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "ItemPedido [idItem=" + idItem + ", pedido=" + pedido + ", produto=" + produto + "]";
	}
	
}
