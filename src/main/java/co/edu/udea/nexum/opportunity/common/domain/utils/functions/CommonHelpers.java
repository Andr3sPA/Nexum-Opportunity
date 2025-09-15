package co.edu.udea.nexum.opportunity.common.domain.utils.functions;

import co.edu.udea.nexum.opportunity.common.domain.model.AuditableModel;
import co.edu.udea.nexum.opportunity.common.domain.model.Model;

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

}
