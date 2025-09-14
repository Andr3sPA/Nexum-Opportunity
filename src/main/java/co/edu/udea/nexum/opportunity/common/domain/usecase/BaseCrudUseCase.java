package co.edu.udea.nexum.profile.common.domain.usecase;

import co.edu.udea.nexum.profile.common.domain.api.BaseCrudServicePort;
import co.edu.udea.nexum.profile.common.domain.exception.EntityNotFoundException;
import co.edu.udea.nexum.profile.common.domain.model.Model;
import co.edu.udea.nexum.profile.common.domain.spi.BaseCrudPersistencePort;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public abstract class BaseCrudUseCase<ID, MODEL extends Model<ID>> implements BaseCrudServicePort<ID, MODEL> {

    @Override
    public MODEL save(MODEL model) {
        validateEntity(null, model);
        MODEL enrichedModel = loadAssociations(model);
        log.info("saving {}: {}", getModelClassName(), enrichedModel);
        return getPersistencePort().save(enrichedModel);
    }

    @Override
    public List<MODEL> findAll() {
        return getPersistencePort().findAll();
    }

    @Override
    public MODEL findById(ID id) {
        MODEL domainModel = getPersistencePort().findById(id);
        if (domainModel == null)
            throw new EntityNotFoundException(getModelClassName());
        return domainModel;
    }

    @Override
    public MODEL updateById(ID id, MODEL payload) {
        MODEL target = findById(id);
        validateEntity(id, payload);
        MODEL patched = patch(target, payload);
        MODEL enrichedPatched = loadAssociations(patched);
        log.info("updating {}: {}", getModelClassName(), patched);
        return getPersistencePort().update(enrichedPatched);
    }

    @Override
    public MODEL deleteById(ID id) {
        MODEL domainModel = findById(id);
        getPersistencePort().deleteById(id);
        log.info("deleting {}: {}", getModelClassName(), domainModel);
        return domainModel;
    }

    protected MODEL loadAssociations(MODEL model) {
        return model;
    }

    protected abstract BaseCrudPersistencePort<ID, MODEL> getPersistencePort();

    protected abstract String getModelClassName();

    protected abstract MODEL patch(MODEL original, final MODEL patch);

    protected abstract void validateEntity(ID currentId, MODEL model);
}
