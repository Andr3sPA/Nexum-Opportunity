package co.edu.udea.nexum.opportunity.common.infrastructure.configuration.documentation;

import co.edu.udea.nexum.opportunity.common.domain.utils.annotations.Generated;
import co.edu.udea.nexum.opportunity.common.infrastructure.utils.constants.ConfigurationConstants;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Generated
@Configuration
public class OpenApiConfiguration {
    
    private static final String BEARER_TOKEN_SCHEME_NAME = "bearerAuth";
    
    @Bean
    public OpenAPI customOpenApi(
            @Value("${app.name}") String appName,
            @Value("${app.description}") String appDescription,
            @Value("${app.version}") String appVersion
    ){
        return new OpenAPI()
                .info(new Info()
                        .title(appName)
                        .description(appDescription)
                        .version(appVersion)
                        .termsOfService(ConfigurationConstants.OPENAPI_TERMS_OF_SERVICE)
                        .license(new License()
                                .name(ConfigurationConstants.OPENAPI_LICENSE_NAME)
                                .url(ConfigurationConstants.OPENAPI_LICENSE_URL)
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList(BEARER_TOKEN_SCHEME_NAME))
                .components(new Components()
                        .addSecuritySchemes(BEARER_TOKEN_SCHEME_NAME, new SecurityScheme()
                                .name(BEARER_TOKEN_SCHEME_NAME)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Enter your JWT token to access the API endpoints")
                        )
                );
    }
}