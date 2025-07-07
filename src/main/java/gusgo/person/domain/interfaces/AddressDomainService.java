package gusgo.person.domain.interfaces;

import gusgo.person.domain.model.Address;
import gusgo.person.rest.exception.BusinessException;

import java.util.List;

public interface AddressDomainService {

    void validateAddresses(List<Address> addresses) throws BusinessException;

}
