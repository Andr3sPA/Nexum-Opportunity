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

/**
 * Application handler for Opportunity operations.
 * Orchestrates the flow between the API layer and domain layer.
 */
@Component
@RequiredArgsConstructor
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
        // Get existing opportunity to preserve system fields
        Opportunity existingOpportunity = opportunityServicePort.findById(id);
        
        // Create updated opportunity using builder with existing values
        Opportunity.OpportunityBuilder updatedBuilder = Opportunity.builder()
                .id(existingOpportunity.getId())
                .createdBy(existingOpportunity.getCreatedBy())
                .creationDate(existingOpportunity.getCreationDate())
                .lastUpdate(existingOpportunity.getLastUpdate())
                .graduateId(existingOpportunity.getGraduateId())
                .salaryRange(existingOpportunity.getSalaryRange())
                .title(existingOpportunity.getTitle())
                .description(existingOpportunity.getDescription())
                .location(existingOpportunity.getLocation())
                .status(existingOpportunity.getStatus());
        
        // Update only the provided fields from the request
        if (requestDto.getTitle() != null) {
            updatedBuilder.title(requestDto.getTitle());
        }
        if (requestDto.getDescription() != null) {
            updatedBuilder.description(requestDto.getDescription());
        }
        if (requestDto.getLocation() != null) {
            updatedBuilder.location(requestDto.getLocation());
        }
        if (requestDto.getStatus() != null) {
            updatedBuilder.status(requestDto.getStatus());
        }
        if (requestDto.getGraduateId() != null) {
            updatedBuilder.graduateId(requestDto.getGraduateId());
        }
        if (requestDto.getSalaryRange() != null) {
            // Map the salary range DTO to domain object
            updatedBuilder.salaryRange(opportunityDtoMapper.toDomain(requestDto).getSalaryRange());
        }
        
        Opportunity updatedOpportunity = opportunityServicePort.updateById(id, updatedBuilder.build());
        return opportunityDtoMapper.toResponse(updatedOpportunity);
    }

    public OpportunityResponseDto deleteOpportunity(Long id) {
        Opportunity deletedOpportunity = opportunityServicePort.deleteById(id);
        return opportunityDtoMapper.toResponse(deletedOpportunity);
    }

    public List<OpportunityResponseDto> getOpportunitiesByGraduateId(UUID graduateId) {
        List<Opportunity> opportunities = opportunityServicePort.findByGraduateId(graduateId);
        return opportunityDtoMapper.toResponses(opportunities);
    }

    public OpportunityResponseDto updateOpportunityStatus(Long id, OpportunityStatus newStatus) {
        Opportunity updatedOpportunity = opportunityServicePort.updateStatus(id, newStatus);
        return opportunityDtoMapper.toResponse(updatedOpportunity);
    }
    
}
