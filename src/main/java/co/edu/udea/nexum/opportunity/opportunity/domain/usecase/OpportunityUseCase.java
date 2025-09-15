package co.edu.udea.nexum.opportunity.opportunity.domain.usecase;

import co.edu.udea.nexum.opportunity.common.domain.spi.BaseCrudPersistencePort;
import co.edu.udea.nexum.opportunity.common.domain.usecase.AuditableCrudUseCase;
import co.edu.udea.nexum.opportunity.common.domain.utils.functions.CommonHelpers;
import co.edu.udea.nexum.opportunity.opportunity.domain.api.OpportunityServicePort;
import co.edu.udea.nexum.opportunity.opportunity.domain.exception.OpportunityNotFoundException;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.Opportunity;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.OpportunityStatus;
import co.edu.udea.nexum.opportunity.opportunity.domain.spi.OpportunityPersistencePort;
import co.edu.udea.nexum.opportunity.security.domain.model.AuthenticatedUser;
import co.edu.udea.nexum.opportunity.security.domain.utils.SecurityContextUtils;
import co.edu.udea.nexum.opportunity.security.domain.utils.enums.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Use case implementation for Opportunity business operations.
 * Contains the business logic for managing opportunities.
 */
@Service
@RequiredArgsConstructor
public class OpportunityUseCase extends AuditableCrudUseCase<Long, Opportunity> implements OpportunityServicePort {
    
    private final OpportunityPersistencePort opportunityPersistencePort;
    private final SecurityContextUtils securityContextUtils;
    
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
        if (model.getTitle() == null || model.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Opportunity title cannot be null or empty");
        }
        
        // Validate salary range if present
        if (model.getSalaryRange() != null) {
            model.getSalaryRange().validate();
        }
        
        // Validate createdBy if present
        if (model.getCreatedBy() == null || model.getCreatedBy().toString().trim().isEmpty()) {
            throw new IllegalArgumentException("createdBy UUID cannot be empty");
        }
    }
    
    @Override
    public Opportunity save(Opportunity model) {
        // Ensure createdBy is set for new opportunities if not already set
        if (model.getCreatedBy() == null) {
            AuthenticatedUser currentUser = securityContextUtils.getCurrentUser();
            model.setCreatedBy(currentUser.getId());
        }
        return super.save(model);
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
    public List<Opportunity> findByGraduateId(UUID graduateId) {
        // Get current authenticated user using the security utility
        AuthenticatedUser currentUser = securityContextUtils.getCurrentUser();
        
        // Get all opportunities for the graduate
        List<Opportunity> opportunities = opportunityPersistencePort.findByGraduateId(graduateId);
        
        // Apply role-based filtering
        if (currentUser.getRole() == RoleName.ADMIN) {
            // Admin can see all opportunities for the graduate
            return opportunities;
        } else if (currentUser.getRole() == RoleName.EMPLOYER) {
            // Employer can only see opportunities they created for this graduate
            return opportunities.stream()
                    .filter(opportunity -> opportunity.getCreatedBy().equals(currentUser.getId()))
                    .toList();
        } else {
            // Other roles are not allowed to access opportunities
            throw new IllegalArgumentException("User role '" + currentUser.getRole() + "' is not authorized to access opportunities");
        }
    }
    
    @Override
    public Opportunity updateStatus(Long id, OpportunityStatus newStatus) {
        Opportunity opportunity = findById(id);
        
        // Validate the new status (add your business rules here)
        if (newStatus == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        
        opportunity.setStatus(newStatus);
        return opportunityPersistencePort.update(opportunity);
    }

    @Override
    public List<Opportunity> findAllOpportunities() {
        // Get current authenticated user using the security utility
        AuthenticatedUser currentUser = securityContextUtils.getCurrentUser();
        
        // Check user role and return appropriate opportunities
        if (currentUser.getRole() == RoleName.ADMIN) {
            // Admin can see all opportunities
            return findAll();
        } else if (currentUser.getRole() == RoleName.EMPLOYER) {
            // Employer can only see opportunities they created
            return findByCreatedBy(currentUser.getId());
        } else {
            // Other roles are not allowed to access opportunities
            throw new IllegalArgumentException("User role '" + currentUser.getRole() + "' is not authorized to access opportunities");
        }
    }

    @Override
    public Opportunity findById(Long id) {
        // Get current authenticated user using the security utility
        AuthenticatedUser currentUser = securityContextUtils.getCurrentUser();
        
        // First, try to find the opportunity using parent's findById
        Opportunity opportunity;
        try {
            opportunity = super.findById(id);
        } catch (Exception e) {
            // If opportunity doesn't exist, throw not found for any user
            throw new OpportunityNotFoundException(id);
        }
        
        // Admin can access any opportunity
        if (currentUser.getRole() == RoleName.ADMIN) {
            return opportunity;
        }
        
        // Employer can only access opportunities they created
        if (currentUser.getRole() == RoleName.EMPLOYER && 
            opportunity.getCreatedBy().equals(currentUser.getId())) {
            return opportunity;
        }
        
        // For any other case (employer trying to access someone else's opportunity), return not found
        throw new OpportunityNotFoundException(id);
    }}
