package gusgo.person.messaging.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import gusgo.person.application.dto.PersonDTO;
import gusgo.person.application.interfaces.KafkaLogRepository;
import gusgo.person.application.interfaces.PersonService;
import gusgo.person.domain.model.KafkaLog;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonConsumerService {

    private final ObjectMapper objectMapper;
    private final PersonService personService;
    private final KafkaLogRepository kafkaLogRepository;

    @KafkaListener(topics = "${spring.kafka.consumer.topic.person}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        try {
            PersonDTO personDTO = objectMapper.readValue(message, PersonDTO.class);
            personService.merge(personDTO);
        } catch (Exception e) {
            kafkaLogRepository.save(new KafkaLog("PersonConsumerService", message, e.getMessage()));
        }
    }
}
