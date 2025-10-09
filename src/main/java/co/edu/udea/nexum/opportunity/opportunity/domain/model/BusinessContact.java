package co.edu.udea.nexum.opportunity.opportunity.domain.model;

import co.edu.udea.nexum.opportunity.common.domain.model.AuditableModel;
import co.edu.udea.nexum.opportunity.common.domain.model.Model;
import co.edu.udea.nexum.opportunity.common.domain.utils.contracts.BaseBuilder;

import java.time.LocalDateTime;

public class BusinessContact implements Model<Long>, AuditableModel {

    private Long id;
    private String businessName;
    private String contactName;
    private String businessEmail;
    private String businessPhone;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdate;

    public BusinessContact(BusinessContactBuilder builder) {
        this.id = builder.id;
        this.businessName = builder.businessName;
        this.contactName = builder.contactName;
        this.businessEmail = builder.businessEmail;
        this.businessPhone = builder.businessPhone;
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

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
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

    public static BusinessContactBuilder builder() {
        return new BusinessContactBuilder();
    }

    public static class BusinessContactBuilder implements BaseBuilder<BusinessContact> {
        private Long id;
        private String businessName;
        private String contactName;
        private String businessEmail;
        private String businessPhone;
        private LocalDateTime creationDate;
        private LocalDateTime lastUpdate;

        public BusinessContactBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public BusinessContactBuilder businessName(String businessName) {
            this.businessName = businessName;
            return this;
        }

        public BusinessContactBuilder contactName(String contactName) {
            this.contactName = contactName;
            return this;
        }

        public BusinessContactBuilder businessEmail(String businessEmail) {
            this.businessEmail = businessEmail;
            return this;
        }

        public BusinessContactBuilder businessPhone(String businessPhone) {
            this.businessPhone = businessPhone;
            return this;
        }

        public BusinessContactBuilder creationDate(LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public BusinessContactBuilder lastUpdate(LocalDateTime lastUpdate) {
            this.lastUpdate = lastUpdate;
            return this;
        }

        @Override
        public BusinessContact build() {
            return new BusinessContact(this);
        }
    }
}