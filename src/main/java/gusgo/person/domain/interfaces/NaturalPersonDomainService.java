package gusgo.person.domain.interfaces;

import gusgo.person.domain.model.NaturalPerson;
import gusgo.person.rest.exception.BusinessException;

public interface NaturalPersonDomainService {

    void validateNaturalPerson(NaturalPerson person) throws BusinessException;

    void validateMainDocument(NaturalPerson person) throws BusinessException;

    void validateErpId(NaturalPerson person) throws BusinessException;

}
