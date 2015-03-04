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

import edu.capella.webb.oauth.server.domain.Authority;
import edu.capella.webb.oauth.server.repository.AuthorityRepository;
import edu.capella.webb.oauth.server.security.AuthorityConstants;
import edu.capella.webb.oauth.server.util.ScopeConstant;

@RestController
@RequestMapping("/api/authorities")
public class AuthorityEndpoint {

	private final Logger log = LoggerFactory.getLogger(AuthorityEndpoint.class);
	
	private AuthorityRepository authorityRepository;
	
	@Autowired
	public AuthorityEndpoint(AuthorityRepository authorityRepository) {
		this.authorityRepository = authorityRepository;
	}
	
    @RolesAllowed(AuthorityConstants.ADMIN)
    @PreAuthorize("#oauth2.hasScope('" + ScopeConstant.OAUTH_SERVER_READ + "')")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)  	
	public ResponseEntity<List<Authority>> getAuthorities() {
		
		List<Authority> authorities = authorityRepository.findAll();
		
		return new ResponseEntity<>(authorities, HttpStatus.OK);
	}
	
}
