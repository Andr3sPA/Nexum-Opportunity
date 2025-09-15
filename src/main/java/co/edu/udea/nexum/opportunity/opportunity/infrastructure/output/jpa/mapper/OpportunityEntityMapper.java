package co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.mapper;

import co.edu.udea.nexum.opportunity.common.infrastructure.output.jpa.mapper.BaseEntityMapper;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.Opportunity;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.SalaryRange;
import co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.entity.OpportunityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.math.BigDecimal;

/**
 * Mapper for converting between Opportunity domain model and OpportunityEntity.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OpportunityEntityMapper extends BaseEntityMapper<Opportunity, OpportunityEntity> {
    
    @Mapping(target = "salaryMin", source = "salaryRange", qualifiedByName = "salaryRangeToMin")
    @Mapping(target = "salaryMax", source = "salaryRange", qualifiedByName = "salaryRangeToMax")
    @Mapping(target = "salaryCurrency", source = "salaryRange", qualifiedByName = "salaryRangeToCurrency")
    OpportunityEntity toEntity(Opportunity opportunity);
    
    @Mapping(target = "salaryRange", source = ".", qualifiedByName = "entityToSalaryRange")
    Opportunity toDomain(OpportunityEntity entity);
    
    @Named("salaryRangeToMin")
    default BigDecimal salaryRangeToMin(SalaryRange salaryRange) {
        return salaryRange != null ? salaryRange.getMin() : null;
    }
    
    @Named("salaryRangeToMax")
    default BigDecimal salaryRangeToMax(SalaryRange salaryRange) {
        return salaryRange != null ? salaryRange.getMax() : null;
    }
    
    @Named("salaryRangeToCurrency")
    default String salaryRangeToCurrency(SalaryRange salaryRange) {
        return salaryRange != null ? salaryRange.getCurrency() : null;
    }
    
    @Named("entityToSalaryRange")
    default SalaryRange entityToSalaryRange(OpportunityEntity entity) {
        if (entity.getSalaryMin() == null && entity.getSalaryMax() == null && entity.getSalaryCurrency() == null) {
            return null;
        }
        return SalaryRange.builder()
                .min(entity.getSalaryMin())
                .max(entity.getSalaryMax())
                .currency(entity.getSalaryCurrency())
                .build();
    }
    
}
