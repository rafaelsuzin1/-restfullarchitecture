package org.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "CLIENT")
public class Client extends ResourceSupport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CLIENT", length = 8)
	private Long idClient;
	
	@Column(name = "NAME_CLIENT")
	private String nameClient;
	
	@JsonCreator
	public Client() {

	}

	@JsonCreator
	public Client(@JsonProperty("nameClient")String nameClient) {
		this.nameClient = nameClient;
	}
}
