package co.edu.udea.nexum.opportunity.opportunity.application.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import co.edu.udea.nexum.opportunity.common.application.dto.response.BaseResponse;
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

    private Long salaryRangeId;

    // Edit code for anonymous editing or assignment
    private String editCode;

    // New fields
    private LocalDate expirationDate;

    // Flattened fields for backward compatibility
    private String complementaryStudies;
    private ExperienceLevel requiredExperience;
    private Boolean travelAvailability;
    private WorkModality workModality;
    private Set<String> coursedPrograms;
    private Set<String> programCompetencies;
    private Set<String> jobAreas;
    private String businessName;
    private String contactName;
    private String businessEmail;
    private String businessPhone;

    // Related entities
    private BusinessContactResponseDto businessContact;
    private CandidateRequirementsResponseDto candidateRequirements;
}
