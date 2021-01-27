package kr.smartscore.gplace.infrastructure.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ListConverter implements AttributeConverter<List<Map<String, Object>>, String> {

    private  static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Override
    public String convertToDatabaseColumn(List<Map<String, Object>> attribute) {
        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(attribute);
        } catch (final JsonProcessingException e) {
            // logger.error("JSON writing error", e);
        }

        return customerInfoJson;
    }

    @Override
    public List<Map<String, Object>> convertToEntityAttribute(String dbData) {
        List<Map<String, Object>> customerInfo = null;
        try {
            if (dbData !=null) customerInfo = objectMapper.readValue(dbData, List.class);
        } catch (final IOException e) {
            // logger.error("JSON reading error", e);
        }
        return customerInfo;
    }
}
