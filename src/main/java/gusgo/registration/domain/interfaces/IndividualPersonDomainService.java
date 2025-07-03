package gusgo.registration.domain.interfaces;

import gusgo.registration.domain.model.IndividualPerson;
import gusgo.registration.rest.exception.BusinessException;

public interface IndividualPersonDomainService {

    void validateIndividualPerson(IndividualPerson person) throws BusinessException;

}
