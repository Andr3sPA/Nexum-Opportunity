package co.edu.udea.nexum.opportunity.security.infrastructure.output.feign.dto.response;

import co.edu.udea.nexum.opportunity.common.infrastructure.output.feign.dto.response.FeignResponse;
import co.edu.udea.nexum.opportunity.security.domain.utils.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticatedUserResponse implements FeignResponse {
    private UUID id;
    private RoleName role;
    private String email;
    private String token;
}
