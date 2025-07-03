package gusgo.registration.domain.interfaces;

import gusgo.registration.domain.model.BusinessPerson;
import gusgo.registration.rest.exception.BusinessException;

public interface BusinessPersonDomainService {

    void validateBusinessPerson(BusinessPerson businessPerson) throws BusinessException;

}
