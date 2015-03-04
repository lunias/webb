package edu.capella.webb.oauth.server.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "OAuth client details not found")
public class OAuthClientDetailsNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4046228588949119763L;

	public OAuthClientDetailsNotFoundException(String clientId) {
		super("Could not find OAuthClientDetails with clientId '" + clientId + "'");
	}

}
