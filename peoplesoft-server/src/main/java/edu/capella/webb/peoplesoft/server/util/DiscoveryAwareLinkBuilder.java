package edu.capella.webb.peoplesoft.server.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import edu.capella.webb.peoplesoft.server.domain.learnerInfo.PSLearnerInfoV;
import edu.capella.webb.peoplesoft.server.rest.LearnerInfoEndpoint;

@Component
public class DiscoveryAwareLinkBuilder {

	private final DiscoveryClient discoveryClient;
	
	// request mappings
	private static final RequestMapping LEARNER_INFO_ENDPOINT 
		= LearnerInfoEndpoint.class.getAnnotation(RequestMapping.class);
	
	@Autowired
	public DiscoveryAwareLinkBuilder(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}
	
	@HystrixCommand(fallbackMethod = "defaultLearnerInfoSelfLink")
	public Link buildLearnerInfoSelfLink(PSLearnerInfoV learnerInfo) {						
		
		InstanceInfo instance = discoveryClient.getNextServerFromEureka("peoplesoft-server", false);
		
		String requestUrl = instance.getHomePageUrl() + LEARNER_INFO_ENDPOINT.value()[0] + "/{id}";
		
		String url = UriComponentsBuilder.fromHttpUrl(requestUrl)
				.buildAndExpand(learnerInfo.getEmployeeId())
				.toUriString();		
		
		return new Link(url, "self");
	}
	
	@SuppressWarnings("unused")
	private Link defaultLearnerInfoSelfLink(PSLearnerInfoV learnerInfo) {
		return null;
	}
}
