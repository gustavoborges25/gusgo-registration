package gusgo.person.messaging.consumer;

import gusgo.person.application.dto.PersonDTO;
import gusgo.person.application.interfaces.PersonService;
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
