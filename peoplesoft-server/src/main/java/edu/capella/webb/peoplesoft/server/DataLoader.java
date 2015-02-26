package edu.capella.webb.peoplesoft.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import edu.capella.webb.peoplesoft.server.domain.Person;
import edu.capella.webb.peoplesoft.server.repository.PersonRepository;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	PersonRepository personRepository;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		personRepository.save(new Person("Ethan", "Anderson"));
		
	}

}
