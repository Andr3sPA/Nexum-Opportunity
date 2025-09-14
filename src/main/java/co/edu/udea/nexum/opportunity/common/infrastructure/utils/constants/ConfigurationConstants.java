package co.edu.udea.nexum.profile.common.infrastructure.utils.constants;


import co.edu.udea.nexum.profile.common.domain.utils.annotations.Generated;

@Generated
public class ConfigurationConstants {

    private ConfigurationConstants() {
        throw new IllegalStateException("Utility Class");
    }

    // Documentation
    public static final String OPENAPI_TERMS_OF_SERVICE = "https://swagger.io/terms/";
    public static final String OPENAPI_LICENSE_NAME = "Apache 2.0";
    public static final String OPENAPI_LICENSE_URL = "https://springdoc.org";

    // Feign
    public static final String ENTITY_NOT_FOUND = "La entidad ya existe";
    public static final String UNKNOWN_ERROR = "Error desconocido al conectar con otro microservicio";
    public static final String SERVER_ERROR = "Hubo un error en otro servicio";
    public static final boolean CUSTOM_MESSAGE = true;
    public static final String ANALYZING_ERROR_MESSAGE = "Problema al intentar analizar el error";

    // Security
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String ROLE_PREFIX = "ROLE_";
    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String ALLOWED_METHOD_PATCH = "PATCH";
    public static final String ALLOWED_METHOD_POST = "POST";
    public static final String ALLOWED_METHOD_GET = "GET";
    public static final String ALLOWED_METHOD_PUT = "PUT";
    public static final String ALLOWED_METHOD_DELETE = "DELETE";
    public static final String ALLOWED_METHOD_OPTIONS = "OPTIONS";
    public static final String ALLOWED_HEADERS_ALL = "*";
    public static final boolean ALLOW_CREDENTIALS = true;
    public static final String AUTH_PATH = "/v1/auth/**";
    public static final String CORS_CONFIGURATION_PATHS_PATTERN = "/**";


}