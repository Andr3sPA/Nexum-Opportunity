package co.edu.udea.nexum.opportunity.security.domain.utils.enums;

import co.edu.udea.nexum.opportunity.common.domain.utils.annotations.Generated;

@Generated
public enum RoleName {
    /**
     * Someone with Admin role can do anything in the system
     */
    ADMIN,
    /**
     * An administrative can add, read, modify and filter graduates, but cannot remove it
     */
    ADMINISTRATIVE,
    /**
     * A pre graduate only can see their own information
     */
    PRE_GRADUATE,
    /**
     * A graduate can modify their information and add updates
     */
    GRADUATE,
    /**
     * A Dean can list and filter to search graduates
     */
    DEAN,
    /**
     * An employer can search and hire graduates
     */
    EMPLOYER,
}
