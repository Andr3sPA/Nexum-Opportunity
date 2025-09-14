package co.edu.udea.nexum.profile.common.infrastructure.output.jpa.mapper;

import java.util.List;

public interface BaseEntityMapper<MODEL, ENTITY>{
    MODEL toDomain(ENTITY entity);
    List<MODEL> toDomains(List<ENTITY> entities);
    ENTITY toEntity(MODEL model);
    List<ENTITY> toEntities(List<MODEL> models);
}
