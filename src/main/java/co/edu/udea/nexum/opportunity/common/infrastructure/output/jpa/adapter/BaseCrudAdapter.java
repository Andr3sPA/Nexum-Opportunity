package co.edu.udea.nexum.opportunity.common.infrastructure.output.jpa.adapter;

import co.edu.udea.nexum.opportunity.common.domain.model.Model;

import java.util.List;

public interface BaseCrudAdapter<ID, MODEL extends Model<ID>> {
    MODEL save(MODEL model);
    List<MODEL> findAll();
    MODEL findById(ID id);
    MODEL update(MODEL model);
    void deleteById(ID id);
}
