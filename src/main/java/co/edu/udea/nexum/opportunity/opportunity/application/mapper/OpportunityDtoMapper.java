package co.edu.udea.nexum.opportunity.opportunity.application.mapper;

import co.edu.udea.nexum.opportunity.opportunity.application.dto.SalaryRangeDto;
import co.edu.udea.nexum.opportunity.opportunity.application.dto.request.OpportunityRequestDto;
import co.edu.udea.nexum.opportunity.opportunity.application.dto.response.OpportunityResponseDto;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.Opportunity;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.SalaryRange;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * Mapper for converting between Opportunity domain model and DTOs.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OpportunityDtoMapper {
    
    // Response DTO mapping methods
    OpportunityResponseDto toResponseDto(Opportunity opportunity);
    List<OpportunityResponseDto> toResponseDtos(List<Opportunity> opportunities);
    
    // For requests, we map to domain but ignore system-managed fields
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "lastUpdate", ignore = true)
    Opportunity toDomainFromRequest(OpportunityRequestDto requestDto);
    
    // Salary range mapping methods
    SalaryRangeDto salaryRangeToDto(SalaryRange salaryRange);
    SalaryRange salaryRangeToDomain(SalaryRangeDto salaryRangeDto);
    
}
