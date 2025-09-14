package co.edu.udea.nexum.profile.common.infrastructure.configuration.feign;

import co.edu.udea.nexum.profile.common.domain.utils.annotations.Generated;
import co.edu.udea.nexum.profile.common.infrastructure.utils.context.TokenContext;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static co.edu.udea.nexum.profile.common.infrastructure.utils.constants.ConfigurationConstants.AUTHORIZATION_HEADER;
import static co.edu.udea.nexum.profile.common.infrastructure.utils.constants.ConfigurationConstants.TOKEN_PREFIX;

@Generated
public class FeignBasicInterceptor {
    @Bean
    public RequestInterceptor feignInterceptor() {
        return requestTemplate -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                requestTemplate.header(
                        AUTHORIZATION_HEADER,
                        TOKEN_PREFIX + TokenContext.getToken()
                );
            }
        };
    }
}
