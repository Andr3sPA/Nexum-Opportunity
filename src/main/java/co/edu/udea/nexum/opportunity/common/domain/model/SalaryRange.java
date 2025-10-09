package co.edu.udea.nexum.opportunity.common.domain.model;

import co.edu.udea.nexum.opportunity.common.domain.utils.annotations.Generated;
import co.edu.udea.nexum.opportunity.common.domain.utils.contracts.BaseBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Generated
public class SalaryRange implements Model<Long>, AuditableModel, OrderableModel {

    private Long id;
    private String salary;
    private Integer order;
    private Boolean active;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdate;

    public SalaryRange(SalaryRangeBuilder builder) {
        this.id = builder.id;
        this.salary = builder.salary;
        this.order = builder.order;
        this.active = builder.active;
        this.creationDate = builder.creationDate;
        this.lastUpdate = builder.lastUpdate;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Override
    public Integer getOrder() {
        return order;
    }

    @Override
    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    @Override
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public static SalaryRangeBuilder builder() {
        return new SalaryRangeBuilder();
    }

    public static class SalaryRangeBuilder implements BaseBuilder<SalaryRange> {
        private Long id;
        private String salary;
        private Integer order;
        private Boolean active;
        private LocalDateTime creationDate;
        private LocalDateTime lastUpdate;

        public SalaryRangeBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public SalaryRangeBuilder salary(String salary) {
            this.salary = salary;
            return this;
        }

        public SalaryRangeBuilder order(Integer order) {
            this.order = order;
            return this;
        }

        public SalaryRangeBuilder active(Boolean active) {
            this.active = active;
            return this;
        }

        public SalaryRangeBuilder creationDate(LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public SalaryRangeBuilder lastUpdate(LocalDateTime lastUpdate) {
            this.lastUpdate = lastUpdate;
            return this;
        }

        @Override
        public SalaryRange build() {
            return new SalaryRange(this);
        }
    }
}