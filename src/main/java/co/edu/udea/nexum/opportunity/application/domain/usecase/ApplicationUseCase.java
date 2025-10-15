package co.edu.udea.nexum.opportunity.application.domain.usecase;

import co.edu.udea.nexum.opportunity.application.domain.model.Application;
import co.edu.udea.nexum.opportunity.common.domain.spi.BaseCrudPersistencePort;
import co.edu.udea.nexum.opportunity.common.domain.usecase.AuditableCrudUseCase;
import co.edu.udea.nexum.opportunity.application.domain.spi.ApplicationPersistencePort;
import co.edu.udea.nexum.opportunity.opportunity.domain.exception.OpportunityNotFoundException;
import co.edu.udea.nexum.opportunity.opportunity.domain.spi.OpportunityPersistencePort;
import co.edu.udea.nexum.opportunity.security.domain.utils.SecurityContextUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
@Component
@RequiredArgsConstructor
public class ApplicationUseCase extends AuditableCrudUseCase<UUID, Application> {
    private final ApplicationPersistencePort applicationPersistencePort;
    private final OpportunityPersistencePort opportunityPersistencePort;
    @Override
    protected BaseCrudPersistencePort<UUID, Application> getPersistencePort() {
        return applicationPersistencePort;
    }
    public List<Application> findByUserId(UUID userId) {
        return applicationPersistencePort.findByUserId(userId);
    }
    public Application save(Application application) {
        // Validar existencia de la oportunidad
        if (opportunityPersistencePort.findById(application.getOpportunity().getId()) == null) {
            throw new OpportunityNotFoundException(application.getOpportunity().getId());
        }
        else if(applicationPersistencePort.findById(application.getId()).getUserId()==application.getUserId()){
            return null;
        }
        return applicationPersistencePort.save(application);
    }

    @Override
    protected String getModelClassName() {
        return "";
    }

    @Override
    protected Application patch(Application original, Application patch) {
        return null;
    }

    @Override
    protected void validateEntity(UUID currentId, Application model) {

    }
}
