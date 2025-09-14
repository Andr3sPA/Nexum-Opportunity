package co.edu.udea.nexum.profile.common.domain.model;

import java.time.LocalDateTime;

public interface AuditableModel {
    LocalDateTime getCreationDate();
    void setCreationDate(LocalDateTime creationDate);

    LocalDateTime getLastUpdate();
    void setLastUpdate(LocalDateTime lastUpdate);
}
