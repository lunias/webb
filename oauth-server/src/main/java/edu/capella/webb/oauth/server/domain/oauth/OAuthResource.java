package edu.capella.webb.oauth.server.domain.oauth;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "oauth_resource")
public class OAuthResource implements Serializable {

	private static final long serialVersionUID = 3075972792760171330L;

	@NotNull
    @Size(min = 0, max = 256)
    @Id
    @Column(name = "resource_id", length = 256)
	private String resourceId;
        
    @Column(name = "uri")
    private String uri;
    
    public OAuthResource(String resourceId) {
    	this.resourceId = resourceId;
    }
    
    public OAuthResource() {
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((resourceId == null) ? 0 : resourceId.hashCode());
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
		OAuthResource other = (OAuthResource) obj;
		if (resourceId == null) {
			if (other.resourceId != null)
				return false;
		} else if (!resourceId.equals(other.resourceId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OAuthResource [resourceId=" + resourceId + ", uri=" + uri + "]";
	}	    
}
