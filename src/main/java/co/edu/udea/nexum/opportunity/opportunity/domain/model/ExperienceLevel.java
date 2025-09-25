package co.edu.udea.nexum.opportunity.opportunity.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExperienceLevel {
    NO_EXPERIENCE("No experience required"),
    LESS_THAN_1_YEAR("Less than 1 year"),
    ONE_TO_TWO_YEARS("1-2 years"),
    TWO_TO_FOUR_YEARS("2-4 years"),
    FOUR_TO_SIX_YEARS("4-6 years"),
    MORE_THAN_SIX_YEARS("More than 6 years"),
    NOT_SPECIFIED("Not specified");

    private final String displayName;

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }

    @JsonCreator
    public static ExperienceLevel fromString(String value) {
        if (value == null) {
            return null;
        }
        
        for (ExperienceLevel level : ExperienceLevel.values()) {
            if (level.displayName.equalsIgnoreCase(value) || level.name().equalsIgnoreCase(value)) {
                return level;
            }
        }
        
        throw new IllegalArgumentException("Unknown experience level: " + value);
    }

    @Override
    public String toString() {
        return displayName;
    }
}
