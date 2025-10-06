package co.edu.udea.nexum.opportunity.application.application.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@Generated
public class ApplicationRequest {
    @NotNull
    private Long opportunityId;

    private String userId;
}
