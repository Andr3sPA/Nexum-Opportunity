package co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import co.edu.udea.nexum.opportunity.common.infrastructure.output.jpa.entity.NexumEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "opportunity_hired", indexes = {
    @Index(columnList = "opportunity_id"),
    @Index(columnList = "user_id")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpportunityHiredEntity implements NexumEntity<Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "opportunity_id", nullable = false)
  private Long opportunityId;

  @Column(name = "user_id", nullable = false, columnDefinition = "uuid")
  private UUID userId;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

}

