package co.edu.udea.nexum.profile.common.domain.utils.functions;

import co.edu.udea.nexum.profile.common.domain.exception.EntityNotFoundException;
import co.edu.udea.nexum.profile.common.domain.model.AuditableModel;
import co.edu.udea.nexum.profile.common.domain.model.Model;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
public class CommonHelpers {

    public static final String VALIDATING_EXISTENCE_OF_ENTITY = "Validating existence of {} with id '{}'";

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


    public static void replaceIfNotNull(String value, Consumer<String> setter) {
        if (value != null && !value.isEmpty()) {
            setter.accept(value);
        }
    }


    public static <T> void replaceIfNotNull(List<T> value, Consumer<List<T>> setter) {
        if (value != null) {
            setter.accept(value);
        }
    }

    public static <T, REF extends Model<T>> void validateCatalogReference(REF reference, Function<T, REF> finder, Class<?> clazz) {
        log.info(VALIDATING_EXISTENCE_OF_ENTITY, clazz.getSimpleName(), reference.getId());
        if (finder.apply(reference.getId()) == null) throw new EntityNotFoundException(clazz.getSimpleName());
    }

}
