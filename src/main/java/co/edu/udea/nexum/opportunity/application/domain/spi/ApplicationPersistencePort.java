package co.edu.udea.nexum.opportunity.application.domain.spi;

import co.edu.udea.nexum.opportunity.application.domain.model.Application;
import co.edu.udea.nexum.opportunity.common.domain.spi.BaseCrudPersistencePort;

import java.util.List;
import java.util.UUID;

public interface ApplicationPersistencePort extends BaseCrudPersistencePort<UUID, Application> {
    List<Application> findByUserId(UUID userId);
}
