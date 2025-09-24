package co.edu.udea.nexum.opportunity.opportunity.application.dto.request;

import co.edu.udea.nexum.opportunity.common.application.dto.request.BaseRequest;
import co.edu.udea.nexum.opportunity.opportunity.application.dto.SalaryRangeDto;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.OpportunityStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Data Transfer Object for Opportunity creation and update requests.
 * Used for API requests - excludes system-managed fields like createdBy.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OpportunityRequestDto implements BaseRequest{
    
    @NotBlank(message = "Title is required")
    private String title;
    
    private String description;
    
    private String location;
    
    private OpportunityStatus status;
    
    private UUID graduateId; // Target graduate for this opportunity
    
    private SalaryRangeDto salaryRange;
}
