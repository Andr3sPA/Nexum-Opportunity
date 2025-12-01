package co.edu.udea.nexum.opportunity.application.infraestructure.output.jpa.repository;

import co.edu.udea.nexum.opportunity.application.infraestructure.output.jpa.entity.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, UUID> {
    List<ApplicationEntity> findByUserId(UUID userId);
    
    @Query("SELECT COUNT(a) FROM ApplicationEntity a")
    long countAllApplications();
    
    @Query("SELECT COUNT(a) FROM ApplicationEntity a WHERE a.createdDate >= :startDate")
    long countApplicationsCreatedAfter(@Param("startDate") LocalDateTime startDate);
    
    @Query(value = "SELECT TO_CHAR(a.created_date, 'YYYY-MM-DD') as date, COUNT(a) as count FROM opportunity.application a WHERE a.created_date BETWEEN :startDate AND :endDate GROUP BY TO_CHAR(a.created_date, 'YYYY-MM-DD') ORDER BY date", nativeQuery = true)
    List<Object[]> countApplicationsByDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
