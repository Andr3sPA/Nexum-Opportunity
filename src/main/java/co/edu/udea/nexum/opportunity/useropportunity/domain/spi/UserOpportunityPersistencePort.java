package co.edu.udea.nexum.opportunity.useropportunity.domain.spi;

import co.edu.udea.nexum.opportunity.common.domain.spi.BaseCrudPersistencePort;
import co.edu.udea.nexum.opportunity.useropportunity.domain.model.UserOpportunity;

import java.util.List;
import java.util.UUID;

public interface UserOpportunityPersistencePort extends BaseCrudPersistencePort<UUID, UserOpportunity> {
    List<UserOpportunity> findByUserId(UUID userId);
}
