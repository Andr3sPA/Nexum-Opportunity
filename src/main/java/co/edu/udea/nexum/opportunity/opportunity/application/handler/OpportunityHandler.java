package co.edu.udea.nexum.opportunity.opportunity.application.handler;

import java.util.List;
import java.util.UUID;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import co.edu.udea.nexum.opportunity.opportunity.application.dto.request.OpportunityRequestDto;
import co.edu.udea.nexum.opportunity.opportunity.application.dto.response.CandidateRequirementsResponseDto;
import co.edu.udea.nexum.opportunity.opportunity.application.dto.response.OpportunityResponseDto;
import co.edu.udea.nexum.opportunity.opportunity.application.mapper.OpportunityDtoMapper;
import co.edu.udea.nexum.opportunity.opportunity.domain.api.OpportunityServicePort;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.Opportunity;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.OpportunityStatus;
import co.edu.udea.nexum.opportunity.common.infrastructure.output.feign.adapter.CatalogFeignAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Application handler for Opportunity operations.
 * Orchestrates the flow between the API layer and domain layer.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class OpportunityHandler {

  private final OpportunityServicePort opportunityServicePort;
  private final OpportunityDtoMapper opportunityDtoMapper;
  private final CatalogFeignAdapter catalogFeignAdapter;

  public OpportunityResponseDto createOpportunity(OpportunityRequestDto requestDto) {
    Opportunity opportunity = opportunityDtoMapper.toDomain(requestDto);
    Opportunity savedOpportunity = opportunityServicePort.save(opportunity);
    return opportunityDtoMapper.toResponse(savedOpportunity);
  }

  public List<OpportunityResponseDto> getAllOpportunities() {
    List<Opportunity> opportunities = opportunityServicePort.findAllOpportunities();
    List<OpportunityResponseDto> responses = opportunityDtoMapper.toResponses(opportunities);
    // Enrich each response with human-readable names for programs, competencies and job areas
    responses.forEach(this::enrichResponseWithNames);
    return responses;
  }

  public OpportunityResponseDto getOpportunityById(Long id) {
    Opportunity opportunity = opportunityServicePort.findById(id);
    OpportunityResponseDto response = opportunityDtoMapper.toResponse(opportunity);
    enrichResponseWithNames(response);
    return response;
  }

  public OpportunityResponseDto updateOpportunity(Long id, OpportunityRequestDto requestDto) {
    Opportunity opportunity = opportunityDtoMapper.toDomain(requestDto);
    Opportunity updatedOpportunity = opportunityServicePort.updateById(id, opportunity);
    return opportunityDtoMapper.toResponse(updatedOpportunity);
  }

  public OpportunityResponseDto deleteOpportunity(Long id) {
    Opportunity deletedOpportunity = opportunityServicePort.deleteById(id);
    return opportunityDtoMapper.toResponse(deletedOpportunity);
  }

  public OpportunityResponseDto updateOpportunityStatus(Long id, OpportunityStatus newStatus) {
    Opportunity updatedOpportunity = opportunityServicePort.updateStatus(id, newStatus);
    return opportunityDtoMapper.toResponse(updatedOpportunity);
  }

  public void assignOwnerByEditCode(String editCode, String ownerId) {
    log.debug("Assigning owner by edit code {} to {}", editCode, ownerId);
    opportunityServicePort.assignOwnerByEditCode(editCode, UUID.fromString(ownerId));
  }

  // Helper to enrich response DTO with names instead of only ids
  private void enrichResponseWithNames(OpportunityResponseDto response) {
    if (response == null) return;
    CandidateRequirementsResponseDto cr = response.getCandidateRequirements();
    if (cr == null) return;

    // Convert coursedProgramIds -> coursedPrograms (names)
    if (cr.getCoursedProgramIds() != null && !cr.getCoursedProgramIds().isEmpty()) {
      Set<String> programNames = cr.getCoursedProgramIds().stream()
          .map(id -> {
            try {
              return catalogFeignAdapter.getProgramById(id).getName();
            } catch (Exception e) {
              log.debug("Could not fetch program name for id {}: {}", id, e.getMessage());
              return String.valueOf(id);
            }
          })
          .collect(Collectors.toSet());
      response.setCoursedPrograms(programNames);
      // Optionally hide the numeric ids from candidateRequirements to avoid duplication
      cr.setCoursedProgramIds(null);
    }

    // Convert programCompetencyIds -> programCompetencies (names)
    if (cr.getProgramCompetencyIds() != null && !cr.getProgramCompetencyIds().isEmpty()) {
      Set<String> competencyNames = cr.getProgramCompetencyIds().stream()
          .map(id -> {
            try {
              return catalogFeignAdapter.getProgramCompetencyById(id).getName();
            } catch (Exception e) {
              log.debug("Could not fetch competency name for id {}: {}", id, e.getMessage());
              return String.valueOf(id);
            }
          })
          .collect(Collectors.toSet());
      response.setProgramCompetencies(competencyNames);
      cr.setProgramCompetencyIds(null);
    }

    // Convert jobAreaIds -> jobAreas (names)
    if (cr.getJobAreaIds() != null && !cr.getJobAreaIds().isEmpty()) {
      Set<String> jobAreaNames = cr.getJobAreaIds().stream()
          .map(id -> {
            try {
              return catalogFeignAdapter.getJobAreaById(id).getName();
            } catch (Exception e) {
              log.debug("Could not fetch job area name for id {}: {}", id, e.getMessage());
              return String.valueOf(id);
            }
          })
          .collect(Collectors.toSet());
      response.setJobAreas(jobAreaNames);
      cr.setJobAreaIds(null);
    }
  }

}
