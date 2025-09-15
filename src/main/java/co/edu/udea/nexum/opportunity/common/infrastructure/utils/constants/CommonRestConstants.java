package co.edu.udea.nexum.opportunity.common.infrastructure.utils.constants;

import co.edu.udea.nexum.opportunity.common.domain.utils.annotations.Generated;

@Generated
public class CommonRestConstants {
    private CommonRestConstants() {
        throw new IllegalStateException("Utility class");
    }

    // API CODES
    public static final String SWAGGER_CODE_CREATED = "201";
    public static final String SWAGGER_CODE_OK = "200";
    public static final String SWAGGER_CODE_ACCEPTED = "202";
    public static final String SWAGGER_CODE_BAD_REQUEST = "400";
    public static final String SWAGGER_CODE_NOT_FOUND = "404";
    public static final String SWAGGER_CODE_CONFLICT = "409";

    // Validations
    public static final String SWAGGER_ERROR_VALIDATIONS_DO_NOT_PASS = "Validations don't pass";

    // HOME
    public static final String SWAGGER_SUMMARY_GET_HOME = "And endpoint to test if app is running";

    // COMMON CRUD MESSAGES

    public static final String COMMON_ID_PATH = "/{id}";
    public static final String HOME_MESSAGE = "Microservicio de catálogo de Nexum";


}
