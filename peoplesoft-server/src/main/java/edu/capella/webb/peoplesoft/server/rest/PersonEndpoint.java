package edu.capella.webb.peoplesoft.server.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.capella.webb.peoplesoft.server.domain.Person;
import edu.capella.webb.peoplesoft.server.repository.PersonRepository;

@RestController
public class PersonEndpoint {

	private PersonRepository personRepository;
	
	@Autowired
	public PersonEndpoint(PersonRepository personRepository) {
		
		this.personRepository = personRepository;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/people")
	public ResponseEntity<List<Person>> getPeople() {
		
		List<Person> people = personRepository.findAll();
		
		return new ResponseEntity<>(people, HttpStatus.OK);
	}
	
}
