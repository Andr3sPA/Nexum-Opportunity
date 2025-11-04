package co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.repository;

import co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.entity.OpportunityHiredEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface OpportunityHiredRepository extends JpaRepository<OpportunityHiredEntity, Long> {

    List<OpportunityHiredEntity> findByOpportunityId(Long opportunityId);

    @Transactional
    void deleteByOpportunityId(Long opportunityId);

    @Transactional
    void deleteByOpportunityIdAndUserIdIn(Long opportunityId, List<UUID> userIds);

}

