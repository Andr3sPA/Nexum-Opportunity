package co.edu.udea.nexum.opportunity.opportunity.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representing the possible work modalities for an opportunity.
 */
public enum WorkModality {
    REMOTE("Remote"),
    ON_SITE("On Site"),
    HYBRID("Hybrid");
    
    private final String displayName;
    
    WorkModality(String displayName) {
        this.displayName = displayName;
    }
    
    @JsonValue
    public String getDisplayName() {
        return displayName;
    }
    
    @JsonCreator
    public static WorkModality fromString(String value) {
        if (value == null) {
            return null;
        }
        
        for (WorkModality workModality : WorkModality.values()) {
            if (workModality.getDisplayName().equalsIgnoreCase(value) || 
                workModality.name().equalsIgnoreCase(value)) {
                return workModality;
            }
        }
        
        throw new IllegalArgumentException("Unknown work modality: " + value);
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
