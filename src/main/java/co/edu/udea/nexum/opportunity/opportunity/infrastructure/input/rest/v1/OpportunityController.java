package co.edu.udea.nexum.opportunity.opportunity.infrastructure.input.rest.v1;

import co.edu.udea.nexum.opportunity.opportunity.application.dto.request.OpportunityRequestDto;
import co.edu.udea.nexum.opportunity.opportunity.application.dto.response.OpportunityResponseDto;
import co.edu.udea.nexum.opportunity.opportunity.application.handler.OpportunityHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * REST Controller for Opportunity operations.
 * Provides API endpoints for managing opportunities.
 */
@RestController
@RequestMapping("/api/v1/opportunities")
@RequiredArgsConstructor
@Tag(name = "Opportunities", description = "Opportunity management operations")
public class OpportunityController {
    
    private final OpportunityHandler opportunityHandler;
    
    @PostMapping
    @Operation(summary = "Create a new opportunity")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('EMPLOYER') or hasRole('ADMIN')")
    public ResponseEntity<OpportunityResponseDto> createOpportunity(
            @Valid @RequestBody OpportunityRequestDto requestDto) {
        OpportunityResponseDto createdOpportunity = opportunityHandler.createOpportunity(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOpportunity);
    }
    
    @GetMapping
    @Operation(summary = "Get all opportunities created by the current employer if not admin, else get all")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('EMPLOYER') or hasRole('ADMIN')")
    public ResponseEntity<List<OpportunityResponseDto>> getAllOpportunities() {
        List<OpportunityResponseDto> opportunities = opportunityHandler.getAllOpportunities();
        return ResponseEntity.ok(opportunities);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get opportunity by ID")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('EMPLOYER') or hasRole('ADMIN')")
    public ResponseEntity<OpportunityResponseDto> getOpportunityById(@PathVariable Long id) {
        OpportunityResponseDto opportunity = opportunityHandler.getOpportunityById(id);
        return ResponseEntity.ok(opportunity);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update opportunity by ID - only the creator or admin can update")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('EMPLOYER') or hasRole('ADMIN')")
    public ResponseEntity<OpportunityResponseDto> updateOpportunity(
            @PathVariable Long id,
            @Valid @RequestBody OpportunityRequestDto requestDto) {
        OpportunityResponseDto updatedOpportunity = opportunityHandler.updateOpportunity(id, requestDto);
        return ResponseEntity.ok(updatedOpportunity);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete opportunity by ID - only the creator or admin can delete")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('EMPLOYER') or hasRole('ADMIN')")
    public ResponseEntity<OpportunityResponseDto> deleteOpportunity(@PathVariable Long id) {
        OpportunityResponseDto deletedOpportunity = opportunityHandler.deleteOpportunity(id);
        return ResponseEntity.ok(deletedOpportunity);
    }
    
    @GetMapping("/graduate/{graduateId}")
    @Operation(summary = "Get opportunities directed to a specific graduate")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('EMPLOYER') or hasRole('ADMIN')")
    public ResponseEntity<List<OpportunityResponseDto>> getOpportunitiesByGraduateId(@PathVariable UUID graduateId) {
        List<OpportunityResponseDto> opportunities = opportunityHandler.getOpportunitiesByGraduateId(graduateId);
        return ResponseEntity.ok(opportunities);
    }
    
}
