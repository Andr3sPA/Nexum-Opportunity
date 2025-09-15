package co.edu.udea.nexum.opportunity.common.application.mapper;

import co.edu.udea.nexum.opportunity.common.application.dto.request.BaseRequest;

import java.util.List;

public interface BaseRequestMapper<MODEL, REQUEST extends BaseRequest>{
    MODEL toDomain(REQUEST entity);
    List<MODEL> toDomains(List<REQUEST> entities);
}
