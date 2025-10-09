package co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.mapper;

import co.edu.udea.nexum.opportunity.common.infrastructure.output.jpa.mapper.BaseEntityMapper;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.BusinessContact;
import co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.entity.BusinessContactEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * Mapper for converting between BusinessContact domain model and BusinessContactEntity.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BusinessContactEntityMapper extends BaseEntityMapper<BusinessContact, BusinessContactEntity> {

}