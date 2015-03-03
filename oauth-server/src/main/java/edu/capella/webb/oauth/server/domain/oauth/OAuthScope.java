package edu.capella.webb.oauth.server.domain.oauth;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "oauth_scope")
public class OAuthScope implements Serializable {
	 
	private static final long serialVersionUID = 4229203303849785089L;
	
	@NotNull
    @Size(min = 0, max = 256)
    @Id
    @Column(name = "scope", length = 256)
	private String scope;
	
	public OAuthScope(String scope) {
		this.scope = scope;
	}
	
	public OAuthScope() {
	}	

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((scope == null) ? 0 : scope.hashCode());
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
		OAuthScope other = (OAuthScope) obj;
		if (scope == null) {
			if (other.scope != null)
				return false;
		} else if (!scope.equals(other.scope))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OAuthScope [scope=" + scope + "]";
	}	
}
