package co.edu.udea.nexum.opportunity.opportunity.infrastructure.input.rest.v1;

import co.edu.udea.nexum.opportunity.opportunity.application.dto.request.OpportunityRequestDto;
import co.edu.udea.nexum.opportunity.opportunity.application.dto.response.OpportunityResponseDto;
import co.edu.udea.nexum.opportunity.opportunity.application.handler.OpportunityHandler;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.OpportunityStatus;
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

/**
 * REST Controller for Opportunity operations.
 * Provides API endpoints for managing opportunities.
 */
@RestController
@RequestMapping("/v1/opportunities")
@RequiredArgsConstructor
@Tag(name = "Opportunities", description = "Opportunity management operations")
public class OpportunityController {

  private final OpportunityHandler opportunityHandler;

  @PostMapping
  @Operation(summary = "Create a new opportunity")
  @SecurityRequirement(name = "bearerAuth")
  @PreAuthorize("hasRole('EMPLOYER') or hasRole('ADMIN') or hasRole('ANONYMOUS')")
  public ResponseEntity<OpportunityResponseDto> createOpportunity(
      @Valid @RequestBody OpportunityRequestDto requestDto) {
    OpportunityResponseDto createdOpportunity = opportunityHandler.createOpportunity(requestDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdOpportunity);
  }

  @GetMapping
  @Operation(summary = "Get opportunities based on user role: employers see their own, graduates see active opportunities, admins see all")
  @SecurityRequirement(name = "bearerAuth")
  @PreAuthorize("hasRole('EMPLOYER') or hasRole('ADMIN') or hasRole('ADMINISTRATIVE') or hasRole('GRADUATE')")
  public ResponseEntity<List<OpportunityResponseDto>> getAllOpportunities() {
    List<OpportunityResponseDto> opportunities = opportunityHandler.getAllOpportunities();
    return ResponseEntity.ok(opportunities);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get opportunity by ID")
  @SecurityRequirement(name = "bearerAuth")
  @PreAuthorize("hasRole('EMPLOYER') or hasRole('ADMIN') or hasRole('ADMINISTRATIVE') or hasRole('GRADUATE')")
  public ResponseEntity<OpportunityResponseDto> getOpportunityById(@PathVariable("id") Long id) {
    OpportunityResponseDto opportunity = opportunityHandler.getOpportunityById(id);
    return ResponseEntity.ok(opportunity);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update opportunity by ID - only the creator or admin can update")
  @SecurityRequirement(name = "bearerAuth")
  @PreAuthorize("hasRole('EMPLOYER') or hasRole('ADMIN') or hasRole('ADMINISTRATIVE')")
  public ResponseEntity<OpportunityResponseDto> updateOpportunity(
      @PathVariable("id") Long id,
      @Valid @RequestBody OpportunityRequestDto requestDto) {
    OpportunityResponseDto updatedOpportunity = opportunityHandler.updateOpportunity(id, requestDto);
    return ResponseEntity.ok(updatedOpportunity);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete opportunity by ID - only the creator or admin can delete")
  @SecurityRequirement(name = "bearerAuth")
  @PreAuthorize("hasRole('EMPLOYER') or hasRole('ADMIN') or hasRole('ADMINISTRATIVE')")
  public ResponseEntity<OpportunityResponseDto> deleteOpportunity(@PathVariable("id") Long id) {
    OpportunityResponseDto deletedOpportunity = opportunityHandler.deleteOpportunity(id);
    return ResponseEntity.ok(deletedOpportunity);
  }

  @PutMapping("/assign-owner/{editCode}")
  @Operation(summary = "Assign owner to opportunities by edit code - used when anonymous user registers as employer")
  @SecurityRequirement(name = "bearerAuth")
  @PreAuthorize("hasRole('EMPLOYER') or hasRole('ADMIN') or hasRole('ADMINISTRATIVE') or hasRole('ANONYMOUS')")
  public ResponseEntity<Void> assignOwnerByEditCode(
      @PathVariable("editCode") String editCode,
      @RequestBody String ownerId) {
    opportunityHandler.assignOwnerByEditCode(editCode, ownerId);
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/{id}/status")
  @Operation(summary = "Update opportunity status by ID - only ADMIN")
  @SecurityRequirement(name = "bearerAuth")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<OpportunityResponseDto> updateOpportunityStatus(
      @PathVariable("id") Long id,
      @RequestParam("status") String status) {
    OpportunityStatus newStatus = OpportunityStatus.valueOf(status.toUpperCase());
    OpportunityResponseDto updated = opportunityHandler.updateOpportunityStatus(id, newStatus);
    return ResponseEntity.ok(updated);
  }

}
