package co.edu.udea.nexum.profile.common.infrastructure.utils.context;

import co.edu.udea.nexum.profile.common.domain.utils.annotations.Generated;

@Generated
public class TokenContext {
    private static final ThreadLocal<String> TOKEN = new ThreadLocal<>();

    public static void setToken(String token) {
        TOKEN.set(token);
    }

    public static String getToken() {
        return TOKEN.get();
    }

    public static void clear() {
        TOKEN.remove();
    }
}
