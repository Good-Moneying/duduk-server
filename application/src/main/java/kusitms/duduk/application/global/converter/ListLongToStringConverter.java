package kusitms.duduk.application.global.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import java.util.List;
import kusitms.duduk.common.exception.custom.ConvertException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

public class ListLongToStringConverter implements AttributeConverter<List<Long>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);

    @Override
    public String convertToDatabaseColumn(List<Long> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new ConvertException("attribute를 String으로 변환하는 과정에서 오류가 발생하였습니다.");
        }
    }

    @Override
    public List<Long> convertToEntityAttribute(String dbData) {
        TypeReference<List<Long>> typeReference = new TypeReference<>() {
        };
        try {
            return objectMapper.readValue(dbData, typeReference);
        } catch (Exception e) {
            throw new ConvertException("dbData를 List<Long>으로 변환하는 과정에서 오류가 발생하였습니다.");
        }
    }
}
