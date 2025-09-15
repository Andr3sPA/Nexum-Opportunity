package co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.entity;

import co.edu.udea.nexum.opportunity.common.infrastructure.output.jpa.entity.NexumEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.udea.nexum.opportunity.opportunity.domain.model.OpportunityStatus;

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
    
    @Column(name = "location")
    private String location;
    
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
    
    // TODO: Add additional fields based on your business requirements
    
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