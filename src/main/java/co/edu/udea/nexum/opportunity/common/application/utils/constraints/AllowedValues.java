package co.edu.udea.nexum.profile.common.application.utils.constraints;


import co.edu.udea.nexum.profile.common.application.utils.validators.AllowedValuesValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AllowedValuesValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface AllowedValues {
    String message() default "value is not allowed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};


    String[] values();
}
