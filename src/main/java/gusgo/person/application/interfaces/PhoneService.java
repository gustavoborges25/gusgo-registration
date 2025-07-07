package gusgo.person.application.interfaces;

import gusgo.person.application.dto.PhoneDTO;
import gusgo.person.domain.model.Person;

import java.util.List;

public interface PhoneService {

    void update(Person person, List<PhoneDTO> addressDTOS);

}
