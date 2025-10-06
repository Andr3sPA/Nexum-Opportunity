package co.edu.udea.nexum.opportunity.application.infraestructure.output.jpa.repository;

import co.edu.udea.nexum.opportunity.application.infraestructure.output.jpa.entity.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, UUID> {
    List<ApplicationEntity> findByUserId(UUID userId);
}
