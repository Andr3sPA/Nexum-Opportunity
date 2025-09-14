package co.edu.udea.nexum.profile.common.infrastructure.input.rest;

import co.edu.udea.nexum.profile.common.application.dto.request.BaseRequest;
import co.edu.udea.nexum.profile.common.application.dto.response.BaseResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BaseCrudController<ID, RESPONSE extends BaseResponse, REQUEST extends BaseRequest> {
    ResponseEntity<RESPONSE> save(REQUEST request);
    ResponseEntity<List<RESPONSE>> findAll();
    ResponseEntity<RESPONSE>  findById(ID id);
    ResponseEntity<RESPONSE> updateById(ID id, REQUEST request);
    ResponseEntity<RESPONSE> deleteById(ID id);
}
