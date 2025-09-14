package co.edu.udea.nexum.profile.common.infrastructure.utils;

import co.edu.udea.nexum.profile.common.domain.utils.annotations.Generated;
import co.edu.udea.nexum.profile.common.infrastructure.configuration.advisor.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Generated
public class ExceptionResponseBuilder {
    private ExceptionResponseBuilder(){
        throw new IllegalStateException("Utility class");
    }

    public static ResponseEntity<ExceptionResponse> buildResponse(RuntimeException e, HttpStatus status) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .statusCode(status.value())
                .status(status)
                .timestamp(LocalDateTime.now())
                .message(e.getMessage()).build();
        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);
    }
}