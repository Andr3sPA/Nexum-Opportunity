package co.edu.udea.nexum.opportunity.application.infraestructure.output.jpa.adapter;

import co.edu.udea.nexum.opportunity.application.domain.model.Application;
import co.edu.udea.nexum.opportunity.application.infraestructure.output.jpa.entity.ApplicationEntity;
import co.edu.udea.nexum.opportunity.common.infrastructure.output.jpa.adapter.impl.BaseCrudAdapterImpl;
import co.edu.udea.nexum.opportunity.common.infrastructure.output.jpa.mapper.BaseEntityMapper;
import co.edu.udea.nexum.opportunity.application.domain.spi.ApplicationPersistencePort;
import co.edu.udea.nexum.opportunity.application.infraestructure.output.jpa.mapper.ApplicationMapper;
import co.edu.udea.nexum.opportunity.application.infraestructure.output.jpa.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ApplicationJpaAdapter extends BaseCrudAdapterImpl<UUID, Application, ApplicationEntity> implements ApplicationPersistencePort {
    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper userOpportunityEntityMapper;

    @Override
    protected BaseEntityMapper<Application, ApplicationEntity> getMapper() {
        return userOpportunityEntityMapper;
    }

    @Override
    protected ApplicationRepository getRepository() {
        return applicationRepository;
    }

    @Override
    public List<Application> findByUserId(UUID userId) {
        return userOpportunityEntityMapper.toDomains(applicationRepository.findByUserId(userId));
    }

    @Override
    public List<Long> findOpportunityIdsByApplicantId(UUID applicantId) {
        return List.of();
    }

    @Override
    public Application findById(UUID uuid) {
        return null;
    }




}
