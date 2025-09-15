package co.edu.udea.nexum.opportunity.opportunity.domain.exception;

import co.edu.udea.nexum.opportunity.common.domain.exception.EntityNotFoundException;

/**
 * Exception thrown when an opportunity is not found.
 */
public class OpportunityNotFoundException extends EntityNotFoundException {
    
    public OpportunityNotFoundException() {
        super("Opportunity");
    }
    
    public OpportunityNotFoundException(Long id) {
        super("Opportunity", "id", id.toString());
    }
    
    public OpportunityNotFoundException(String message, boolean custom) {
        super(message, custom);
    }
}
