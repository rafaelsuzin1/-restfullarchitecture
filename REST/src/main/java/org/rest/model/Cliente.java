package org.rest.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "CLIENTE")
public class Cliente extends ResourceSupport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CLIENTE", length = 8)
	private Long idCliente;
	
	@Column(name = "NOME_CLIENTE")
	private String nomeCliente;
	
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private List<Pedido> pedidos;
	
	@JsonCreator
	public Cliente() {

	}

	@JsonCreator
	public Cliente(@JsonProperty("nomeCliente")String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	/*
	@JsonIgnore
	public List<Pedido> getPedidos() {
		return pedidos;
	}*/
	/*public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}*/
	
}
