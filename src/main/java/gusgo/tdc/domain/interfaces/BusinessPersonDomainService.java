package gusgo.tdc.domain.interfaces;

import gusgo.tdc.domain.model.BusinessPerson;
import gusgo.tdc.rest.exception.BusinessException;

public interface BusinessPersonDomainService {
    void validateBusinessPerson(BusinessPerson businessPerson) throws BusinessException;
}
