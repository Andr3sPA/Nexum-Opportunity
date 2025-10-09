package co.edu.udea.nexum.opportunity.opportunity.application.dto.request;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.edu.udea.nexum.opportunity.common.application.dto.request.BaseRequest;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.ExperienceLevel;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.OpportunityStatus;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.WorkModality;
import jakarta.validation.constraints.Future;
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
public class OpportunityRequestDto implements BaseRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    private OpportunityStatus status;

    private Long salaryRangeId;

    // Edit code for anonymous editing (auto-generated)
    private String editCode;

    @Future(message = "Expiration date must be in the future")
    private LocalDate expirationDate;

    // Business information
    private String businessName;
    private String contactName;
    private String businessEmail;
    private String businessPhone;

    // Candidate requirements
    private String complementaryStudies;
    private ExperienceLevel requiredExperience;
    private Boolean travelAvailability; // null means not specified
    private WorkModality workModality;
    private String location;

    // Multiple selections
    private Set<Long> coursedProgramIds; // References to catalog programs
    private Set<Long> programCompetencyIds; // References to catalog competencies
    private Set<Long> jobAreaIds; // References to catalog job areas
}
