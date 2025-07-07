package gusgo.person.application.interfaces;

import gusgo.person.application.dto.EmailDTO;
import gusgo.person.domain.model.Person;

import java.util.List;

public interface EmailService {

    void update(Person person, List<EmailDTO> emailDTOS);

}
