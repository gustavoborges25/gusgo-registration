package gusgo.registration.domain.service;

import gusgo.registration.application.resources.ValidationConstants;
import gusgo.registration.domain.interfaces.EmailDomainService;
import gusgo.registration.domain.model.Email;
import gusgo.registration.rest.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailDomainServiceImpl implements EmailDomainService {
    @Override
    public void validateEmails(List<Email> emails) throws BusinessException {

        if (emails.isEmpty()) {
            throw new BusinessException(ValidationConstants.EMAIL_IS_MANDATORY);
        }

        emails.forEach(email -> {
            if (email.getEmail() == null || email.getEmail().isBlank()) {
                throw new BusinessException(ValidationConstants.EMAIL_IS_MANDATORY);
            }
        });

    }
}
