package gusgo.person.domain.interfaces;

import gusgo.person.domain.model.Email;
import gusgo.person.rest.exception.BusinessException;

import java.util.List;

public interface EmailDomainService {

    void validateEmails(List<Email> emails) throws BusinessException;

}
