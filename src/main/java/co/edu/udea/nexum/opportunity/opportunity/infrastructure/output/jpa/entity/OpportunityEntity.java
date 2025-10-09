package co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.entity;

import co.edu.udea.nexum.opportunity.common.infrastructure.output.jpa.entity.NexumEntity;
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
import java.util.HashSet;
import java.util.Set;
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

  // User UUID who created this opportunity (nullable for anonymous creation)
  @Column(name = "created_by")
  private UUID createdBy;

  // Foreign key to salary range in catalog
  @Column(name = "salary_range_id")
  private Long salaryRangeId;

  // Edit code for anonymous editing
  @Column(name = "edit_code")
  private String editCode;

  @Column(name = "expiration_date")
  private LocalDate expirationDate;

  // Relationships to other entities
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "business_contact_id")
  private BusinessContactEntity businessContact;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "candidate_requirements_id")
  private CandidateRequirementsEntity candidateRequirements;

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
