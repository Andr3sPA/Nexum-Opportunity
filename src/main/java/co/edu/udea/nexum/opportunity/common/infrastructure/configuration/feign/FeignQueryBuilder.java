package co.edu.udea.nexum.opportunity.common.infrastructure.configuration.feign;

import co.edu.udea.nexum.opportunity.common.domain.utils.annotations.Generated;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.QueryMapEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Generated
public class FeignQueryBuilder implements QueryMapEncoder {
    @Override
    public Map<String, Object> encode(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(o, new TypeReference<>() {
        });
    }
}