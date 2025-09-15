package co.edu.udea.nexum.opportunity.security.domain.api.security;

import co.edu.udea.nexum.opportunity.security.domain.model.AuthenticatedUser;

public interface AuthServicePort {
    AuthenticatedUser validateToken(String token);
}
