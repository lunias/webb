package edu.capella.webb.peoplesoft.server.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import edu.capella.webb.peoplesoft.server.domain.PSLearnerInfoV;
import edu.capella.webb.peoplesoft.server.repository.PSLearnerInfoVRepository;

@RestController
public class PersonEndpoint {

	private PSLearnerInfoVRepository psLearnerInfoVRepository;
	
	@Autowired
	public PersonEndpoint(PSLearnerInfoVRepository psLearnerInfoVRepository) {
		
		this.psLearnerInfoVRepository = psLearnerInfoVRepository;
	}	
	
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000")
	})	
	@RequestMapping(method = RequestMethod.GET, value = "/learnerInfo")
	public ResponseEntity<List<PSLearnerInfoV>> getLearnerInfo() {
		
		List<PSLearnerInfoV> learnerInfo = psLearnerInfoVRepository.findAll();
		
		return new ResponseEntity<>(learnerInfo, HttpStatus.OK);
	}
	
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
	})	
	@RequestMapping(method = RequestMethod.GET, value = "/learnerInfo/{employeeId}")
	public ResponseEntity<PSLearnerInfoV> getLearnerInfoByEmployeeId(@PathVariable String employeeId) {
		
		PSLearnerInfoV learnerInfo = psLearnerInfoVRepository.findFirstByEmployeeId(employeeId);
		
		return new ResponseEntity<>(learnerInfo, HttpStatus.OK);
	}
	
}
