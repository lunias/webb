package edu.capella.webb.peoplesoft.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.capella.webb.peoplesoft.server.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	List<Person> findByFirstName(String firstName);
	
}
