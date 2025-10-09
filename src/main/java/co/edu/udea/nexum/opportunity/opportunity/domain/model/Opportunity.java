package co.edu.udea.nexum.opportunity.opportunity.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
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
  private Long salaryRangeId;

  // Edit code for anonymous editing
  private String editCode;

  private LocalDate expirationDate;

  // Related entities
  private BusinessContact businessContact;
  private CandidateRequirements candidateRequirements;

  // Helper methods for backward compatibility
  public String getBusinessName() {
    return businessContact != null ? businessContact.getBusinessName() : null;
  }

  public String getContactName() {
    return businessContact != null ? businessContact.getContactName() : null;
  }

  public String getBusinessEmail() {
    return businessContact != null ? businessContact.getBusinessEmail() : null;
  }

  public String getBusinessPhone() {
    return businessContact != null ? businessContact.getBusinessPhone() : null;
  }

  public String getComplementaryStudies() {
    return candidateRequirements != null ? candidateRequirements.getComplementaryStudies() : null;
  }

  public ExperienceLevel getRequiredExperience() {
    return candidateRequirements != null ? candidateRequirements.getRequiredExperience() : null;
  }

  public String getLocation() {
    return candidateRequirements != null ? candidateRequirements.getLocation() : null;
  }

  public Boolean getTravelAvailability() {
    return candidateRequirements != null ? candidateRequirements.getTravelAvailability() : null;
  }

  public WorkModality getWorkModality() {
    return candidateRequirements != null ? candidateRequirements.getWorkModality() : null;
  }

  public Set<Long> getCoursedProgramIds() {
    return candidateRequirements != null ? candidateRequirements.getCoursedProgramIds() : new HashSet<>();
  }

  public Set<Long> getProgramCompetencyIds() {
    return candidateRequirements != null ? candidateRequirements.getProgramCompetencyIds() : new HashSet<>();
  }

  public Set<Long> getJobAreaIds() {
    return candidateRequirements != null ? candidateRequirements.getJobAreaIds() : new HashSet<>();
  }

}
