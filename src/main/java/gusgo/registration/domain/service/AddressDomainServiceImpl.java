package gusgo.registration.domain.service;

import gusgo.registration.application.resources.ValidationConstants;
import gusgo.registration.domain.interfaces.AddressDomainService;
import gusgo.registration.domain.model.Address;
import gusgo.registration.rest.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressDomainServiceImpl implements AddressDomainService {

    @Override
    public void validateAddresses(List<Address> addresses) throws BusinessException {
        if (addresses.isEmpty()) {
            throw new BusinessException(ValidationConstants.ADDRESS_IS_MANDATORY);
        }

        addresses.forEach(address -> {
            validateField(address.getStreet(), ValidationConstants.STREET_IS_MANDATORY);
            validateField(address.getNumber(), ValidationConstants.NUMBER_IS_MANDATORY);
            validateField(address.getNeighborhood(), ValidationConstants.NEIGHBORHOOD_IS_MANDATORY);
            validateField(address.getZipCode(), ValidationConstants.ZIP_CODE_IS_MANDATORY);
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
