package co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.feign.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BasicProgramResponse {
    private String code;
    private String name;
}