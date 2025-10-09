package co.edu.udea.nexum.opportunity.opportunity.domain.api;

import java.util.List;
import java.util.UUID;

import co.edu.udea.nexum.opportunity.common.domain.api.BaseCrudServicePort;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.Opportunity;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.OpportunityStatus;

/**
 * Service port interface for Opportunity operations.
 * Defines the business operations available for Opportunity entities.
 */
public interface OpportunityServicePort extends BaseCrudServicePort<Long, Opportunity> {
    
    List<Opportunity> findAllOpportunities();
    List<Opportunity> findByStatus(OpportunityStatus status);
    List<Opportunity> findByCreatedBy(UUID createdBy);
    Opportunity updateStatus(Long id, OpportunityStatus newStatus);
    void assignOwnerByEditCode(String editCode, UUID ownerId);

}
