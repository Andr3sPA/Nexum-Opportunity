package co.edu.udea.nexum.opportunity.opportunity.application.dto.request;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.edu.udea.nexum.opportunity.common.application.dto.request.BaseRequest;
import co.edu.udea.nexum.opportunity.opportunity.application.dto.SalaryRangeDto;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.ContractType;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.ExperienceLevel;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.OpportunityStatus;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.WorkModality;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    
    // Contract basic data fields (mandatory for creation, optional for updates)
    private ContractType contractType;
    
    private LocalDate startDate;
    
    private Integer durationInMonths; // Duration in months, null for indefinite
    
    // Additional opportunity description fields (optional according to requirements)
    private String complementaryStudies;
    
    private ExperienceLevel requiredExperience;
    
    private Boolean travelAvailability; // null means not specified
    
    private WorkModality workModality;
}
