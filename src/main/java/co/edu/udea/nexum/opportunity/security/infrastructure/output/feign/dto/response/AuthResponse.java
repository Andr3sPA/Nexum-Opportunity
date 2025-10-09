package co.edu.udea.nexum.opportunity.security.infrastructure.output.feign.dto.response;

import co.edu.udea.nexum.opportunity.common.infrastructure.output.feign.dto.response.FeignResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse implements FeignResponse {
    private String id;
}