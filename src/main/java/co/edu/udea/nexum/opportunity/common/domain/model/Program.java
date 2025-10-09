package co.edu.udea.nexum.opportunity.common.domain.model;

import co.edu.udea.nexum.opportunity.common.domain.utils.annotations.Generated;
import co.edu.udea.nexum.opportunity.common.domain.utils.contracts.BaseBuilder;

@Generated
public class Program implements Model<Long> {

    private Long id;
    private String name;
    private String sniesCode;

    public Program(ProgramBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.sniesCode = builder.sniesCode;
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

    public String getSniesCode() {
        return sniesCode;
    }

    public void setSniesCode(String sniesCode) {
        this.sniesCode = sniesCode;
    }

    public static ProgramBuilder builder() {
        return new ProgramBuilder();
    }

    public static class ProgramBuilder implements BaseBuilder<Program> {
        private Long id;
        private String name;
        private String sniesCode;

        public ProgramBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ProgramBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProgramBuilder sniesCode(String sniesCode) {
            this.sniesCode = sniesCode;
            return this;
        }

        @Override
        public Program build() {
            return new Program(this);
        }
    }
}