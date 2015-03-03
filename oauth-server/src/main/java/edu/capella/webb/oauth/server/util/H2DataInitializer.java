package edu.capella.webb.oauth.server.util;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import edu.capella.webb.oauth.server.domain.Authority;
import edu.capella.webb.oauth.server.domain.oauth.OAuthClientDetails;
import edu.capella.webb.oauth.server.domain.oauth.OAuthScope;
import edu.capella.webb.oauth.server.domain.user.User;
import edu.capella.webb.oauth.server.repository.AuthorityRepository;
import edu.capella.webb.oauth.server.repository.UserRepository;
import edu.capella.webb.oauth.server.repository.oauth.OAuthClientDetailsRepository;
import edu.capella.webb.oauth.server.repository.oauth.OAuthScopeRepository;

@Component
@Profile({Constant.SPRING_PROFILE_DEVELOPMENT, Constant.SPRING_PROFILE_STAND_ALONE})
public class H2DataInitializer implements InitializingBean {
		
	private final Logger log = LoggerFactory.getLogger(H2DataInitializer.class);	
	
	@Autowired
	AuthorityRepository authorityRepository;
	
	@Autowired
	OAuthScopeRepository oauthScopeRepository;	
	
	@Autowired
	UserRepository userRepository;	
	
	@Autowired
	OAuthClientDetailsRepository oauthClientDetailsRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public void afterPropertiesSet() throws Exception {

		List<Authority> authorities = Arrays.asList(new Authority[] {
				new Authority("ROLE_USER"),
				new Authority("ROLE_ADMIN")				
		});
		
		authorities = authorityRepository.save(authorities);			
		
		List<User> users = Arrays.asList(new User[] {
				new User("anonymoususer", passwordEncoder.encode("anon_password"), "Anonymous", "User", "anon@anon.com"),
				new User("eanderson", passwordEncoder.encode("password"), "Ethan", "Anderson", "ethanaa@gmail.com"),
				new User("test", passwordEncoder.encode("password"), "Test", "Test", "test@test.com")
		});
		
		users = userRepository.save(users);
		
		List<OAuthScope> oauthScopes = Arrays.asList(new OAuthScope[] {
			new OAuthScope("super"),
			new OAuthScope("oauth-server.read"),
			new OAuthScope("oauth-server.write"),
			new OAuthScope("oauth-server.delete"),			
			new OAuthScope("peoplesoft-server.read"),
			new OAuthScope("peoplesoft-server.write"),
			new OAuthScope("peoplesoft-server.delete")
		});
		
		oauthScopes = oauthScopeRepository.save(oauthScopes);
		
		List<OAuthClientDetails> oauthClientDetails = Arrays.asList(new OAuthClientDetails[] {
				new OAuthClientDetails("readiness-center", "rcsecret", "password", "", 1800, 0, "", "true"),
				new OAuthClientDetails("iguide", "igsecret", "password", "", 1800, 0, "", "true")
		});
		
		oauthClientDetails = oauthClientDetailsRepository.save(oauthClientDetails);
		
		for (OAuthClientDetails details : oauthClientDetails) {
			
			details.addAuthority(authorities.get(0));
			
			switch(details.getClientId()) {
			
			case "readiness-center":
				details.addScope(oauthScopes.get(1));
				details.addScope(oauthScopes.get(2));
				details.addScope(oauthScopes.get(3));
				
				break;
				
			case "iguide":
				details.addScope(oauthScopes.get(4));
				details.addScope(oauthScopes.get(5));

				break;
				
			default:
				log.warn("unhandled client id encountered");
			}
		}
		
		oauthClientDetails = oauthClientDetailsRepository.save(oauthClientDetails);		
	}

}
