package gusgo.person.application.interfaces;

import gusgo.person.application.dto.PersonDTO;
import gusgo.person.rest.exception.BusinessException;

import java.util.List;
import java.util.UUID;

public interface PersonService {

    PersonDTO create(PersonDTO personDto) throws BusinessException;

    List<PersonDTO> findAll();

    PersonDTO update(UUID id, PersonDTO personDTO);

    void merge(PersonDTO personDTO);
}
