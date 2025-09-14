package co.edu.udea.nexum.profile.common.infrastructure.output.jpa.mapper;

import co.edu.udea.nexum.profile.common.domain.utils.annotations.Generated;
import co.edu.udea.nexum.profile.common.domain.utils.pagination.PaginationData;
import co.edu.udea.nexum.profile.common.infrastructure.output.jpa.utils.PaginationJpa;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Generated
@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaginationDataMapper {
    PaginationJpa toJPA(PaginationData paginationData);
}
