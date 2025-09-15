package co.edu.udea.nexum.opportunity.security.infrastructure.output.feign.client;

import co.edu.udea.nexum.opportunity.common.infrastructure.configuration.feign.FeignClientConfiguration;
import co.edu.udea.nexum.opportunity.common.infrastructure.configuration.feign.FeignErrorDecoder;
import co.edu.udea.nexum.opportunity.security.infrastructure.output.feign.dto.response.AuthenticatedUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static co.edu.udea.nexum.opportunity.security.infrastructure.output.feign.utils.constants.CommonFeignConstants.*;

@FeignClient(
        name = AUTHENTICATION_FEIGN_NAME,
        url = AUTHENTICATION_FEIGN_URL,
        configuration = {FeignClientConfiguration.class, FeignErrorDecoder.class}
)
public interface AuthFeign {

    @GetMapping(AUTHENTICATION_FEIGN_VALIDATE_END_POINT)
    AuthenticatedUserResponse validateToken(@RequestParam String token);
}
