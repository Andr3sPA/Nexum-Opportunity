package co.edu.udea.nexum.opportunity.application.application.handler;

import co.edu.udea.nexum.opportunity.application.application.dto.request.ApplicationRequest;
import co.edu.udea.nexum.opportunity.application.application.mapper.ApplicationRequestMapper;
import co.edu.udea.nexum.opportunity.application.domain.model.Application;
import co.edu.udea.nexum.opportunity.application.domain.usecase.ApplicationUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import co.edu.udea.nexum.opportunity.security.domain.utils.SecurityContextUtils;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ApplicationHandler {
    private final ApplicationUseCase applicationUseCase;
    private final SecurityContextUtils securityContextUtils;
    private final ApplicationRequestMapper applicationRequestMapper;

    public Application save(ApplicationRequest request) {
        Application applicationToSave = applicationRequestMapper.toDomain(request, securityContextUtils.getCurrentUser().getUserId());
        return applicationUseCase.save(applicationToSave);
    }
    public List<Application> getAll(){
        return applicationUseCase.findByUserId(securityContextUtils.getCurrentUser().getUserId());
    }
}
