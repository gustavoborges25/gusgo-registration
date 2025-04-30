package gusgo.person.application.interfaces;

import gusgo.person.application.dto.PersonDTO;
import gusgo.person.domain.model.Person;
import gusgo.person.rest.exception.BusinessException;

import java.util.UUID;

public interface PersonFactory {

    Person create(PersonDTO personDto) throws BusinessException;

    Person uodate(UUID id, PersonDTO personDto) throws BusinessException;
}
