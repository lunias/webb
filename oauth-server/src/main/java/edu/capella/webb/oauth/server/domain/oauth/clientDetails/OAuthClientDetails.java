package edu.capella.webb.oauth.server.domain.oauth.clientDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.capella.webb.oauth.server.domain.Authority;
import edu.capella.webb.oauth.server.domain.oauth.OAuthResource;
import edu.capella.webb.oauth.server.domain.oauth.OAuthScope;
import edu.capella.webb.oauth.server.domain.user.User;

@Entity
@Table(name = "oauth_client_details")
public class OAuthClientDetails implements ClientDetails, Serializable {

	private static final long serialVersionUID = 4049425310865326805L;
	
    @NotNull
    @Size(min = 0, max = 256)
    @Id
    @Column(name = "client_id", length = 256)
	private String clientId;    
    
    @Column(name = "client_secret")
    private String clientSecret;    
    
    @Column(name = "authorized_grant_types")
    private String authorizedGrantTypes;
    
    @Column(name = "web_server_redirect_uri")
    private String webServerRedirectUri;    
    
    @Column(name = "access_token_validity")
    private Integer accessTokenValidity;
    
    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValidity;
    
    @Size(min = 0, max = 4096)
    @Column(name = "additional_information")
    private String additionalInformation;
    
    @Size(min = 0, max = 256)
    @Column(name = "autoapprove")
    private String autoApprove;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "oauth_client_resource",
            joinColumns = {@JoinColumn(name = "client_id", referencedColumnName = "client_id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id", referencedColumnName = "resource_id")})
    private Set<OAuthResource> resources = new HashSet<>();
        
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "oauth_client_scope",
            joinColumns = {@JoinColumn(name = "client_id", referencedColumnName = "client_id")},
            inverseJoinColumns = {@JoinColumn(name = "scope_name", referencedColumnName = "scope")})
    private Set<OAuthScope> scopes = new HashSet<>();       
    
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "oauth_client_authority",
            joinColumns = {@JoinColumn(name = "client_id", referencedColumnName = "client_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    private Set<Authority> authorities = new HashSet<>();
    
    @JsonIgnore
    @ManyToMany(mappedBy = "clientDetails")
    private Set<User> usersThatCanAccess;    
    
    public OAuthClientDetails() {
	}
    
    public OAuthClientDetails(ClientDetails clientDetails) {
    	
    	this.clientId = clientDetails.getClientId();
    	this.clientSecret = clientDetails.getClientSecret();
    	this.authorizedGrantTypes = StringUtils.collectionToCommaDelimitedString(clientDetails.getAuthorizedGrantTypes());
    	this.webServerRedirectUri = StringUtils.collectionToCommaDelimitedString(clientDetails.getRegisteredRedirectUri());
    	this.accessTokenValidity = clientDetails.getAccessTokenValiditySeconds();
    	this.refreshTokenValidity = clientDetails.getRefreshTokenValiditySeconds();
    	this.autoApprove = "TRUE";
    	
    	Set<String> resourceIds = clientDetails.getResourceIds();
    	for (String resourceId : resourceIds) {
    		this.resources.add(new OAuthResource(resourceId));
    	}
    	
    	Set<String> scopes = clientDetails.getScope();
    	for (String scope : scopes) {
    		this.scopes.add(new OAuthScope(scope));
    	}
    	
    	Collection<GrantedAuthority> authorities = clientDetails.getAuthorities();
    	for (GrantedAuthority authority : authorities) {
    		this.authorities.add(new Authority(authority));
    	}
    }       

	public OAuthClientDetails(String clientId, String clientSecret,
			String authorizedGrantTypes, String webServerRedirectUri,
			Integer accessTokenValidity, Integer refreshTokenValidity,
			String additionalInformation, String autoApprove) {

		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.authorizedGrantTypes = authorizedGrantTypes;
		this.webServerRedirectUri = webServerRedirectUri;
		this.accessTokenValidity = accessTokenValidity;
		this.refreshTokenValidity = refreshTokenValidity;
		this.additionalInformation = additionalInformation;
		this.autoApprove = autoApprove;
	}

	@Override
	public String getClientId() {
		return clientId;
	}

	@Override
	public boolean isSecretRequired() {
		return true;
	}

	@Override
	public String getClientSecret() {
		return clientSecret;
	}

	@Override
	public boolean isScoped() {
		return true;
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		return StringUtils.commaDelimitedListToSet(authorizedGrantTypes);
	}

	@Override
	public Set<String> getRegisteredRedirectUri() {
		return StringUtils.commaDelimitedListToSet(webServerRedirectUri);
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		return accessTokenValidity;
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		return refreshTokenValidity;
	}

	@Override
	public boolean isAutoApprove(String scope) {
		return Boolean.parseBoolean(autoApprove);
	}

	@SuppressWarnings("serial")
	@Override
	public Map<String, Object> getAdditionalInformation() {
		return new HashMap<String, Object>() {
			{
				put("info", additionalInformation);
			}
		};
	}
	
	@Override
	public Set<String> getResourceIds() {

		Set<String> resourceStrings = new HashSet<>();
		for (OAuthResource resource : resources) {
			resourceStrings.add(resource.getResourceId());
		}
		
		return resourceStrings;
	}

	@Override
	public Set<String> getScope() {
		
		Set<String> scopeStrings = new HashSet<>();
		for (OAuthScope scope : scopes) {
			scopeStrings.add(scope.getScope());
		}
		
		return scopeStrings;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {

		return new HashSet<GrantedAuthority>(authorities);	
	}		

	public Set<OAuthResource> getResources() {
		return resources;
	}

	public Set<OAuthScope> getScopes() {
		return scopes;
	}		

	public Set<User> getUsersThatCanAccess() {
		return usersThatCanAccess;
	}

	public void setUsersThatCanAccess(Set<User> usersThatCanAcess) {
		this.usersThatCanAccess = usersThatCanAcess;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public void setWebServerRedirectUri(String webServerRedirectUri) {
		this.webServerRedirectUri = webServerRedirectUri;
	}

	public void setAccessTokenValidity(Integer accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public void setRefreshTokenValidity(Integer refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public void setAutoApprove(String autoApprove) {
		this.autoApprove = autoApprove;
	}		
	
	public void setResources(Set<OAuthResource> resources) {
		this.resources = resources;
	}

	public void setScopes(Set<OAuthScope> scopes) {
		this.scopes = scopes;
	}
	
	public void addScope(OAuthScope scope) {
		this.scopes.add(scope);
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
	
	public void addAuthority(Authority authority) {
		this.authorities.add(authority);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((clientId == null) ? 0 : clientId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OAuthClientDetails other = (OAuthClientDetails) obj;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OAuthClientDetails [clientId=" + clientId + ", clientSecret="
				+ clientSecret + ", authorizedGrantTypes="
				+ authorizedGrantTypes + ", webServerRedirectUri="
				+ webServerRedirectUri + ", accessTokenValidity="
				+ accessTokenValidity + ", refreshTokenValidity="
				+ refreshTokenValidity + ", additionalInformation="
				+ additionalInformation + ", autoApprove=" + autoApprove + "]";
	}	
}
