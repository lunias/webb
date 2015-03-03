package edu.capella.webb.oauth.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.capella.webb.oauth.server.domain.Authority;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
