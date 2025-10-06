package co.edu.udea.nexum.opportunity.application.infraestructure.input.rest.v1;

import co.edu.udea.nexum.opportunity.application.application.dto.request.ApplicationRequest;
import co.edu.udea.nexum.opportunity.common.domain.utils.annotations.Generated;
import co.edu.udea.nexum.opportunity.application.application.handler.ApplicationHandler;
import co.edu.udea.nexum.opportunity.application.domain.model.Application;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import static co.edu.udea.nexum.opportunity.application.infraestructure.utils.constants.ApplicationRestConstants.*;

@Generated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = APPLICATION_CONTROLLER_PATH)
@Tag(name = APPLICATION_CONTROLLER_NAME, description = APPLICATION_CONTROLLER_DESCRIPTION)
public class ApplicationController {
    private final ApplicationHandler applicationHandler;

    @Operation(summary = SWAGGER_SAVE_NEW_USER_OPPORTUNITY_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = SWAGGER_CODE_CREATED, description = SWAGGER_NEW_USER_OPPORTUNITY_SAVED_SUCCESSFULLY),
            @ApiResponse(responseCode = SWAGGER_CODE_BAD_REQUEST, description = SWAGGER_ERROR_VALIDATIONS_DO_NOT_PASS)
    })
    @PostMapping
    public ResponseEntity<Application> save(@RequestBody ApplicationRequest application) {
        Application saved = applicationHandler.save(application);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    @GetMapping
    public ResponseEntity<Iterable<Application>> getAll() {
        Iterable<Application> applications = applicationHandler.getAll();
        return ResponseEntity.ok(applications);
    }

}
