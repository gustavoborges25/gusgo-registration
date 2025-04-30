package gusgo.person.domain.interfaces;

import gusgo.person.domain.model.Phone;
import gusgo.person.rest.exception.BusinessException;

import java.util.List;

public interface PhoneDomainService {

    void validatePhones(List<Phone> phones) throws BusinessException;

}
