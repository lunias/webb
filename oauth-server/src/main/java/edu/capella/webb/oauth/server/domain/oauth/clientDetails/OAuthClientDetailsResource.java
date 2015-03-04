package edu.capella.webb.oauth.server.domain.oauth.clientDetails;

import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.hateoas.ResourceSupport;

import edu.capella.webb.oauth.server.domain.Authority;
import edu.capella.webb.oauth.server.domain.oauth.OAuthResource;
import edu.capella.webb.oauth.server.domain.oauth.OAuthScope;

public class OAuthClientDetailsResource extends ResourceSupport {

    @NotNull
    @Size(min = 0, max = 256)
	private String clientId;
	
	private Set<String> authorizedGrantTypes;
	
	private Set<String> redirectUrls;
	
	private Integer accessTokenLifeSeconds;
	
	private Integer refreshTokenLifeSeconds;
	
    @Size(min = 0, max = 4096)
	private String additionalInfo;
	
	private Boolean autoApprove;
	
	private Set<OAuthResource> resources;
	
	private Set<OAuthScope> scopes;
	
	private Set<Authority> authorities;
	
	public OAuthClientDetailsResource() {
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Set<String> getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(Set<String> authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public Set<String> getRedirectUrls() {
		return redirectUrls;
	}

	public void setRedirectUrls(Set<String> redirectUrls) {
		this.redirectUrls = redirectUrls;
	}

	public Integer getAccessTokenLifeSeconds() {
		return accessTokenLifeSeconds;
	}

	public void setAccessTokenLifeSeconds(Integer accessTokenLifeSeconds) {
		this.accessTokenLifeSeconds = accessTokenLifeSeconds;
	}

	public Integer getRefreshTokenLifeSeconds() {
		return refreshTokenLifeSeconds;
	}

	public void setRefreshTokenLifeSeconds(Integer refreshTokenLifeSeconds) {
		this.refreshTokenLifeSeconds = refreshTokenLifeSeconds;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public Boolean getAutoApprove() {
		return autoApprove;
	}

	public void setAutoApprove(Boolean autoApprove) {
		this.autoApprove = autoApprove;
	}

	public Set<OAuthResource> getResources() {
		return resources;
	}

	public void setResources(Set<OAuthResource> resources) {
		this.resources = resources;
	}

	public Set<OAuthScope> getScopes() {
		return scopes;
	}

	public void setScopes(Set<OAuthScope> scopes) {
		this.scopes = scopes;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "OAuthClientDetailsResource [clientId=" + clientId
				+ ", authorizedGrantTypes=" + authorizedGrantTypes
				+ ", redirectUrls=" + redirectUrls
				+ ", accessTokenLifeSeconds=" + accessTokenLifeSeconds
				+ ", refreshTokenLifeSeconds=" + refreshTokenLifeSeconds
				+ ", additionalInfo=" + additionalInfo + ", autoApprove="
				+ autoApprove + ", resources=" + resources + ", scopes="
				+ scopes + ", authorities=" + authorities + "]";
	}			
}
