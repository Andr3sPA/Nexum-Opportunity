package co.edu.udea.nexum.profile.common.domain.utils.constants;

import co.edu.udea.nexum.profile.common.domain.utils.annotations.Generated;

@Generated
public class CommonDomainConstants {


    private CommonDomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    // Exception Message
    public static final String ENTITY_NOT_FOUND_TEMPLATE_MESSAGE = "No se ha encontrado una entidad del tipo '%s'";
    public static final String ENTITY_WITH_ATTRIBUTE_NOT_FOUND_TEMPLATE_MESSAGE = "No se ha encontrado una entidad del tipo '%s' con %s '%s'";
    public static final String ENTITY_WITH_ATTRIBUTE_ALREADY_EXIST_TEMPLATE_MESSAGE = "Ya existe una entidad del tipo '%s' con %s '%s'";
    public static final String FORBIDDEN_RESOURCE_ACCESS_ERROR_MESSAGE = "El usuario ha intentado acceder o modificar un recurso que no le pertenece";

    public static final String INVALID_TOKEN_MESSAGE = "El token proporcionado no es válido";
    public static final String EXPIRED_TOKEN_MESSAGE = "El token proporcionado ha expirado";

    public static final Integer DEFAULT_PAGE_NUMBER = 0;
    public static final Integer DEFAULT_PAGE_SIZE = 10;
    public static final Boolean DEFAULT_ASCENDING = true;

    public static final int OUTDATED_THRESHOLD = 30*6;

}
