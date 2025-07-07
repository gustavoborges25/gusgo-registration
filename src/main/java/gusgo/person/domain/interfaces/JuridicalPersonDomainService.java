package gusgo.person.domain.interfaces;

import gusgo.person.domain.model.JuridicalPerson;
import gusgo.person.rest.exception.BusinessException;

public interface JuridicalPersonDomainService {

    void validateJuridicalPerson(JuridicalPerson juridicalPerson) throws BusinessException;

    void validateMainDocument(JuridicalPerson juridicalPerson) throws BusinessException;

    void validateErpId(JuridicalPerson juridicalPerson) throws BusinessException;
}
