package gusgo.person.application.interfaces;

import gusgo.person.application.dto.AddressDTO;
import gusgo.person.domain.model.Person;

import java.util.List;

public interface AddressService {

    void update(Person person, List<AddressDTO> addressDTOS);

}
