package co.edu.udea.nexum.opportunity.application.infraestructure.output.jpa.entity;

import co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.entity.OpportunityEntity;
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
    
    // Add a getter to return date as string for JSON serialization
    @Transient
    public String getCreatedDateString() {
        return createdDate != null ? createdDate.toString() : null;
    }



    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
    
    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
        lastUpdate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdate = LocalDateTime.now();
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

}
