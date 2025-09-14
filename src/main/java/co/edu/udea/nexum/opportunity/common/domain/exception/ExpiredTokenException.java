package co.edu.udea.nexum.profile.common.domain.exception;

import co.edu.udea.nexum.profile.common.domain.utils.annotations.Generated;

import static co.edu.udea.nexum.profile.common.domain.utils.constants.CommonDomainConstants.EXPIRED_TOKEN_MESSAGE;

@Generated
public class ExpiredTokenException extends NexumException {
    public ExpiredTokenException() {
        super(EXPIRED_TOKEN_MESSAGE);
    }
}
