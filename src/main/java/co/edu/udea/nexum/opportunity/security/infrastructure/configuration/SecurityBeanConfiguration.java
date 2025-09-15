package co.edu.udea.nexum.opportunity.security.infrastructure.configuration;

import co.edu.udea.nexum.opportunity.common.domain.utils.annotations.Generated;
import co.edu.udea.nexum.opportunity.security.domain.api.security.AuthServicePort;
import co.edu.udea.nexum.opportunity.security.domain.spi.security.AuthPersistencePort;
import co.edu.udea.nexum.opportunity.security.domain.usecase.AuthUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Generated
@Configuration
public class SecurityBeanConfiguration {
    @Bean
    public AuthServicePort authServicePort(AuthPersistencePort authPersistencePort) {
        return new AuthUseCase(authPersistencePort);
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }
}
