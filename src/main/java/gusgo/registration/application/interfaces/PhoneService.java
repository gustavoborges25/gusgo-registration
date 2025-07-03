package gusgo.registration.application.interfaces;

import gusgo.registration.application.dto.PhoneDTO;
import gusgo.registration.domain.model.Person;

import java.util.List;

public interface PhoneService {

    void update(Person person, List<PhoneDTO> addressDTOS);

}
