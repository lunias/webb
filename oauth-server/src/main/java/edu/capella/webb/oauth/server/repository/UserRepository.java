package edu.capella.webb.oauth.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.capella.webb.oauth.server.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByUsername(String username);

    User findOneByEmail(String email);
}
