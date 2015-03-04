package edu.capella.webb.oauth.server.rest;

import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.capella.webb.oauth.server.domain.oauth.clientDetails.OAuthClientDetails;
import edu.capella.webb.oauth.server.domain.oauth.clientDetails.OAuthClientDetailsAssembler;
import edu.capella.webb.oauth.server.domain.oauth.clientDetails.OAuthClientDetailsResource;
import edu.capella.webb.oauth.server.domain.user.User;
import edu.capella.webb.oauth.server.domain.user.UserAssembler;
import edu.capella.webb.oauth.server.domain.user.UserResource;
import edu.capella.webb.oauth.server.repository.UserRepository;
import edu.capella.webb.oauth.server.security.AuthorityConstants;
import edu.capella.webb.oauth.server.security.SecurityUtil;
import edu.capella.webb.oauth.server.service.UserService;
import edu.capella.webb.oauth.server.util.ScopeConstant;

@RestController
@RequestMapping("/api/users")
public class UserEndpoint {

	private final Logger log = LoggerFactory.getLogger(UserEndpoint.class);
	
	private UserRepository userRepository;
	private UserService userService;
	private UserAssembler userAssembler;
	private OAuthClientDetailsAssembler clientDetailsAssembler;	
	
	@Autowired
	public UserEndpoint(UserRepository userRepository, UserService userService, UserAssembler userAssembler, OAuthClientDetailsAssembler clientDetailsAssembler) {
		
		this.userRepository = userRepository;
		this.userService = userService;
		this.userAssembler = userAssembler;
		this.clientDetailsAssembler = clientDetailsAssembler;
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
    @RequestMapping(method = RequestMethod.GET, value = "/me", produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<UserResource> getAuthenticatedUser() {
    	
    	String username = SecurityUtil.getCurrentLogin();
    	
    	if (username == null) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	
    	User user = userRepository.findOneByUsername(username);
    	
    	return new ResponseEntity<>(userAssembler.toResource(user), HttpStatus.OK);
    }
    
    @RolesAllowed(AuthorityConstants.ADMIN)
    @PreAuthorize("#oauth2.hasScope('" + ScopeConstant.OAUTH_SERVER_WRITE + "')")
    @RequestMapping(method = RequestMethod.PUT, value = "/me", produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<UserResource> updateAuthenticatedUser(@RequestBody UserResource modifiedUser) {
    	
    	String username = SecurityUtil.getCurrentLogin();
    	
    	if (username == null) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	
    	User user = userService.updateUserAccountInfo(modifiedUser);
    	
    	return new ResponseEntity<>(userAssembler.toResource(user), HttpStatus.OK);
    }    
    
    @RolesAllowed(AuthorityConstants.ADMIN)
    @PreAuthorize("#oauth2.hasScope('" + ScopeConstant.OAUTH_SERVER_READ + "')")
    @RequestMapping(method = RequestMethod.GET, value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)     
    public ResponseEntity<UserResource> getUserByUsername(@PathVariable String username) {
    	
    	User user = userRepository.findOneByUsername(username);
    	
    	return new ResponseEntity<>(userAssembler.toResource(user), HttpStatus.OK);
    }
    
    @RolesAllowed(AuthorityConstants.ADMIN)
    @PreAuthorize("#oauth2.hasScope('" + ScopeConstant.OAUTH_SERVER_WRITE + "')")
    @RequestMapping(method = RequestMethod.PUT, value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)     
    public ResponseEntity<UserResource> updateUser(@PathVariable String username, @RequestBody UserResource modifiedUser) {
    	
    	User user = userService.updateUser(modifiedUser);
    	
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
    @RequestMapping(method = RequestMethod.GET, value = "/byEmail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)      
    public ResponseEntity<UserResource> getUserByEmail(@PathVariable String email) {
    	
    	User user = userRepository.findOneByEmail(email);
    	
    	return new ResponseEntity<>(userAssembler.toResource(user), HttpStatus.OK);
    }
    
    @RolesAllowed(AuthorityConstants.ADMIN)
    @PreAuthorize("#oauth2.hasScope('" + ScopeConstant.OAUTH_SERVER_READ + "')")
    @RequestMapping(method = RequestMethod.GET, value = "/{username}/clients", produces = MediaType.APPLICATION_JSON_VALUE)      
    public ResponseEntity<List<OAuthClientDetailsResource>> getClientDetails(@PathVariable String username) {
    	
    	Set<OAuthClientDetails> clientDetails = userService.getClientDetails(username);
    	
    	return new ResponseEntity<>(clientDetailsAssembler.toResources(clientDetails), HttpStatus.OK);
    }        
}
