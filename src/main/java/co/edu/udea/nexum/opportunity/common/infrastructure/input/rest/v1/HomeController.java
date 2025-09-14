package co.edu.udea.nexum.profile.common.infrastructure.input.rest.v1;

import co.edu.udea.nexum.profile.common.domain.utils.annotations.Generated;
import co.edu.udea.nexum.profile.common.infrastructure.utils.constants.CommonRestConstants;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Generated
@RestController
@RequestMapping("/v1/home")
public class HomeController {

    @Operation(summary = CommonRestConstants.SWAGGER_SUMMARY_GET_HOME)
    @GetMapping
    public String home(){
        return "Microservicio de perfiles de Nexum";
    }
}
