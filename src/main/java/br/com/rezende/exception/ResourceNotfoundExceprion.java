package br.com.rezende.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotfoundExceprion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceNotfoundExceprion(String message) {
		super(message);
	}

}
