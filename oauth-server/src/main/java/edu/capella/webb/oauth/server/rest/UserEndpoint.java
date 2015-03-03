package edu.capella.webb.oauth.server.rest;

import javax.annotation.security.RolesAllowed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.capella.webb.oauth.server.domain.user.User;
import edu.capella.webb.oauth.server.domain.user.UserAssembler;
import edu.capella.webb.oauth.server.domain.user.UserResource;
import edu.capella.webb.oauth.server.repository.UserRepository;
import edu.capella.webb.oauth.server.security.AuthorityConstants;
import edu.capella.webb.oauth.server.util.ScopeConstant;

@RestController
@RequestMapping("/api/users")
public class UserEndpoint {

	private final Logger log = LoggerFactory.getLogger(UserEndpoint.class);
	
	private UserRepository userRepository;
	private UserAssembler userAssembler;
	
	@Autowired
	public UserEndpoint(UserRepository userRepository, UserAssembler userAssembler) {
		
		this.userRepository = userRepository;
		this.userAssembler = userAssembler;
	}
	
    @RolesAllowed(AuthorityConstants.ADMIN)
    @PreAuthorize("#oauth2.hasScope('" + ScopeConstant.OAUTH_SERVER_READ + "')")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)    
	public ResponseEntity<PagedResources<UserResource>> getUsers(Pageable pageable, PagedResourcesAssembler<User> assembler) {
		
    	Page<User> page = userRepository.findAll(pageable);
    	
    	return new ResponseEntity<>(assembler.toResource(page, userAssembler), HttpStatus.OK);    	
	}
    
    @RolesAllowed(AuthorityConstants.ADMIN)
    @PreAuthorize("#oauth2.hasScope('" + ScopeConstant.OAUTH_SERVER_READ + "')")
    @RequestMapping(method = RequestMethod.GET, value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)     
    public ResponseEntity<UserResource> getUserByUsername(@PathVariable String username) {
    	
    	User user = userRepository.findOneByUsername(username);
    	
    	return new ResponseEntity<>(userAssembler.toResource(user), HttpStatus.OK);
    }
    
    @RolesAllowed(AuthorityConstants.ADMIN)
    @PreAuthorize("#oauth2.hasScope('" + ScopeConstant.OAUTH_SERVER_READ + "')")
    @RequestMapping(method = RequestMethod.GET, value = "/byId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)      
    public ResponseEntity<UserResource> getUserById(@PathVariable Long id) {
    	
    	User user = userRepository.findOne(id);
    	
    	return new ResponseEntity<>(userAssembler.toResource(user), HttpStatus.OK);
    }
    
    @RolesAllowed(AuthorityConstants.ADMIN)
    @PreAuthorize("#oauth2.hasScope('" + ScopeConstant.OAUTH_SERVER_READ + "')")
    @RequestMapping(method = RequestMethod.GET, value = "/byEmail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)      
    public ResponseEntity<UserResource> getUserByEmail(@PathVariable String email) {
    	
    	User user = userRepository.findOneByEmail(email);
    	
    	return new ResponseEntity<>(userAssembler.toResource(user), HttpStatus.OK);
    }
}
