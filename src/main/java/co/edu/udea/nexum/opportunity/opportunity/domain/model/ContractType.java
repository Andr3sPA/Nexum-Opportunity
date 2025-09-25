package co.edu.udea.nexum.opportunity.opportunity.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representing the possible contract types for an opportunity.
 */
public enum ContractType {
    FULL_TIME("Full Time"),
    PART_TIME("Part Time"),
    CONTRACT("Contract"),
    TEMPORARY("Temporary"),
    INTERNSHIP("Internship"),
    FREELANCE("Freelance");
    
    private final String displayName;
    
    ContractType(String displayName) {
        this.displayName = displayName;
    }
    
    @JsonValue
    public String getDisplayName() {
        return displayName;
    }
    
    @JsonCreator
    public static ContractType fromString(String value) {
        if (value == null) {
            return null;
        }
        
        for (ContractType contractType : ContractType.values()) {
            if (contractType.getDisplayName().equalsIgnoreCase(value) || 
                contractType.name().equalsIgnoreCase(value)) {
                return contractType;
            }
        }
        
        throw new IllegalArgumentException("Unknown contract type: " + value);
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
