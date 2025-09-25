package co.edu.udea.nexum.opportunity.opportunity;

import co.edu.udea.nexum.opportunity.security.domain.model.AuthenticatedUser;
import co.edu.udea.nexum.opportunity.security.domain.utils.SecurityContextUtils;
import co.edu.udea.nexum.opportunity.security.domain.utils.enums.RoleName;
import co.edu.udea.nexum.opportunity.security.infrastructure.output.feign.client.AuthFeign;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.ContractType;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.ExperienceLevel;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.WorkModality;
import co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.entity.OpportunityEntity;
import co.edu.udea.nexum.opportunity.opportunity.domain.model.OpportunityStatus;
import co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.jpa.repository.OpportunityJpaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Comprehensive integration tests for Opportunity management endpoints.
 * Tests role-based access control, authorization scenarios, and exception handling.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class OpportunityTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OpportunityJpaRepository opportunityRepository;

    @MockitoBean
    private SecurityContextUtils securityContextUtils;

    @MockitoBean
    private AuthFeign authFeign; // Mock external auth service

    // Test data constants
    private static final UUID ADMIN_USER_ID = UUID.fromString("11111111-1111-1111-1111-111111111111");
    private static final UUID EMPLOYER_USER_ID = UUID.fromString("22222222-2222-2222-2222-222222222222");
    private static final UUID OTHER_EMPLOYER_USER_ID = UUID.fromString("33333333-3333-3333-3333-333333333333");
    private static final UUID GRADUATE_USER_ID = UUID.fromString("44444444-4444-4444-4444-444444444444");
    private static final UUID TARGET_GRADUATE_ID = UUID.fromString("55555555-5555-5555-5555-555555555555");

    // Test user instances
    private AuthenticatedUser adminUser;
    private AuthenticatedUser employerUser;
    private AuthenticatedUser graduateUser;

    @BeforeEach
    void setUp() {
        // Initialize test users
        adminUser = createUser(ADMIN_USER_ID, RoleName.ADMIN);
        employerUser = createUser(EMPLOYER_USER_ID, RoleName.EMPLOYER);
        graduateUser = createUser(GRADUATE_USER_ID, RoleName.GRADUATE);

        // Clear database before each test
        opportunityRepository.deleteAll();
    }
    
    private AuthenticatedUser createUser(UUID id, RoleName role) {
        return AuthenticatedUser.builder()
                .id(id)
                .role(role)
                .token("mock-jwt-token")
                .build();
    }

    private void setSecurityContext(AuthenticatedUser user) {
        String roleName = user.getRole().name();
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                user,
                null,
                List.of(new SimpleGrantedAuthority("ROLE_" + roleName))
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        
        // Also setup the mock for SecurityContextUtils for any code that uses it directly
        when(securityContextUtils.getCurrentUser()).thenReturn(user);
    }

    private String createOpportunityJson(UUID graduateId) {
        return String.format("""
            {
                "title": "Senior Developer Position",
                "description": "Looking for an experienced developer with Spring Boot experience",
                "location": "Medellin",
                "salaryMin": 5000000,
                "salaryMax": 8000000,
                "salaryCurrency": "COP",
                "graduateId": "%s",
                "contractType": "FULL_TIME",
                "startDate": "2024-01-15",
                "durationInMonths": 12,
                "complementaryStudies": "Spring Boot certification preferred",
                "requiredExperience": "4-6 years",
                "travelAvailability": false,
                "workModality": "HYBRID"
            }
            """, graduateId != null ? graduateId.toString() : TARGET_GRADUATE_ID.toString());
    }

    private OpportunityEntity createTestOpportunity(UUID createdBy, UUID graduateId) {
        OpportunityEntity opportunity = OpportunityEntity.builder()
                .title("Test Opportunity")
                .description("Test Description")
                .location("Test Location")
                .status(OpportunityStatus.ACTIVE)
                .createdBy(createdBy)
                .graduateId(graduateId)
                .salaryMin(BigDecimal.valueOf(3000000))
                .salaryMax(BigDecimal.valueOf(5000000))
                .salaryCurrency("COP")
                // New mandatory fields
                .contractType(ContractType.FULL_TIME)
                .startDate(LocalDate.of(2024, 2, 1))
                .durationInMonths(12)
                // Optional fields
                .complementaryStudies("Basic programming knowledge")
                .requiredExperience(ExperienceLevel.TWO_TO_FOUR_YEARS)
                .travelAvailability(false)
                .workModality(WorkModality.ON_SITE)
                .build();
        return opportunityRepository.save(opportunity);
    }

    @Nested
    @DisplayName("Create Opportunity Tests")
    class CreateOpportunityTests {

        @Test
        @DisplayName("ADMIN can create opportunities")
        void adminCanCreateOpportunities() throws Exception {
            // Given
            setSecurityContext(adminUser);

            // When & Then
            mockMvc.perform(post("/v1/opportunities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(createOpportunityJson(TARGET_GRADUATE_ID)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.title").value("Senior Developer Position"))
                    .andExpect(jsonPath("$.description").value("Looking for an experienced developer with Spring Boot experience"))
                    .andExpect(jsonPath("$.location").value("Medellin"));
        }

        @Test
        @DisplayName("EMPLOYER can create opportunities")
        void employerCanCreateOpportunities() throws Exception {
            // Given
            setSecurityContext(employerUser);

            // When & Then
            mockMvc.perform(post("/v1/opportunities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(createOpportunityJson(TARGET_GRADUATE_ID)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.title").value("Senior Developer Position"))
                    .andExpect(jsonPath("$.location").value("Medellin"));
        }

        @Test
        @DisplayName("GRADUATE cannot create opportunities")
        void graduateCannotCreateOpportunities() throws Exception {
            // Given
            setSecurityContext(graduateUser);

            // When & Then
            mockMvc.perform(post("/v1/opportunities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(createOpportunityJson(TARGET_GRADUATE_ID)))
                    .andExpect(status().isForbidden());
        }
    }

    @Nested
    @DisplayName("Read Opportunity Tests")
    class ReadOpportunityTests {

        @Test
        @DisplayName("ADMIN can access any opportunity by ID")
        void adminCanAccessAnyOpportunityById() throws Exception {
            // Given
            setSecurityContext(adminUser);
            OpportunityEntity opportunity = createTestOpportunity(OTHER_EMPLOYER_USER_ID, TARGET_GRADUATE_ID);

            // When & Then
            mockMvc.perform(get("/v1/opportunities/{id}", opportunity.getId())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(opportunity.getId()))
                    .andExpect(jsonPath("$.title").value("Test Opportunity"));
        }

        @Test
        @DisplayName("EMPLOYER can access own opportunities by ID")  
        void employerCanAccessOwnOpportunitiesById() throws Exception {
            // Given
            setSecurityContext(employerUser);
            OpportunityEntity opportunity = createTestOpportunity(EMPLOYER_USER_ID, TARGET_GRADUATE_ID);

            // When & Then
            mockMvc.perform(get("/v1/opportunities/{id}", opportunity.getId())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(opportunity.getId()))
                    .andExpect(jsonPath("$.title").value("Test Opportunity"));
        }

        @Test
        @DisplayName("EMPLOYER cannot access other employer's opportunities")
        void employerCannotAccessOtherEmployerOpportunities() throws Exception {
            // Given
            setSecurityContext(employerUser);
            OpportunityEntity opportunity = createTestOpportunity(OTHER_EMPLOYER_USER_ID, TARGET_GRADUATE_ID);

            // When & Then
            mockMvc.perform(get("/v1/opportunities/{id}", opportunity.getId())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound()); // Security pattern: return 404 to prevent information disclosure
        }
    }

    @Nested
    @DisplayName("Update Opportunity Tests")
    class UpdateOpportunityTests {

        @Test
        @DisplayName("ADMIN can update any opportunity")
        void adminCanUpdateAnyOpportunity() throws Exception {
            // Given
            setSecurityContext(adminUser);
            OpportunityEntity opportunity = createTestOpportunity(OTHER_EMPLOYER_USER_ID, TARGET_GRADUATE_ID);

            String updateJson = """
                {
                    "title": "Updated Senior Developer Position",
                    "description": "Updated description",
                    "location": "Bogota"
                }
                """;

            // When & Then
            mockMvc.perform(put("/v1/opportunities/{id}", opportunity.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(updateJson))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.title").value("Updated Senior Developer Position"))
                    .andExpect(jsonPath("$.location").value("Bogota"));
        }

        @Test
        @DisplayName("EMPLOYER can update own opportunities")
        void employerCanUpdateOwnOpportunities() throws Exception {
            // Given
            setSecurityContext(employerUser);
            OpportunityEntity opportunity = createTestOpportunity(EMPLOYER_USER_ID, TARGET_GRADUATE_ID);

            String updateJson = """
                {
                    "title": "Updated Position",
                    "description": "Updated description"
                }
                """;

            // When & Then
            mockMvc.perform(put("/v1/opportunities/{id}", opportunity.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(updateJson))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.title").value("Updated Position"));
        }

        @Test
        @DisplayName("EMPLOYER cannot update other employer's opportunities")
        void employerCannotUpdateOtherEmployerOpportunities() throws Exception {
            // Given
            setSecurityContext(employerUser);
            OpportunityEntity opportunity = createTestOpportunity(OTHER_EMPLOYER_USER_ID, TARGET_GRADUATE_ID);

            String updateJson = """
                {
                    "title": "Attempted Update"
                }
                """;

            // When & Then
            mockMvc.perform(put("/v1/opportunities/{id}", opportunity.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(updateJson))
                    .andExpect(status().isNotFound()); // Security pattern: return 404 to prevent information disclosure
        }
    }

    @Nested
    @DisplayName("Delete Opportunity Tests")
    class DeleteOpportunityTests {

        @Test
        @DisplayName("ADMIN can delete any opportunity")
        void adminCanDeleteAnyOpportunity() throws Exception {
            // Given
            setSecurityContext(adminUser);
            OpportunityEntity opportunity = createTestOpportunity(OTHER_EMPLOYER_USER_ID, TARGET_GRADUATE_ID);

            // When & Then
            mockMvc.perform(delete("/v1/opportunities/{id}", opportunity.getId())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk()) // Delete returns 200 OK with deleted opportunity data
                    .andExpect(jsonPath("$.id").value(opportunity.getId()))
                    .andExpect(jsonPath("$.title").value("Test Opportunity"));
        }

        @Test
        @DisplayName("EMPLOYER can delete own opportunities")
        void employerCanDeleteOwnOpportunities() throws Exception {
            // Given
            setSecurityContext(employerUser);
            OpportunityEntity opportunity = createTestOpportunity(EMPLOYER_USER_ID, TARGET_GRADUATE_ID);

            // When & Then
            mockMvc.perform(delete("/v1/opportunities/{id}", opportunity.getId())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk()) // Delete returns 200 OK with deleted opportunity data
                    .andExpect(jsonPath("$.id").value(opportunity.getId()))
                    .andExpect(jsonPath("$.title").value("Test Opportunity"));
        }

        @Test
        @DisplayName("EMPLOYER cannot delete other employer's opportunities")
        void employerCannotDeleteOtherEmployerOpportunities() throws Exception {
            // Given
            setSecurityContext(employerUser);
            OpportunityEntity opportunity = createTestOpportunity(OTHER_EMPLOYER_USER_ID, TARGET_GRADUATE_ID);

            // When & Then
            mockMvc.perform(delete("/v1/opportunities/{id}", opportunity.getId())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound()); // Security pattern: return 404 to prevent information disclosure
        }
    }

    @Nested
    @DisplayName("Authorization Edge Cases")
    class AuthorizationEdgeCasesTests {

        @Test
        @DisplayName("Returns 404 for non-existent opportunity")
        void returnsNotFoundForNonExistentOpportunity() throws Exception {
            // Given
            setSecurityContext(adminUser);
            Long nonExistentId = 99999L;

            // When & Then
            mockMvc.perform(get("/v1/opportunities/{id}", nonExistentId)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("Returns 401 for unauthorized access (no user)")
        void returnsUnauthorizedForNoUser() throws Exception {
            // Given - no security context set intentionally

            // When & Then
            mockMvc.perform(get("/v1/opportunities")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        @DisplayName("Handles unsupported role gracefully")
        void handlesUnsupportedRoleGracefully() throws Exception {
            // Given
            AuthenticatedUser preGraduateUser = AuthenticatedUser.builder()
                    .id(UUID.randomUUID())
                    .role(RoleName.PRE_GRADUATE)
                    .token("mock-jwt-token")
                    .build();
            setSecurityContext(preGraduateUser);

            // When & Then
            mockMvc.perform(get("/v1/opportunities")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isForbidden());
        }
    }

    @Nested
    @DisplayName("Validation Tests")
    class ValidationTests {

        @Test
        @DisplayName("Returns 400 for invalid opportunity data")
        void returnsBadRequestForInvalidData() throws Exception {
            // Given
            setSecurityContext(adminUser);

            String invalidJson = """
                {
                    "title": "",
                    "description": null,
                    "location": "",
                    "salaryMin": -1000,
                    "salaryMax": -500
                }
                """;

            // When & Then
            mockMvc.perform(post("/v1/opportunities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(invalidJson))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Returns 400 for malformed JSON")
        void returnsBadRequestForMalformedJson() throws Exception {
            // Given
            setSecurityContext(adminUser);

            String malformedJson = "{ title: 'Missing quotes', invalid json }";

            // When & Then
            mockMvc.perform(post("/v1/opportunities")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(malformedJson))
                    .andExpect(status().isBadRequest());
        }
    }
}
