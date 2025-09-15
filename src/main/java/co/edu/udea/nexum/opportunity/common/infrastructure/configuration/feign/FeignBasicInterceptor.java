package co.edu.udea.nexum.opportunity.common.infrastructure.configuration.feign;

import co.edu.udea.nexum.opportunity.common.domain.utils.annotations.Generated;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static co.edu.udea.nexum.opportunity.common.infrastructure.utils.constants.ConfigurationConstants.AUTHORIZATION_HEADER;
import static co.edu.udea.nexum.opportunity.common.infrastructure.utils.constants.ConfigurationConstants.TOKEN_PREFIX;

@Generated
public class FeignBasicInterceptor {
    @Bean
    public RequestInterceptor feignInterceptor() {
        return requestTemplate -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                requestTemplate.header(
                        AUTHORIZATION_HEADER,
                        TOKEN_PREFIX + authentication.getCredentials().toString()
                );
            }
        };
    }
}
