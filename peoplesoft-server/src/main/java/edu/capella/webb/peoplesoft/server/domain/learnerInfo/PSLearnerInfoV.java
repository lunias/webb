package edu.capella.webb.peoplesoft.server.domain.learnerInfo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import edu.capella.webb.peoplesoft.server.domain.util.YesOrNoConverter;

@Entity
@IdClass(PSLearnerInfoVPK.class)
@Table(name = "SYSADM.PS_CU_LRNR_INFO_V")
public class PSLearnerInfoV implements Serializable {
	
	private static final long serialVersionUID = -1173332404762584667L;			
	
	@Id
	@Column(name = "EMPLID")
	private String employeeId;
	
	@Column(name = "SUBJECT")
	private String subject;

	@Id
	@Column(name = "CATALOG_NBR")
	private String catalogNumber;
	
	@Id
	@Column(name = "CRSE_ATTR")	
	private String courseAttribute;
	
	@Id
	@Column(name = "CRSE_ATTR_VALUE")		
	private String courseAttributeValue;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")	
	@Column(name = "TERM_BEGIN_DT")
	private DateTime termBeginDate;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")	
	@Column(name = "TERM_END_DT")
	private DateTime termEndDate;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name = "SESS_BEGIN_DT")
	private DateTime sessionBeginDate;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name = "SESS_END_DT")
	private DateTime sessionEndDate;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name = "START_DT")
	private DateTime startDate;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name = "END_DT")
	private DateTime endDate;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name = "CENSUS_DT")	
	private DateTime censusDate;
	
	@Column(name = "DESCR")
	private String description;
	
	@Convert(converter = YesOrNoConverter.class)
	@Column(name = "INCLUDE_IN_GPA")
	private Boolean includeInGpa;
	
	@Convert(converter = YesOrNoConverter.class)	
	@Column(name = "EARN_CREDIT")
	private Boolean earningCredit;
	
	@Column(name = "UNT_EARNED")
	private Integer unitsEarned;
	
	@Convert(converter = YesOrNoConverter.class)	
	@Column(name = "VALID_ATTEMPT")
	private Boolean validAttempt;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name = "ENRL_ADD_DT")		
	private DateTime enrollAddDate;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Column(name = "ENRL_DROP_DT")		
	private DateTime enrollDropDate;
	
	@Column(name = "OPRID")
	private String oprId;
	
	public PSLearnerInfoV() {
		
	}		

	public PSLearnerInfoV(String employeeId, String subject,
			String catalogNumber, String courseAttribute,
			String courseAttributeValue, DateTime termBeginDate,
			DateTime termEndDate, DateTime sessionBeginDate,
			DateTime sessionEndDate, DateTime startDate, DateTime endDate,
			DateTime censusDate, String description, Boolean includeInGpa,
			Boolean earningCredit, Integer unitsEarned, Boolean validAttempt,
			DateTime enrollAddDate, DateTime enrollDropDate, String oprId) {
		
		super();
		this.employeeId = employeeId;
		this.subject = subject;
		this.catalogNumber = catalogNumber;
		this.courseAttribute = courseAttribute;
		this.courseAttributeValue = courseAttributeValue;
		this.termBeginDate = termBeginDate;
		this.termEndDate = termEndDate;
		this.sessionBeginDate = sessionBeginDate;
		this.sessionEndDate = sessionEndDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.censusDate = censusDate;
		this.description = description;
		this.includeInGpa = includeInGpa;
		this.earningCredit = earningCredit;
		this.unitsEarned = unitsEarned;
		this.validAttempt = validAttempt;
		this.enrollAddDate = enrollAddDate;
		this.enrollDropDate = enrollDropDate;
		this.oprId = oprId;
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

	public DateTime getTermBeginDate() {
		return termBeginDate;
	}

	public void setTermBeginDate(DateTime termBeginDate) {
		this.termBeginDate = termBeginDate;
	}

	public DateTime getTermEndDate() {
		return termEndDate;
	}

	public void setTermEndDate(DateTime termEndDate) {
		this.termEndDate = termEndDate;
	}

	public DateTime getSessionBeginDate() {
		return sessionBeginDate;
	}

	public void setSessionBeginDate(DateTime sessionBeginDate) {
		this.sessionBeginDate = sessionBeginDate;
	}

	public DateTime getSessionEndDate() {
		return sessionEndDate;
	}

	public void setSessionEndDate(DateTime sessionEndDate) {
		this.sessionEndDate = sessionEndDate;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

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

	public DateTime getEnrollAddDate() {
		return enrollAddDate;
	}

	public void setEnrollAddDate(DateTime enrollAddDate) {
		this.enrollAddDate = enrollAddDate;
	}

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
