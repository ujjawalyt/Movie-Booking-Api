package com.movie.api.Enum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ShowTimeConverter implements AttributeConverter<ShowTime, String> {

	 @Override
	    public String convertToDatabaseColumn(ShowTime showTime) {
	        return showTime.getDisplayName(); // Store enum display name as string
	    }

	    @Override
	    public ShowTime convertToEntityAttribute(String dbData) {
	        for (ShowTime showTime : ShowTime.values()) {
	            if (showTime.getDisplayName().equals(dbData)) {
	                return showTime; // Retrieve enum based on display name
	            }
	        }
	        throw new IllegalArgumentException("Unknown database value: " + dbData);
	    }
}