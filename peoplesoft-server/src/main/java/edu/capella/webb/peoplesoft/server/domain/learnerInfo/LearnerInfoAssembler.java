package edu.capella.webb.peoplesoft.server.domain.learnerInfo;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import edu.capella.webb.peoplesoft.server.rest.LearnerInfoEndpoint;

@Component
public class LearnerInfoAssembler extends ResourceAssemblerSupport<PSLearnerInfoV, LearnerInfoResource> {

	public LearnerInfoAssembler() {
		super(LearnerInfoEndpoint.class, LearnerInfoResource.class);
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
		
		resource.add(linkTo(methodOn(LearnerInfoEndpoint.class).getLearnerInfoByEmployeeId(entity.getEmployeeId())).withSelfRel());
		
//		resource.add(linkTo(LearnerInfoEndpoint.class).slash(entity.getEmployeeId()).withSelfRel());
		
		return resource;
	}

}
