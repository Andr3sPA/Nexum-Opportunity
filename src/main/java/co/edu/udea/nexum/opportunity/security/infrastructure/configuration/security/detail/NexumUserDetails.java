package co.edu.udea.nexum.opportunity.security.infrastructure.configuration.security.detail;

import co.edu.udea.nexum.opportunity.common.domain.utils.annotations.Generated;
import co.edu.udea.nexum.opportunity.common.infrastructure.utils.constants.ConfigurationConstants;
import co.edu.udea.nexum.opportunity.security.domain.api.security.AuthServicePort;
import co.edu.udea.nexum.opportunity.security.domain.model.AuthenticatedUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
@Generated
@RequiredArgsConstructor
public class NexumUserDetails implements UserDetailsService {
    private final AuthServicePort authServicePort;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        AuthenticatedUser authenticatedUser =  authServicePort.validateToken(token);
        Collection<? extends GrantedAuthority> authorities =
                Set.of(new SimpleGrantedAuthority(ConfigurationConstants.ROLE_PREFIX + authenticatedUser.getRole().name()));

        return new User(authenticatedUser.getId().toString(), token, authorities);
    }
}
