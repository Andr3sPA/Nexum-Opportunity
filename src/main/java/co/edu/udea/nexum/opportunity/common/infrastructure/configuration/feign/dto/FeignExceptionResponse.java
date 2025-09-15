package co.edu.udea.nexum.opportunity.common.infrastructure.configuration.feign.dto;

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
