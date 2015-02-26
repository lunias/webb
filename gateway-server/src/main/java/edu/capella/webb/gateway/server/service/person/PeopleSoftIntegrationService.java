package edu.capella.webb.gateway.server.service.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import rx.Observable;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.command.ObservableResult;

import edu.capella.webb.gateway.server.service.person.resource.Person;

@Service
public class PeopleSoftIntegrationService {

	private RestTemplate restTemplate;
	
	@Autowired
	public PeopleSoftIntegrationService(RestTemplate restTemplate) {
		
		this.restTemplate = restTemplate;
	}
	
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
	}, fallbackMethod = "stubPerson")	
	public Observable<Person> getPerson(final String personId) {
		return new ObservableResult<Person>() {

			@Override
			public Person invoke() {

				return restTemplate.getForObject("http://peoplesoft-server/learnerInfo/{personId}",
						Person.class, personId);
			}			
		};
	}
	
	@SuppressWarnings("unused")
	private Person stubPerson(final String personId) {
		
		Person person = new Person();
		person.setEmployeeId("00000000");
		person.setDescription("the system is down");
		
		return person;
	}
	
}
