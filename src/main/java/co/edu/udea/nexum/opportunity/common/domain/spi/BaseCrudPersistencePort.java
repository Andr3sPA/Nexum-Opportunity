package co.edu.udea.nexum.profile.common.domain.spi;

import co.edu.udea.nexum.profile.common.domain.model.Model;

import java.util.List;

public interface BaseCrudPersistencePort<ID, MODEL extends Model<ID>> {
    MODEL save(MODEL model);
    List<MODEL> findAll();
    MODEL findById(ID id);
    MODEL update(MODEL model);
    void deleteById(ID id);
}
