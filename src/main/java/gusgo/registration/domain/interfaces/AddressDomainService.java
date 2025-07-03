package gusgo.registration.domain.interfaces;

import gusgo.registration.domain.model.Address;
import gusgo.registration.rest.exception.BusinessException;

import java.util.List;

public interface AddressDomainService {

    void validateAddresses(List<Address> addresses) throws BusinessException;

}
