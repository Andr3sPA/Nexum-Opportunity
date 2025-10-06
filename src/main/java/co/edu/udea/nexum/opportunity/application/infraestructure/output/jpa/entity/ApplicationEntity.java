package co.edu.udea.nexum.opportunity.application.infraestructure.output.jpa.entity;

import co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.entity.OpportunityEntity;
import co.edu.udea.nexum.opportunity.application.domain.model.ApplicationStatus;
import jakarta.persistence.*;
import lombok.*;
import co.edu.udea.nexum.opportunity.common.infrastructure.output.jpa.entity.NexumEntity;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
    name = "application",
    uniqueConstraints = {
        @UniqueConstraint(name = "uq_user_opportunity", columnNames = {"user_id", "opportunity_id"})
    },
    indexes = {
        @Index(name = "idx_user_opportunity_user_id", columnList = "user_id"),
        @Index(name = "idx_user_opportunity_opportunity_id", columnList = "opportunity_id")
    }
)
public class ApplicationEntity implements NexumEntity<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    // Solo guardas el ID del user (no una relación JPA real)
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    // Relación con la oportunidad (esta sí está en este micro)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "opportunity_id", nullable = false, referencedColumnName = "id")
    private OpportunityEntity opportunity;

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50, nullable = false)
    private ApplicationStatus status;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
        lastUpdate = LocalDateTime.now();
        if (status == null) {
            status = ApplicationStatus.PENDING;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdate = LocalDateTime.now();
    }

    public void setStatus(ApplicationStatus status) {
        if (status == null) {
            this.status = ApplicationStatus.PENDING;
        } else {
            this.status = status;
        }
    }
}
