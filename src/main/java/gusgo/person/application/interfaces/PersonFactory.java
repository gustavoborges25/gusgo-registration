package gusgo.person.application.interfaces;

import gusgo.person.application.dto.PersonDTO;
import gusgo.person.domain.model.Person;
import gusgo.person.rest.exception.BusinessException;

import java.util.UUID;

public interface PersonFactory {

    Person save(Person person) throws BusinessException;

    Person toEntity(PersonDTO personDTO);

    void setMainDocument(Person person, String mainDocument);

    void setSecondaryDocument(Person person, String mainDocument);

    void setIsBranch(Person person, boolean isBranch);

    Person getEntityById(UUID id);

    Person getEntityByMainDocument(String mainDocument);

    void validateAll(Person person) throws BusinessException;

    void validateToUpdate(Person person) throws BusinessException;

    PersonDTO getDTO(Person person);
}
