package gusgo.registration.domain.service;

import gusgo.registration.application.resources.ValidationConstants;
import gusgo.registration.domain.interfaces.PhoneDomainService;
import gusgo.registration.domain.model.Phone;
import gusgo.registration.rest.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneDomainServiceImpl implements PhoneDomainService {
    @Override
    public void validatePhones(List<Phone> phones) throws BusinessException {

        if (phones.isEmpty()) {
            throw new BusinessException(ValidationConstants.PHONE_IS_MANDATORY);
        }

        phones.forEach(phone -> {
            if (phone.getPhone() == null || phone.getPhone().isBlank()) {
                throw new BusinessException(ValidationConstants.PHONE_IS_MANDATORY);
            }
        });

    }
}
