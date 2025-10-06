package co.edu.udea.nexum.opportunity.application.application.mapper;

import co.edu.udea.nexum.opportunity.application.application.dto.request.ApplicationRequest;
import co.edu.udea.nexum.opportunity.application.domain.model.Application;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.Opportunity;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRequestMapper {
    public Application toDomain(ApplicationRequest request, java.util.UUID userId) {
        return Application.builder()
                .userId(userId)
                .opportunity(Opportunity.builder().id(request.getOpportunityId()).build())
                .build();
    }
}

