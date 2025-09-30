package co.edu.udea.nexum.opportunity.useropportunity.infraestructure.output.jpa.repository;

import co.edu.udea.nexum.opportunity.useropportunity.infraestructure.output.jpa.entity.UserOpportunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserOpportunityRepository extends JpaRepository<UserOpportunityEntity, UUID> {
    List<UserOpportunityEntity> findByUserId(UUID userId);
}
