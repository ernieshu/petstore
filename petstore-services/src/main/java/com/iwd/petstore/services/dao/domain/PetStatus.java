package com.iwd.petstore.services.dao.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import antlr.StringUtils;

public enum PetStatus {

	AVAILABLE, PENDING, SOLD;

	private static Map<String, PetStatus> namesMap = new HashMap<String, PetStatus>(3);
	
    static {
        namesMap.put("AVAILABLE", AVAILABLE);
        namesMap.put("PENDING", PENDING);
        namesMap.put("SOLD", SOLD);
    }	
	
    @JsonCreator
    public static PetStatus forValue(String value) {
        return namesMap.get(value.toUpperCase());
    }
    
    @JsonValue
    public String toValue() {
        for (Entry<String, PetStatus> entry : namesMap.entrySet()) {
            if (entry.getValue() == this)
                return entry.getKey();
        }

        return null; // or fail
    }	
	
}
