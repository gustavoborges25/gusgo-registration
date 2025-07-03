package gusgo.registration.messaging.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import gusgo.registration.application.dto.PersonDTO;
import gusgo.registration.rest.exception.BusinessException;
import org.apache.kafka.common.serialization.Deserializer;

public class PersonDTODeserializer implements Deserializer<PersonDTO> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public PersonDTO deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, PersonDTO.class);
        } catch (Exception e) {
            throw new BusinessException("Failed to deserialize Person", e);
        }
    }

}
