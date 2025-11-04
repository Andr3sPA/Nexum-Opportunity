package co.edu.udea.nexum.opportunity.opportunity.application;

import co.edu.udea.nexum.opportunity.opportunity.application.dto.ProfileDto;
import co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.entity.OpportunityHiredEntity;
import co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.repository.OpportunityHiredRepository;
import co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.repository.OpportunityJpaRepository;
import co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.entity.OpportunityEntity;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.OpportunityStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OpportunityHiredService {

    private static final int PAGE_SIZE = 5;

    private final ProfileClient profileClient;
    private final OpportunityHiredRepository hiredRepository;
    private final OpportunityJpaRepository opportunityJpaRepository;

    public OpportunityHiredService(ProfileClient profileClient,
                                   OpportunityHiredRepository hiredRepository,
                                   OpportunityJpaRepository opportunityJpaRepository) {
        this.profileClient = profileClient;
        this.hiredRepository = hiredRepository;
        this.opportunityJpaRepository = opportunityJpaRepository;
    }

    public List<ProfileDto> searchCandidates(String q, int page) {
        return profileClient.searchProfiles(q, page, PAGE_SIZE);
    }

    @Transactional
    public void markHired(Long opportunityId, List<UUID> userIds) {
        OpportunityEntity opp = opportunityJpaRepository.findById(opportunityId)
            .orElseThrow(() -> new IllegalArgumentException("Opportunity not found: " + opportunityId));

        if (opp.getStatus() == null || opp.getStatus() != OpportunityStatus.ACTIVE) {
            throw new IllegalStateException("Opportunity must be ACTIVE to mark hired");
        }

        hiredRepository.deleteByOpportunityId(opportunityId);

        List<OpportunityHiredEntity> entities = userIds.stream()
            .map(id -> OpportunityHiredEntity.builder()
                .opportunityId(opportunityId)
                .userId(id)
                .createdAt(LocalDateTime.now())
                .build())
            .collect(Collectors.toList());

        hiredRepository.saveAll(entities);
    }

    public List<UUID> getHiredUserIds(Long opportunityId) {
        return hiredRepository.findByOpportunityId(opportunityId).stream()
            .map(OpportunityHiredEntity::getUserId)
            .collect(Collectors.toList());
    }
}

