package co.edu.udea.nexum.opportunity.useropportunity.infraestructure.input.rest.v1;

import co.edu.udea.nexum.opportunity.common.domain.utils.annotations.Generated;
import co.edu.udea.nexum.opportunity.useropportunity.application.handler.UserOpportunityHandler;
import co.edu.udea.nexum.opportunity.useropportunity.domain.model.UserOpportunity;
import co.edu.udea.nexum.opportunity.useropportunity.domain.usecase.UserOpportunityUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import static co.edu.udea.nexum.opportunity.useropportunity.infraestructure.utils.constants.UserOpportunityRestConstants.*;

@Generated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = USER_OPPORTUNITY_CONTROLLER_PATH)
@Tag(name = USER_OPPORTUNITY_CONTROLLER_NAME, description = USER_OPPORTUNITY_CONTROLLER_DESCRIPTION)
public class UserOpportunityController {
    private final UserOpportunityHandler userOpportunityHandler;

    @Operation(summary = SWAGGER_SAVE_NEW_USER_OPPORTUNITY_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = SWAGGER_CODE_CREATED, description = SWAGGER_NEW_USER_OPPORTUNITY_SAVED_SUCCESSFULLY),
            @ApiResponse(responseCode = SWAGGER_CODE_BAD_REQUEST, description = SWAGGER_ERROR_VALIDATIONS_DO_NOT_PASS)
    })
    @PostMapping
    public ResponseEntity<UserOpportunity> save(@RequestBody UserOpportunity userOpportunity) {
        UserOpportunity saved = userOpportunityHandler.save(userOpportunity);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


}
