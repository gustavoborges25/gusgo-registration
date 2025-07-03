package gusgo.registration.domain.interfaces;

import gusgo.registration.domain.model.Phone;
import gusgo.registration.rest.exception.BusinessException;

import java.util.List;

public interface PhoneDomainService {

    void validatePhones(List<Phone> phones) throws BusinessException;

}
