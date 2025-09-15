package co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.repository;

import co.edu.udea.nexum.opportunity.opportunity.domain.model.OpportunityStatus;
import co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.entity.OpportunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * JPA Repository interface for OpportunityEntity.
 * Spring Data JPA will automatically implement these methods.
 */
@Repository
public interface OpportunityJpaRepository extends JpaRepository<OpportunityEntity, Long> {
    
    /**
     * Find opportunities by status.
     * Spring Data JPA will generate the implementation automatically.
     */
    List<OpportunityEntity> findByStatus(OpportunityStatus status);
    
    /**
     * Find opportunities by the user who created them.
     * Spring Data JPA will generate the implementation automatically.
     */
    List<OpportunityEntity> findByCreatedBy(UUID createdBy);
    
    /**
     * Find opportunities directed to a specific graduate.
     * Spring Data JPA will generate the implementation automatically.
     */
    List<OpportunityEntity> findByGraduateId(UUID graduateId);
    
    /**
     * Find opportunities by location.
     * Spring Data JPA will generate the implementation automatically.
     */
    List<OpportunityEntity> findByLocation(String location);
    
    /**
     * Find opportunities by location containing a specific text (case-insensitive).
     * Spring Data JPA will generate the implementation automatically.
     */
    List<OpportunityEntity> findByLocationContainingIgnoreCase(String location);
    
    /**
     * Find opportunities by status and created by user.
     * Spring Data JPA will generate the implementation automatically.
     */
    List<OpportunityEntity> findByStatusAndCreatedBy(OpportunityStatus status, UUID createdBy);
    
}
