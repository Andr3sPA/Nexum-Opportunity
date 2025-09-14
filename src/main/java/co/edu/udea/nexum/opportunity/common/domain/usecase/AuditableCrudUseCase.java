package co.edu.udea.nexum.profile.common.domain.usecase;

import co.edu.udea.nexum.profile.common.domain.model.AuditableModel;
import co.edu.udea.nexum.profile.common.domain.model.Model;

import java.time.LocalDateTime;

public abstract class AuditableCrudUseCase<ID, MODEL extends Model<ID> & AuditableModel> extends BaseCrudUseCase<ID, MODEL> {
    @Override
    public MODEL save(MODEL model) {
        LocalDateTime now = LocalDateTime.now();
        model.setCreationDate(now);
        model.setLastUpdate(now);
        return super.save(model);
    }

    @Override
    public MODEL updateById(ID id, MODEL payload) {
        LocalDateTime now = LocalDateTime.now();
        if (payload.getCreationDate() == null)
            payload.setCreationDate(now);
        payload.setLastUpdate(now);

        return super.updateById(id, payload);
    }
}
