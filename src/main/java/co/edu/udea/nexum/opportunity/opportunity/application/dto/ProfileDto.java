package co.edu.udea.nexum.opportunity.opportunity.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileDto {
    private UUID id;
    private String name;
    private String middleName;
    private String lastname;
    private String secondLastname;
    private String email;
    private String mobile;

    public ProfileDto() {}

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getSecondLastname() { return secondLastname; }
    public void setSecondLastname(String secondLastname) { this.secondLastname = secondLastname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        if (name != null) sb.append(name).append(' ');
        if (middleName != null) sb.append(middleName).append(' ');
        if (lastname != null) sb.append(lastname).append(' ');
        if (secondLastname != null) sb.append(secondLastname);
        return sb.toString().trim();
    }
}

