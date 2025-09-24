package co.edu.udea.nexum.opportunity;

import co.edu.udea.nexum.opportunity.security.infrastructure.output.feign.client.AuthFeign;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@ActiveProfiles("test")
class OpportunityApplicationTests {

  @MockitoBean
  private AuthFeign authFeign; // Mock external auth service to prevent connection issues

  @Test
  void contextLoads() {
    // This test verifies that the Spring context can be loaded successfully
  }

}
