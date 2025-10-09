package co.edu.udea.nexum.opportunity.common.infrastructure.output.feign.client;

import co.edu.udea.nexum.opportunity.common.infrastructure.output.feign.dto.response.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "catalog-service", url = "${nexum.catalog.base_url}")
public interface CatalogFeign {

    @GetMapping("/salary-ranges/{id}")
    SalaryRangeResponse getSalaryRangeById(@PathVariable("id") Long id);

    @GetMapping("/job-areas/{id}")
    JobAreaResponse getJobAreaById(@PathVariable("id") Long id);

    @GetMapping("/program-competencies/{id}")
    ProgramCompetencyResponse getProgramCompetencyById(@PathVariable("id") Long id);

    @GetMapping("/programs/{id}")
    ProgramResponse getProgramById(@PathVariable("id") Long id);
}
