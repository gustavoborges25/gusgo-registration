package gusgo.person.domain.service;

import gusgo.person.application.resources.ValidationConstants;
import gusgo.person.domain.interfaces.PhoneDomainService;
import gusgo.person.domain.model.Phone;
import gusgo.person.rest.exception.BusinessException;
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
