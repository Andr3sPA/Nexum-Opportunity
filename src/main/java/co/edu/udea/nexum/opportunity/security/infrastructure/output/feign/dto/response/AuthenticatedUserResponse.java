package co.edu.udea.nexum.opportunity.security.infrastructure.output.feign.dto.response;

import co.edu.udea.nexum.opportunity.common.infrastructure.output.feign.dto.response.FeignResponse;
import co.edu.udea.nexum.opportunity.security.domain.utils.enums.RoleName;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AuthenticatedUserResponse implements FeignResponse {
    private UUID id;
    private RoleName role;
    private String token;
}
