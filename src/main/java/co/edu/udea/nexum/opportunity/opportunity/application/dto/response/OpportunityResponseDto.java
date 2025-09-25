package co.edu.udea.nexum.opportunity.opportunity.application.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.edu.udea.nexum.opportunity.common.application.dto.response.BaseResponse;
import co.edu.udea.nexum.opportunity.opportunity.application.dto.SalaryRangeDto;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.ContractType;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.ExperienceLevel;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.OpportunityStatus;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.WorkModality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    
    // Contract basic data fields (mandatory according to requirements)
    private ContractType contractType;
    
    private LocalDate startDate;
    
    private Integer durationInMonths; // Duration in months, null for indefinite
    
    // Additional opportunity description fields (optional according to requirements)
    private String complementaryStudies;
    
    private ExperienceLevel requiredExperience;
    
    private Boolean travelAvailability; // null means not specified
    
    private WorkModality workModality;
}
