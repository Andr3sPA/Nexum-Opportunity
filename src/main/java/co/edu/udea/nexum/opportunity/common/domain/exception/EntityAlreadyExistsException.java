package co.edu.udea.nexum.profile.common.domain.exception;

import co.edu.udea.nexum.profile.common.domain.utils.annotations.Generated;
import co.edu.udea.nexum.profile.common.domain.utils.constants.CommonDomainConstants;

@Generated
public class EntityAlreadyExistsException extends NexumException {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }

    public EntityAlreadyExistsException(String className, String attr, String id) {
        super(String.format(
                CommonDomainConstants.ENTITY_WITH_ATTRIBUTE_ALREADY_EXIST_TEMPLATE_MESSAGE,
                className, attr, id
        ));
    }
}