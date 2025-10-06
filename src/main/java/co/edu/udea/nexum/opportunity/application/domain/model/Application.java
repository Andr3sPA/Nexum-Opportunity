package co.edu.udea.nexum.opportunity.application.domain.model;
import co.edu.udea.nexum.opportunity.common.domain.model.AuditableModel;
import co.edu.udea.nexum.opportunity.common.domain.model.Model;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.Opportunity;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Builder
@Generated
public class Application implements Model<UUID>, AuditableModel{
    private UUID id;
    private UUID userId;
    private Opportunity opportunity;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdate;
    private ApplicationStatus status;

    @Override
    public LocalDateTime getCreationDate() {
        return null;
    }

    @Override
    public void setCreationDate(LocalDateTime creationDate) {

    }

    @Override
    public LocalDateTime getLastUpdate() {
        return null;
    }

    @Override
    public void setLastUpdate(LocalDateTime lastUpdate) {

    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {

    }
}
