package co.edu.udea.nexum.opportunity.common.application.mapper;

import co.edu.udea.nexum.opportunity.common.application.dto.response.BaseResponse;

import java.util.List;

public interface BaseResponseMapper<MODEL, RESPONSE extends BaseResponse>{
    RESPONSE toResponse(MODEL model);
    List<RESPONSE> toResponses(List<MODEL> models);
}
