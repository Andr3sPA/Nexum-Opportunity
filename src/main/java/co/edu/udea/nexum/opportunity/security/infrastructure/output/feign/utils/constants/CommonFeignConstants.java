package co.edu.udea.nexum.opportunity.security.infrastructure.output.feign.utils.constants;

import co.edu.udea.nexum.opportunity.common.domain.utils.annotations.Generated;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Generated
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonFeignConstants {
    public static final String AUTHENTICATION_FEIGN_NAME = "AUTHENTICATION-FEIGN";
    public static final String AUTHENTICATION_FEIGN_URL = "${nexum.profile.base_url}/auth";
    public static final String AUTHENTICATION_FEIGN_VALIDATE_END_POINT = "/validate";
}
