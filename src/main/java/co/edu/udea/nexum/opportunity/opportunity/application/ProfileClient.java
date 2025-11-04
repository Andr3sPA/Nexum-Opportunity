package co.edu.udea.nexum.opportunity.opportunity.application;

import co.edu.udea.nexum.opportunity.opportunity.application.dto.ProfileDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProfileClient {

    private static final Logger log = LoggerFactory.getLogger(ProfileClient.class);

    private final String baseUrl;
    private final RestTemplate rest;
    private final ObjectMapper mapper = new ObjectMapper();

    public ProfileClient(@Value("${nexum.profile.base_url}") String baseUrl) {
        this.baseUrl = baseUrl;
        this.rest = new RestTemplate();
    }

    public List<ProfileDto> searchProfiles(String q, int page, int size) {
        UriComponentsBuilder b = UriComponentsBuilder.fromHttpUrl(baseUrl)
            .pathSegment("users", "filter")
            .queryParam("page", page)
            .queryParam("pageSize", size);

        if (q != null && !q.isBlank()) {
            b.queryParam("name", q);
            b.queryParam("identity_document", q);
        }

        String url = b.toUriString();
        try {
            HttpHeaders headers = new HttpHeaders();
            // copiar Authorization de la request entrante si existe
            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attrs != null) {
                HttpServletRequest currentRequest = attrs.getRequest();
                String auth = currentRequest.getHeader("Authorization");
                if (auth != null && !auth.isBlank()) {
                    headers.set("Authorization", auth);
                }
            }

            HttpEntity<Void> entity = new HttpEntity<>(headers);
            log.debug("[ProfileClient] Calling URL {} with headers: {}", url, headers);
            ResponseEntity<String> respEntity = rest.exchange(url, HttpMethod.GET, entity, String.class);
            String resp = respEntity.getBody();
            log.debug("[ProfileClient] Response status: {}, body length: {}", respEntity.getStatusCodeValue(), resp == null ? 0 : resp.length());

            if (resp == null) return List.of();
            JsonNode root = mapper.readTree(resp);
            JsonNode content = root.has("content") ? root.get("content") : root;
            List<ProfileDto> out = new ArrayList<>();
            if (content != null && content.isArray()) {
                for (JsonNode item : content) {
                    ProfileDto p = mapper.treeToValue(item, ProfileDto.class);
                    out.add(p);
                }
            }
            return out;
        } catch (Exception ex) {
            log.warn("[ProfileClient] Error fetching profiles from {} : {}", url, ex.toString());
            log.debug("[ProfileClient] Stacktrace:", ex);
            return List.of();
        }
    }
}
