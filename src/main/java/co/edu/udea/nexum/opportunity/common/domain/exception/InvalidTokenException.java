package co.edu.udea.nexum.profile.common.domain.exception;

import co.edu.udea.nexum.profile.common.domain.utils.annotations.Generated;

import static co.edu.udea.nexum.profile.common.domain.utils.constants.CommonDomainConstants.INVALID_TOKEN_MESSAGE;

@Generated
public class InvalidTokenException extends NexumException {

  public InvalidTokenException() {
        super(INVALID_TOKEN_MESSAGE);
    }
}
