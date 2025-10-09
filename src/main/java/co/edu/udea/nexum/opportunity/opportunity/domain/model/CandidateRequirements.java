package co.edu.udea.nexum.opportunity.opportunity.domain.model;

import co.edu.udea.nexum.opportunity.common.domain.model.AuditableModel;
import co.edu.udea.nexum.opportunity.common.domain.model.Model;
import co.edu.udea.nexum.opportunity.common.domain.utils.contracts.BaseBuilder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class CandidateRequirements implements Model<Long>, AuditableModel {

    private Long id;
    private String complementaryStudies;
    private ExperienceLevel requiredExperience;
    private String location;
    private Boolean travelAvailability;
    private WorkModality workModality;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdate;
    private Set<Long> coursedProgramIds;
    private Set<Long> programCompetencyIds;
    private Set<Long> jobAreaIds;

    public CandidateRequirements(CandidateRequirementsBuilder builder) {
        this.id = builder.id;
        this.complementaryStudies = builder.complementaryStudies;
        this.requiredExperience = builder.requiredExperience;
        this.location = builder.location;
        this.travelAvailability = builder.travelAvailability;
        this.workModality = builder.workModality;
        this.creationDate = builder.creationDate;
        this.lastUpdate = builder.lastUpdate;
        this.coursedProgramIds = builder.coursedProgramIds != null ? builder.coursedProgramIds : new HashSet<>();
        this.programCompetencyIds = builder.programCompetencyIds != null ? builder.programCompetencyIds : new HashSet<>();
        this.jobAreaIds = builder.jobAreaIds != null ? builder.jobAreaIds : new HashSet<>();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getComplementaryStudies() {
        return complementaryStudies;
    }

    public void setComplementaryStudies(String complementaryStudies) {
        this.complementaryStudies = complementaryStudies;
    }

    public ExperienceLevel getRequiredExperience() {
        return requiredExperience;
    }

    public void setRequiredExperience(ExperienceLevel requiredExperience) {
        this.requiredExperience = requiredExperience;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getTravelAvailability() {
        return travelAvailability;
    }

    public void setTravelAvailability(Boolean travelAvailability) {
        this.travelAvailability = travelAvailability;
    }

    public WorkModality getWorkModality() {
        return workModality;
    }

    public void setWorkModality(WorkModality workModality) {
        this.workModality = workModality;
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

    public Set<Long> getCoursedProgramIds() {
        return coursedProgramIds;
    }

    public void setCoursedProgramIds(Set<Long> coursedProgramIds) {
        this.coursedProgramIds = coursedProgramIds;
    }

    public Set<Long> getProgramCompetencyIds() {
        return programCompetencyIds;
    }

    public void setProgramCompetencyIds(Set<Long> programCompetencyIds) {
        this.programCompetencyIds = programCompetencyIds;
    }

    public Set<Long> getJobAreaIds() {
        return jobAreaIds;
    }

    public void setJobAreaIds(Set<Long> jobAreaIds) {
        this.jobAreaIds = jobAreaIds;
    }

    public static CandidateRequirementsBuilder builder() {
        return new CandidateRequirementsBuilder();
    }

    public static class CandidateRequirementsBuilder implements BaseBuilder<CandidateRequirements> {
        private Long id;
        private String complementaryStudies;
        private ExperienceLevel requiredExperience;
        private String location;
        private Boolean travelAvailability;
        private WorkModality workModality;
        private LocalDateTime creationDate;
        private LocalDateTime lastUpdate;
        private Set<Long> coursedProgramIds;
        private Set<Long> programCompetencyIds;
        private Set<Long> jobAreaIds;

        public CandidateRequirementsBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CandidateRequirementsBuilder complementaryStudies(String complementaryStudies) {
            this.complementaryStudies = complementaryStudies;
            return this;
        }

        public CandidateRequirementsBuilder requiredExperience(ExperienceLevel requiredExperience) {
            this.requiredExperience = requiredExperience;
            return this;
        }

        public CandidateRequirementsBuilder location(String location) {
            this.location = location;
            return this;
        }

        public CandidateRequirementsBuilder travelAvailability(Boolean travelAvailability) {
            this.travelAvailability = travelAvailability;
            return this;
        }

        public CandidateRequirementsBuilder workModality(WorkModality workModality) {
            this.workModality = workModality;
            return this;
        }

        public CandidateRequirementsBuilder creationDate(LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public CandidateRequirementsBuilder lastUpdate(LocalDateTime lastUpdate) {
            this.lastUpdate = lastUpdate;
            return this;
        }

        public CandidateRequirementsBuilder coursedProgramIds(Set<Long> coursedProgramIds) {
            this.coursedProgramIds = coursedProgramIds;
            return this;
        }

        public CandidateRequirementsBuilder programCompetencyIds(Set<Long> programCompetencyIds) {
            this.programCompetencyIds = programCompetencyIds;
            return this;
        }

        public CandidateRequirementsBuilder jobAreaIds(Set<Long> jobAreaIds) {
            this.jobAreaIds = jobAreaIds;
            return this;
        }

        @Override
        public CandidateRequirements build() {
            return new CandidateRequirements(this);
        }
    }
}