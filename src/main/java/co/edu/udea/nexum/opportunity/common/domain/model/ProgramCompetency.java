package co.edu.udea.nexum.opportunity.common.domain.model;

import co.edu.udea.nexum.opportunity.common.domain.utils.annotations.Generated;
import co.edu.udea.nexum.opportunity.common.domain.utils.contracts.BaseBuilder;

@Generated
public class ProgramCompetency implements Model<Long> {

    private Long id;
    private String name;
    private String description;

    public ProgramCompetency(ProgramCompetencyBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static ProgramCompetencyBuilder builder() {
        return new ProgramCompetencyBuilder();
    }

    public static class ProgramCompetencyBuilder implements BaseBuilder<ProgramCompetency> {
        private Long id;
        private String name;
        private String description;

        public ProgramCompetencyBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ProgramCompetencyBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProgramCompetencyBuilder description(String description) {
            this.description = description;
            return this;
        }

        @Override
        public ProgramCompetency build() {
            return new ProgramCompetency(this);
        }
    }
}