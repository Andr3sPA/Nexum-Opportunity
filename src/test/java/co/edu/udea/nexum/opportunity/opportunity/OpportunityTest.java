package co.edu.udea.nexum.opportunity.opportunity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class OpportunityTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldCreateOpportunity() throws Exception {
    // Given: request body with opportunity info
    String requestJson = """
        {
            "title": "Software Developer Position",
            "description": "Exciting opportunity for a Java developer",
            "location": "Medellín, Colombia",
            "status": "Draft",
            "graduateId": "123e4567-e89b-12d3-a456-426614174000",
            "salaryRange": {
                "min": 3000000,
                "max": 5000000,
                "currency": "COP"
            }
        }
        """;

    // When: calling the endpoint
    mockMvc.perform(post("/api/v1/opportunities")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestJson)
        .header("Authorization", "Bearer test-token"))
        // Then: verify status and structure
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.title").value("Software Developer Position"))
        .andExpect(jsonPath("$.description").value("Exciting opportunity for a Java developer"))
        .andExpect(jsonPath("$.location").value("Medellín, Colombia"))
        .andExpect(jsonPath("$.graduateId").value("123e4567-e89b-12d3-a456-426614174000"))
        .andExpect(jsonPath("$.status").value("Draft"))
        .andExpect(jsonPath("$.salaryRange.min").value(3000000))
        .andExpect(jsonPath("$.salaryRange.max").value(5000000))
        .andExpect(jsonPath("$.salaryRange.currency").value("COP"))
        .andExpect(jsonPath("$.creationDate").exists())
        .andExpect(jsonPath("$.lastUpdate").exists());
  }

  @Test
  void shouldGetOpportunitiesByGraduateId() throws Exception {
    // Given: a graduate ID
    String graduateId = "123e4567-e89b-12d3-a456-426614174000";

    // When: calling the endpoint to find opportunities for that graduate
    mockMvc.perform(get("/api/v1/opportunities/graduate/" + graduateId)
        .header("Authorization", "Bearer test-token"))
        // Then: verify status and structure
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$[*].graduateId").value(graduateId));
  }

}
