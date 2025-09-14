package co.edu.udea.nexum.profile.common.application.utils.validators;

import co.edu.udea.nexum.profile.common.application.utils.constraints.AllowedValues;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class AllowedValuesValidator implements ConstraintValidator<AllowedValues, String> {
    private List<String> allowedValues;

    @Override
    public void initialize(AllowedValues constraintAnnotation) {
        allowedValues = Arrays.asList(constraintAnnotation.values());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) return false;
        return allowedValues.contains(value);
    }
}
