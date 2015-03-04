package edu.capella.webb.oauth.server.domain.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import edu.capella.webb.oauth.server.domain.Authority;
import edu.capella.webb.oauth.server.rest.UserEndpoint;

@Component
public class UserAssembler extends ResourceAssemblerSupport<User, UserResource> {

	private static final String CLIENTS = "clients";
	
	public UserAssembler() {
		super(UserEndpoint.class, UserResource.class);
	}
	
	@Override
	public UserResource toResource(User entity) {
		
		UserResource resource = instantiateResource(entity);
		
		BeanUtils.copyProperties(entity, resource, "password");
		
		for (Authority auth : entity.getAuthorities()) {
			resource.getRoles().add(auth.getName());
		}
		
		resource.add(linkTo(UserEndpoint.class).slash(entity.getUsername()).withSelfRel());
		resource.add(linkTo(UserEndpoint.class).slash(entity.getUsername()).slash(CLIENTS).withRel(CLIENTS));
		
		return resource;
	}
}
