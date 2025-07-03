package gusgo.registration.application.interfaces;

import gusgo.registration.application.dto.EmailDTO;
import gusgo.registration.domain.model.Person;

import java.util.List;

public interface EmailService {

    void update(Person person, List<EmailDTO> emailDTOS);

}
