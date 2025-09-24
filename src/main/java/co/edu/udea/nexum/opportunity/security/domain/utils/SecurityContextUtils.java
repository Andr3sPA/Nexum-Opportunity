package co.edu.udea.nexum.opportunity.security.domain.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import co.edu.udea.nexum.opportunity.security.domain.api.security.AuthServicePort;
import co.edu.udea.nexum.opportunity.security.domain.model.AuthenticatedUser;
import co.edu.udea.nexum.opportunity.security.domain.utils.enums.RoleName;
import lombok.RequiredArgsConstructor;

/**
 * Utility class for handling security context operations.
 */
@Component
@RequiredArgsConstructor
public class SecurityContextUtils {
    
    private final AuthServicePort authServicePort;
    
    /**
     * Gets the current authenticated user from the security context.
     * 
     * @return the authenticated user
     * @throws IllegalStateException if user is not authenticated
     */
    public AuthenticatedUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("User is not authenticated");
        }
        
        String token = (String) authentication.getCredentials();
        return authServicePort.validateToken(token);
    }
    
    /**
     * Checks if the current user is authenticated.
     * 
     * @return true if user is authenticated, false otherwise
     */
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }

    /**
     * Checks if the current authenticated user has the specified role.
     * 
     * @param role the role to check
     * @return true if user has the role, false otherwise
     */
    public boolean currentUserHasRole(RoleName role) {
        AuthenticatedUser currentUser = getCurrentUser();
        return currentUser != null && currentUser.getRole() == role;
    }

    /**
     * Checks if the current authenticated user has the ADMIN role.
     *
     * @return true if user is admin, false otherwise
     */
    public boolean currentUserIsAdmin() {
        return currentUserHasRole(RoleName.ADMIN);
    }
}
