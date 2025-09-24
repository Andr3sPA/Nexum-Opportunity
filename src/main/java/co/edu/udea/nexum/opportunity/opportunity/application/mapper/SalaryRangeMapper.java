package co.edu.udea.nexum.opportunity.opportunity.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import co.edu.udea.nexum.opportunity.common.application.mapper.BaseRequestMapper;
import co.edu.udea.nexum.opportunity.common.application.mapper.BaseResponseMapper;
import co.edu.udea.nexum.opportunity.opportunity.application.dto.SalaryRangeDto;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.SalaryRange;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SalaryRangeMapper extends BaseRequestMapper<SalaryRange, SalaryRangeDto>, BaseResponseMapper<SalaryRange, SalaryRangeDto> {
}
