package edu.capella.webb.oauth.server.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User not found")
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5355072504902897722L;
	
	public UserNotFoundException(String id) {
		super("Could not find User with id '" + id + "'");
	}

}
