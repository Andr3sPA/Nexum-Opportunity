package co.edu.udea.nexum.profile.common.infrastructure.configuration.documentation;

import co.edu.udea.nexum.profile.common.domain.utils.annotations.Generated;
import co.edu.udea.nexum.profile.common.infrastructure.utils.constants.ConfigurationConstants;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Generated
@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI customOpenApi(
            @Value("${app.name}") String appName,
            @Value("${app.description}") String appDescription,
            @Value("${app.version}") String appVersion
    ){
        return new OpenAPI().info(new Info()
                .title(appName)
                .description(appDescription)
                .version(appVersion)
                .termsOfService(ConfigurationConstants.OPENAPI_TERMS_OF_SERVICE)
                .license(new License()
                        .name(ConfigurationConstants.OPENAPI_LICENSE_NAME)
                        .url(ConfigurationConstants.OPENAPI_LICENSE_URL)
                )
        );
    }
}