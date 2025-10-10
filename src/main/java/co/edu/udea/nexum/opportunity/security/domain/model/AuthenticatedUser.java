package co.edu.udea.nexum.opportunity.security.domain.model;


import co.edu.udea.nexum.opportunity.common.domain.model.Model;
import co.edu.udea.nexum.opportunity.common.domain.utils.annotations.Generated;
import co.edu.udea.nexum.opportunity.common.domain.utils.contracts.BaseBuilder;
import co.edu.udea.nexum.opportunity.security.domain.utils.enums.RoleName;

import java.util.UUID;

@Generated
public class AuthenticatedUser implements Model<UUID> {
    private UUID id;
    private RoleName role;
    private String token;
    private UUID userId;

    @Generated
    public AuthenticatedUser(AuthenticatedUserBuilder builder) {
        this.id = builder.id;
        this.role = builder.role;
        this.token = builder.token;
        this.userId = builder.userId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public RoleName getRole() {
        return role;
    }

    public void setRole(RoleName role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public static AuthenticatedUserBuilder builder(){
        return new AuthenticatedUserBuilder();
    }

    @Generated
    public static class AuthenticatedUserBuilder implements BaseBuilder<AuthenticatedUser> {
        private UUID id;
        private RoleName role;
        private String token;
        private UUID userId;

        public AuthenticatedUserBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public AuthenticatedUserBuilder role(RoleName role) {
            this.role = role;
            return this;
        }

        public AuthenticatedUserBuilder token(String token) {
            this.token = token;
            return this;
        }

        public AuthenticatedUserBuilder userId(UUID userId) {
            this.userId = userId;
            return this;
        }

        @Override
        public AuthenticatedUser build() {
            return new AuthenticatedUser(this);
        }
    }
}
