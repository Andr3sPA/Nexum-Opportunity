package co.edu.udea.nexum.profile.common.infrastructure.configuration.feign.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeignExceptionResponse {
    private Integer statusCode;
    private String status;
    private String message;
    private String timestamp;
}
