package edu.capella.webb.oauth.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.capella.webb.oauth.server.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByLogin(String login);

    User findOneByEmail(String email);
}
