package co.edu.udea.nexum.profile.common.infrastructure.output.feign.mapper;

import co.edu.udea.nexum.profile.common.infrastructure.output.feign.dto.request.FeignRequest;

import java.util.List;

public interface FeignRequestMapper<MODEL, REQUEST extends FeignRequest>{
    REQUEST toRequest(MODEL entity);
    List<REQUEST> toRequest(List<MODEL> entities);
}
