package edu.capella.webb.oauth.server.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Cannot delete the user you are currently logged in as")
public class CurrentUserDeletionException extends RuntimeException {

	private static final long serialVersionUID = -3146415997506640898L;

	public CurrentUserDeletionException(String username) {
		super("User '" + username + "' cannot delete itself");
	}

}
