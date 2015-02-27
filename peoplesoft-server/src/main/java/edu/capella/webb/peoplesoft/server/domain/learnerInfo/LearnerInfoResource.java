package edu.capella.webb.peoplesoft.server.domain.learnerInfo;

import org.joda.time.DateTime;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import edu.capella.webb.peoplesoft.server.domain.util.CustomDateTimeSerializer;

public class LearnerInfoResource extends ResourceSupport {

	private String employeeId;
	private String subject;
	private String catalogNumber;
	private String courseAttribute;
	private String courseAttributeValue;
	private DateTime termBeginDate;
	private DateTime termEndDate;
	private DateTime sessionBeginDate;
	private DateTime sessionEndDate;
	private DateTime startDate;
	private DateTime endDate;
	private DateTime censusDate;
	private String description;
	private Boolean includeInGpa;
	private Boolean earningCredit;
	private Integer unitsEarned;
	private Boolean validAttempt;
	private DateTime enrollAddDate;
	private DateTime enrollDropDate;
	private String oprId;
	
	public LearnerInfoResource() {
		
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCatalogNumber() {
		return catalogNumber;
	}

	public void setCatalogNumber(String catalogNumber) {
		this.catalogNumber = catalogNumber;
	}

	public String getCourseAttribute() {
		return courseAttribute;
	}

	public void setCourseAttribute(String courseAttribute) {
		this.courseAttribute = courseAttribute;
	}

	public String getCourseAttributeValue() {
		return courseAttributeValue;
	}

	public void setCourseAttributeValue(String courseAttributeValue) {
		this.courseAttributeValue = courseAttributeValue;
	}

	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public DateTime getTermBeginDate() {
		return termBeginDate;
	}

	public void setTermBeginDate(DateTime termBeginDate) {
		this.termBeginDate = termBeginDate;
	}

	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public DateTime getTermEndDate() {
		return termEndDate;
	}

	public void setTermEndDate(DateTime termEndDate) {
		this.termEndDate = termEndDate;
	}

	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public DateTime getSessionBeginDate() {
		return sessionBeginDate;
	}

	public void setSessionBeginDate(DateTime sessionBeginDate) {
		this.sessionBeginDate = sessionBeginDate;
	}

	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public DateTime getSessionEndDate() {
		return sessionEndDate;
	}

	public void setSessionEndDate(DateTime sessionEndDate) {
		this.sessionEndDate = sessionEndDate;
	}

	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}
	
	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public DateTime getCensusDate() {
		return censusDate;
	}

	public void setCensusDate(DateTime censusDate) {
		this.censusDate = censusDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIncludeInGpa() {
		return includeInGpa;
	}

	public void setIncludeInGpa(Boolean includeInGpa) {
		this.includeInGpa = includeInGpa;
	}

	public Boolean getEarningCredit() {
		return earningCredit;
	}

	public void setEarningCredit(Boolean earningCredit) {
		this.earningCredit = earningCredit;
	}

	public Integer getUnitsEarned() {
		return unitsEarned;
	}

	public void setUnitsEarned(Integer unitsEarned) {
		this.unitsEarned = unitsEarned;
	}

	public Boolean getValidAttempt() {
		return validAttempt;
	}

	public void setValidAttempt(Boolean validAttempt) {
		this.validAttempt = validAttempt;
	}

	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public DateTime getEnrollAddDate() {
		return enrollAddDate;
	}

	public void setEnrollAddDate(DateTime enrollAddDate) {
		this.enrollAddDate = enrollAddDate;
	}

	@JsonSerialize(using = CustomDateTimeSerializer.class)
	public DateTime getEnrollDropDate() {
		return enrollDropDate;
	}

	public void setEnrollDropDate(DateTime enrollDropDate) {
		this.enrollDropDate = enrollDropDate;
	}

	public String getOprId() {
		return oprId;
	}

	public void setOprId(String oprId) {
		this.oprId = oprId;
	}			
}
