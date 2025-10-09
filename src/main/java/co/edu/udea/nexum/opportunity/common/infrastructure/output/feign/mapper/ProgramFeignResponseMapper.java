package co.edu.udea.nexum.opportunity.common.infrastructure.output.feign.mapper;

import co.edu.udea.nexum.opportunity.common.domain.model.Program;
import co.edu.udea.nexum.opportunity.common.infrastructure.output.feign.dto.response.ProgramResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProgramFeignResponseMapper extends FeignResponseMapper<Program, ProgramResponse> {

    @Override
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "sniesCode", source = "sniesCode")
    Program toDomain(ProgramResponse response);

    @Override
    List<Program> toDomains(List<ProgramResponse> responses);
}