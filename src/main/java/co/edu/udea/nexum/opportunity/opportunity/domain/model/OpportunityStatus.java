package co.edu.udea.nexum.opportunity.opportunity.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum representing the possible statuses of an opportunity.
 */
public enum OpportunityStatus {
    DRAFT("Draft"),
    ACTIVE("Active"),
    CLOSED("Closed"),
    EXPIRED("Expired"),
    ON_HOLD("On Hold"),
    CANCELLED("Cancelled");
    
    private final String displayName;
    
    OpportunityStatus(String displayName) {
        this.displayName = displayName;
    }
    
    @JsonValue
    public String getDisplayName() {
        return displayName;
    }
    
    @JsonCreator
    public static OpportunityStatus fromString(String value) {
        if (value == null) {
            return null;
        }
        
        // First try to match by enum constant name (DRAFT, ACTIVE, etc.)
        try {
            return OpportunityStatus.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            // If that fails, try to match by display name (Draft, Active, etc.)
            for (OpportunityStatus status : OpportunityStatus.values()) {
                if (status.displayName.equalsIgnoreCase(value)) {
                    return status;
                }
            }
            throw new IllegalArgumentException("Invalid OpportunityStatus: " + value);
        }
    }
    
    @Override
    public String toString() {
        return displayName;
    }
}
