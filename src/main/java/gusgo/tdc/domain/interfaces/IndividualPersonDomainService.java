package gusgo.tdc.domain.interfaces;

import gusgo.tdc.domain.model.IndividualPerson;
import gusgo.tdc.rest.exception.BusinessException;

public interface IndividualPersonDomainService {
    void validateIndividualPerson(IndividualPerson person) throws BusinessException;
}
