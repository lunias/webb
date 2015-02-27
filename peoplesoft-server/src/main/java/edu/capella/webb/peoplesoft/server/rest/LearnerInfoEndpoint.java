package edu.capella.webb.peoplesoft.server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import edu.capella.webb.peoplesoft.server.domain.learnerInfo.LearnerInfoAssembler;
import edu.capella.webb.peoplesoft.server.domain.learnerInfo.LearnerInfoResource;
import edu.capella.webb.peoplesoft.server.domain.learnerInfo.PSLearnerInfoV;
import edu.capella.webb.peoplesoft.server.repository.PSLearnerInfoVRepository;

@RestController
@RequestMapping("/api/learnerInfo")
public class LearnerInfoEndpoint {

	private PSLearnerInfoVRepository psLearnerInfoVRepository;
	
	private LearnerInfoAssembler learnerInfoAssembler;
	
	@Autowired
	public LearnerInfoEndpoint(PSLearnerInfoVRepository psLearnerInfoVRepository, LearnerInfoAssembler learnerInfoAssembler) {
		
		this.psLearnerInfoVRepository = psLearnerInfoVRepository;
		this.learnerInfoAssembler = learnerInfoAssembler;
	}	
	
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
	})	
	@RequestMapping(method = RequestMethod.GET, value = "")
	public ResponseEntity<PagedResources<LearnerInfoResource>> getLearnerInfo(Pageable pageable, PagedResourcesAssembler<PSLearnerInfoV> assembler) {
		
		Page<PSLearnerInfoV> page = psLearnerInfoVRepository.findAll(pageable);	
		
		return new ResponseEntity<>(assembler.toResource(page, learnerInfoAssembler), HttpStatus.OK);
	}
	
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
	})	
	@RequestMapping(method = RequestMethod.GET, value = "/{employeeId}")
	public ResponseEntity<LearnerInfoResource> getLearnerInfoByEmployeeId(@PathVariable String employeeId) {
		
		PSLearnerInfoV learnerInfo = psLearnerInfoVRepository.findFirstByEmployeeId(employeeId);
		if (learnerInfo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(learnerInfoAssembler.toResource(learnerInfo), HttpStatus.OK);
	}
	
}
