package org.rest.modelo;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Produto extends ResourceSupport {

	private Long idProduto;

	private String dsProduto;

	@JsonCreator
	public Produto() {

	}

	@JsonCreator
	public Produto(@JsonProperty("nomeCliente") String dsProduto) {
		this.dsProduto = dsProduto;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getDsProduto() {
		return dsProduto;
	}

	public void setDsProduto(String dsProduto) {
		this.dsProduto = dsProduto;
	}
	
}
