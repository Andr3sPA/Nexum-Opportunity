package co.edu.udea.nexum.opportunity.opportunity.domain.model;

import co.edu.udea.nexum.opportunity.common.domain.model.AuditableModel;
import co.edu.udea.nexum.opportunity.common.domain.model.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Opportunity implements Model<Long>, AuditableModel {
    
    private Long id;
    private String title;
    private String description;
    private String location;
    private OpportunityStatus status;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdate;
    
    // New properties
    private UUID createdBy; // User UUID who created this opportunity
    private UUID graduateId; // User UUID the opportunity is directed to (target graduate)
    private SalaryRange salaryRange; // Salary range for this opportunity
    
}


