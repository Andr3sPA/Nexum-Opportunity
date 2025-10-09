package co.edu.udea.nexum.opportunity.opportunity.infrastructure.output.feign.dto.response;

import co.edu.udea.nexum.opportunity.security.domain.utils.enums.RoleName;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicUserResponse {
    private UUID id;
    private String name;
    private String middleName;
    private String lastname;
    private String secondLastname;
    private String gender;
    private List<BasicProgramResponse> programs;
    private String email;
    private String academicEmail;
    private String mobile;
    private String country;
    private String city;
    private RoleName role;
}