package co.edu.udea.nexum.opportunity.opportunity.domain.usecase;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import co.edu.udea.nexum.opportunity.common.domain.exception.EntityNotFoundException;
import co.edu.udea.nexum.opportunity.common.domain.spi.BaseCrudPersistencePort;
import co.edu.udea.nexum.opportunity.common.domain.usecase.AuditableCrudUseCase;
import co.edu.udea.nexum.opportunity.common.domain.utils.functions.CommonHelpers;
import co.edu.udea.nexum.opportunity.common.infrastructure.output.feign.adapter.CatalogFeignAdapter;
import co.edu.udea.nexum.opportunity.opportunity.domain.api.OpportunityServicePort;
import co.edu.udea.nexum.opportunity.opportunity.domain.exception.OpportunityNotFoundException;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.Opportunity;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.OpportunityStatus;
import co.edu.udea.nexum.opportunity.opportunity.domain.spi.OpportunityPersistencePort;
import co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.feign.client.UserFeign;
import co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.feign.dto.response.BasicUserResponse;
import co.edu.udea.nexum.opportunity.security.domain.model.AuthenticatedUser;
import co.edu.udea.nexum.opportunity.security.domain.utils.SecurityContextUtils;
import co.edu.udea.nexum.opportunity.security.domain.utils.enums.RoleName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Use case implementation for Opportunity business operations.
 * Contains the business logic for managing opportunities.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OpportunityUseCase extends AuditableCrudUseCase<Long, Opportunity> implements OpportunityServicePort {

  private final OpportunityPersistencePort opportunityPersistencePort;
  private final SecurityContextUtils securityContextUtils;
  private final CatalogFeignAdapter catalogFeignAdapter;
  private final UserFeign userFeign;

  @Override
  protected BaseCrudPersistencePort<Long, Opportunity> getPersistencePort() {
    return opportunityPersistencePort;
  }

  @Override
  protected String getModelClassName() {
    return "Opportunity";
  }

  @Override
  protected Opportunity patch(Opportunity original, Opportunity patch) {
    CommonHelpers.replaceIfNotNull(original, patch);
    return original;
  }

  @Override
  protected void validateEntity(Long currentId, Opportunity model) {
    if (securityContextUtils.isAuthenticated()) {
      // Validate salary range ID if present
      if (model.getSalaryRangeId() != null) {
        try {
          catalogFeignAdapter.getSalaryRangeById(model.getSalaryRangeId());
        } catch (Exception e) {
          throw new EntityNotFoundException("Invalid salary range ID: " + model.getSalaryRangeId());
        }
      }

      // Validate coursed program IDs if present
      if (model.getCoursedProgramIds() != null) {
        for (Long programId : model.getCoursedProgramIds()) {
          try {
            catalogFeignAdapter.getProgramById(programId);
          } catch (Exception e) {
            throw new EntityNotFoundException("Invalid coursed program ID: " + programId);
          }
        }
      }

      // Validate program competency IDs if present
      if (model.getProgramCompetencyIds() != null) {
        for (Long competencyId : model.getProgramCompetencyIds()) {
          try {
            catalogFeignAdapter.getProgramCompetencyById(competencyId);
          } catch (Exception e) {
            throw new EntityNotFoundException("Invalid program competency ID: " + competencyId);
          }
        }
      }

      // Validate job area IDs if present
      if (model.getJobAreaIds() != null) {
        for (Long jobAreaId : model.getJobAreaIds()) {
          try {
            catalogFeignAdapter.getJobAreaById(jobAreaId);
          } catch (Exception e) {
            throw new EntityNotFoundException("Invalid job area ID: " + jobAreaId);
          }
        }
      }
    }
  }

  @Override
  public Opportunity save(Opportunity model) {
    // Ensure createdBy is set for new opportunities if user is authenticated
    if (model.getCreatedBy() == null && securityContextUtils.isAuthenticated()) {
      try {
        AuthenticatedUser currentUser = securityContextUtils.getCurrentUser();
        model.setCreatedBy(currentUser.getId());
      } catch (IllegalStateException e) {
        log.debug("Error setting createdBy for anonymous creation", e);
      }
    } else {
      log.debug("User is not authenticated, leaving createdBy as null for anonymous creation");
    }

    // Generate edit code for anonymous editing
    if (model.getEditCode() == null || model.getEditCode().trim().isEmpty()) {
      model.setEditCode(generateEditCode());
    }

    log.debug("Saving opportunity {}", model);
    return super.save(model);
  }

  private String generateEditCode() {
    // Generate a random 8-character alphanumeric code
    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    StringBuilder code = new StringBuilder();
    for (int i = 0; i < 8; i++) {
      code.append(chars.charAt((int) (Math.random() * chars.length())));
    }
    return code.toString();
  }

  @Override
  public List<Opportunity> findByStatus(OpportunityStatus status) {
    return opportunityPersistencePort.findByStatus(status);
  }

  @Override
  public List<Opportunity> findByCreatedBy(UUID createdBy) {
    return opportunityPersistencePort.findByCreatedBy(createdBy);
  }

  @Override
  public Opportunity updateStatus(Long id, OpportunityStatus newStatus) {
    Opportunity opportunity = findById(id);

    if (newStatus == null) {
      throw new IllegalArgumentException("Status cannot be null");
    }

    opportunity.setStatus(newStatus);
    return updateById(id, opportunity);
  }

  @Override
  public List<Opportunity> findAllOpportunities() {
    AuthenticatedUser currentUser = securityContextUtils.getCurrentUser();
    if (currentUser.getRole() == RoleName.ADMIN || currentUser.getRole() == RoleName.ADMINISTRATIVE) {
      // Admin puede ver todas las oportunidades
      return findAll();
    } else if (currentUser.getRole() == RoleName.EMPLOYER) {
      // Employer solo ve las que creó - obtener el BasicUser ID desde UserFeign
      log.debug("Employer user, getting BasicUser ID for auth ID {}", currentUser.getId());
      BasicUserResponse basicUser = userFeign.getUserBasicByAuthId(currentUser.getId());
      log.debug("Employer user, returning opportunities created by BasicUser ID {}", basicUser.getId());
      return findByCreatedBy(basicUser.getId());
    } else if (currentUser.getRole() == RoleName.GRADUATE) {
      // Graduate puede ver solo las oportunidades activas
      return findByStatus(OpportunityStatus.ACTIVE);
    } else {
      // Otros roles no pueden ver oportunidades
      throw new IllegalArgumentException(
          "User role '" + currentUser.getRole() + "' is not authorized to access opportunities");
    }
  }

  @Override
  public Opportunity findById(Long id) {
    AuthenticatedUser currentUser = securityContextUtils.getCurrentUser();

    Opportunity opportunity;
    try {
      opportunity = super.findById(id);
    } catch (Exception e) {
      // If opportunity doesn't exist, throw not found for any user
      throw new OpportunityNotFoundException(id);
    }

    // Admin can access any opportunity
    if (currentUser.getRole() == RoleName.ADMIN || currentUser.getRole() == RoleName.ADMINISTRATIVE) {
      return opportunity;
    }

    // Employer can only access opportunities they created
    if (currentUser.getRole() == RoleName.EMPLOYER &&
        opportunity.getCreatedBy().equals(currentUser.getId())) {
      return opportunity;
    }

    // Graduate can access active opportunities
    if (currentUser.getRole() == RoleName.GRADUATE &&
        opportunity.getStatus() == OpportunityStatus.ACTIVE) {
      return opportunity;
    }

    // For any other case, return not found
    throw new OpportunityNotFoundException(id);
  }

  @Override
  public Opportunity updateById(Long id, Opportunity opportunity) {
    // Ensure the opportunity exists and is accessible by the current user
    Opportunity existingOpportunity = findById(id);
    Opportunity patchedOpportunity = patch(existingOpportunity, opportunity);
    validateEntity(id, patchedOpportunity);
    return super.updateById(id, patchedOpportunity);
  }

  @Override
  public void assignOwnerByEditCode(String editCode, UUID ownerId) {
    List<Opportunity> opportunities = opportunityPersistencePort.findByEditCode(editCode);
    for (Opportunity opportunity : opportunities) {
      if (opportunity.getCreatedBy() == null) { // Only assign if not already owned
        opportunity.setCreatedBy(ownerId);
        opportunity.setEditCode(null);
        opportunityPersistencePort.update(opportunity);
      }
    }
  }
}
