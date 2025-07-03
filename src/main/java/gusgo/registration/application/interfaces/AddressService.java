package gusgo.registration.application.interfaces;

import gusgo.registration.application.dto.AddressDTO;
import gusgo.registration.domain.model.Person;

import java.util.List;

public interface AddressService {

    void update(Person person, List<AddressDTO> addressDTOS);

}
