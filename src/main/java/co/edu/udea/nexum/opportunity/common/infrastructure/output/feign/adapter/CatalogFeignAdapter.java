package co.edu.udea.nexum.opportunity.common.infrastructure.output.feign.adapter;

import co.edu.udea.nexum.opportunity.common.domain.model.*;
import co.edu.udea.nexum.opportunity.common.infrastructure.output.feign.client.CatalogFeign;
import co.edu.udea.nexum.opportunity.common.infrastructure.output.feign.dto.response.*;
import co.edu.udea.nexum.opportunity.common.infrastructure.output.feign.mapper.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CatalogFeignAdapter {

    private final CatalogFeign catalogFeign;
    private final SalaryRangeFeignResponseMapper salaryRangeMapper;
    private final ProgramFeignResponseMapper programMapper;
    private final ProgramCompetencyFeignResponseMapper programCompetencyMapper;
    private final JobAreaFeignResponseMapper jobAreaMapper;

    public SalaryRange getSalaryRangeById(Long id) {
        try {
            SalaryRangeResponse response = catalogFeign.getSalaryRangeById(id);
            return salaryRangeMapper.toDomain(response);
        } catch (Exception e) {
            log.error("Error fetching salary range with id {}: {}", id, e.getMessage());
            throw new RuntimeException("Salary range not found: " + id, e);
        }
    }

    public JobArea getJobAreaById(Long id) {
        try {
            JobAreaResponse response = catalogFeign.getJobAreaById(id);
            return jobAreaMapper.toDomain(response);
        } catch (Exception e) {
            log.error("Error fetching job area with id {}: {}", id, e.getMessage());
            throw new RuntimeException("Job area not found: " + id, e);
        }
    }

    public ProgramCompetency getProgramCompetencyById(Long id) {
        try {
            ProgramCompetencyResponse response = catalogFeign.getProgramCompetencyById(id);
            return programCompetencyMapper.toDomain(response);
        } catch (Exception e) {
            log.error("Error fetching program competency with id {}: {}", id, e.getMessage());
            throw new RuntimeException("Program competency not found: " + id, e);
        }
    }

    public Program getProgramById(Long id) {
        try {
            ProgramResponse response = catalogFeign.getProgramById(id);
            return programMapper.toDomain(response);
        } catch (Exception e) {
            log.error("Error fetching program with id {}: {}", id, e.getMessage());
            throw new RuntimeException("Program not found: " + id, e);
        }
    }
}