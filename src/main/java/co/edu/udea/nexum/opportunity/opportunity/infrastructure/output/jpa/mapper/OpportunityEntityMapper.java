package co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.mapper;

import co.edu.udea.nexum.opportunity.common.infrastructure.output.jpa.mapper.BaseEntityMapper;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.Opportunity;
import co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.entity.OpportunityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * Mapper for converting between Opportunity domain model and OpportunityEntity.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OpportunityEntityMapper extends BaseEntityMapper<Opportunity, OpportunityEntity> {

}
