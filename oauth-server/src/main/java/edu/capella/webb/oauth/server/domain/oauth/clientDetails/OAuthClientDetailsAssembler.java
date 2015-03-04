package edu.capella.webb.oauth.server.domain.oauth.clientDetails;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import edu.capella.webb.oauth.server.domain.Authority;
import edu.capella.webb.oauth.server.domain.oauth.OAuthResource;
import edu.capella.webb.oauth.server.domain.oauth.OAuthScope;
import edu.capella.webb.oauth.server.rest.OAuthEndpoint;

@Component
public class OAuthClientDetailsAssembler extends ResourceAssemblerSupport<OAuthClientDetails, OAuthClientDetailsResource> {

	public OAuthClientDetailsAssembler() {
		super(OAuthEndpoint.class, OAuthClientDetailsResource.class);
	}

	@Override
	public OAuthClientDetailsResource toResource(OAuthClientDetails entity) {
	
		OAuthClientDetailsResource resource = instantiateResource(entity);
		
		resource.setClientId(entity.getClientId());
		resource.setAuthorizedGrantTypes(entity.getAuthorizedGrantTypes());
		resource.setRedirectUrls(entity.getRegisteredRedirectUri());
		resource.setAccessTokenLifeSeconds(entity.getAccessTokenValiditySeconds());
		resource.setRefreshTokenLifeSeconds(entity.getRefreshTokenValiditySeconds());
		resource.setAdditionalInfo((String) entity.getAdditionalInformation().get("info"));
		resource.setAutoApprove(entity.isAutoApprove(""));
		
		Set<OAuthResource> resources = new HashSet<>();
		for (String resourceId : entity.getResourceIds()) {
			resources.add(new OAuthResource(resourceId));
		}
		
		resource.setResources(resources);
		
		Set<OAuthScope> scopes = new HashSet<>();
		for (String scope : entity.getScope()) {
			scopes.add(new OAuthScope(scope));
		}
		
		resource.setScopes(scopes);
		
		Set<Authority> authorities = new HashSet<>();
		for (GrantedAuthority grantedAuthority : entity.getAuthorities()) {
			authorities.add(new Authority(grantedAuthority));
		}
		
		resource.setAuthorities(authorities);
		
		resource.add(linkTo(OAuthEndpoint.class).slash("clients").slash(entity.getClientId()).withSelfRel());
		
		return resource;
	}		
}
