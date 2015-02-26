package edu.capella.webb.gateway.server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import rx.Observable;
import rx.Observer;
import edu.capella.webb.gateway.server.resource.PersonDetails;
import edu.capella.webb.gateway.server.service.person.PeopleSoftIntegrationService;

@RestController
public class PersonEndpoint {

	PeopleSoftIntegrationService peopleSoftIntegrationService;
	
	@Autowired
	public PersonEndpoint(PeopleSoftIntegrationService peopleSoftIntegrationService) {
		
		this.peopleSoftIntegrationService = peopleSoftIntegrationService;		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/people/{id}")
	public DeferredResult<ResponseEntity<PersonDetails>> getPersonById(@PathVariable String id) {
		
		Observable<ResponseEntity<PersonDetails>> details = peopleSoftIntegrationService.getPerson(id)
				.map((person) -> {
					
					PersonDetails personDetails = new PersonDetails();
					personDetails.setEmployeeId(person.getEmployeeId());
					personDetails.setDescription(person.getDescription());
					personDetails.setEmail("placeholder-email");
					
					return new ResponseEntity<>(personDetails, HttpStatus.OK);					
				});
		
		return toDeferredResult(details);
	}
	
    private DeferredResult<ResponseEntity<PersonDetails>> toDeferredResult(Observable<ResponseEntity<PersonDetails>> details) {
    	
        DeferredResult<ResponseEntity<PersonDetails>> result = new DeferredResult<>();
        
        details.subscribe(new Observer<ResponseEntity<PersonDetails>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onNext(ResponseEntity<PersonDetails> personDetails) {
                result.setResult(personDetails);
            }
        });
        
        return result;
    }	
	
}
