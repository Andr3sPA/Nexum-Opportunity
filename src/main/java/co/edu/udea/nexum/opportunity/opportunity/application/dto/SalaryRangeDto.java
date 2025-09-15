package co.edu.udea.nexum.opportunity.opportunity.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import java.math.BigDecimal;

/**
 * Data Transfer Object for SalaryRange.
 * Used for API requests and responses.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalaryRangeDto {
    
    @DecimalMin(value = "0.0", inclusive = false, message = "Minimum salary must be positive")
    @Digits(integer = 8, fraction = 2, message = "Salary must have at most 8 integer digits and 2 decimal places")
    private BigDecimal min;
    
    @DecimalMin(value = "0.0", inclusive = false, message = "Maximum salary must be positive")
    @Digits(integer = 8, fraction = 2, message = "Salary must have at most 8 integer digits and 2 decimal places")
    private BigDecimal max;
    
    private String currency; // ISO currency code (USD, EUR, etc.)
    
}
