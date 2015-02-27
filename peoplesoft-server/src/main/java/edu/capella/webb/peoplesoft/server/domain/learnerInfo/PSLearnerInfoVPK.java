package edu.capella.webb.peoplesoft.server.domain.learnerInfo;

import java.io.Serializable;

public class PSLearnerInfoVPK implements Serializable {

	private static final long serialVersionUID = 984939069174322578L;

	String employeeId;
	String catalogNumber;
	String courseAttribute;
	String courseAttributeValue;	

	public PSLearnerInfoVPK() {

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((catalogNumber == null) ? 0 : catalogNumber.hashCode());
		result = prime * result
				+ ((courseAttribute == null) ? 0 : courseAttribute.hashCode());
		result = prime
				* result
				+ ((courseAttributeValue == null) ? 0 : courseAttributeValue
						.hashCode());
		result = prime * result
				+ ((employeeId == null) ? 0 : employeeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PSLearnerInfoVPK other = (PSLearnerInfoVPK) obj;
		if (catalogNumber == null) {
			if (other.catalogNumber != null)
				return false;
		} else if (!catalogNumber.equals(other.catalogNumber))
			return false;
		if (courseAttribute == null) {
			if (other.courseAttribute != null)
				return false;
		} else if (!courseAttribute.equals(other.courseAttribute))
			return false;
		if (courseAttributeValue == null) {
			if (other.courseAttributeValue != null)
				return false;
		} else if (!courseAttributeValue.equals(other.courseAttributeValue))
			return false;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		return true;
	}		
}
