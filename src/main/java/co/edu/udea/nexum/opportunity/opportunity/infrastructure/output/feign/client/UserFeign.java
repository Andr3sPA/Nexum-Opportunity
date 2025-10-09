package co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.feign.client;

import co.edu.udea.nexum.opportunity.common.infrastructure.configuration.feign.FeignClientConfiguration;
import co.edu.udea.nexum.opportunity.common.infrastructure.configuration.feign.FeignErrorDecoder;
import co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.feign.dto.response.BasicUserResponse;
import co.edu.udea.nexum.opportunity.security.infrastructure.output.feign.dto.response.AuthenticatedUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(
        name = "UserFeign",
        url = "${nexum.profile.base_url}/users",
        configuration = {FeignClientConfiguration.class, FeignErrorDecoder.class}
)
public interface UserFeign {

    @GetMapping()
    AuthenticatedUserResponse validateToken(@RequestParam("token") String token);

    @GetMapping("/basic")
    BasicUserResponse getUserBasicByAuthId(@RequestParam("authId") UUID authId);
}

