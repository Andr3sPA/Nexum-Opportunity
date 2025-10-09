package co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.adapter;

import co.edu.udea.nexum.opportunity.opportunity.domain.model.Opportunity;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.OpportunityStatus;
import co.edu.udea.nexum.opportunity.opportunity.domain.spi.OpportunityPersistencePort;
import co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.entity.OpportunityEntity;
import co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.mapper.OpportunityEntityMapper;
import co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.repository.OpportunityJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * JPA Adapter implementation for Opportunity persistence.
 * Implements the OpportunityPersistencePort using JPA repository.
 */
@Repository
@RequiredArgsConstructor
public class OpportunityJpaAdapter implements OpportunityPersistencePort {

    private final OpportunityJpaRepository opportunityJpaRepository;
    private final OpportunityEntityMapper opportunityEntityMapper;

    @Override
    public Opportunity save(Opportunity model) {
        OpportunityEntity entity = opportunityEntityMapper.toEntity(model);
        OpportunityEntity savedEntity = opportunityJpaRepository.save(entity);
        return opportunityEntityMapper.toDomain(savedEntity);
    }

    @Override
    public List<Opportunity> findAll() {
        List<OpportunityEntity> entities = opportunityJpaRepository.findAll();
        return opportunityEntityMapper.toDomains(entities);
    }

    @Override
    public Opportunity findById(Long id) {
        Optional<OpportunityEntity> entityOptional = opportunityJpaRepository.findById(id);
        return entityOptional.map(opportunityEntityMapper::toDomain).orElse(null);
    }

    @Override
    public Opportunity update(Opportunity model) {
        OpportunityEntity entity = opportunityEntityMapper.toEntity(model);
        OpportunityEntity updatedEntity = opportunityJpaRepository.save(entity);
        return opportunityEntityMapper.toDomain(updatedEntity);
    }

    @Override
    public void deleteById(Long id) {
        opportunityJpaRepository.deleteById(id);
    }

    @Override
    public List<Opportunity> findByStatus(OpportunityStatus status) {
        List<OpportunityEntity> entities = opportunityJpaRepository.findByStatus(status);
        return opportunityEntityMapper.toDomains(entities);
    }

    @Override
    public List<Opportunity> findByCreatedBy(UUID createdBy) {
        List<OpportunityEntity> entities = opportunityJpaRepository.findByCreatedBy(createdBy);
        return opportunityEntityMapper.toDomains(entities);
    }

    @Override
    public List<Opportunity> findByLocation(String location) {
        List<OpportunityEntity> entities = opportunityJpaRepository.findByCandidateRequirementsLocation(location);
        return opportunityEntityMapper.toDomains(entities);
    }

    public List<Opportunity> findByLocationContaining(String location) {
        List<OpportunityEntity> entities = opportunityJpaRepository.findByCandidateRequirementsLocationContainingIgnoreCase(location);
        return opportunityEntityMapper.toDomains(entities);
    }

    public List<Opportunity> findByStatusAndCreatedBy(OpportunityStatus status, UUID createdBy) {
        List<OpportunityEntity> entities = opportunityJpaRepository.findByStatusAndCreatedBy(status, createdBy);
        return opportunityEntityMapper.toDomains(entities);
    }

    @Override
    public List<Opportunity> findByEditCode(String editCode) {
        List<OpportunityEntity> entities = opportunityJpaRepository.findByEditCode(editCode);
        return opportunityEntityMapper.toDomains(entities);
    }

}
