package co.edu.udea.nexum.opportunity.common.infrastructure.output.feign.mapper;

import co.edu.udea.nexum.opportunity.common.domain.model.ProgramCompetency;
import co.edu.udea.nexum.opportunity.common.infrastructure.output.feign.dto.response.ProgramCompetencyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProgramCompetencyFeignResponseMapper extends FeignResponseMapper<ProgramCompetency, ProgramCompetencyResponse> {

    @Override
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    ProgramCompetency toDomain(ProgramCompetencyResponse response);

    @Override
    List<ProgramCompetency> toDomains(List<ProgramCompetencyResponse> responses);
}