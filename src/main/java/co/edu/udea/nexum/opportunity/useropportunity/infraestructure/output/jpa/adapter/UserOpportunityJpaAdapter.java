package co.edu.udea.nexum.opportunity.useropportunity.infraestructure.output.jpa.adapter;

import co.edu.udea.nexum.opportunity.common.infrastructure.output.jpa.adapter.impl.BaseCrudAdapterImpl;
import co.edu.udea.nexum.opportunity.common.infrastructure.output.jpa.mapper.BaseEntityMapper;
import co.edu.udea.nexum.opportunity.useropportunity.domain.model.UserOpportunity;
import co.edu.udea.nexum.opportunity.useropportunity.domain.spi.UserOpportunityPersistencePort;
import co.edu.udea.nexum.opportunity.useropportunity.infraestructure.output.jpa.entity.UserOpportunityEntity;
import co.edu.udea.nexum.opportunity.useropportunity.infraestructure.output.jpa.mapper.UserOpportunityMapper;
import co.edu.udea.nexum.opportunity.useropportunity.infraestructure.output.jpa.repository.UserOpportunityRepository;
import lombok.RequiredArgsConstructor;
import org.h2.engine.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserOpportunityJpaAdapter extends BaseCrudAdapterImpl<UUID, UserOpportunity, UserOpportunityEntity> implements UserOpportunityPersistencePort {
    private final UserOpportunityRepository userOpportunityRepository;
    private final UserOpportunityMapper userOpportunityEntityMapper;

    @Override
    protected BaseEntityMapper<UserOpportunity,UserOpportunityEntity> getMapper() {
        return userOpportunityEntityMapper;
    }

    @Override
    protected UserOpportunityRepository getRepository() {
        return userOpportunityRepository;
    }

    @Override
    public List<UserOpportunity> findByUserId(UUID userId) {
        return userOpportunityEntityMapper.toDomains(userOpportunityRepository.findByUserId(userId));
    }

    @Override
    public UserOpportunity findById(UUID uuid) {
        return null;
    }




}
