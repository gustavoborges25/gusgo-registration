package gusgo.person.domain.service;

import gusgo.person.application.resources.ValidationConstants;
import gusgo.person.domain.interfaces.PersonDomainService;
import gusgo.person.domain.model.Person;
import gusgo.person.rest.exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class PersonDomainServiceImpl implements PersonDomainService {

    @Override
    public void validatePerson(Person person) throws BusinessException {

        if (person.getName() == null || person.getName().isBlank()) {
            throw new BusinessException(ValidationConstants.NAME_IS_MANDATORY);
        }

        if (person.getErpCode() == null || person.getErpCode().isBlank()) {
            throw new BusinessException(ValidationConstants.ERP_CODE_IS_MANDATORY);
        }

    }
}
