package co.edu.udea.nexum.opportunity.common.domain.model;

import java.time.LocalDateTime;

public interface AuditableModel {
    LocalDateTime getCreationDate();
    void setCreationDate(LocalDateTime creationDate);

    LocalDateTime getLastUpdate();
    void setLastUpdate(LocalDateTime lastUpdate);
}
