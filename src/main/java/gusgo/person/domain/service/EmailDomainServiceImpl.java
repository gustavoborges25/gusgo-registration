package gusgo.person.domain.service;

import gusgo.person.application.resources.ValidationConstants;
import gusgo.person.domain.interfaces.EmailDomainService;
import gusgo.person.domain.model.Email;
import gusgo.person.rest.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailDomainServiceImpl implements EmailDomainService {
    @Override
    public void validateEmails(List<Email> emails) throws BusinessException {
        if  (emails == null || emails.isEmpty()) {
            return;
        }
        emails.forEach(email -> {
            if (email.getEmail() == null || email.getEmail().isBlank()) {
                throw new BusinessException(ValidationConstants.EMAIL_IS_MANDATORY);
            }
        });
    }
}
