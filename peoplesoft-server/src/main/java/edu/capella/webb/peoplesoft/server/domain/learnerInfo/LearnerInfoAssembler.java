package edu.capella.webb.peoplesoft.server.domain.learnerInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import edu.capella.webb.peoplesoft.server.rest.LearnerInfoEndpoint;
import edu.capella.webb.peoplesoft.server.util.DiscoveryAwareLinkBuilder;

@Component
public class LearnerInfoAssembler extends ResourceAssemblerSupport<PSLearnerInfoV, LearnerInfoResource> {

	private DiscoveryAwareLinkBuilder discoveryAwareLinkBuilder;
	
	@Autowired
	public LearnerInfoAssembler(DiscoveryAwareLinkBuilder discoveryAwareLinkBuilder) {
		super(LearnerInfoEndpoint.class, LearnerInfoResource.class);
		
		this.discoveryAwareLinkBuilder = discoveryAwareLinkBuilder;
	}

	@Override
	public LearnerInfoResource toResource(PSLearnerInfoV entity) {

		LearnerInfoResource resource = instantiateResource(entity);
		
		resource.setEmployeeId(entity.getEmployeeId());
		resource.setSubject(entity.getSubject());
		resource.setCatalogNumber(entity.getCatalogNumber());
		resource.setCourseAttribute(entity.getCourseAttribute());
		resource.setCourseAttributeValue(entity.getCourseAttributeValue());
		resource.setTermBeginDate(entity.getTermBeginDate());
		resource.setTermEndDate(entity.getTermEndDate());
		resource.setSessionBeginDate(entity.getSessionBeginDate());
		resource.setSessionEndDate(entity.getSessionEndDate());
		resource.setStartDate(entity.getStartDate());
		resource.setEndDate(entity.getEndDate());
		resource.setCensusDate(entity.getCensusDate());
		resource.setDescription(entity.getDescription());
		resource.setIncludeInGpa(entity.getIncludeInGpa());
		resource.setEarningCredit(entity.getEarningCredit());
		resource.setUnitsEarned(entity.getUnitsEarned());
		resource.setValidAttempt(entity.getValidAttempt());
		resource.setEnrollAddDate(entity.getEnrollAddDate());
		resource.setEnrollDropDate(entity.getEnrollDropDate());
		resource.setOprId(entity.getOprId());		
			
		// resource links
		
		Link selfLink = discoveryAwareLinkBuilder.buildLearnerInfoSelfLink(entity);
		if (selfLink != null)
			resource.add(selfLink);		
		
		return resource;
	}

}
