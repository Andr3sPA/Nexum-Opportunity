package co.edu.udea.nexum.opportunity.opportunity.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Value object representing a salary range for an opportunity.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalaryRange {
    
    private BigDecimal min;
    private BigDecimal max;
    private String currency;
    
    /**
     * Validates that the salary range is consistent (min <= max).
     */
    public void validate() {
        if (min != null && max != null && min.compareTo(max) > 0) {
            throw new IllegalArgumentException("Minimum salary cannot be greater than maximum salary");
        }
        if (min != null && min.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Minimum salary cannot be negative");
        }
        if (max != null && max.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Maximum salary cannot be negative");
        }
    }
    
    /**
     * Checks if the salary range is empty (both min and max are null).
     */
    public boolean isEmpty() {
        return min == null && max == null;
    }
}
