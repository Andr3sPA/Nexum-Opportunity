package co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.entity;

import co.edu.udea.nexum.opportunity.common.infrastructure.output.jpa.entity.NexumEntity;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.ContractType;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.ExperienceLevel;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.OpportunityStatus;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.WorkModality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * JPA Entity for Opportunity.
 * Represents the database table structure for opportunities.
 */
@Entity
@Table(name = "opportunity")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpportunityEntity implements NexumEntity<Long> {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "title", nullable = false)
    private String title;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OpportunityStatus status;
    
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
    
    // New properties
    @Column(name = "created_by")
    private UUID createdBy; // User UUID who created this opportunity
    
    @Column(name = "graduate_id")
    private UUID graduateId; // User UUID the opportunity is directed to (target graduate)
    
    // Salary range fields - storing as separate columns for better querying
    @Column(name = "salary_min", precision = 10, scale = 2)
    private BigDecimal salaryMin;
    
    @Column(name = "salary_max", precision = 10, scale = 2)
    private BigDecimal salaryMax;
    
    @Column(name = "salary_currency", length = 3)
    private String salaryCurrency; // ISO currency code (USD, EUR, etc.)
    
    // Contract basic data fields (mandatory according to requirements)
    @Enumerated(EnumType.STRING)
    @Column(name = "contract_type")
    private ContractType contractType;
    
    @Column(name = "start_date")
    private LocalDate startDate;
    
    @Column(name = "duration_in_months")
    private Integer durationInMonths; // Duration in months, null for indefinite
    
    // Additional opportunity description fields (optional according to requirements)
    @Column(name = "complementary_studies", columnDefinition = "TEXT")
    private String complementaryStudies;
    
    @Column(name = "required_experience")
    @Enumerated(EnumType.STRING)
    private ExperienceLevel requiredExperience;
        
    @Column(name = "location")
    private String location;

    @Column(name = "travel_availability")
    private Boolean travelAvailability; // null means not specified
    
    @Enumerated(EnumType.STRING)
    @Column(name = "work_modality")
    private WorkModality workModality;
    
    @PrePersist
    protected void onCreate() {
        creationDate = LocalDateTime.now();
        lastUpdate = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        lastUpdate = LocalDateTime.now();
    }
}