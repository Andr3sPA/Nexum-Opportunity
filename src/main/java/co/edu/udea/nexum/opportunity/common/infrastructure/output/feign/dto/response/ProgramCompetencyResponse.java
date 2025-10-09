package co.edu.udea.nexum.opportunity.common.infrastructure.output.feign.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramCompetencyResponse implements FeignResponse {
    private Long id;
    private String name;
    private String description;
}