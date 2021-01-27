package kr.smartscore.gplace.infrastructure.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ObjectConverter implements AttributeConverter<Object, String> {

    private  static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Override
    public String convertToDatabaseColumn(Object attribute) {
        String customerInfoJson = null;
        try {
            customerInfoJson = objectMapper.writeValueAsString(attribute);
        } catch (final JsonProcessingException e) {
            // logger.error("JSON writing error", e);
        }

        return customerInfoJson;
    }

    @Override
    public Object convertToEntityAttribute(String dbData) {
        Object customerInfo = null;
        try {
            if (dbData !=null) customerInfo = objectMapper.readValue(dbData, Object.class);
        } catch (final IOException e) {
            // logger.error("JSON reading error", e);
        }

        return customerInfo;
    }
}
