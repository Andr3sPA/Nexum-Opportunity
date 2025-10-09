package co.edu.udea.nexum.opportunity.common.infrastructure.configuration.feign;

import co.edu.udea.nexum.opportunity.common.domain.utils.annotations.Generated;
import feign.Feign;
import feign.Logger;
import feign.QueryMapEncoder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static co.edu.udea.nexum.opportunity.common.infrastructure.utils.constants.ConfigurationConstants.AUTHORIZATION_HEADER;

@Generated
@Configuration
public class FeignClientConfiguration {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public QueryMapEncoder feignQueryMapEncoder(FeignQueryBuilder feignQueryBuilder) {
        Feign.builder().queryMapEncoder(feignQueryBuilder);
        return feignQueryBuilder;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                String authHeader = request.getHeader(AUTHORIZATION_HEADER);
                if (authHeader != null) {
                    template.header(AUTHORIZATION_HEADER, authHeader);
                }
            }
        };
    }

    @Bean
    public RequestInterceptor jwtRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (requestAttributes != null) {
                    HttpServletRequest request = requestAttributes.getRequest();
                    String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
                    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                        template.header(AUTHORIZATION_HEADER, authorizationHeader);
                    }
                }
            }
        };
    }

/*
 */
}