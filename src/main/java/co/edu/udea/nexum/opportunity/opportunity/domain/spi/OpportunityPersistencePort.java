package co.edu.udea.nexum.opportunity.opportunity.domain.spi;

import java.util.List;

import co.edu.udea.nexum.opportunity.common.domain.spi.BaseCrudPersistencePort;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.Opportunity;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.OpportunityStatus;

/**
 * Port interface for Opportunity persistence operations.
 * Extends the base CRUD operations with opportunity-specific persistence methods.
 */
public interface OpportunityPersistencePort extends BaseCrudPersistencePort<Long, Opportunity> {
    
    List<Opportunity> findByStatus(OpportunityStatus status);
    List<Opportunity> findByCreatedBy(java.util.UUID createdBy);
    List<Opportunity> findByGraduateId(java.util.UUID graduateId);
    List<Opportunity> findByLocation(String location);
    
}
