package co.edu.udea.nexum.profile.common.application.handler.impl;

import co.edu.udea.nexum.profile.common.application.dto.request.BaseRequest;
import co.edu.udea.nexum.profile.common.application.dto.response.BaseResponse;
import co.edu.udea.nexum.profile.common.application.handler.BaseCrudHandler;
import co.edu.udea.nexum.profile.common.application.mapper.BaseRequestMapper;
import co.edu.udea.nexum.profile.common.application.mapper.BaseResponseMapper;
import co.edu.udea.nexum.profile.common.domain.api.BaseCrudServicePort;
import co.edu.udea.nexum.profile.common.domain.model.Model;

import java.util.List;

public abstract class BaseCrudHandlerImpl<ID, MODEL extends Model<ID>, RESPONSE extends BaseResponse, REQUEST extends BaseRequest> implements BaseCrudHandler<ID, RESPONSE, REQUEST> {
    @Override
    public RESPONSE save(REQUEST request) {
        MODEL model = getRequestMapper().toDomain(request);
        return getResponseMapper().toResponse(
                getServicePort().save(model)
        );
    }

    @Override
    public List<RESPONSE> findAll() {
        return getResponseMapper().toResponses(
                getServicePort().findAll()
        );
    }

    @Override
    public RESPONSE findById(ID id) {
        return getResponseMapper().toResponse(
                getServicePort().findById(id)
        );
    }

    @Override
    public RESPONSE updateById(ID id, REQUEST request) {
        MODEL model = getRequestMapper().toDomain(request);
        return getResponseMapper().toResponse(
                getServicePort().updateById(id, model)
        );
    }

    @Override
    public RESPONSE deleteById(ID id) {
        return getResponseMapper().toResponse(
                getServicePort().deleteById(id)
        );
    }

    protected abstract BaseCrudServicePort<ID, MODEL> getServicePort();
    protected abstract BaseResponseMapper<MODEL, RESPONSE> getResponseMapper();
    protected abstract BaseRequestMapper<MODEL, REQUEST> getRequestMapper();
}
