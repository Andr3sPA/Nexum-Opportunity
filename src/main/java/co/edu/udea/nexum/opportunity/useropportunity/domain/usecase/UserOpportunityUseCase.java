package co.edu.udea.nexum.opportunity.useropportunity.domain.usecase;

import co.edu.udea.nexum.opportunity.common.domain.spi.BaseCrudPersistencePort;
import co.edu.udea.nexum.opportunity.common.domain.usecase.AuditableCrudUseCase;
import co.edu.udea.nexum.opportunity.useropportunity.domain.model.UserOpportunity;
import co.edu.udea.nexum.opportunity.useropportunity.domain.spi.UserOpportunityPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
@Component
@RequiredArgsConstructor
public class UserOpportunityUseCase extends AuditableCrudUseCase<UUID, UserOpportunity> {
    private final UserOpportunityPersistencePort userOpportunityPersistencePort;
    @Override
    protected BaseCrudPersistencePort<UUID, UserOpportunity> getPersistencePort() {
        return null;
    }
    public List<UserOpportunity> findByUserId(UUID userId) {
        return userOpportunityPersistencePort.findByUserId(userId);
    }
    public UserOpportunity save(UserOpportunity userOpportunity) {
        return userOpportunityPersistencePort.save(userOpportunity);
    }

    @Override
    protected String getModelClassName() {
        return "";
    }

    @Override
    protected UserOpportunity patch(UserOpportunity original, UserOpportunity patch) {
        return null;
    }

    @Override
    protected void validateEntity(UUID currentId, UserOpportunity model) {

    }
}
