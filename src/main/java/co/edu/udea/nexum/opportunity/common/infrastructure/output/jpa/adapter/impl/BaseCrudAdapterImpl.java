package co.edu.udea.nexum.profile.common.infrastructure.output.jpa.adapter.impl;

import co.edu.udea.nexum.profile.common.domain.model.Model;
import co.edu.udea.nexum.profile.common.infrastructure.output.jpa.adapter.BaseCrudAdapter;
import co.edu.udea.nexum.profile.common.infrastructure.output.jpa.entity.NexumEntity;
import co.edu.udea.nexum.profile.common.infrastructure.output.jpa.mapper.BaseEntityMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.StreamSupport;

public abstract class BaseCrudAdapterImpl<ID, MODEL extends Model<ID>, ENTITY extends NexumEntity<ID>>
        implements BaseCrudAdapter<ID, MODEL> {

    @Override
    @Transactional
    public MODEL save(MODEL model) {
        ENTITY entity = getMapper().toEntity(model);
        return getMapper().toDomain(
                getRepository().save(entity)
        );
    }

    @Override
    @Transactional
    public List<MODEL> findAll() {
        Iterable<ENTITY> iterable = getRepository().findAll();
        List<ENTITY> entities = StreamSupport.stream(iterable.spliterator(), false).toList();
        return getMapper().toDomains(
                entities
        );
    }

    @Override
    @Transactional
    public MODEL findById(ID id) {
        return getMapper().toDomain(
                getRepository().findById(id).orElse(null)
        );
    }

    @Override
    @Transactional
    public MODEL update(MODEL model) {
        ENTITY entity = getMapper().toEntity(model);
        return getMapper().toDomain(
                getRepository().save(entity)
        );
    }

    @Override
    @Transactional
    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }

    protected abstract BaseEntityMapper<MODEL, ENTITY> getMapper();
    protected abstract CrudRepository<ENTITY, ID> getRepository();
}
