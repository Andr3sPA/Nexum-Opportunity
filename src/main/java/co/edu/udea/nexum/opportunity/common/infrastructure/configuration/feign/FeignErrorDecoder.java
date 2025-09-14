package co.edu.udea.nexum.profile.common.infrastructure.configuration.feign;

import co.edu.udea.nexum.profile.common.domain.exception.EntityAlreadyExistsException;
import co.edu.udea.nexum.profile.common.domain.exception.EntityNotFoundException;
import co.edu.udea.nexum.profile.common.infrastructure.configuration.feign.dto.FeignExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.core5.http.HttpStatus;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static co.edu.udea.nexum.profile.common.infrastructure.utils.constants.ConfigurationConstants.*;

@Slf4j
@Generated
public class FeignErrorDecoder implements ErrorDecoder {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        FeignExceptionResponse exceptionResponse = null;
        try {
            String msg = new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
            exceptionResponse = parseException(msg, FeignExceptionResponse.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return switch (response.status()) {
            case HttpStatus.SC_NOT_FOUND ->
                    new EntityNotFoundException(
                            exceptionResponse != null ? exceptionResponse.getMessage() : ENTITY_NOT_FOUND,
                            CUSTOM_MESSAGE
                    );
            case HttpStatus.SC_CONFLICT ->
                    new EntityAlreadyExistsException(
                            exceptionResponse != null ? exceptionResponse.getMessage() : ENTITY_NOT_FOUND
                    );
            case HttpStatus.SC_SERVER_ERROR ->
                    new RuntimeException(
                            exceptionResponse != null ? exceptionResponse.getMessage() : SERVER_ERROR
                    );
            default ->
                    new RuntimeException(
                            exceptionResponse != null ? exceptionResponse.getMessage() : UNKNOWN_ERROR
                    );
        };
    }

    private static <T> T parseException(String body, Class<T> clazz) {
        try {
            return objectMapper.readValue(body, clazz);
        } catch (IOException e) {
            log.info("Error al momento de parsear el error {}", body);
            throw new RuntimeException(ANALYZING_ERROR_MESSAGE, e);
        }
    }

}
