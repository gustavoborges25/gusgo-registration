package gusgo.registration.application.interfaces;

import gusgo.registration.application.dto.PersonDTO;
import gusgo.registration.domain.model.Person;
import gusgo.registration.rest.exception.BusinessException;

import java.util.UUID;

public interface PersonFactory {

    Person create(PersonDTO personDto) throws BusinessException;

    Person update(UUID id, PersonDTO personDto) throws BusinessException;
}
