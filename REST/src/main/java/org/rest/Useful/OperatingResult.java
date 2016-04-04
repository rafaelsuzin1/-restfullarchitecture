package org.rest.Useful;

import org.springframework.hateoas.ResourceSupport;

public class OperatingResult extends ResourceSupport{

	public static final String SUCCESS="SUCCESS";
	public static final String FAILURE="FAILURE";
	
	private ResourceSupport object;
	private String status = SUCCESS;
	private String message = "";
	public ResourceSupport getObject() {
		return object;
	}
	public void setObject(ResourceSupport object) {
		this.object = object;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
