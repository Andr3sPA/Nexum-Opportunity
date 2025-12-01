package co.edu.udea.nexum.opportunity.application.infraestructure.input.rest.v1;

import co.edu.udea.nexum.opportunity.application.application.dto.request.ApplicationRequest;
import co.edu.udea.nexum.opportunity.common.domain.utils.annotations.Generated;
import co.edu.udea.nexum.opportunity.application.application.handler.ApplicationHandler;
import co.edu.udea.nexum.opportunity.application.domain.model.Application;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static co.edu.udea.nexum.opportunity.application.infraestructure.utils.constants.ApplicationRestConstants.*;

@Generated
@RestController
@RequiredArgsConstructor
@RequestMapping(value = APPLICATION_CONTROLLER_PATH)
@Tag(name = APPLICATION_CONTROLLER_NAME, description = APPLICATION_CONTROLLER_DESCRIPTION)
public class ApplicationController {
    private final ApplicationHandler applicationHandler;

    @Operation(summary = SWAGGER_SAVE_NEW_USER_OPPORTUNITY_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(responseCode = SWAGGER_CODE_CREATED, description = SWAGGER_NEW_USER_OPPORTUNITY_SAVED_SUCCESSFULLY),
            @ApiResponse(responseCode = SWAGGER_CODE_BAD_REQUEST, description = SWAGGER_ERROR_VALIDATIONS_DO_NOT_PASS)
    })
    @PostMapping
    public ResponseEntity<Application> save(@RequestBody ApplicationRequest application) {
        Application saved = applicationHandler.save(application);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
    @GetMapping
    public ResponseEntity<Iterable<Application>> getAll() {
        Iterable<Application> applications = applicationHandler.getAll();
        return ResponseEntity.ok(applications);
    }
    
    @GetMapping("/metrics")
    @Operation(summary = "Get application metrics")
    public ResponseEntity<Map<String, Object>> getApplicationMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("totalApplications", applicationHandler.getTotalApplicationsCount());
        metrics.put("applicationsLast6Months", applicationHandler.getApplicationsByMonth(6));
        metrics.put("applicationsLast12Months", applicationHandler.getApplicationsByMonth(12));
        return ResponseEntity.ok(metrics);
    }
    
    @GetMapping("/metrics/timeline")
    @Operation(summary = "Get application timeline data")
    public ResponseEntity<Map<String, Object>> getApplicationTimeline(
        @RequestParam(defaultValue = "12") int months) {
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusMonths(months);

        List<Object[]> timelineData = applicationHandler.getApplicationsByDateRange(startDate, endDate);

        // Format timeline data for frontend
        List<Map<String, Object>> formattedTimeline = timelineData.stream()
            .map(row -> {
                String dateStr = (String) row[0]; // Already a string from SQL
                Long count = (Long) row[1];
                Map<String, Object> formattedRow = new HashMap<>();
                formattedRow.put("date", dateStr); // Use string directly
                formattedRow.put("count", count);
                return formattedRow;
            })
            .collect(java.util.stream.Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("timeline", formattedTimeline);
        response.put("startDate", startDate);
        response.put("endDate", endDate);
        response.put("months", months);

        return ResponseEntity.ok(response);
    }

}
