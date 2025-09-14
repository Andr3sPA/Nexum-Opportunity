package co.edu.udea.nexum.profile.common.infrastructure.configuration.advisor;


import co.edu.udea.nexum.profile.common.domain.exception.EntityAlreadyExistsException;
import co.edu.udea.nexum.profile.common.domain.exception.EntityNotFoundException;
import co.edu.udea.nexum.profile.common.domain.exception.NexumException;
import co.edu.udea.nexum.profile.common.domain.utils.annotations.Generated;
import co.edu.udea.nexum.profile.common.infrastructure.configuration.advisor.dto.ExceptionResponse;
import co.edu.udea.nexum.profile.common.infrastructure.configuration.advisor.dto.ValidationExceptionResponse;
import co.edu.udea.nexum.profile.common.infrastructure.utils.ExceptionResponseBuilder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Generated
@ControllerAdvice
public class BasicExceptionAdvisor {

    @ExceptionHandler(NexumException.class)
    public ResponseEntity<ExceptionResponse> handleNexumException(NexumException e) {
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFound(EntityNotFoundException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleEntityAlreadyExists(EntityAlreadyExistsException e){
        return ExceptionResponseBuilder.buildResponse(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler( MethodArgumentNotValidException.class )
    public ResponseEntity<ValidationExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ValidationExceptionResponse exceptionResponse = ValidationExceptionResponse.builder()
                .statusCode(e.getStatusCode().value())
                .status(HttpStatus.resolve(e.getStatusCode().value()))
                .timestamp(LocalDateTime.now())
                .errors(e.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList())
                .message(e.getBody().getDetail()).build();
        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);
    }
}