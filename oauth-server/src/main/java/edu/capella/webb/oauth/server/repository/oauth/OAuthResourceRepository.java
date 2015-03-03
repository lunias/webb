package edu.capella.webb.oauth.server.repository.oauth;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.capella.webb.oauth.server.domain.oauth.OAuthResource;

public interface OAuthResourceRepository extends JpaRepository<OAuthResource, String> {

}
