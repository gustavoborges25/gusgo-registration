package gusgo.registration.domain.interfaces;

import gusgo.registration.domain.model.Email;
import gusgo.registration.rest.exception.BusinessException;

import java.util.List;

public interface EmailDomainService {

    void validateEmails(List<Email> emails) throws BusinessException;

}
