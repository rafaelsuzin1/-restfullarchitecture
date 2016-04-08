package org.rest.modelo;

import java.util.Map;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Cliente extends ResourceSupport {
	
	private Long idCliente;
	
	private String nomeCliente;
	
	@JsonCreator
	public Cliente() {

	}

	@JsonCreator
	public Cliente(@JsonProperty("nomeCliente")String nomeCliente/*,@JsonProperty("_links") Map<String, Map<String, String>> links*/) {
		this.nomeCliente = nomeCliente;
		
		/*for (String linkRel: links.keySet()) {
			Map<String, String> linkURIs = links.get(linkRel);
			for (String linkHref: linkURIs.keySet()) {
				Link link=new Link(linkURIs.get(linkHref), linkRel);
				this.add(link);
			}
		}*/
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

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nomeCliente=" + nomeCliente + ", links= " + getLinks()+ "]";
	}

}