package gusgo.person.domain.interfaces;

import gusgo.person.domain.model.BusinessPerson;
import gusgo.person.rest.exception.BusinessException;

public interface BusinessPersonDomainService {

    void validateBusinessPerson(BusinessPerson businessPerson) throws BusinessException;

}
