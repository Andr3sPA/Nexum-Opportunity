package co.edu.udea.nexum.opportunity.common.domain.utils.functions;

import co.edu.udea.nexum.opportunity.common.domain.model.AuditableModel;
import co.edu.udea.nexum.opportunity.common.domain.model.Model;

import java.lang.reflect.Field;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class CommonHelpers {

    public static <ID, MODEL extends Model<ID> & AuditableModel> MODEL updateOrCreateNewIfOutdated(
            MODEL enrichedModel,
            Duration outdatedThreshold,
            Function<MODEL, MODEL> saveFunction,
            Function<MODEL, MODEL> updateFunction
    ) {
        LocalDateTime now = LocalDateTime.now();
        boolean isOutdated = enrichedModel.getCreationDate().isBefore(now.minus(outdatedThreshold));

        if (isOutdated) {
            enrichedModel.setId(null);
            enrichedModel.setCreationDate(now);
        }

        enrichedModel.setLastUpdate(now);
        return isOutdated ? saveFunction.apply(enrichedModel) : updateFunction.apply(enrichedModel);
    }


    public static <T> void replaceIfNotNull(T value, Consumer<T> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }


    public static <T> void replaceIfNotNull(List<T> value, Consumer<List<T>> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }

    /**
     * Updates fields in the target object with non-null values from the source object.
     * This method uses reflection to copy all non-null fields from source to target.
     * 
     * @param target The object to be updated
     * @param source The object containing the new values
     * @param <T> The type of the objects
     */
    public static <T> void replaceIfNotNull(T target, T source) {
        if (target == null || source == null) {
            return;
        }
        
        Class<?> clazz = target.getClass();
        
        // Process all declared fields
        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                try {
                    field.setAccessible(true);
                    Object sourceValue = field.get(source);
                    
                    // Only update if source value is not null
                    if (sourceValue != null) {
                        field.set(target, sourceValue);
                    }
                } catch (IllegalAccessException e) {
                    // Log the error but continue processing other fields
                    System.err.println("Failed to copy field: " + field.getName() + " - " + e.getMessage());
                }
            }
            clazz = clazz.getSuperclass();
        }
    }

}
