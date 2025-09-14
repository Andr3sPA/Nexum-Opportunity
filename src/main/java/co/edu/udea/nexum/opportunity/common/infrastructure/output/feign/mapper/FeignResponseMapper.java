package co.edu.udea.nexum.profile.common.infrastructure.output.feign.mapper;

import co.edu.udea.nexum.profile.common.infrastructure.output.feign.dto.response.FeignResponse;

import java.util.List;

public interface FeignResponseMapper<MODEL, RESPONSE extends FeignResponse>{
    MODEL toDomain(RESPONSE response);
    List<MODEL> toDomains(List<RESPONSE> responses);
}
