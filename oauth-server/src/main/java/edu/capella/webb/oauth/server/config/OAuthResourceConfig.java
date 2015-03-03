package edu.capella.webb.oauth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import edu.capella.webb.oauth.server.security.AjaxLogoutSuccessHandler;
import edu.capella.webb.oauth.server.security.AuthorityConstants;
import edu.capella.webb.oauth.server.security.Http401UnauthorizedEntryPoint;

@Configuration
@EnableResourceServer
public class OAuthResourceConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private Http401UnauthorizedEntryPoint authenticationEntryPoint;

    @Autowired
    private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;	
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {		
		
		resources.resourceId("oauth-server");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {

		http
			.exceptionHandling()
			.authenticationEntryPoint(authenticationEntryPoint)
		.and()
        	.logout()
        	.logoutUrl("/logout")
        	.logoutSuccessHandler(ajaxLogoutSuccessHandler)
        .and()
        	.csrf()
        		.requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize")).disable()
        	.headers()
        		.frameOptions().disable()
        	.sessionManagement()
        		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        	.authorizeRequests()
        	.antMatchers(HttpMethod.POST, "/api/oauth/token").permitAll()                
        	.antMatchers("/api/authenticate").permitAll()
        	.antMatchers("/api/register").permitAll()
        	.antMatchers("/api/logs/**").hasAnyAuthority(AuthorityConstants.ADMIN)
        	.antMatchers("/api/**").authenticated()
        	.antMatchers("/protected/**").authenticated()
        	.antMatchers("/metrics/**").hasAuthority(AuthorityConstants.ADMIN)
        	.antMatchers("/health/**").hasAuthority(AuthorityConstants.ADMIN)
        	.antMatchers("/trace/**").hasAuthority(AuthorityConstants.ADMIN)
        	.antMatchers("/dump/**").hasAuthority(AuthorityConstants.ADMIN)
        	.antMatchers("/shutdown/**").hasAuthority(AuthorityConstants.ADMIN)
        	.antMatchers("/beans/**").hasAuthority(AuthorityConstants.ADMIN)
        	.antMatchers("/configprops/**").hasAuthority(AuthorityConstants.ADMIN)
        	.antMatchers("/info/**").hasAuthority(AuthorityConstants.ADMIN)
        	.antMatchers("/autoconfig/**").hasAuthority(AuthorityConstants.ADMIN)
        	.antMatchers("/env/**").hasAuthority(AuthorityConstants.ADMIN)
        	.antMatchers("/trace/**").hasAuthority(AuthorityConstants.ADMIN)
        	.antMatchers("/api-docs/**").hasAuthority(AuthorityConstants.ADMIN);		
	}
	
}
