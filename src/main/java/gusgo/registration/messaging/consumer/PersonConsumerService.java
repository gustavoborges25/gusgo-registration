package gusgo.registration.messaging.consumer;

import gusgo.registration.application.dto.PersonDTO;
import gusgo.registration.application.interfaces.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonConsumerService {

    private final PersonService personService;

    @KafkaListener(topics = "person_topic", groupId = "personDTO-group")
    public void consume(PersonDTO personDTO) {
        personService.merge(personDTO);
    }
}
