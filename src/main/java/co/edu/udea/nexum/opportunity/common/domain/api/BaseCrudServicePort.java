package co.edu.udea.nexum.opportunity.common.domain.api;

import co.edu.udea.nexum.opportunity.common.domain.model.Model;

import java.util.List;

public interface BaseCrudServicePort<ID, MODEL extends Model<ID>> {
    MODEL findById(ID id);
    MODEL save(MODEL model);
    List<MODEL> findAll();
    MODEL updateById(ID id, MODEL model);
    MODEL deleteById(ID id);
}
