package edu.capella.webb.oauth.server.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Email already exists")
public class EmailAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -3146415997506640898L;

	public EmailAlreadyExistsException(String email) {
		super("Email '" + email + "' already exists");
	}

}
