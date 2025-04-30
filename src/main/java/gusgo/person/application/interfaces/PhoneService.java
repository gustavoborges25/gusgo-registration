package gusgo.person.application.interfaces;

import gusgo.person.application.dto.PhoneDTO;
import gusgo.person.domain.model.Person;
import gusgo.person.domain.model.Phone;

import java.util.List;

public interface PhoneService {

    List<Phone> update(Person person, List<PhoneDTO> addressDTOS);

}
