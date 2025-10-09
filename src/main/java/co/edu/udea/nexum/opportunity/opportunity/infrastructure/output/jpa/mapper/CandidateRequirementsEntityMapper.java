package co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.mapper;

import co.edu.udea.nexum.opportunity.common.infrastructure.output.jpa.mapper.BaseEntityMapper;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.CandidateRequirements;
import co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.entity.CandidateRequirementsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * Mapper for converting between CandidateRequirements domain model and CandidateRequirementsEntity.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CandidateRequirementsEntityMapper extends BaseEntityMapper<CandidateRequirements, CandidateRequirementsEntity> {

}