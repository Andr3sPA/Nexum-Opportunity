package co.edu.udea.nexum.opportunity.useropportunity.application.handler;

import co.edu.udea.nexum.opportunity.common.application.handler.BaseCrudHandler;
import co.edu.udea.nexum.opportunity.useropportunity.domain.model.UserOpportunity;
import co.edu.udea.nexum.opportunity.useropportunity.domain.usecase.UserOpportunityUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserOpportunityHandler {
    private final UserOpportunityUseCase userOpportunityUseCase;
    public UserOpportunity save(UserOpportunity userOpportunity) {
        return userOpportunityUseCase.save(userOpportunity);
    }
}
