package co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.entity;

import co.edu.udea.nexum.opportunity.common.infrastructure.output.jpa.entity.NexumEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * JPA Entity for Business Contact information.
 * Contains business contact details for opportunities.
 */
@Entity
@Table(name = "business_contact")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessContactEntity implements NexumEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "business_contact_id")
    private Long id;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "business_email")
    private String businessEmail;

    @Column(name = "business_phone")
    private String businessPhone;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @PrePersist
    protected void onCreate() {
        creationDate = LocalDateTime.now();
        lastUpdate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdate = LocalDateTime.now();
    }
}