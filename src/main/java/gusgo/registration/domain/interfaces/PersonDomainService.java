package gusgo.registration.domain.interfaces;

import gusgo.registration.domain.model.Person;
import gusgo.registration.rest.exception.BusinessException;

public interface PersonDomainService {

    void validatePerson(Person person) throws BusinessException;

}
