package gusgo.person.domain.service;

import gusgo.person.application.resources.ValidationConstants;
import gusgo.person.domain.interfaces.AddressDomainService;
import gusgo.person.domain.model.Address;
import gusgo.person.rest.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressDomainServiceImpl implements AddressDomainService {

    @Override
    public void validateAddresses(List<Address> addresses) throws BusinessException {
        if (addresses == null || addresses.isEmpty()) {
            return;
        }
        addresses.forEach(address -> {
            validateField(address.getStreet(), ValidationConstants.STREET_IS_MANDATORY);
            validateField(address.getState(), ValidationConstants.STATE_IS_MANDATORY);
            validateField(address.getCity(), ValidationConstants.CITY_IS_MANDATORY);
        });
    }

    private void validateField(String fieldValue, String errorMessage) {
        if (fieldValue == null || fieldValue.isBlank()) {
            throw new BusinessException(errorMessage);
        }
    }
}
