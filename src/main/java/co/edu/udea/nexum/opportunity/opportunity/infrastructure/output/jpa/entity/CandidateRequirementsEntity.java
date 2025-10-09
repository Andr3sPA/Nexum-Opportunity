package co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.entity;

import co.edu.udea.nexum.opportunity.common.infrastructure.output.jpa.entity.NexumEntity;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.ExperienceLevel;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.WorkModality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * JPA Entity for Candidate Requirements.
 * Contains requirements and qualifications for opportunities.
 */
@Entity
@Table(name = "candidate_requirements")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateRequirementsEntity implements NexumEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_requirements_id")
    private Long id;

    @Column(name = "complementary_studies", columnDefinition = "TEXT")
    private String complementaryStudies;

    @Column(name = "required_experience")
    @Enumerated(EnumType.STRING)
    private ExperienceLevel requiredExperience;

    @Column(name = "location")
    private String location;

    @Column(name = "travel_availability")
    private Boolean travelAvailability; // null means not specified

    @Enumerated(EnumType.STRING)
    @Column(name = "work_modality")
    private WorkModality workModality;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    // Many-to-many relationships
    @ElementCollection
    @CollectionTable(
        name = "candidate_requirements_programs",
        joinColumns = @JoinColumn(name = "candidate_requirements_id")
    )
    @Column(name = "program_id")
    @Builder.Default
    private Set<Long> coursedProgramIds = new HashSet<>();

    @ElementCollection
    @CollectionTable(
        name = "candidate_requirements_competencies",
        joinColumns = @JoinColumn(name = "candidate_requirements_id")
    )
    @Column(name = "competency_id")
    @Builder.Default
    private Set<Long> programCompetencyIds = new HashSet<>();

    @ElementCollection
    @CollectionTable(
        name = "candidate_requirements_job_areas",
        joinColumns = @JoinColumn(name = "candidate_requirements_id")
    )
    @Column(name = "job_area_id")
    @Builder.Default
    private Set<Long> jobAreaIds = new HashSet<>();

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