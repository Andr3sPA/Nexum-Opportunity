package co.edu.udea.nexum.opportunity.opportunity.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import co.edu.udea.nexum.opportunity.common.application.dto.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Business Contact responses.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessContactResponseDto implements BaseResponse {

    private Long id;
    private String businessName;
    private String contactName;
    private String businessEmail;
    private String businessPhone;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdate;
}