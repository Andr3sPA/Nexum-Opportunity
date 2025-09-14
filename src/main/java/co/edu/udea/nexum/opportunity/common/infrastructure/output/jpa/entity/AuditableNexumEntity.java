package co.edu.udea.nexum.profile.common.infrastructure.output.jpa.entity;

import java.time.LocalDateTime;

public interface AuditableNexumEntity {
    LocalDateTime getCreationDate();
    void setCreationDate(LocalDateTime creationDate);

    LocalDateTime getLastUpdate();
    void setLastUpdate(LocalDateTime lastUpdate);
}
