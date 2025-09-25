package co.edu.udea.nexum.opportunity.opportunity.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.udea.nexum.opportunity.common.domain.model.AuditableModel;
import co.edu.udea.nexum.opportunity.common.domain.model.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Opportunity implements Model<Long>, AuditableModel {

    private Long id;
    private String title;
    private String description;
    private OpportunityStatus status;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdate;
    private UUID createdBy;
    private UUID graduateId;
    private SalaryRange salaryRange;

    // Contract basic data fields (mandatory according to requirements)
    private ContractType contractType;
    private LocalDate startDate;
    private Integer durationInMonths; // Duration in months, null for indefinite

    // Additional opportunity description fields (optional according to
    // requirements)
    private String complementaryStudies;
    private ExperienceLevel requiredExperience;
    private String location;
    private Boolean travelAvailability; // null means not specified
    private WorkModality workModality;

}
