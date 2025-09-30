package co.edu.udea.nexum.opportunity.useropportunity.infraestructure.output.jpa.mapper;

import co.edu.udea.nexum.opportunity.common.domain.utils.annotations.Generated;
import co.edu.udea.nexum.opportunity.common.infrastructure.output.jpa.mapper.BaseEntityMapper;
import co.edu.udea.nexum.opportunity.useropportunity.domain.model.UserOpportunity;
import co.edu.udea.nexum.opportunity.useropportunity.infraestructure.output.jpa.entity.UserOpportunityEntity;
import co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.entity.OpportunityEntity;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.Opportunity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Generated
@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserOpportunityMapper extends BaseEntityMapper<UserOpportunity, UserOpportunityEntity> {

    @Mapping(target = "opportunity", source = "opportunity", qualifiedByName = "opportunityToEntity")
    UserOpportunityEntity toEntity(UserOpportunity domain);

    @Mapping(target = "opportunity", source = "opportunity", qualifiedByName = "entityToOpportunity")
    UserOpportunity toDomain(UserOpportunityEntity entity);

    @Named("opportunityToEntity")
    default OpportunityEntity opportunityToEntity(Opportunity opportunity) {
        if (opportunity == null) return null;
        OpportunityEntity entity = new OpportunityEntity();
        entity.setId(opportunity.getId());
        return entity;
    }

    @Named("entityToOpportunity")
    default Opportunity entityToOpportunity(OpportunityEntity entity) {
        if (entity == null) return null;
        return Opportunity.builder().id(entity.getId()).build();
    }

}
