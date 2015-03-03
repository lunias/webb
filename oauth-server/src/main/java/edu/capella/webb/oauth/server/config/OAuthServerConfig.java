package edu.capella.webb.oauth.server.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuthServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;	

	@Autowired	
	@Qualifier("customJpaClientDetailsService")
	private ClientDetailsService jpaClientDetailsService;
	
    @Bean
    public TokenStore tokenStore() {
    	
        return new JdbcTokenStore(dataSource);
    }
    
    @Bean
    public AuthorizationServerTokenServices tokenServices() {
    	
    	DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
    	
    	defaultTokenServices.setTokenStore(tokenStore());
    	defaultTokenServices.setAccessTokenValiditySeconds(1800);
    	defaultTokenServices.setRefreshTokenValiditySeconds(1800);
    	
    	return defaultTokenServices;
    }		

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		clients.withClientDetails(jpaClientDetailsService);		
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		endpoints
			.tokenServices(tokenServices())
			.tokenStore(tokenStore())
			.authenticationManager(authenticationManager);
	}

}
