package co.edu.udea.nexum.opportunity.opportunity.application.handler;

import co.edu.udea.nexum.opportunity.opportunity.application.dto.request.OpportunityRequestDto;
import co.edu.udea.nexum.opportunity.opportunity.application.dto.response.OpportunityResponseDto;
import co.edu.udea.nexum.opportunity.opportunity.application.mapper.OpportunityDtoMapper;
import co.edu.udea.nexum.opportunity.opportunity.domain.api.OpportunityServicePort;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.Opportunity;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.OpportunityStatus;
import co.edu.udea.nexum.opportunity.security.domain.model.AuthenticatedUser;
import co.edu.udea.nexum.opportunity.security.domain.utils.SecurityContextUtils;
import co.edu.udea.nexum.opportunity.security.domain.utils.enums.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Application handler for Opportunity operations.
 * Orchestrates the flow between the API layer and domain layer.
 */
@Component
@RequiredArgsConstructor
public class OpportunityHandler {
    
    private final OpportunityServicePort opportunityServicePort;
    private final OpportunityDtoMapper opportunityDtoMapper;
    private final SecurityContextUtils securityContextUtils;
    
    // New methods using request/response DTOs
    public OpportunityResponseDto createOpportunity(OpportunityRequestDto requestDto) {
        // Get current authenticated user
        AuthenticatedUser currentUser = securityContextUtils.getCurrentUser();
        
        // Map request to domain object
        Opportunity opportunity = opportunityDtoMapper.toDomainFromRequest(requestDto);
        
        // Set createdBy from authenticated user
        opportunity.setCreatedBy(currentUser.getId());
        
        // Save opportunity
        Opportunity savedOpportunity = opportunityServicePort.save(opportunity);
        
        // Return response DTO
        return opportunityDtoMapper.toResponseDto(savedOpportunity);
    }
    
    public List<OpportunityResponseDto> getAllOpportunities() {
        List<Opportunity> opportunities = opportunityServicePort.findAllOpportunities();
        return opportunityDtoMapper.toResponseDtos(opportunities);
    }
    
    public OpportunityResponseDto getOpportunityById(Long id) {
        Opportunity opportunity = opportunityServicePort.findById(id);
        return opportunityDtoMapper.toResponseDto(opportunity);
    }
    
    public OpportunityResponseDto updateOpportunity(Long id, OpportunityRequestDto requestDto) {
        // Get current authenticated user
        AuthenticatedUser currentUser = securityContextUtils.getCurrentUser();
        
        // Check if user can update this opportunity
        Opportunity existingOpportunity = opportunityServicePort.findById(id);
        validateUserCanModifyOpportunity(currentUser, existingOpportunity);
        
        // Map request to domain object
        Opportunity opportunity = opportunityDtoMapper.toDomainFromRequest(requestDto);
        
        // Preserve the original createdBy field
        opportunity.setCreatedBy(existingOpportunity.getCreatedBy());
        
        // Update opportunity
        Opportunity updatedOpportunity = opportunityServicePort.updateById(id, opportunity);
        
        // Return response DTO
        return opportunityDtoMapper.toResponseDto(updatedOpportunity);
    }
    
    public OpportunityResponseDto deleteOpportunity(Long id) {
        // Get current authenticated user
        AuthenticatedUser currentUser = securityContextUtils.getCurrentUser();
        
        // Check if user can delete this opportunity
        Opportunity existingOpportunity = opportunityServicePort.findById(id);
        validateUserCanModifyOpportunity(currentUser, existingOpportunity);
        
        // Delete opportunity
        Opportunity deletedOpportunity = opportunityServicePort.deleteById(id);
        
        // Return response DTO
        return opportunityDtoMapper.toResponseDto(deletedOpportunity);
    }
    
    public List<OpportunityResponseDto> getOpportunitiesByGraduateId(UUID graduateId) {
        List<Opportunity> opportunities = opportunityServicePort.findByGraduateId(graduateId);
        return opportunityDtoMapper.toResponseDtos(opportunities);
    }
    
    public OpportunityResponseDto updateOpportunityStatus(Long id, OpportunityStatus newStatus) {
        // Get current authenticated user
        AuthenticatedUser currentUser = securityContextUtils.getCurrentUser();
        
        // Check if user can update this opportunity
        Opportunity existingOpportunity = opportunityServicePort.findById(id);
        validateUserCanModifyOpportunity(currentUser, existingOpportunity);
        
        // Update status
        Opportunity updatedOpportunity = opportunityServicePort.updateStatus(id, newStatus);
        
        // Return response DTO
        return opportunityDtoMapper.toResponseDto(updatedOpportunity);
    }
    
    private void validateUserCanModifyOpportunity(AuthenticatedUser currentUser, Opportunity opportunity) {
        // Admin can modify any opportunity
        if (currentUser.getRole() == RoleName.ADMIN) {
            return;
        }
        
        // Employer can only modify opportunities they created
        if (currentUser.getRole() == RoleName.EMPLOYER && 
            opportunity.getCreatedBy().equals(currentUser.getId())) {
            return;
        }
        
        // Otherwise, access denied
        throw new IllegalArgumentException("User is not authorized to modify this opportunity");
    }
}
