package gusgo.person.domain.interfaces;

import gusgo.person.domain.model.IndividualPerson;
import gusgo.person.rest.exception.BusinessException;

public interface IndividualPersonDomainService {

    void validateIndividualPerson(IndividualPerson person) throws BusinessException;

}
