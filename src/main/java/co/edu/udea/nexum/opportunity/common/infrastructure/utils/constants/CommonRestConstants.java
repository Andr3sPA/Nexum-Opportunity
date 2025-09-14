package co.edu.udea.nexum.profile.common.infrastructure.utils.constants;

import co.edu.udea.nexum.profile.common.domain.utils.annotations.Generated;

@Generated
public class CommonRestConstants {
    public static final String COMMON_ID_PATH = "/{id}";

    private CommonRestConstants() {
        throw new IllegalStateException("Utility class");
    }

    // API CODES
    public static final String SWAGGER_CODE_OK = "200";
    public static final String SWAGGER_CODE_CREATED = "201";
    public static final String SWAGGER_CODE_ACCEPTED = "202";
    public static final String SWAGGER_CODE_BAD_REQUEST = "400";
    public static final String SWAGGER_CODE_UNAUTHORIZED = "401";
    public static final String SWAGGER_CODE_FORBIDDEN = "403";
    public static final String SWAGGER_CODE_NOT_FOUND = "404";
    public static final String SWAGGER_CODE_CONFLICT = "409";

    // Validations
    public static final String SWAGGER_ERROR_VALIDATIONS_DO_NOT_PASS = "Validations don't pass";

    // HOME
    public static final String SWAGGER_SUMMARY_GET_HOME = "And endpoint to test if app is running";

    // COMMON CRUD MESSAGES

}
