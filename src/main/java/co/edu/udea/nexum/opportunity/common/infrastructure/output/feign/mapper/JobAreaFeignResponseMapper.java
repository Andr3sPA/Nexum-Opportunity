package co.edu.udea.nexum.opportunity.common.infrastructure.output.feign.mapper;

import co.edu.udea.nexum.opportunity.common.domain.model.JobArea;
import co.edu.udea.nexum.opportunity.common.infrastructure.output.feign.dto.response.JobAreaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobAreaFeignResponseMapper extends FeignResponseMapper<JobArea, JobAreaResponse> {

    @Override
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    JobArea toDomain(JobAreaResponse response);

    @Override
    List<JobArea> toDomains(List<JobAreaResponse> responses);
}