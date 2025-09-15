package co.edu.udea.nexum.opportunity.security.domain.spi.security;

import co.edu.udea.nexum.opportunity.security.domain.model.AuthenticatedUser;

public interface AuthPersistencePort {
    AuthenticatedUser validateToken(String token);
}
