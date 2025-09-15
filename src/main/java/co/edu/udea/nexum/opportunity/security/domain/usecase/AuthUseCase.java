package co.edu.udea.nexum.opportunity.security.domain.usecase;

import co.edu.udea.nexum.opportunity.common.domain.utils.annotations.Generated;
import co.edu.udea.nexum.opportunity.security.domain.api.security.AuthServicePort;
import co.edu.udea.nexum.opportunity.security.domain.model.AuthenticatedUser;
import co.edu.udea.nexum.opportunity.security.domain.spi.security.AuthPersistencePort;

@Generated
public class AuthUseCase implements AuthServicePort {
    private final AuthPersistencePort authPersistencePort;

    public AuthUseCase(AuthPersistencePort authPersistencePort) {
        this.authPersistencePort = authPersistencePort;
    }

    @Override
    public AuthenticatedUser validateToken(String token) {
        return authPersistencePort.validateToken(token);
    }
}
