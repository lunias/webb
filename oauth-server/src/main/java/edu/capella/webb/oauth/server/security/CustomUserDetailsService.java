package edu.capella.webb.oauth.server.security;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.capella.webb.oauth.server.domain.Authority;
import edu.capella.webb.oauth.server.domain.User;
import edu.capella.webb.oauth.server.repository.UserRepository;

@Component("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	private final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);	
	
	private UserRepository userRepository;
	
	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		
		this.userRepository = userRepository;		 
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.debug("Authenticating {}", username);
        
        String lowercaseUsername = username.toLowerCase();
        
        User userFromDatabase = userRepository.findOneByLogin(username);        
        if (userFromDatabase == null) {
            throw new UsernameNotFoundException("User " + lowercaseUsername + " was not found in the database");
        }

        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority : userFromDatabase.getAuthorities()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
            grantedAuthorities.add(grantedAuthority);
        }
        
        return new org.springframework.security.core.userdetails.User(lowercaseUsername,
            userFromDatabase.getPassword(), grantedAuthorities);
	}

}
