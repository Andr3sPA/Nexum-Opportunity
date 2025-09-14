package co.edu.udea.nexum.profile.common.domain.exception;

import co.edu.udea.nexum.profile.common.domain.utils.annotations.Generated;
import co.edu.udea.nexum.profile.common.domain.utils.constants.CommonDomainConstants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Generated
public class EntityNotFoundException extends NexumException {
    public EntityNotFoundException(String message, boolean custom) {
        super(message);
        if (custom) log.error(message);
    }

    public EntityNotFoundException(String className){
      super(String.format(
              CommonDomainConstants.ENTITY_NOT_FOUND_TEMPLATE_MESSAGE,
              className
      ));
    }

    public EntityNotFoundException(String classname, String attr, String id){
        super(String.format(
                CommonDomainConstants.ENTITY_WITH_ATTRIBUTE_NOT_FOUND_TEMPLATE_MESSAGE,
                classname, attr, id
        ));
    }
}
