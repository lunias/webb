package edu.capella.webb.oauth.server.rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.capella.webb.oauth.server.domain.oauth.clientDetails.OAuthClientDetails;
import edu.capella.webb.oauth.server.domain.oauth.clientDetails.OAuthClientDetailsAssembler;
import edu.capella.webb.oauth.server.domain.oauth.clientDetails.OAuthClientDetailsResource;
import edu.capella.webb.oauth.server.repository.oauth.OAuthClientDetailsRepository;
import edu.capella.webb.oauth.server.security.AuthorityConstants;
import edu.capella.webb.oauth.server.util.ScopeConstant;

@RestController
@RequestMapping("/api/oauth")
public class OAuthEndpoint {
		
	private final Logger log = LoggerFactory.getLogger(OAuthEndpoint.class);
	
	private OAuthClientDetailsRepository oauthClientDetailsRepository;
	private OAuthClientDetailsAssembler clientDetailsAssembler;
	
	@Autowired
	public OAuthEndpoint(OAuthClientDetailsRepository oauthClientDetailsRepository, OAuthClientDetailsAssembler clientDetailsAssembler) {
		
		this.oauthClientDetailsRepository = oauthClientDetailsRepository;
		this.clientDetailsAssembler = clientDetailsAssembler;
	}
	
    @RolesAllowed(AuthorityConstants.ADMIN)
    @PreAuthorize("#oauth2.hasScope('" + ScopeConstant.OAUTH_SERVER_READ + "')")
    @RequestMapping(method = RequestMethod.GET, value="clients", produces = MediaType.APPLICATION_JSON_VALUE)  	
	public ResponseEntity<List<OAuthClientDetailsResource>> getOAuthClients() {
		
    	List<OAuthClientDetails> clients = oauthClientDetailsRepository.findAll();    	
    	
		return new ResponseEntity<>(clientDetailsAssembler.toResources(clients), HttpStatus.OK);
	}	
	
}
