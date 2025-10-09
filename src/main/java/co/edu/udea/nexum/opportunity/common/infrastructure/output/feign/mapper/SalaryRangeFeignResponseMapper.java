package co.edu.udea.nexum.opportunity.common.infrastructure.output.feign.mapper;

import co.edu.udea.nexum.opportunity.common.domain.model.SalaryRange;
import co.edu.udea.nexum.opportunity.common.infrastructure.output.feign.dto.response.SalaryRangeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SalaryRangeFeignResponseMapper extends FeignResponseMapper<SalaryRange, SalaryRangeResponse> {

    @Override
    @Mapping(target = "id", source = "id")
    @Mapping(target = "salary", source = "salary")
    @Mapping(target = "order", source = "order")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "lastUpdate", ignore = true)
    SalaryRange toDomain(SalaryRangeResponse response);

    @Override
    List<SalaryRange> toDomains(List<SalaryRangeResponse> responses);
}