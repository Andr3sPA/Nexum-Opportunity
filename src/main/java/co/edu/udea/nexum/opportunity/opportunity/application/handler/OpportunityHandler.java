package co.edu.udea.nexum.opportunity.opportunity.application.handler;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import co.edu.udea.nexum.opportunity.opportunity.application.dto.request.OpportunityRequestDto;
import co.edu.udea.nexum.opportunity.opportunity.application.dto.response.OpportunityResponseDto;
import co.edu.udea.nexum.opportunity.opportunity.application.mapper.OpportunityDtoMapper;
import co.edu.udea.nexum.opportunity.opportunity.domain.api.OpportunityServicePort;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.Opportunity;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.OpportunityStatus;
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

  public OpportunityResponseDto createOpportunity(OpportunityRequestDto requestDto) {
    Opportunity opportunity = opportunityDtoMapper.toDomain(requestDto);
    Opportunity savedOpportunity = opportunityServicePort.save(opportunity);
    return opportunityDtoMapper.toResponse(savedOpportunity);
  }

  public List<OpportunityResponseDto> getAllOpportunities() {
    List<Opportunity> opportunities = opportunityServicePort.findAllOpportunities();
    return opportunityDtoMapper.toResponses(opportunities);
  }

  public OpportunityResponseDto getOpportunityById(Long id) {
    Opportunity opportunity = opportunityServicePort.findById(id);
    return opportunityDtoMapper.toResponse(opportunity);
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

}
