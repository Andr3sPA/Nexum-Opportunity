package co.edu.udea.nexum.opportunity.opportunity.application.dto.response;

import co.edu.udea.nexum.opportunity.common.application.dto.response.BaseResponse;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.ExperienceLevel;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.WorkModality;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Data Transfer Object for Candidate Requirements responses.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CandidateRequirementsResponseDto implements BaseResponse {

    private Long id;
    private String complementaryStudies;
    private ExperienceLevel requiredExperience;
    private String location;
    private Boolean travelAvailability;
    private WorkModality workModality;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdate;
    private Set<Long> coursedProgramIds;
    private Set<Long> programCompetencyIds;
    private Set<Long> jobAreaIds;
}