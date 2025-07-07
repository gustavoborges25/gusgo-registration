package gusgo.person.domain.interfaces;

import gusgo.person.domain.model.Person;
import gusgo.person.rest.exception.BusinessException;

public interface PersonDomainService {

    void validatePerson(Person person) throws BusinessException;

}
