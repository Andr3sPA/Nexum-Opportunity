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
   * @throws IllegalStateException if user is not authenticated or is anonymous
   */
  public AuthenticatedUser getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null || !authentication.isAuthenticated()) {
      throw new IllegalStateException("User is not authenticated");
    }

    // Check if this is an anonymous user
    if (authentication.getAuthorities().stream()
        .anyMatch(auth -> auth.getAuthority().equals("ROLE_ANONYMOUS"))) {
      throw new IllegalStateException("Anonymous user cannot be retrieved as authenticated user");
    }

    String token = (String) authentication.getCredentials();
    if (token == null) {
      throw new IllegalStateException("No token found in authentication credentials");
    }

    return authServicePort.validateToken(token);
  }

  /**
   * Checks if the current user is authenticated (not anonymous).
   *
   * @return true if user is authenticated with a valid token, false otherwise
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
