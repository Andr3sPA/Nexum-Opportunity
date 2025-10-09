package co.edu.udea.nexum.opportunity.common.domain.model;

import co.edu.udea.nexum.opportunity.common.domain.utils.annotations.Generated;
import co.edu.udea.nexum.opportunity.common.domain.utils.contracts.BaseBuilder;

@Generated
public class JobArea implements Model<Long> {

    private Long id;
    private String name;
    private String description;

    public JobArea(JobAreaBuilder builder) {
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

    public static JobAreaBuilder builder() {
        return new JobAreaBuilder();
    }

    public static class JobAreaBuilder implements BaseBuilder<JobArea> {
        private Long id;
        private String name;
        private String description;

        public JobAreaBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public JobAreaBuilder name(String name) {
            this.name = name;
            return this;
        }

        public JobAreaBuilder description(String description) {
            this.description = description;
            return this;
        }

        @Override
        public JobArea build() {
            return new JobArea(this);
        }
    }
}