package co.edu.udea.nexum.opportunity.opportunity.application.dto.response;

import co.edu.udea.nexum.opportunity.common.application.dto.response.BaseResponse;
import co.edu.udea.nexum.opportunity.opportunity.application.dto.SalaryRangeDto;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.OpportunityStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data Transfer Object for Opportunity responses.
 * Used for API responses - excludes internal UUIDs for security.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpportunityResponseDto implements BaseResponse {
    
    private Long id;
    
    private String title;
    
    private String description;
    
    private String location;
    
    private OpportunityStatus status;
    
    private LocalDateTime creationDate;
    
    private LocalDateTime lastUpdate;
    
    // Note: createdBy UUID is intentionally excluded from API responses for security
    // graduateId is included since it's public information about who the opportunity targets
    
    private UUID graduateId; // Target graduate for this opportunity
    
    private SalaryRangeDto salaryRange;
}
