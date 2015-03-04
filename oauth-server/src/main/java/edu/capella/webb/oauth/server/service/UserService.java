package edu.capella.webb.oauth.server.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.capella.webb.oauth.server.domain.Authority;
import edu.capella.webb.oauth.server.domain.oauth.clientDetails.OAuthClientDetails;
import edu.capella.webb.oauth.server.domain.user.User;
import edu.capella.webb.oauth.server.domain.user.UserResource;
import edu.capella.webb.oauth.server.repository.AuthorityRepository;
import edu.capella.webb.oauth.server.repository.UserRepository;
import edu.capella.webb.oauth.server.rest.exception.EmailAlreadyExistsException;
import edu.capella.webb.oauth.server.rest.exception.UserNotFoundException;

@Service
@Transactional
public class UserService {

	private UserRepository userRepository;	
	private AuthorityRepository authorityRepository;
	
	@Autowired
	public UserService(UserRepository userRepository, AuthorityRepository authorityRepository) {
		
		this.userRepository = userRepository;
		this.authorityRepository = authorityRepository;
	}
	
    public User updateUser(UserResource userResource) throws UserNotFoundException, EmailAlreadyExistsException {
    	
    	String requestedEmail = userResource.getEmail();
    	String requestedUsername = userResource.getUsername();
    	
    	User user = userRepository.findOneByUsername(requestedUsername);
    	if (user == null) {
    		throw new UserNotFoundException(requestedUsername);
    	}    	    
    	
    	User existingEmailUser = userRepository.findOneByEmail(userResource.getEmail());
    	if (existingEmailUser != null && !requestedEmail.equals(user.getEmail())) {
    		throw new EmailAlreadyExistsException(requestedEmail);
    	}
    	
    	user.setFirstName(userResource.getFirstName());
    	user.setLastName(userResource.getLastName());
    	user.setEmail(requestedEmail);
    	
    	Set<Authority> validAuthorities = new HashSet<>();
    	for (String role : userResource.getRoles()) {
    		
    		Authority authority = authorityRepository.findOne(role);    		
    		if (authority != null) {
        		validAuthorities.add(authority);	
    		}
    	}
    	
    	user.setAuthorities(validAuthorities);
    	
    	return userRepository.save(user);
    }	
	
	public User updateUserAccountInfo(UserResource userResource) throws UserNotFoundException {
		
		String username = userResource.getUsername();
		
		User user = userRepository.findOneByUsername(username);
		
		if (user == null) throw new UserNotFoundException(username);
		
		user.setFirstName(userResource.getFirstName());
		user.setLastName(userResource.getLastName());
		user.setEmail(userResource.getEmail());
		user.setLangKey(userResource.getLangKey());
		
		return userRepository.save(user);
	}
	
	@Transactional(readOnly = true)
	public Set<OAuthClientDetails> getClientDetails(String username) throws UserNotFoundException {
		
		User user = userRepository.findOneByUsername(username);
		
		if (user == null) throw new UserNotFoundException(username);
		
		user.getClientDetails().size();
		
		return user.getClientDetails();
	}
	
}
