package co.edu.udea.nexum.opportunity.opportunity.application.usecase;

import co.edu.udea.nexum.opportunity.opportunity.domain.api.OpportunityServicePort;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.Opportunity;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.OpportunityStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Use case for creating opportunities.
 * Contains specific business logic for opportunity creation.
 */
@Service
@RequiredArgsConstructor
public class CreateOpportunityUseCase {
    
    private final OpportunityServicePort opportunityServicePort;
    
    public Opportunity execute(Opportunity opportunity) {
        // TODO: Add specific creation business logic here
        // Example: validation, enrichment, notifications, etc.
        
        validateOpportunityForCreation(opportunity);
        enrichOpportunityData(opportunity);
        
        return opportunityServicePort.save(opportunity);
    }
    
    private void validateOpportunityForCreation(Opportunity opportunity) {
        // TODO: Add creation-specific validation logic
        if (opportunity.getTitle() == null || opportunity.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Opportunity title is required");
        }
    }
    
    private void enrichOpportunityData(Opportunity opportunity) {
        // TODO: Add data enrichment logic
        // Example: set default status, normalize data, etc.
        if (opportunity.getStatus() == null) {
            opportunity.setStatus(OpportunityStatus.DRAFT);
        }
    }
}
