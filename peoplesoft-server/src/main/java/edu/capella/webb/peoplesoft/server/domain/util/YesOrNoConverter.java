package edu.capella.webb.peoplesoft.server.domain.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class YesOrNoConverter implements AttributeConverter<Boolean, String> {

	@Override
	public String convertToDatabaseColumn(Boolean attribute) {
		
		if (attribute == null) return "N";
		
		return attribute ? "Y" : "N";
	}

	@Override
	public Boolean convertToEntityAttribute(String dbData) {

		return "Y".equalsIgnoreCase(dbData);
	}

}
